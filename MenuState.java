import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
    private BackgroundElement bg;
    //to keep track of the selected option
    private int currentChoice = 0 ;
    private String[] options= {
      "Start",
      "Help",
      "Quit"
    };
private Color titleColor;
private Font titleFont;
private Font font;
    @Override
    public void init() {

    }

    @Override
    public void update() {
     bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
     //draw background
        bg.draw(g);

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Colt Express. ",80,70);
        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length ; i++) {
            if (i == currentChoice ) {
                g.setColor(Color.BLACK);
            }else {
                g.setColor(Color.RED);
            }
            // draw them binethom 15 pixel
            g.drawString(options[i],145,140 + i * 15 );
        }
    }

    private void select() {
        if(currentChoice == 0 ){
            //start
            gsm.setGameState(GameStateManager.GAMECONTROLLERSTATE);
        }
        if(currentChoice == 1 ){
            //Help
        }
        if(currentChoice == 2 ){
            //quit
            System.exit(0);
        }

    }
    @Override
    public void keyPressed(int k) {

         if (k == KeyEvent.VK_ENTER) {
             select();
         }
           if (k == KeyEvent.VK_UP) {
             //move up
               currentChoice--;
             //if we reach the top end go to the last
               if (currentChoice == -1){
                   currentChoice = options.length -1 ;
               }
         }
    if (k == KeyEvent.VK_DOWN) {
        //move down
        currentChoice++;
        //if we reach the end end go to the top
        if (currentChoice == options.length ){
            currentChoice = 0 ;
        }
         }

    }

    @Override
    public void keyReleased(int k) {

    }

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
    try {

        bg = new BackgroundElement("/Resources/Background/menubg.gif", 1);
        //moves to the left
        bg.setVector(-0.1,0);
        titleColor = new Color(128,0,0);
        titleFont = new Font ("Century Gothic",Font.PLAIN , 28 );
        font = new Font ("Arial",Font.PLAIN , 12 );

    }catch(Exception e) {
        e.printStackTrace();
    }
    }
}
