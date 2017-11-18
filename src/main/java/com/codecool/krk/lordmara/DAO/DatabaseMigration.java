package com.codecool.krk.lordmara.DAO;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {

    public static void migrateDatbase() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:sqlite:src/main/resources/db/database.db", null, null);
        flyway.migrate();
    }

}
