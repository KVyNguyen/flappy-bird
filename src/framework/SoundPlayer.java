package framework;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer
{

    private Clip soundClip;

    public SoundPlayer(File path)
    {
        try
        {
            AudioInputStream audio;
            audio = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = audio.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
                    16, baseFormat.getChannels(), baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, audio);
            soundClip = AudioSystem.getClip();
            soundClip.open(dais);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void play()
    {
        if (soundClip != null)
        {
            stop();
            soundClip.setFramePosition(0);
            soundClip.start();
        }
    }

    public void stop()
    {
        if (soundClip.isRunning())
            soundClip.stop();
    }

    public void close()
    {
        soundClip.close();
    }
}
