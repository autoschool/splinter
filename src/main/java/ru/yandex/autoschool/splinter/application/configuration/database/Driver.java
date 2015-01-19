package ru.yandex.autoschool.splinter.application.configuration.database;

@SuppressWarnings("unused")
public enum Driver {
    MYSQL (com.mysql.jdbc.Driver.class),
    H2 (org.h2.Driver.class);
    private Class driverClass;
    Driver(Class driverClass) {
        this.driverClass = driverClass;
    }
    public Class getDriverClass() {
        return driverClass;
    }
}
