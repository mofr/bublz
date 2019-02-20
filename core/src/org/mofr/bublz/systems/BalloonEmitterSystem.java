package org.mofr.bublz.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import org.mofr.bublz.components.BalloonEmitter;
import org.mofr.bublz.factories.BalloonFactory;

public class BalloonEmitterSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private BalloonFactory balloonFactory;

    public BalloonEmitterSystem(BalloonFactory balloonFactory) {
        this.balloonFactory = balloonFactory;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BalloonEmitter.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            BalloonEmitter balloonEmitter = BalloonEmitter.mapper.get(entity);
            balloonEmitter.cooldown -= deltaTime;
            if (balloonEmitter.cooldown < 0) {
                int width = Gdx.graphics.getWidth();
                float x = MathUtils.random(width * 0.1f, width * 0.9f);
                float y = balloonEmitter.startY;
                float z = MathUtils.random(balloonEmitter.minZ, balloonEmitter.maxZ);
                float speedY = balloonEmitter.minSpeed + (balloonEmitter.maxSpeed - balloonEmitter.minSpeed) * z / (balloonEmitter.maxZ - balloonEmitter.minZ);
                float size = balloonEmitter.minSize + (1 - balloonEmitter.minSize) * z / (balloonEmitter.maxZ - balloonEmitter.minZ);
                balloonFactory.create(x, y, z, speedY, size);
                balloonEmitter.cooldown = MathUtils.random(balloonEmitter.cooldownMin, balloonEmitter.cooldownMax);
            }
        }
    }
}
