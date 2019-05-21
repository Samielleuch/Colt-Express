import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Game!");

        window.setLayout(new BorderLayout());

        //creat GamePanel

        GamePanel gp = new GamePanel();
        window.add(gp, BorderLayout.CENTER);

        //create Buttons Panel
        Buttons bp = new Buttons(gp);
        window.add(bp, BorderLayout.SOUTH);


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}