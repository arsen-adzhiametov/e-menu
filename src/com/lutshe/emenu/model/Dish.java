package com.lutshe.emenu.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@Element(name = "dish")
public class Dish {

    @Attribute
    private long id;

    @Element
    String name;

    @ElementList
    List<Clazz> classes;

    @ElementList
    List<Mod> mods;

    @ElementList
    List<Unit> units;


    private static class Clazz{

        @Attribute
        String name;
    }

    private static class Mod{

        @Attribute
        String name;

        @Attribute
        String price;
    }

    private static class Unit{

        @Attribute
        String name;

        @Attribute
        String price;
    }

}
