import java.util.ArrayList;

public class Word {

    private String value;
    private ArrayList<String> friends; //counts words forward from it
    private int[] friendfrequency;
    private int[] frienddistance;
    private float x, y;

    public Word(String text) {
        friends = new ArrayList<String>();
        value = text;
    }

    public void addFriends(String friend) {
        friends.add(friend);
    }

    public String getValue() {
        return value;
    }
}
