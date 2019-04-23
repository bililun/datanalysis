import processing.core.PApplet;

import java.util.ArrayList;

public class SongBank implements Sortable {

    PApplet p;
    private ArrayList<Word> songbank;
    private int numFriends = 3;


    public SongBank(PApplet p, String filename) {
        this.p = p;
        loadSongBank(filename);
        sort(songbank);
        removeDuplicates();
    }

    private void removeDuplicates() {
        //ASSUMES SORTED
        //VERY IMPORTANT THAT IT'S ALREADY SORTED
        //LIKE SUPER IMPORTANT

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
            for (int f = 0; f < numFriends && f+i < allWords.length; f++) {
                //System.out.println("adding number " + f + " friend to " + allWords[i]);
                songbank.get(songbank.size()-1).addFriends(allWords[i+f]);
            }
        }
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
}
