package org.mofr.bublz.levels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.mofr.bublz.Assets;
import org.mofr.bublz.factories.BalloonEmitterFactory;

public class Level2 implements Level {
    private final PooledEngine engine;
    private final Assets assets;
    private final BalloonEmitterFactory balloonEmitterFactory;
    private Entity baloonEmitter = null;
    private final Color backgroundColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);

    public Level2(PooledEngine engine, Assets assets) {
        this.engine = engine;
        this.assets = assets;
        balloonEmitterFactory = new BalloonEmitterFactory(engine);
        // TODO make balloons appear fast from the bottom and then slowing down
        // TODO wind
        // TODO floating movement with balloons rotating
    }

    @Override
    public void start() {
        assets.music1.play();
        baloonEmitter = balloonEmitterFactory.create(0.3f, 0.9f, -300, 50, 200, 0, 10, 0.15f);
    }

    @Override
    public void stop() {
        if (baloonEmitter != null) {
            engine.removeEntity(baloonEmitter);
            baloonEmitter = null;
        }
    }

    @Override
    public Music getMusic() {
        return assets.music1;
    }

    @Override
    public Texture getBackground() {
        return assets.background.getBackground1();
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
