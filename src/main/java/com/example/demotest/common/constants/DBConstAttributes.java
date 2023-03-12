package com.example.demotest.common.constants;

public final class DBConstAttributes {
    private DBConstAttributes() {
    }

    private static final String DB_TYPE = "mysql";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "demoTest";
    private static final String PARAMS = "?useSSL=false";
    public static final String URL = "jdbc:" + DB_TYPE + "://" + HOST + ":" + PORT + "/" + DB_NAME + PARAMS;
    public static final String USER = "root";
    public static final String PASS = "lol2281337";
}
