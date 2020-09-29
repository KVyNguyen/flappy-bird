package flappybirds;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground
{
    private BufferedImage groundImage;
    private int x1, y1, x2, y2;

    public Ground()
    {
        try
        {
            groundImage = ImageIO.read(new File("G:\\My Drive\\Java Practice\\Flappy Bird\\src\\Assets\\ground.png"));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        x1 = 0;
        y1 = 500;
        x2 = x1 + 830;
        y2 = 500;
    }

    public void Update()
    {
        x1 -= 2;
        x2 -= 2;

        if (x2 < 0)
        {
            x1 = x2 + 830;
        }
        if (x1 < 0)
        {
            x2 = x1 + 830;
        }
    }

    public void Paint(Graphics2D g)
    {
        g.drawImage(groundImage, x1, y1, null);
        g.drawImage(groundImage, x2, y2, null);
    }

    public int getYGround()
    {
        return y1;
    }
}
