package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class LimitedLifetimeComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<LimitedLifetimeComponent> mapper = ComponentMapper.getFor(LimitedLifetimeComponent.class);

    public float lifetime;

    @Override
    public void reset() {
        lifetime = 0;
    }
}
