package data.binding.list.activities.four;

/**
 * Created by Pankaj Nimgade on 04-05-2016.
 */
public class User {

    private boolean isFriend;

    public User(boolean isFriend) {
        this.isFriend = isFriend;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }
}
