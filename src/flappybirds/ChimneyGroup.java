package flappybirds;

import framework.QueueList;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ChimneyGroup
{
    private QueueList<Chimney> chimneys;
    private BufferedImage botChimney, topChimney;

    public static int size = 6;
    private int topChimneyY = -350;
    private int bottomChimneyY = 200;

    public Chimney getChimney(int i)
    {
        return chimneys.get(i);
    }

    public int getRandomY()
    {
        Random random = new Random();
        int ranNumber;
        ranNumber = random.nextInt(10);

        return ranNumber * 35;
    }

    public ChimneyGroup()
    {
        try
        {
            botChimney = ImageIO.read(new File("G:\\My Drive\\Java Practice\\Flappy Bird\\src\\Assets\\bottomChimney.png"));
            topChimney = ImageIO.read(new File("G:\\My Drive\\Java Practice\\Flappy Bird\\src\\Assets\\topChimney.png"));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        chimneys = new QueueList<Chimney>();
        Chimney chimney;

        for (int i = 0; i < size / 2; i++)
        {
            int deltaY = getRandomY();

            chimney = new Chimney(830 + i * 300, bottomChimneyY + deltaY, 74, 400);
            chimneys.push(chimney);

            chimney = new Chimney(830 + i * 300, topChimneyY + deltaY, 74, 400);
            chimneys.push(chimney);
        }

    }

    public void resetChimneys()
    {
        chimneys = new QueueList<Chimney>();
        Chimney chimney;

        for (int i = 0; i < size / 2; i++)
        {
            int deltaY = getRandomY();

            chimney = new Chimney(830 + i * 300, bottomChimneyY + deltaY, 74, 400);
            chimneys.push(chimney);

            chimney = new Chimney(830 + i * 300, topChimneyY + deltaY, 74, 400);
            chimneys.push(chimney);
        }
    }

    public void update()
    {
        for (int i = 0; i < size; i++)
        {
            chimneys.get(i).update();
        }

        if (chimneys.get(0).getPosX() < -74)
        {
            int deltaY = getRandomY();
            Chimney chimney;

            chimney = chimneys.pop();
            chimney.setPosX(chimneys.get(4).getPosX() + 300);
            chimney.setPosY(bottomChimneyY + deltaY);
            chimney.setIsBehindBird(false);
            chimneys.push(chimney);

            chimney = chimneys.pop();
            chimney.setPosX(chimneys.get(4).getPosX());
            chimney.setPosY(topChimneyY + deltaY);
            chimney.setIsBehindBird(false);
            chimneys.push(chimney);
        }
    }

    public void paint(Graphics2D g)
    {
        for (int i = 0; i < 6; i++)
            if (i % 2 == 0)
            {
                g.drawImage(botChimney, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            }
            else
            {
                g.drawImage(topChimney, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            }
    }
}
