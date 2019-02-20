package org.mofr.bublz.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;
import org.mofr.bublz.BalloonColor;

public class BalloonComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<BalloonComponent> mapper = ComponentMapper.getFor(BalloonComponent.class);

    public BalloonColor color = null;

    @Override
    public void reset() {
        color = null;
    }
}
