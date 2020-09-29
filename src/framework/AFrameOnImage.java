package framework;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class AFrameOnImage
{
    private boolean enablePaintRect = false;
    private int[] dimensionBound = new int[4];

    public AFrameOnImage(int xOnImage, int yOnImage, int w, int h)
    {
        dimensionBound[0] = xOnImage;
        dimensionBound[1] = yOnImage;
        dimensionBound[2] = w;
        dimensionBound[3] = h;
    }

    public void visibleRectDebug(boolean enable)
    {
        enablePaintRect = enable;
    }

    public int[] getBounds()
    {
        return dimensionBound;
    }

    public void Paint(int x, int y, BufferedImage image, Graphics2D g, int anchor, float rotation)
    {
        BufferedImage imageDraw = image.getSubimage(dimensionBound[0], dimensionBound[1], dimensionBound[2], dimensionBound[3]);

        AffineTransform tx = new AffineTransform();
        tx.rotate(rotation, imageDraw.getWidth() / 2, imageDraw.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        imageDraw = op.filter(imageDraw, null);

        g.drawImage(imageDraw, x, y, null);

        if (enablePaintRect)
        {
            PaintBound(g);
        }
    }

    private void PaintBound(Graphics2D g)
    {

    }
}
