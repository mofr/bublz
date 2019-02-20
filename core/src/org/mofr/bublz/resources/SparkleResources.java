package org.mofr.bublz.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SparkleResources {
    private Texture texture;
    private Animation<TextureRegion> animation;
    private Sound sound;

    public SparkleResources() {
        texture = new Texture("sparkle.png");
        int FRAME_COLS = 4;
        int FRAME_ROWS = 2;
        TextureRegion[][] tmp = TextureRegion.split(
                texture,
                texture.getWidth() / FRAME_COLS,
                texture.getHeight() / FRAME_ROWS);
        TextureRegion[] shineFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                shineFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation<TextureRegion>(0.025f, shineFrames);
        sound = Gdx.audio.newSound(Gdx.files.internal("sparkle.wav"));
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public Sound getSound() {
        return sound;
    }

    public void dispose() {
        texture.dispose();
        sound.dispose();
    }
}
