
package ru.alex.lab1.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.alex.lab1.R;
import ru.alex.lab1.adapter.CardPreviewAdapter;
import ru.alex.lab1.callBack.monster.MonsterCallBack;
import ru.alex.lab1.dao.MonsterClassDao;
import ru.alex.lab1.db.AppDataBase;
import ru.alex.lab1.dto.MonsterClassDto;
import ru.alex.lab1.entity.MonsterClass;
import ru.alex.lab1.pojo.RecyclerCardPreview;
import ru.alex.lab1.pojo.Title;
import ru.alex.lab1.recycler.RecyclerViewElement;
import ru.alex.lab1.service.MonsterService;
import ru.alex.lab1.utils.converter.Impl.MonsterClassConverterDbImpl;
import ru.alex.lab1.utils.converter.MonsterConverterDbWithList;

public class RecyclerPreviewCardFragment extends Fragment implements MonsterCallBack<List<RecyclerCardPreview>> {

    private MonsterService monsterService;

    private final List<RecyclerViewElement> recyclerViewElementList;

    private final CardPreviewAdapter monsterAdapter;

    private final MonsterConverterDbWithList<RecyclerCardPreview, MonsterClass, MonsterClassDto> monsterConverterDb;


    public RecyclerPreviewCardFragment() {

        this.recyclerViewElementList = new ArrayList<>();
        this.monsterAdapter = new CardPreviewAdapter(recyclerViewElementList);
        this.monsterConverterDb = new MonsterClassConverterDbImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_preview_card, container, false);
        this.monsterService = new MonsterService(getActivity());
        prepareAndSetRecyclerView(view);
        return view;
    }



    private void prepareAndSetRecyclerView(View view) {
        recyclerViewElementList.add(new Title());

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setAdapter(monsterAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (monsterAdapter.getItemViewType(position) == RecyclerViewElement.TITLE) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        setInitialData();
    }

    private void setInitialData() {
        monsterService.getMonsterClassList(this);
    }

    @Override
    public  void onSuccess(List<RecyclerCardPreview> response) {
        monsterAdapter.updateCardPreviewRecycler(response);

        AsyncTask.execute(() -> {
            AppDataBase db = AppDataBase.getInstance(getContext());
            MonsterClassDao monsterClassDao = db.getMonsterClassDao();
            monsterClassDao.nukeTable();
            List<MonsterClass> monsterClassList = monsterConverterDb.toEntityList(response);
            monsterClassDao.insertAll(monsterClassList);
        });
    }

    @Override
    public void onFail(IOException error) {

        AsyncTask.execute(() -> {
            AppDataBase db = AppDataBase.getInstance(getContext());
            MonsterClassDao monsterClassDao = db.getMonsterClassDao();
            List<MonsterClassDto> monsterClassDtoList = monsterConverterDb.toDtoList(monsterClassDao.getAll());

            getActivity().runOnUiThread(() -> monsterAdapter.updateCardPreviewRecycler(monsterClassDtoList.stream()
                    .map(MonsterClassDto::toPojo).collect(Collectors.toList())));

        });
    }
}