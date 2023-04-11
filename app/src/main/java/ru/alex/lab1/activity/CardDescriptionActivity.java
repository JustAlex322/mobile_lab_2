package ru.alex.lab1.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;

import ru.alex.lab1.R;
import ru.alex.lab1.callBack.monster.MonsterCallBack;
import ru.alex.lab1.dto.MonsterWithDescriptionDto;
import ru.alex.lab1.service.MonsterService;
import ru.alex.lab1.urls.BaseUrl;
import ru.alex.lab1.utils.converter.Impl.GsonMonsterConverterImpl;
import ru.alex.lab1.utils.converter.MonsterConverter;

public class CardDescriptionActivity extends AppCompatActivity implements MonsterCallBack<MonsterWithDescriptionDto> {

    private final MonsterService monsterService = new MonsterService(this);
    private final MonsterConverter monsterConverter = new GsonMonsterConverterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_description);

        findViewById(R.id.card_description_scroll).setVerticalScrollBarEnabled(false);

        long cardId = getIntent().getLongExtra("id", 1);
        monsterService.getMonsterById(cardId, this);
    }

    @Override
    public void onSuccess(MonsterWithDescriptionDto response) {

        setMonsterDescriptionOnUi(response);
    }

    private void setMonsterDescriptionOnUi(MonsterWithDescriptionDto monsterDto) {
        ImageView imageView = findViewById(R.id.card_description_img);
        Glide.with(this)
                .load(BaseUrl.BASE_FOR_IMG + monsterDto.getSource() + monsterDto.getImgName())
                .thumbnail(Glide.with(this).load(R.drawable.preloader))
                .fitCenter()
                .into(imageView);

        TextView textView = findViewById(R.id.card_description_name);
        textView.setText(monsterDto.getName());
        textView.setTypeface(null, Typeface.BOLD);

        textView = findViewById(R.id.card_description_quote);
        textView.setText(monsterDto.getQuote());
        textView.setTypeface(null, Typeface.ITALIC);

        textView = findViewById(R.id.card_description_quote_author);
        textView.setText(monsterDto.getQuoteAuthor());

        textView = findViewById(R.id.card_description_description);
        textView.setText(monsterDto.getDescription());
    }

    @Override
    public void onFail(IOException error) {

    }
}
