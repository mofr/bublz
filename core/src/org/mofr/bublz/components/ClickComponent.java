package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class ClickComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<ClickComponent> mapper = ComponentMapper.getFor(ClickComponent.class);

    public float x;
    public float y;

    public void reset() {
        x = 0;
        y = 0;
    }
}
