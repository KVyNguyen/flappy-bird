package flappybirds;

import framework.AFrameOnImage;
import framework.Animation;
import framework.GameScreen;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlappyBirds extends GameScreen
{

    private BufferedImage birds;
    private Animation birdAnimation;

    private Bird bird;
    private Ground ground;
    private ChimneyGroup chimneyGroup;

    private int Point = 0;

    private int beginScreen = 0;
    private int gameplayScreen = 1;
    private int gameoverScreen = 2;
    private int currentScreen = beginScreen;

    protected static float gravity = 0.15f;

    public FlappyBirds()
    {
        super(800, 600);
        try
        {
            birds = ImageIO.read(new File("G:\\My Drive\\Java Practice\\Flappy Bird\\src\\Assets\\birdSprite.png"));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        birdAnimation = new Animation(70);

        AFrameOnImage frame;
        frame = new AFrameOnImage(0, 0, 60, 60);
        birdAnimation.addFrame(frame);
        frame = new AFrameOnImage(60, 0, 60, 60);
        birdAnimation.addFrame(frame);
        frame = new AFrameOnImage(120, 0, 60, 60);
        birdAnimation.addFrame(frame);
        frame = new AFrameOnImage(60, 0, 60, 60);
        birdAnimation.addFrame(frame);

        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();

        chimneyGroup = new ChimneyGroup();

        BeginGame();
    }

    public static void main(String[] args)
    {
        new FlappyBirds();
    }

    private void resetGame()
    {
        bird.setPos(350, 250);
        bird.setVt(0);
        bird.setLive(true);
        Point = 0;
        chimneyGroup.resetChimneys();
    }

    @Override
    public void gameUpdate(long deltaTime)
    {
        if (currentScreen == beginScreen)
        {
            resetGame();
        }
        else if (currentScreen == gameplayScreen)
        {
            if (bird.getLive())
            {
                birdAnimation.Update(deltaTime);
            }
            bird.update(deltaTime);
            ground.Update();
            chimneyGroup.update();

            for (int i = 0; i < ChimneyGroup.size; i++)
            {
                if (bird.getRect().intersects(chimneyGroup.getChimney(i).getRect()))
                {
                    bird.setLive(false);
                    bird.fallSound.play();
                    System.out.println("alive = false");
                }
            }

            for (int i = 0; i < ChimneyGroup.size; i++)
            {
                if (bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBehindBird() && i % 2 == 0)
                {
                    Point++;
                    bird.getPointSound.play();
                    chimneyGroup.getChimney(i).setIsBehindBird(true);
                }
            }

            if (bird.getPosY() + bird.getH() > ground.getYGround())
            {
                currentScreen = gameoverScreen;
            }
        }
        else
        {

        }


    }

    @Override
    public void gamePaint(Graphics2D g)
    {
        g.setColor(Color.decode("#b8daef"));
        g.fillRect(0, 0, MASTER_WIDTH, MASTER_HEIGHT);

        chimneyGroup.paint(g);
        ground.Paint(g);

        if (bird.getIsFlying())
        {
            birdAnimation.paintAnimation((int) bird.getPosX(), (int) bird.getPosY(), birds, g, 0, -1);
        }
        else
        {
            birdAnimation.paintAnimation((int) bird.getPosX(), (int) bird.getPosY(), birds, g, 0, 0);
        }


        if (currentScreen == beginScreen)
        {
            g.setColor(Color.red);
            g.drawString("Press space to play game", 200, 300);
        }
        if (currentScreen == gameoverScreen)
        {
            g.setColor(Color.red);
            g.drawString("Press space to turn back begin screen", 200, 300);
        }

        g.setColor(Color.red);
        g.drawString("Point: " + Point, 20, 50);
    }

    @Override
    public void keyAction(KeyEvent e, int Event)
    {
        if (Event == KEY_PRESSED)
        {
            if (currentScreen == beginScreen)
            {
                currentScreen = gameplayScreen;
            }
            else if (currentScreen == gameplayScreen)
            {
                if (bird.getLive())
                {
                    bird.fly();
                }
            }
            else if (currentScreen == gameoverScreen)
            {
                currentScreen = beginScreen;
            }
        }
    }
}
