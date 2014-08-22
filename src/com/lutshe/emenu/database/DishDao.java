package com.lutshe.emenu.database;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class DishDao {

    static final String DISH_TABLE = "dish";
    static final String DISH_ID = "id";
    static final String DISH_NAME = "name";

    static final String CLAZZ_TABLE = "clazz";
    static final String CLAZZ_ID = "id";
    static final String CLAZZ_NAME = "name";
    static final String ID_DISH = "id_dish";

    static final String MOD_TABLE = "mod";
    static final String MOD_ID = "id";
    static final String MOD_NAME = "name";
    static final String MOD_PRICE = "price";


    static final String UNIT_TABLE = "unit";
    static final String UNIT_ID = "id";
    static final String UNIT_NAME = "name";
    static final String UNIT_PRICE = "price";

    @Bean
    DatabaseHelper db;

}
