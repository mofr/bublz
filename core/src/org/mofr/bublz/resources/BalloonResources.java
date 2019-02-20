package org.mofr.bublz.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import org.mofr.bublz.BalloonColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BalloonResources {
    private List<Texture> allTextures = new ArrayList<Texture>();
    private HashMap<BalloonColor, Animation<TextureRegion>> flyAnimations = new HashMap<BalloonColor, Animation<TextureRegion>>();
    private HashMap<BalloonColor, Animation<TextureRegion>> popAnimations = new HashMap<BalloonColor, Animation<TextureRegion>>();
    private Sound[] popSounds;

    public BalloonResources() {
        load(BalloonColor.BLUE, "blue-balloon");
        load(BalloonColor.GREEN, "green-balloon");
        load(BalloonColor.ORANGE, "orange-balloon");
        load(BalloonColor.PINK, "pink-balloon");
        load(BalloonColor.PURPLE, "purple-balloon");
        load(BalloonColor.RED, "red-balloon");
        load(BalloonColor.YELLOW, "yellow-balloon");
        popSounds = new Sound[] {
                Gdx.audio.newSound(Gdx.files.internal("balloon_pop1.wav")),
                Gdx.audio.newSound(Gdx.files.internal("balloon_pop2.wav")),
                Gdx.audio.newSound(Gdx.files.internal("balloon_pop3.wav")),
        };
    }

    private void load(BalloonColor color, String prefix) {
        final int frameCount = 6;
        Texture[] textures = new Texture[frameCount];
        for (int i = 0; i < frameCount; ++i) {
            textures[i] = new Texture(prefix + "/" + (i + 1) + ".png");
            textures[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            allTextures.add(textures[i]);
        }

        TextureRegion[] flyFrames = new TextureRegion[]{
                new TextureRegion(textures[0]),
        };
        TextureRegion[] popFrames = new TextureRegion[]{
                new TextureRegion(textures[1]),
                new TextureRegion(textures[2]),
                new TextureRegion(textures[3]),
                new TextureRegion(textures[4]),
                new TextureRegion(textures[5]),
        };
        Animation<TextureRegion> popAnimation = new Animation<TextureRegion>(0.025f, popFrames);
        Animation<TextureRegion> flyAnimation = new Animation<TextureRegion>(0.025f, flyFrames);
        flyAnimations.put(color, flyAnimation);
        popAnimations.put(color, popAnimation);
    }

    public Animation<TextureRegion> getFlyAnimation(BalloonColor color) {
        return flyAnimations.get(color);
    }

    public Animation<TextureRegion> getPopAnimation(BalloonColor color) {
        return popAnimations.get(color);
    }

    public Sound getPopSound() {
        return popSounds[MathUtils.random(popSounds.length - 1)];
    }

    public void dispose() {
        for (Texture texture : allTextures) {
            texture.dispose();
        }
    }
}
