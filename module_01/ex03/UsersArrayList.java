package ex03;

class UserNotFoundException extends RuntimeException {

}

public class UsersArrayList implements UsersList {
    private User[] arr;
    private Integer number;

    private static final Integer defaultSize = 10;

    public UsersArrayList() {
        arr = new User[defaultSize];
        number = 0;
    }

    @Override
    public void add(User user) {
        if (arr.length == number) {
            User[] newArr = new User[number + number];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[number] = user;
        number++;
    }

    @Override
    public User getById(Integer id) {
        for (int i = 0; i < number; i++) {
            if (id.equals(arr[i].getIdentifier())) {
                return arr[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(Integer id) {
        if (id < 0 || id >= number) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[id];
    }

    @Override
    public Integer getUsersNumber() {
        return number;
    }
}