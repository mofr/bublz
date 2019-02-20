package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class AnimationComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<AnimationComponent> mapper = ComponentMapper.getFor(AnimationComponent.class);

    public float time;
    public Animation<TextureRegion> animation;

    @Override
    public void reset() {
        time = 0;
        animation = null;
    }
}
