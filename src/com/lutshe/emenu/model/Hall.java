package com.lutshe.emenu.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@Element(name = "hall")
public class Hall {

    @Attribute
    Integer id;

    @ElementList
    List<Table> tables;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

}
