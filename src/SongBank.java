import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class SongBank implements Sortable {

    PApplet p;
    private ArrayList<Word> songbank;
    private static int NUMFRIENDS = 3;
    public static float RADIUS;
    private static float ANGLE;


    public SongBank(PApplet p, String filename) {
        this.p = p;
        loadSongBank(filename);
        sort(songbank);
        removeDuplicates();
        linkToWords();
        RADIUS = (float)(p.width/2.1);
        ANGLE = p.radians((float)(360.0/songbank.size()));
    }

    public void display() {
        p.push();
        //display the words
        p.textAlign(PConstants.CENTER);
        for (int w = 0; w < songbank.size(); w++) {
            //display
            songbank.get(w).display(p, 0, RADIUS);
            //give it its x and y
            songbank.get(w).setxy(RADIUS * p.sin(w*ANGLE), RADIUS * p.cos(w*ANGLE));
            //rotate
            p.rotate(-ANGLE);
        }
        p.pop();

//        for (Word w : songbank) {
//            w.displaypoint(p);
//        }
    }

    public void test(int wordnumber) {
        p.fill(0);
        System.out.println("Testing " + songbank.get(wordnumber).getValue());
        p.ellipse(songbank.get(wordnumber).getX(), songbank.get(wordnumber).getY(), 50, 50);
        p.ellipse(20, 20, 20, 20);
    }

    public void oneMoreArc(float mouseX, float mouseY) {
        //finds the closest word to the click and tells that word to draw its arcs
        Word clickedword = songbank.get(0);
        float oldDist = p.dist(clickedword.getX(), clickedword.getY(), mouseX, mouseY);
        for (Word w : songbank) {
            float newDist = p.dist(w.getX(), w.getY(), mouseX, mouseY);
            if (newDist < oldDist) {
                //if the new word is closer than the clicked word
                clickedword = w;
                oldDist = p.dist(clickedword.getX(), clickedword.getY(), mouseX, mouseY);
            }
        }
        clickedword.drawArcs(p);
    }

    private void linkToWords() {
        //before this, the friends lists in each word only connect to String values
        //i want to connect each Word to the actual Word s it's friends with
        for (Word w : songbank) {
            w.findRealFriends(this);
        }
    }

    private void removeDuplicates() {
        //ASSUMES SORTED
        //VERY IMPORTANT THAT IT'S ALREADY SORTED
        //LIKE SUPER IMPORTANT

        int i = 0;
        while (i < songbank.size()) { //go through the whole songbank
            Word word = songbank.get(i); //claim the word you're working on
            String value = word.getValue();
            //increment i
            i++;
            //don't try and reach out of the songbank
            //while the word you're working on matches the next word
            while (i < songbank.size() && value.equals(songbank.get(i).getValue())) {
                Word otherword = songbank.get(i);
                word.combine(otherword); //add your friends together
                songbank.remove(i);
            }
        }
        //System.out.println("----------------");
        //printbank();
    }

    private void loadSongBank(String filename) {
        String[] lines = p.loadStrings(filename);
        String everything = p.join(lines, " ");
        everything = everything.toLowerCase();

        String delimiters = " ,()!\"";
        String[] allWords = p.splitTokens(everything, delimiters);

        songbank = new ArrayList<>();
        for (int i = 0; i < allWords.length; i++) {
            songbank.add(new Word(allWords[i]));
            //System.out.println("songbank is of size " + songbank.size());
            //System.out.println(" and the last word is " + allWords[i] + " = " + songbank.get(songbank.size()-1).getValue());
            //give the new word friends
            for (int f = 1; f <= NUMFRIENDS && f+i < allWords.length; f++) {
                //System.out.println("adding number " + f + " friend to " + allWords[i]);
                songbank.get(songbank.size()-1).addFriends(allWords[i+f], f);
            }
        }
        //printbank();
    }

    public ArrayList<Word> sort(ArrayList<Word> whole) {
        ArrayList<Word> left = new ArrayList<>();
        ArrayList<Word> right = new ArrayList<>();
        int center;

        if (whole.size() == 1) {
            return whole;
        } else {
            center = whole.size()/2;
            for (int l = 0; l < center; l++) {
                left.add(whole.get(l));
            }
            for (int r = center; r < whole.size(); r++) {
                right.add(whole.get(r));
            }

            left = sort(left);
            right = sort(right);

            merge(left, right, whole);
        }
        return whole;
    }

    private void merge(ArrayList<Word> left, ArrayList<Word> right, ArrayList<Word> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        //while neither half has been completely used
        //check whether the lowest left or lowest right is smaller
        //put the smaller one in whole
        //increment index of whole and the half picked
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getValue().compareTo(right.get(rightIndex).getValue()) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                wholeIndex++;
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                wholeIndex++;
                rightIndex++;
            }
        }

        //when one of them has been used up
        //if the right has been used up, put all of left in
        while(leftIndex < left.size()) {
            whole.set(wholeIndex, left.get(leftIndex));
            wholeIndex++;
            leftIndex++;
        }
        //if the left has been used up, put all of right in
        while(rightIndex < right.size()) {
            whole.set(wholeIndex, right.get(rightIndex));
            wholeIndex++;
            rightIndex++;
        }
    }

    private void printbank() {
        for (Word w : songbank) {
            System.out.println(w.getValue());
        }
    }

    public Word find(String value) {
        int low = 0;
        int high = songbank.size() - 1;

        while (low <= high) {
            int mid = (low+high)/2;
            String current = songbank.get(mid).getValue();

            if (current.equals(value)) {
                return songbank.get(mid);
            } else if (current.compareTo(value) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Failed to find " + value);
        return null;
    }

    public static int getNumFriends() {
        return NUMFRIENDS;
    }
}
