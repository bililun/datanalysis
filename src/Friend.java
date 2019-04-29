public class Friend {

    private String value;
    private Word word;
    private int distance;
    private int frequency;

    public Friend(String value, int distance, int frequency) {
        this.value = value;
        this.distance = distance;
        this.frequency = frequency;
    }
    public Friend(String value, int distance) {
        this.value = value;
        this.distance = distance;
        this.frequency = 1;
    }

    public boolean isSame(Friend otherfriend) {
        if (value == otherfriend.getValue() && distance == otherfriend.getDistance()) {
            return true;
        }
        return false;
    }

    public String getValue() {
        return value;
    }
    public int getDistance() {
        return distance;
    }
    public int getFrequency() {
        return frequency;
    }
    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
    public void addFrequency(int otherfrequency) {
        frequency += otherfrequency;
    }
}
