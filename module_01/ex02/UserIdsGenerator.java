package ex02;

public class UserIdsGenerator {

    private static UserIdsGenerator userIdsGenerator;
    private Integer lastId;

    private UserIdsGenerator() {
        lastId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null) {
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator;
    }

    public Integer generateId() {
        lastId++;
        return lastId;
    }
}
