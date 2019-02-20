package org.mofr.bublz;

import aurelienribon.tweenengine.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.mofr.bublz.components.BackgroundComponent;

public class Background {
    private PooledEngine engine;
    private TweenManager tweenManager;
    private Entity currentBackground;

    public Background(PooledEngine engine, TweenManager tweenManager) {
        this.engine = engine;
        this.tweenManager = tweenManager;
    }

    public void crossfadeTo(Texture texture, Color color) {
        final Entity previousBackground = currentBackground;
        currentBackground = createBackground(texture, color);

        if (previousBackground != null) {
            BackgroundComponent.mapper.get(currentBackground).color.set(color.r, color.g, color.b, 0);
            Tween tween = Tween.to(currentBackground, EntityTweenAccessor.BACKGROUND_COLOR, 1.5f).target(
                    color.r,
                    color.g,
                    color.b,
                    1
            ).setCallback(new TweenCallback() {
                @Override
                public void onEvent(int type, BaseTween<?> source) {
//                    engine.removeEntity(previousBackground);  // TODO fix background draw order (DrawOrderComponent) and remove previous backgrounds
                }
            }).ease(TweenEquations.easeOutQuad);
            tween.start(tweenManager);
        }
    }

    private Entity createBackground(Texture texture, Color color) {
        Entity backgroundEntity = engine.createEntity();
        BackgroundComponent backgroundComponent = engine.createComponent(BackgroundComponent.class);
        backgroundComponent.texture = texture;
        backgroundComponent.color.set(color);
        backgroundEntity.add(backgroundComponent);
        engine.addEntity(backgroundEntity);
        return backgroundEntity;
    }
}
