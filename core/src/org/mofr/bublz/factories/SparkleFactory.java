package org.mofr.bublz.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import org.mofr.bublz.resources.SparkleResources;
import org.mofr.bublz.components.AnimationComponent;
import org.mofr.bublz.components.LimitedLifetimeComponent;
import org.mofr.bublz.components.TextureRegionComponent;
import org.mofr.bublz.components.TransformComponent;

public class SparkleFactory {
    private PooledEngine engine;
    private SparkleResources sparkleResources;

    public SparkleFactory(PooledEngine engine, SparkleResources sparkleResources) {
        this.engine = engine;
        this.sparkleResources = sparkleResources;
    }

    public Entity create(float x, float y) {
        Entity entity = engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        TextureRegionComponent textureRegionComponent = engine.createComponent(TextureRegionComponent.class);
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        LimitedLifetimeComponent limitedLifetimeComponent = engine.createComponent(LimitedLifetimeComponent.class);
        entity.add(transformComponent);
        entity.add(textureRegionComponent);
        entity.add(animationComponent);
        entity.add(limitedLifetimeComponent);
        transformComponent.x = x;
        transformComponent.y = y;
        animationComponent.animation = sparkleResources.getAnimation();
        limitedLifetimeComponent.lifetime = sparkleResources.getAnimation().getAnimationDuration();
        engine.addEntity(entity);
        sparkleResources.getSound().play();
        return entity;
    }
}
