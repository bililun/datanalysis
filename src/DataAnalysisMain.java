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
        size(800, 800);
    }

    public void setup() {
        //rapgod is uhh very explicit however i wanted to test an extremely lyrical rap piece so
        bank = new SongBank(this, "data/sugarhoneyhoney.txt");
        background(255);
        fill(0);
        translate(width/2, height/2);
        bank.display();
        //displaypoints();
    }

    public void draw() {

    }

    public void mouseClicked() {
        System.out.println("Click registered");
        bank.test(20);
        bank.oneMoreArc(mouseX, mouseY);
        System.out.println("Click completed");
    }

    public void keyPressed() {
        if (key == 32) {
            save("/Users/lauren/Desktop/CS/IntelliJ/DataAnalysis/sugarhoneyhoney.png");
        }
    }

    public void displaypoints() {
        ellipse(0,0,5,5);
        ellipse(SongBank.RADIUS, 0, 5, 5);
        ellipse(0, SongBank.RADIUS, 5, 5);
        ellipse(-SongBank.RADIUS, 0, 5, 5);
        ellipse(0, -SongBank.RADIUS, 5, 5);
    }
}
