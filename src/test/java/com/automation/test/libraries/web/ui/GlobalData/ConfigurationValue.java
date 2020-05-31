package com.automation.test.libraries.web.ui.GlobalData;

public class ConfigurationValue {

    //This configuration for credential Login into Pantry for Good project
    public static String URL = "http://localhost:8080/users/signin";
    public static String loginUserName = "admin@example.com";
    public static String loginPassWord = "password";

    //This configuration for matching ID_TCs column from CSV file
    public static String messageValidate ="Message";

    //This configuration for sorting type on table
    public static String sortASC = "ASC";
    public static String sortDESC = "DESC";

    //This configuration of all column of Inventory page
    public static String columnName = "Name";
    public static String columnCategory = "Category";
    public static String columnQty = "Qty";
    public static String messageDuplicateCategory = "That category already exists";

    //This This configuration of all columns of Food Schedule page
    public static String scheduleItemColumn = "Item";
    public static String scheduleCategoryColumn = "Category";
    public static String scheduleStartDateColumn = "Start Date";
    public static String scheduleFrequencycolumn = "Frequency";

}
