import processing.core.PApplet;

import java.util.ArrayList;

public class Word {

    private String value;
    private ArrayList<Friend> friends; //counts Friends forward from it
    private float x, y;
    private boolean shownArcs;

    public Word(String text) {
        friends = new ArrayList<Friend>();
        value = text;
        shownArcs = false;
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
        shrinkxy();
    }
    private void shrinkxy() {
        x *= 0.95;
        y *= 0.95;
    }

    public void combine(Word otherword) {
        //should take otherword which is another occurence of the same-value word
        //combine friends + frequency
        for (Friend otherf : otherword.getFriends()) { //go through all otherword's friends
            boolean foundMatch = false;
            for (Friend ownf : friends) { //check it against every current friend
                //System.out.println("Checking same: " + otherf.getDistance() + " ?= " + ownf.getDistance() + " and " + otherf.getValue() + " ?= " + ownf.getValue());
                if (otherf.isSame(ownf)) { //if it's the same distance and word, combine them and say we found a match
                    //System.out.print("Match. " + ownf.getFrequency() + " + " + otherf.getFrequency());
                    ownf.addFrequency(otherf.getFrequency());
                    //System.out.println(" = " + ownf.getFrequency());
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) { //if you never found a match
                //System.out.println("no match found for " + otherf.getValue());
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
        if (!shownArcs) {
            for (Friend f : friends) {
                drawArc(p, f);
            }
            shownArcs = true;
        } else {
            System.out.println("Arcs already shown for " + value);
        }
    }

    public void drawArc(PApplet p, Friend friend) {
        //color: black (alpha 100) for closest friends --> gray (alpha 25) for furthest friends
        p.stroke(0, p.map(friend.getDistance(), 0, SongBank.getNumFriends(), 100, 25));
        p.strokeWeight((float)Math.pow(friend.getFrequency(), 1.5));
        p.line(x, y, friend.getWord().getX(), friend.getWord().getY());
    }

    public void displaypoint(PApplet p) {
        p.ellipse(x, y, value.length(), value.length());
    }
}
