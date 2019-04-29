import processing.core.PApplet;

import java.util.ArrayList;

public class Word {

    private String value;
    private ArrayList<Friend> friends; //counts Friends forward from it
    private float x, y;

    public Word(String text) {
        friends = new ArrayList<Friend>();
        value = text;
    }

    public void display(PApplet p, float x, float y) {
        p.text(value, x, y);
        //p.ellipse(x, y-10, 5, 5);
    }

    public void addFriends(String friend, int distance) {
        friends.add(new Friend(friend, distance));
    }
    public String getValue() {
        return value;
    }
    public ArrayList<Friend> getFriends() {
        return friends;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setxy(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void combine(Word otherword) {
        //should take otherword which is another occurence of the same-value word
        //combine friends + frequency
        for (Friend otherf : otherword.getFriends()) { //go through every other friend
            boolean foundMatch = false;
            for (Friend ownf : friends) { //check it against every current friend
                if (otherf.isSame(ownf)) { //if it's the same, combine them and say we found a match
                    ownf.addFrequency(otherf.getFrequency());
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) { //if you never found a match
                friends.add(otherf); //make it a new friend
            }
        }
    }

    public void findRealFriends(SongBank songbank) {
        //should take the whole songbank
        //finds its friends as Words in the songbank and puts the referenced Word in each Friend
        for (Friend f : friends) {
            f.setWord(songbank.find(f.getValue()));
        }
    }

    public void drawArcs(PApplet p) {
        for (Friend f : friends) {
            drawArc(p, f);
        }
    }

    public void drawArc(PApplet p, Friend friend) {
        p.stroke(p.map(friend.getDistance(), 0, SongBank.getNumFriends(), 0, 100));
        p.strokeWeight(friend.getFrequency());
        p.line(x, y, friend.getWord().getX(), friend.getWord().getY());
    }

    public void displaypoint(PApplet p) {
        p.ellipse(x, y, 5, 5);
    }
}
