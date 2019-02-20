package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class TransformComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<TransformComponent> mapper = ComponentMapper.getFor(TransformComponent.class);

    public float x = 0;
    public float y = 0;
    public float z = 0; // the higher the farther

    @Override
    public void reset() {
        x = 0;
        y = 0;
        z = 0;
    }
}
