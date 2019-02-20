package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class LinearMovementComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<LinearMovementComponent> mapper = ComponentMapper.getFor(LinearMovementComponent.class);

    public float speedX = 0;
    public float speedY = 0;

    @Override
    public void reset() {
        speedX = 0;
        speedY = 0;
    }
}
