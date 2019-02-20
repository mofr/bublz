package org.mofr.bublz.factories;

import aurelienribon.tweenengine.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.MathUtils;
import org.mofr.bublz.EntityTweenAccessor;
import org.mofr.bublz.components.*;
import org.mofr.bublz.resources.BubbleResources;

public class BubbleFactory {
    private PooledEngine engine;
    private BubbleResources bubbleResources;
    private TweenManager tweenManager;

    public BubbleFactory(PooledEngine engine, BubbleResources bubbleResources, TweenManager tweenManager) {
        this.engine = engine;
        this.bubbleResources = bubbleResources;
        this.tweenManager = tweenManager;
    }

    public Entity create(float x, float y, float size) {
        Entity entity = engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        TextureRegionComponent textureRegionComponent = engine.createComponent(TextureRegionComponent.class);
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        CircleColliderComponent circleColliderComponent = engine.createComponent(CircleColliderComponent.class);
        LinearMovementComponent floatingPositionComponent = engine.createComponent(LinearMovementComponent.class);
        entity.add(transformComponent);
        entity.add(textureRegionComponent);
        entity.add(animationComponent);
        entity.add(circleColliderComponent);
        entity.add(floatingPositionComponent);
        transformComponent.x = x;
        transformComponent.y = y;
        animationComponent.animation = bubbleResources.getAppearAnimation();
        circleColliderComponent.radius = 280 * size;
        floatingPositionComponent.speedX = MathUtils.random(-10, 10);
        floatingPositionComponent.speedY = MathUtils.random(-10, 10);
        textureRegionComponent.scaleX = 0.0f;
        textureRegionComponent.scaleY = 0.0f;
        engine.addEntity(entity);
        bubbleResources.getAppearSound().play();
        Tween tween = Tween.to(entity, EntityTweenAccessor.SCALE, 0.15f).target(size, size).ease(TweenEquations.easeOutQuad);
        tween.start(tweenManager);
        return entity;
    }
}
