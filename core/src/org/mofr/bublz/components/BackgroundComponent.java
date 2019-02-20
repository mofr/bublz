package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

public class BackgroundComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<BackgroundComponent> mapper = ComponentMapper.getFor(BackgroundComponent.class);

    public Texture texture = null;
    public Color color = new Color(Color.WHITE);

    @Override
    public void reset() {
        texture = null;
        color.set(Color.WHITE);
    }
}
