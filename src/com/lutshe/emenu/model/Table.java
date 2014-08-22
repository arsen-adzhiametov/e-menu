package com.lutshe.emenu.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@Element(name = "table")
public class Table {

    @Attribute
    Integer id;

    @Attribute
    Integer width;

    @Attribute
    Integer height;

    @Attribute
    Integer positionX;

    @Attribute
    Integer positionY;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
