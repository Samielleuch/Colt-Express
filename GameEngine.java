import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameEngine extends JPanel implements Runnable, KeyListener {
    // Les dimensions
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    private static final int SCALE = 3;
    private static final long serialVersionUID = 1L;
    //  thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    // Graphic
    private BufferedImage image;
    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;

    public GameEngine() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
        // create the game state manager :
        gsm = new GameStateManager(this);
    }

    public GameStateManager getGsm() {
        return gsm;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    //when its loaded
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;

    }

    @Override
    public void run() {
        // on initialise
        init();
        //timers for the game
        long start;
        long elapsed;
        long wait;

        //gameLoop
        while (running) {
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if (wait < 0) wait = 10;
            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void draw() {
        gsm.draw(g);
    }

    private void update() {
        gsm.update();
        // reobtenir le focus sur ce panel
        grabFocus();
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        //draw the canvas
        g2.drawImage(image, 0, 0,
                WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());

    }
}