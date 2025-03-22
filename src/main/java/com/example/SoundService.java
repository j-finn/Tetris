package com.example;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SoundService {

  Clip musicClip;
  URL url[] = new URL[10];


  /**
   * Unmodifiable map storing whether each music item is playing or not.
   */
  private final Map<Sound, Boolean> musicPlaying;

  public SoundService() {

    musicPlaying = Arrays.stream(Sound.values())
      .collect(Collectors.toMap(sound -> sound, sound -> false));

    url[0] = getClass().getResource("/delete_line.wav");
    url[1] = getClass().getResource("/zelda_game_over_theme.wav");
    url[2] = getClass().getResource("/original_tetris_theme.wav");
    url[3] = getClass().getResource("/rotation.wav");
    url[4] = getClass().getResource("/touch_floor.wav");
  }


  public void playSoundEffect(Sound sound) {
    play(sound, false);
  }


  private void play(Sound sound, boolean isMusic) {

    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url[sound.getSongNumber()]);
      Clip clip = AudioSystem.getClip();

      if (isMusic) {
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


  /**
   * Toggles the provided {@link Sound} on or off.
   *
   * @param sound to toggle.
   */
  public void toggleMusic(Sound sound) {
    musicPlaying.compute(sound, (k, v) -> !v);

    if (musicPlaying.get(sound)) {
      play(sound, true);
      loop();
    } else {
      stop();
    }
  }


  public enum Sound {

    DELETE_LINE(0),
    GAME_OVER(1),
    TETRIS_THEME(2);

    private final int songNumber;

    Sound(int songNumber) {
      this.songNumber = songNumber;

    }

    public int getSongNumber() {
      return songNumber;
    }
  }
}