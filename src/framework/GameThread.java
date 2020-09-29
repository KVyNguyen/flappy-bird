package framework;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class GameThread extends JPanel implements Runnable
{

    private GameScreen context;

    private Thread thread;

    private Graphics ThisGraphics;

    public static int FPS = 70;

    private BufferedImage buffImage;

    private int MasterWidth, MasterHeight;
    public static float scaleX = 1, scaleY = 1;

    public GameThread(GameScreen context)
    {
        this.context = context;

        MasterWidth = context.CUSTOM_WIDTH;
        MasterHeight = context.CUSTOM_HEIGHT;

        this.thread = new Thread(this);
    }

    public void StartThread()
    {
        thread.start();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, context.CUSTOM_WIDTH, context.CUSTOM_HEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        if (buffImage != null)
        {
            g2.scale(scaleX, scaleY);
            g2.drawImage(buffImage, 0, 0, this);
        }
    }

    private void UpdateSize()
    {
        if (this.getWidth() <= 0)
            return;

        context.CUSTOM_WIDTH = this.getWidth();
        context.CUSTOM_HEIGHT = this.getHeight();

        scaleX = (float) context.CUSTOM_WIDTH / (float) MasterWidth;
        scaleY = (float) context.CUSTOM_HEIGHT / (float) MasterHeight;
    }


    @Override
    public void run()
    {

        long T = 1000 / FPS;
        long TimeBuffer = T / 2;

        long BeginTime = System.currentTimeMillis();
        long EndTime;
        long sleepTime;

        while (true)
        {
            UpdateSize();
            context.gameUpdate(System.currentTimeMillis());
            try
            {
                buffImage = new BufferedImage(MasterWidth, MasterHeight, BufferedImage.TYPE_INT_ARGB);
                if (buffImage == null)
                {
                    return;
                }
                Graphics2D g = (Graphics2D) buffImage.getGraphics();

                if (g != null)
                {
                    context.gamePaint(g);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            repaint();

            EndTime = System.currentTimeMillis();
            sleepTime = T - (EndTime - BeginTime);

            if (sleepTime < 0)
                sleepTime = TimeBuffer;

            try
            {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            BeginTime = System.currentTimeMillis();
        }
    }
}
