package main.java;

import javax.sound.sampled.*;
import java.net.URL;

public class SoundService {

  Clip musicClip;
  URL url[] = new URL[10];

  public SoundService() {

    url[0] = getClass().getResource("/delete_line.wav");
    url[1] = getClass().getResource("/zelda_game_over_theme.wav");
    url[2] = getClass().getResource("/original_tetris_theme.wav");
    url[3] = getClass().getResource("/rotation.wav");
    url[4] = getClass().getResource("/touch_floor.wav");
  }

  public void play(int i , boolean music) {

    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url[i]);
      Clip clip = AudioSystem.getClip();

      if (music) {
        musicClip = clip;
      }

      clip.open(audioInputStream);
      clip.addLineListener(event -> {
        if (event.getType() == LineEvent.Type.STOP) {
          clip.close();
        }
      });

      audioInputStream.close();
      clip.start();

    } catch (Exception e) {

    }
  }


  public void loop() {
    musicClip.loop(Clip.LOOP_CONTINUOUSLY);
  }


  public void stop() {
    musicClip.stop();
    musicClip.close();
  }
}