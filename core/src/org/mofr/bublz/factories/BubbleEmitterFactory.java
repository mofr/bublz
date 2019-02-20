package org.mofr.bublz.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import org.mofr.bublz.components.*;

public class BubbleEmitterFactory {
    private PooledEngine engine;

    public BubbleEmitterFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity create(float cooldownMin, float cooldownMax) {
        Entity entity = engine.createEntity();
        BubbleEmitter bubbleEmitter = engine.createComponent(BubbleEmitter.class);
        entity.add(bubbleEmitter);
        bubbleEmitter.cooldownMin = cooldownMin;
        bubbleEmitter.cooldownMax = cooldownMax;
        engine.addEntity(entity);
        return entity;
    }
}
