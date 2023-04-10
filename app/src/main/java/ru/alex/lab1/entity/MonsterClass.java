package ru.alex.lab1.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "monster_class")
public class MonsterClass {

    @PrimaryKey
    @NonNull
    private Long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "img_name")
    private String imgName;

    @ColumnInfo(name = "img_direction")
    private String imgDirection;

    public MonsterClass(@NonNull Long id, String name, String imgName, String imgDirection) {
        this.id = id;
        this.name = name;
        this.imgName = imgName;
        this.imgDirection = imgDirection;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgName() {
        return imgName;
    }

    public String getImgDirection() {
        return imgDirection;
    }
}