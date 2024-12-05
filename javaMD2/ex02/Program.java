

public class Program {

  public static void main(String []args) {
    User alice = new User("Alice", 1000);
    User bob = new User("Bob", 500);
    User sed = new User("Sed", 1500);
    UsersArrayList usersList = new UsersArrayList();
    usersList.addUser(alice);
    usersList.addUser(bob);
    usersList.addUser(sed);
    
    System.out.println("# of Users: " + usersList.getNumberOfUsers());

    // User searchedUserByID = usersList.getUserByID(2);
    // System.out.println("User's name: " + searchedUserByID.getName());
    
    // User searchedUserByIndex = usersList.getUserByIndex(2);
    // System.out.println("User's name: " + searchedUserByIndex.getName());
    
    for (int i = 0; i < 15; ++i) {
      String userName = "User_" + String.valueOf(i);
      User newUser = new User(userName, 100);
      usersList.addUser(newUser);
    }
    
    for (int i = 0; i < usersList.getNumberOfUsers(); ++i) {
      System.out.println("User's name: " + usersList.getUserByIndex(i).getName());
    }
  }
}