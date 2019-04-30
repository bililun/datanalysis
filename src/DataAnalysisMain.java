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
        push();
        translate(width/2, height/2);
        //rapgod is uhh very explicit however i wanted to test an extremely lyrical rap piece so
        bank = new SongBank(this, "data/oldtownroad.txt");
        background(255);
        fill(0);
        bank.display();
        //displaypoints();
        pop();
    }

    public void draw() {
//        push();
//        translate(width/2, height/2);
//        pop();
    }

    public void mouseClicked() {
        push();
        translate(width/2, height/2);
        //bank.test(12);
        //subtract width/2 and height/2 to correct for mouseX and mouseY being assigned before translation
        bank.oneMoreArc(mouseX-width/2, mouseY-height/2);
        pop();
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
