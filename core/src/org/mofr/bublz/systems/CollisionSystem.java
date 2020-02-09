package org.mofr.bublz.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import org.mofr.bublz.Assets;
import org.mofr.bublz.components.*;
import org.mofr.bublz.factories.SparkleFactory;

public class CollisionSystem extends EntitySystem {
    private ImmutableArray<Entity> colliderEntities;
    private ImmutableArray<Entity> clickEntities;
    private PooledEngine engine;
    private SparkleFactory sparkleFactory;
    private Assets assets;

    public CollisionSystem(PooledEngine engine, SparkleFactory sparkleFactory, Assets assets) {
        this.engine = engine;
        this.sparkleFactory = sparkleFactory;
        this.assets = assets;
    }

    @Override
    public void addedToEngine(Engine engine) {
        colliderEntities = engine.getEntitiesFor(Family.all(CircleColliderComponent.class).get());
        clickEntities = engine.getEntitiesFor(Family.all(ClickComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity clickEntity : clickEntities) {
            ClickComponent clickComponent = ClickComponent.mapper.get(clickEntity);
            boolean hit = false;

            // TODO iterate in z order
            for (Entity colliderEntity : colliderEntities) {
                CircleColliderComponent circleCollider = CircleColliderComponent.mapper.get(colliderEntity);
                TransformComponent transformComponent = TransformComponent.mapper.get(colliderEntity);
                AnimationComponent animationComponent = AnimationComponent.mapper.get(colliderEntity);

                float radius = circleCollider.radius;
                double dx = transformComponent.x - clickComponent.x;
                double dy = transformComponent.y - clickComponent.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance <= radius) {
                    colliderEntity.remove(CircleColliderComponent.class);
                    hit = true;
                    LimitedLifetimeComponent limitedLifetimeComponent = engine.createComponent(LimitedLifetimeComponent.class);
                    colliderEntity.add(limitedLifetimeComponent);
                    animationComponent.time = 0;

                    BalloonComponent balloonComponent = BalloonComponent.mapper.get(colliderEntity);
                    if (balloonComponent != null) {
                        animationComponent.animation = assets.balloon.getPopAnimation(balloonComponent.color);
                        limitedLifetimeComponent.lifetime = assets.balloon.getPopAnimation(balloonComponent.color).getAnimationDuration();
                        float volume = Math.max(transformComponent.z / 10, 0.3f);
                        assets.balloon.getPopSound().play(volume);  // TODO stereo sound
                    } else {
                        animationComponent.animation = assets.bubble.getSplashAnimation();
                        limitedLifetimeComponent.lifetime = assets.bubble.getSplashAnimation().getAnimationDuration();
                        assets.bubble.getSplashSound().play();
                    }
                    break;
                }
            }

            if (!hit) {
                sparkleFactory.create(clickComponent.x, clickComponent.y);
            }

            engine.removeEntity(clickEntity);
        }
    }
}
