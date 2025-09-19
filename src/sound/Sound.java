package sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/songs/game.mp3");
        soundURL[1] = getClass().getResource("/sound/songs/terrorRoom.mp3");
        soundURL[2] = getClass().getResource("/sound/songs/hackerRoom.mp3");
        soundURL[3] = getClass().getResource("/sound/songs/labRoom.mp3");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);

            AudioFormat baseFormat = audioInputStream.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16, // bits
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false); // little-endian

            AudioInputStream decodedAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);

            clip = AudioSystem.getClip();
            clip.open(decodedAudioInputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f) {
            throw new IllegalArgumentException("Volume must be between 0 and 1");
        }

        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }
}