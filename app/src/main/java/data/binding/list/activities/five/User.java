package data.binding.list.activities.five;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.nimgade.pk.mytutorialapplication.BR;

/**
 * Created by Pankaj Nimgade on 04-05-2016.
 */
public class User extends BaseObservable {

    private String firstName;
    private String lastName;
    private boolean isFriend;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
        notifyPropertyChanged(BR.friend);
    }
}
