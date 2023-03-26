package ru.alex.lab1.db;

import java.util.HashMap;
import java.util.Map;

import ru.alex.lab1.R;
import ru.alex.lab1.dto.MonsterDto;

public class DataBase {
    private final Map<Integer, MonsterDto> monsterDb;
    private static DataBase instance;
    private DataBase(){
        monsterDb = new HashMap<>();
        setInitialData();
    }
    public static DataBase getInstance() {

        if (instance == null) {
            instance = new DataBase();
        }

        return instance;
    }

    public MonsterDto getMonsterById(int id) {
        return monsterDb.get(id);
    }

    private void setInitialData() {
        MonsterDto monsterDto = new MonsterDto(R.drawable.vampire, "Вампиры",
                "Вампир может не есть и три года, но если уж он вышел на охоту… \nОн не остановится, пока не упьется кровью! ",
                "Гвинт",
                "Вампиры - cобирательное название группы существ, попавших на Континент во " +
                        "время глобального катаклизма под названием Сопряжение Сфер. " +
                        "Существует несколько видов вампиров, различных по повадкам и привычкам, а также по способностям и слабостям");
        monsterDb.put(1, monsterDto);

        monsterDto = new MonsterDto(R.drawable.ghost, "Духи и призраки",
                "Призраки появляются, когда смерть не избавляет умершего от страданий. Тот, кто привлечет к себе внимание морока, немедля перестанет мечтать о смерти! ",
                "Гвинт: Маг-Отступник", "Призрак (ориг. Upiór) — чудовище, упоминаемое в литературной саге и фигурирующее в играх Ведьмак, Ведьмак 2: Убийцы Королей, Ведьмак 3: Дикая Охота и Ведьмак: Охотник на чудовищ, неупокоенная душа, вернувшаяся из-за завесы смерти");
        monsterDb.put(2, monsterDto);

        monsterDto = new MonsterDto(R.drawable.draconid, "Дракониды",
                "Здесь похоронен благородный рыцарь Родерик, который пал в схватке с василиском. Точнее, то, что от него осталось.",
                "надгробная надпись на кладбище в Вызиме.", "Дракониды, также известные как Гады, или Ящеры (ориг. Drakonid) — класс чудовищ в литературной саге, а также в играх Ведьмак, Ведьмак 2: Убийцы Королей, Ведьмак 3: Дикая Охота, Кровная вражда: Ведьмак. Истории и Гвинт: Ведьмак. Карточная игра. ");
        monsterDb.put(3, monsterDto);

        monsterDto = new MonsterDto(R.drawable.relict, "Реликты",
                "Бес выглядит почти как олень. Огромный бешеный олень!",
                "Описание Беса в «Гвинте", "Включает в себя разнообразных тварей без схожих черт: крупных и размером с человека, разумных и нет, агрессивных и мирных, обладающих магическими способностями и лишенных связи с магией. ");
        monsterDb.put(4, monsterDto);

        monsterDto = new MonsterDto(R.drawable.ogr, "Огры",
                "Уху-ху, аха-ха! Злая зимушка-зима! Снегом сыплет, уши щиплет, гонит троллей на поля!",
                "народная песня Северного Каэдвена", "Огры, Огроиды, или Огровые (ориг. Ogroid) — класс чудовищ в литературной саге, а также в играх Ведьмак 2: Убийцы Королей, Ведьмак 3: Дикая Охота, Кровная вражда: Ведьмак. Истории и Гвинт: Ведьмак. Карточная игра.\n" +
                "\n" +
                "Объединяет гуманоидных разумных существ разных размеров, обычно агрессивно настроенных по отношению к людям и иногда способных разговаривать. ");
        monsterDb.put(5, monsterDto);

        monsterDto = new MonsterDto(R.drawable.gibrid, "Гибриды",
                "На самом деле чудовища не собирают в логове сокровищ. Ну, за исключением гарпий. Эти, да. Они блестяшки любят",
                "Лето, ведьмак Школы Змеи", "Гибриды (ориг. Hybrid) — класс чудовищ в литературной саге, а также в играх Ведьмак, Ведьмак 2: Убийцы Королей, Ведьмак 3: Дикая Охота, Кровная вражда: Ведьмак. Истории и Гвинт: Ведьмак. Карточная игра.\n" +
                "\n" +
                "Как можно понять по названию, включает существ, совмещающих в себе признаки нескольких видов, в том числе иногда и людей. Встречаются и разумные представители класса, способные разговаривать и не всегда агрессивные по отношению к человеку. ");
        monsterDb.put(6, monsterDto);

//        monsterDto = new MonsterDto(R.drawable.coursed, "Проклятые",
//                "Не так страшен волк, как его малюют. Зато волколак куда страшней!",
//                "Эльза Вильге, лучница",
//                "Как можно понять по названию, в основном, включает в себя существ, подвергшихся действию проклятия. ");
//        monsterDb.put(7, monsterDto);
    }
}
