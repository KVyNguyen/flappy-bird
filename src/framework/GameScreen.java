package framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public abstract class GameScreen extends JFrame implements KeyListener
{
    public static int KEY_PRESSED = 0;
    public static int KEY_RELEASED = 1;

    public int CUSTOM_WIDTH = 500;
    public int CUSTOM_HEIGHT = 500;

    private GameThread G_Thread;

    public static int MASTER_WIDTH = 500, MASTER_HEIGHT = 500;

    public GameScreen()
    {
        InitThread();
        InitScreen();
    }

    public void RegisterImage(int id, BufferedImage image)
    {

    }

    public BufferedImage getImageWithID(int id)
    {
        return null;
    }

    public GameScreen(int w, int h)
    {
        this.CUSTOM_WIDTH = w;
        this.CUSTOM_HEIGHT = h;
        MASTER_WIDTH = CUSTOM_WIDTH;
        MASTER_HEIGHT = CUSTOM_HEIGHT;
        InitThread();
        InitScreen();
    }

    private void InitScreen()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        setVisible(true);
    }

    public void BeginGame()
    {
        G_Thread.StartThread();
    }

    private void InitThread()
    {
        G_Thread = new GameThread(this);
        add(G_Thread);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keyAction(e, GameScreen.KEY_PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keyAction(e, GameScreen.KEY_RELEASED);
    }

    public abstract void gameUpdate(long deltaTime);

    public abstract void gamePaint(Graphics2D g);

    public abstract void keyAction(KeyEvent e, int Event);

}
