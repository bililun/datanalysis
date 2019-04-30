import processing.core.PApplet;

public class DataAnalysisMain extends PApplet{

    private SongBank bank;
    private boolean go = false;
    private int progress = 0;

    public static void main(String[] args) {
        PApplet.main("DataAnalysisMain");
    }
    public DataAnalysisMain() {

    }

    public void settings() {
        //fullScreen();
        size(8000, 8000);
    }

    public void setup() {
        //frameRate(3);
        colorMode(RGB, 255, 25,255, 100);
        push();
        translate(width/2, height/2);
        //rapgod is uhh very explicit however i wanted to test a lot of words so
        //UPDATE RAPGOD IS A BAD IDEA IN ORDER TO FIT ALL THE WORDS THE CANVAS HAS TO BE 8000x8000 AND IT TAKES ABOUT TEN MILLION YEARS AND ALMOST CRASHED MY COMPUTER
        //roxanne is Very repetitive
        //old town road is probably my favorite
        bank = new SongBank(this, "data/rapgod.txt");
        background(255);
        fill(0);
        bank.display();
        //displaypoints();
        pop();
    }

    public void draw() {
        push();
        translate(width/2, height/2);
        if (go && progress < bank.getSize()) {
            bank.arcWord(progress);
            progress++;
        }
        pop();
    }

    public void mouseClicked() {
        push();
        translate(width/2, height/2);
        //bank.test(12);
        //subtract width/2 and height/2 to correct for mouseX and mouseY being assigned before translation
        bank.clickArc(mouseX-width/2, mouseY-height/2);
        pop();
    }

    public void keyPressed() {
        if (key == 32) {
            save("/Users/lauren/Desktop/CS/IntelliJ/DataAnalysis/photos/rapgod.png");
        } else if (key == 'g') {
            go = !go;
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
