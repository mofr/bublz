package org.mofr.bublz.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import org.mofr.bublz.components.BalloonComponent;
import org.mofr.bublz.components.TransformComponent;

public class BalloonMovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, BalloonComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            TransformComponent transformComponent = TransformComponent.mapper.get(entity);
            BalloonComponent balloonComponent = BalloonComponent.mapper.get(entity);
        }
    }
}
