package org.mofr.bublz.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import org.mofr.bublz.components.LimitedLifetimeComponent;

public class LimitedLifetimeSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(LimitedLifetimeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            LimitedLifetimeComponent limitedLifetimeComponent = LimitedLifetimeComponent.mapper.get(entity);

            limitedLifetimeComponent.lifetime -= deltaTime;
            if (limitedLifetimeComponent.lifetime <= 0) {
                getEngine().removeEntity(entity);
            }
        }
    }
}
