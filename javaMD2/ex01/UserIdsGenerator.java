package ex01;

public class UserIdsGenerator {
  private static int lastGeneratedID = 0;
  private final static UserIdsGenerator instance = new UserIdsGenerator();

  private UserIdsGenerator() {}

  public static UserIdsGenerator getInstance() {
      return instance;
  }

  public int generateId() {
      return ++lastGeneratedID;
  }
}