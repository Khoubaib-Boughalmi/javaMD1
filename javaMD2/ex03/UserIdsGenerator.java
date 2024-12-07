package ex03;

public class UserIdsGenerator {
  private static int lastGeneratedID = 0;
  private static UserIdsGenerator instance;

  private UserIdsGenerator() {}

  public static synchronized UserIdsGenerator getInstance() {
      if (instance == null) {
          instance = new UserIdsGenerator();
      }
      return instance;
  }

  public int generateId() {
      return ++lastGeneratedID;
  }
}