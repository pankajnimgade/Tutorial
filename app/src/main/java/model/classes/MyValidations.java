package model.classes;

/**
 * Created by Pankaj Nimgade on 15-01-2016.
 */
public class MyValidations {

    public static boolean isValidText(String text) {
        if (text == null) {
            return false;
        }
        if (text.trim().contentEquals("")) {
            return false;
        }

        if (text.trim().length() < 1) {
            return false;
        }

        if (text.trim().toLowerCase().contentEquals("null")) {
            return false;
        }
        return true;
    }
}
