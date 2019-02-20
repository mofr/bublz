package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class TextureRegionComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<TextureRegionComponent> mapper = ComponentMapper.getFor(TextureRegionComponent.class);

    public TextureRegion textureRegion;
    public float originX = 0.5f;
    public float originY = 0.5f;
    public float scaleX = 1;
    public float scaleY = 1;

    @Override
    public void reset() {
        textureRegion = null;
        originX = 0.5f;
        originY = 0.5f;
        scaleX = 1;
        scaleY = 1;
    }
}
