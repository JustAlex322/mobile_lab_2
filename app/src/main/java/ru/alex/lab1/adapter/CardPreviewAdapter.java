package ru.alex.lab1.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.alex.lab1.R;
import ru.alex.lab1.pojo.RecyclerCardPreview;
import ru.alex.lab1.recycler.RecyclerViewElement;

public class CardPreviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<RecyclerViewElement> elements;

    public CardPreviewAdapter(List<RecyclerViewElement> elements) {
        this.elements = elements;
    }

    @Override
    public int getItemViewType(int position) {
        return elements.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == RecyclerViewElement.TITLE) {
            return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.title, parent, false));
        }

        if (viewType == RecyclerViewElement.MONSTER_LIST_TITLE) {
            return new MonsterTitleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.monster_list_title, parent, false));
        }

        return new MonsterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        elements.get(position).onBindHandler(holder);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public static class MonsterViewHolder extends RecyclerView.ViewHolder {

        public ImageView getMonsterImage() {
            return monsterImage;
        }

        public TextView getMonsterTextView() {
            return monsterTextView;
        }

        private final ImageView monsterImage;
        private final TextView monsterTextView;

        public MonsterViewHolder(View itemView) {
            super(itemView);
            monsterImage = itemView.findViewById(R.id.recycler_view_card_img);
            monsterTextView = itemView.findViewById(R.id.recycler_view_card_title);
        }

    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class MonsterTitleViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public TextView getTextView() {
            return textView;
        }

        public MonsterTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.monster_list_title_text);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateCardPreviewRecycler(List<RecyclerCardPreview> monsterClassList) {
        elements.addAll(monsterClassList);

        notifyDataSetChanged();
    }

}
