package org.mofr.bublz.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.MathUtils;
import org.mofr.bublz.BalloonColor;
import org.mofr.bublz.components.*;
import org.mofr.bublz.resources.BalloonResources;

public class BalloonFactory {
    private PooledEngine engine;
    private BalloonResources balloonResources;

    public BalloonFactory(PooledEngine engine, BalloonResources balloonResources) {
        this.engine = engine;
        this.balloonResources = balloonResources;
    }

    public Entity create(float x, float y, float z, float speedY, float size) {
        Entity entity = engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        TextureRegionComponent textureRegionComponent = engine.createComponent(TextureRegionComponent.class);
        LinearMovementComponent linearMovementComponent = engine.createComponent(LinearMovementComponent.class);
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        BalloonComponent balloonComponent = engine.createComponent(BalloonComponent.class);
        CircleColliderComponent circleColliderComponent = engine.createComponent(CircleColliderComponent.class);
        entity.add(transformComponent);
        entity.add(textureRegionComponent);
        entity.add(animationComponent);
        entity.add(balloonComponent);
        entity.add(linearMovementComponent);
        entity.add(circleColliderComponent);
        transformComponent.x = x;
        transformComponent.y = y;
        transformComponent.z = z;
        balloonComponent.color = BalloonColor.values()[MathUtils.random(BalloonColor.values().length - 1)];
        animationComponent.animation = balloonResources.getFlyAnimation(balloonComponent.color);
        linearMovementComponent.speedY = speedY;
        circleColliderComponent.radius = 180 * size;
        textureRegionComponent.scaleX = size;
        textureRegionComponent.scaleY = size;
        engine.addEntity(entity);
        return entity;
    }
}
