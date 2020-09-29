package flappybirds;

import framework.Objects;
import framework.SoundPlayer;
import java.awt.Rectangle;
import java.io.File;

public class Bird extends Objects
{
    private float vt = 0;
    private boolean isFlying = false;
    private Rectangle rect;
    private boolean isLive = true;
    protected SoundPlayer flapSound, fallSound, getPointSound;

    public Bird (int x, int y, int w, int h)
    {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);

        flapSound = new SoundPlayer(new File("G:\\My Drive\\Java Practice\\Flappy Bird\\src\\Assets\\fap.wav"));
        fallSound = new SoundPlayer(new File("G:\\My Drive\\Java Practice\\Flappy Bird\\src\\Assets\\fall.wav"));
        getPointSound = new SoundPlayer(new File("G:\\My Drive\\Java Practice\\Flappy Bird\\src\\Assets\\getpoint.wav"));
    }

    public void setLive(boolean b)
    {
        isLive = b;
    }

    public boolean getLive()
    {
        return isLive;
    }

    public Rectangle getRect()
    {
        return rect;
    }

    public void setVt(float vt)
    {
        this.vt = vt;
    }

    public void update(long deltaTime)
    {
        vt += FlappyBirds.gravity;

        this.setPosY(this.getPosY() + vt);
        this.rect.setLocation((int) this.getPosX(), (int) this.getPosY());

        if (vt < 0)
        {
            isFlying = true;
        }
        else
        {
            isFlying = false;
        }
    }

    public void fly()
    {
        vt = -3;
        flapSound.play();
    }

    public boolean getIsFlying()
    {
        return isFlying;
    }
}
