package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class BubbleEmitter implements Pool.Poolable, Component {
    public static final ComponentMapper<BubbleEmitter> mapper = ComponentMapper.getFor(BubbleEmitter.class);

    public float cooldownMin = 1.0f;
    public float cooldownMax = 1.0f;
    public float cooldown = 0;

    @Override
    public void reset() {
        cooldownMin = 1.0f;
        cooldownMax = 1.0f;
        cooldown = 0;
    }
}
