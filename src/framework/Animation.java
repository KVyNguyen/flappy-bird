package framework;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation
{
    private long beginTime = 0;
    private long measure = 20;
    private AFrameOnImage[] frames;
    private int NumOfFrame = 0;
    private int CurrentFrame = 0;

    public Animation(long measure)
    {
        this.measure = measure;
    }

    public void Update(long deltaTime)
    {
        if (NumOfFrame > 0)
        {
            if (deltaTime - beginTime > measure)
            {
                CurrentFrame++;
                if (CurrentFrame >= NumOfFrame)
                    CurrentFrame = 0;
                beginTime = deltaTime;
            }
        }
    }

    public void addFrame(AFrameOnImage sprite)
    {
        AFrameOnImage[] bufferSprites = frames;
        frames = new AFrameOnImage[NumOfFrame + 1];
        for (int i = 0; i < NumOfFrame; i++)
            frames[i] = bufferSprites[i];
        frames[NumOfFrame] = sprite;
        NumOfFrame++;
    }

    public void paintAnimation(int x, int y, BufferedImage image, Graphics2D g, int anchor, float rotation)
    {
        frames[CurrentFrame].Paint(x, y, image, g, anchor, rotation);
    }
}
