package org.mofr.bublz.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import org.mofr.bublz.components.AnimationComponent;
import org.mofr.bublz.components.TextureRegionComponent;

public class AnimationSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(AnimationComponent.class, TextureRegionComponent.class).get());
    }

    @Override
    public void update (float deltaTime) {
        for (Entity entity : entities) {
            AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
            TextureRegionComponent textureRegionComponent = TextureRegionComponent.mapper.get(entity);

            textureRegionComponent.textureRegion = animationComponent.animation.getKeyFrame(animationComponent.time);
            animationComponent.time += deltaTime;
        }
    }
}
