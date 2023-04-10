package ru.alex.lab1.dto;

import ru.alex.lab1.pojo.RecyclerCardPreview;

public abstract class BaseCardPreviewDto {

    public BaseCardPreviewDto(Long id, String name, String imgName, String source) {
        this.id = id;
        this.name = name;
        this.imgName = imgName;
        this.source = source;
    }

    public Long id;

    public String name;

    public String imgName;

    public String source;

    public abstract RecyclerCardPreview toPojo();
}
