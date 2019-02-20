package org.mofr.bublz.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import org.mofr.bublz.components.BalloonEmitter;

public class BalloonEmitterFactory {
    private PooledEngine engine;

    public BalloonEmitterFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity create(float cooldownMin, float cooldownMax, float startY, float minSpeed, float maxSpeed, float minZ, float maxZ, float minSize) {
        Entity entity = engine.createEntity();
        BalloonEmitter balloonEmitter = engine.createComponent(BalloonEmitter.class);
        entity.add(balloonEmitter);
        balloonEmitter.cooldownMin = cooldownMin;
        balloonEmitter.cooldownMax = cooldownMax;
        balloonEmitter.startY = startY;
        balloonEmitter.minSpeed = minSpeed;
        balloonEmitter.maxSpeed = maxSpeed;
        balloonEmitter.minZ = minZ;
        balloonEmitter.maxZ = maxZ;
        balloonEmitter.minSize = minSize;
        engine.addEntity(entity);
        return entity;
    }
}
