package ex03;

public class UsersArrayList implements UsersList{

  public static class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
  }

  public int usersCount = 0; 
  private User[] usersList = new User[10];
  
  public UsersArrayList() {}

  @Override
  public void addUser(User user) {    
    if (usersCount == usersList.length) {
      int newSize = usersList.length + (usersList.length / 2);
      User[] tmpUsersList = new User[newSize];
      for (int j = 0; j < usersCount; ++j) {
        tmpUsersList[j] = usersList[j];
      }
      usersList = tmpUsersList;
    }
    usersList[usersCount++] = user;
  }

  @Override
  public User getUserById(int id) {
    for (int i = 0; i < usersCount; i++) {
      if (usersList[i] != null && usersList[i].getIdentifier() == id) {
        return usersList[i];
      }
    }
    throw new UserNotFoundException("User with ID " + id + " not found.");
  }

  @Override
  public User getUserByIndex(int index) {
    if (index >= 0 && index < usersCount) return usersList[index];
    throw new UserNotFoundException("User with ID " + index + " not found.");
  }

  @Override
  public int getNumberOfUsers() {
    return usersCount;
  }

}