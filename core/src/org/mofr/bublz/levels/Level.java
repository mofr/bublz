package org.mofr.bublz.levels;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public interface Level {
    void start();
    void stop();
    Music getMusic();
    Texture getBackground();  // TODO background decorations
    Color getBackgroundColor();
}
