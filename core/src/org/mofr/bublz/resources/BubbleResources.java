package org.mofr.bublz.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class BubbleResources {
    private Texture splashTexture1;
    private Texture splashTexture2;
    private Texture splashTexture3;
    private Animation<TextureRegion> appearAnimation;
    private Animation<TextureRegion> splashAnimation1;
    private Animation<TextureRegion> splashAnimation2;
    private Animation<TextureRegion> splashAnimation3;
    private Sound[] appearSounds;
    private Sound splashSound;

    public BubbleResources() {
        splashTexture1 = new Texture("bubble_1.png");
        splashTexture2 = new Texture("bubble_2.png");
        splashTexture3 = new Texture("bubble_3.png");
        splashTexture1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[] splashFrames1 = SpriteSheetUtility.splitSpriteSheet(splashTexture1, 3, 2);
        TextureRegion[] splashFrames2 = SpriteSheetUtility.splitSpriteSheet(splashTexture2, 4, 2);
        TextureRegion[] splashFrames3 = SpriteSheetUtility.splitSpriteSheet(splashTexture3, 4, 2);
        TextureRegion[] appearFrames = new TextureRegion[]{splashFrames1[0]};
        appearAnimation = new Animation<TextureRegion>(0.015f, appearFrames);
        splashAnimation1 = new Animation<TextureRegion>(0.035f, splashFrames1);
        splashAnimation2 = new Animation<TextureRegion>(0.025f, splashFrames2);
        splashAnimation3 = new Animation<TextureRegion>(0.035f, splashFrames3);
        appearSounds = new Sound[]{
                Gdx.audio.newSound(Gdx.files.internal("bubble1.wav")),
                Gdx.audio.newSound(Gdx.files.internal("bubble2.wav")),
                Gdx.audio.newSound(Gdx.files.internal("bubble3.wav")),
        };
        splashSound = Gdx.audio.newSound(Gdx.files.internal("water_splash.wav"));
    }

    public Animation<TextureRegion> getAppearAnimation() {
        return appearAnimation;
    }

    public Animation<TextureRegion> getSplashAnimation() {
        int random = MathUtils.random(1, 2);
        if (random == 1) {
            return splashAnimation1;
        } else if (random == 2) {
            return splashAnimation2;
        } else {
            return splashAnimation3;
        }
    }

    public Sound getAppearSound() {
        return appearSounds[MathUtils.random(appearSounds.length - 1)];
    }

    public Sound getSplashSound() {
        return splashSound;
    }

    public void dispose() {
    }
}
