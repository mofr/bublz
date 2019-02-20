package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class BalloonEmitter implements Pool.Poolable, Component {
    public static final ComponentMapper<BalloonEmitter> mapper = ComponentMapper.getFor(BalloonEmitter.class);

    public float cooldownMin = 1.0f;
    public float cooldownMax = 1.0f;
    public float cooldown = 0;
    public float startY = 0;
    public float minSpeed = 0;
    public float maxSpeed = 0;
    public float minZ = 0;
    public float maxZ = 0;
    public float minSize = 1;

    @Override
    public void reset() {
        cooldownMin = 1.0f;
        cooldownMax = 1.0f;
        cooldown = 0;
        startY = 0;
        minSpeed = 0;
        maxSpeed = 0;
        minSize = 1;
    }
}
