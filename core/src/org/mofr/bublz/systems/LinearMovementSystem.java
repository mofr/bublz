package org.mofr.bublz.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import org.mofr.bublz.components.LinearMovementComponent;
import org.mofr.bublz.components.TransformComponent;

public class LinearMovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(LinearMovementComponent.class, TransformComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            TransformComponent transformComponent = TransformComponent.mapper.get(entity);
            LinearMovementComponent floatingPositionComponent = LinearMovementComponent.mapper.get(entity);

            transformComponent.x += floatingPositionComponent.speedX * deltaTime;
            transformComponent.y += floatingPositionComponent.speedY * deltaTime;
        }
    }
}
