package config;

public class Config {

  private static String dbUserName;
  private static String dbPassword;
  private static String dbUrl;
  private static String dbName;
  private static String dbPort;

  public static String getDbUserName() {
    return dbUserName;
  }

  public static void setDbUserName(String dbUserName) {
    Config.dbUserName = dbUserName;
  }

  public static String getDbPassword() {
    return dbPassword;
  }

  public static void setDbPassword(String dbPassword) {
    Config.dbPassword = dbPassword;
  }

  public static String getDbUrl() {
    return dbUrl;
  }

  public static void setDbUrl(String dbUrl) {
    Config.dbUrl = dbUrl;
  }

  public static String getDbName() {
    return dbName;
  }

  public static void setDbName(String dbName) {
    Config.dbName = dbName;
  }

  public static String getDbPort() {
    return dbPort;
  }

  public static void setDbPort(String dbPort) {
    Config.dbPort = dbPort;
  }
}
