package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class CircleColliderComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<CircleColliderComponent> mapper = ComponentMapper.getFor(CircleColliderComponent.class);

    public float radius = 0;

    @Override
    public void reset() {
        radius = 0;
    }
}
