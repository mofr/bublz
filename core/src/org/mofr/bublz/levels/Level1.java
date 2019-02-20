package org.mofr.bublz.levels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.mofr.bublz.Assets;
import org.mofr.bublz.factories.BubbleEmitterFactory;

public class Level1 implements Level {
    private final PooledEngine engine;
    private final Assets assets;
    private final BubbleEmitterFactory bubbleEmitterFactory;
    private Entity bubbleEmitter = null;
    private final Color backgroundColor = new Color(0.0f, 0.6f, 0.6f, 1f);

    public Level1(PooledEngine engine, Assets assets) {
        this.engine = engine;
        this.assets = assets;
        bubbleEmitterFactory = new BubbleEmitterFactory(engine);
    }

    @Override
    public void start() {
        assets.music1.play();
        bubbleEmitter = bubbleEmitterFactory.create(0.5f, 1.2f);
    }

    @Override
    public void stop() {
        if (bubbleEmitter != null) {
            engine.removeEntity(bubbleEmitter);
            bubbleEmitter = null;
        }
    }

    @Override
    public Music getMusic() {
        return assets.music1;
    }

    @Override
    public Texture getBackground() {
        return assets.background.getBackground2();
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
