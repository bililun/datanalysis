import processing.core.PApplet;

public class DataAnalysisMain extends PApplet{

    private SongBank bank;

    public static void main(String[] args) {
        PApplet.main("DataAnalysisMain");
    }
    public DataAnalysisMain() {

    }

    public void settings() {
        //fullScreen();
        size(600, 600);
    }

    public void setup() {
        //rapgod is uhh very explicit however i wanted to text an extremely lyrical rap piece so
        bank = new SongBank(this, "data/sugarhoneyhoney.txt");
    }

    public void draw() {

    }
}
