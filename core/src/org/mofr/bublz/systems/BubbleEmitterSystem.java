package org.mofr.bublz.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import org.mofr.bublz.components.BubbleEmitter;
import org.mofr.bublz.factories.BubbleFactory;

public class BubbleEmitterSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private BubbleFactory bubbleFactory;

    public BubbleEmitterSystem(BubbleFactory bubbleFactory) {
        this.bubbleFactory = bubbleFactory;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BubbleEmitter.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            BubbleEmitter bubbleEmitter = BubbleEmitter.mapper.get(entity);
            bubbleEmitter.cooldown -= deltaTime;
            if (bubbleEmitter.cooldown < 0) {
                int width = Gdx.graphics.getWidth();
                int height = Gdx.graphics.getHeight();
                float x = MathUtils.random(width * 0.1f, width * 0.9f);
                float y = MathUtils.random(height * 0.1f, height * 0.9f);
                float size = MathUtils.random(0.3f, 0.55f);
                bubbleFactory.create(x, y, size);
                bubbleEmitter.cooldown = MathUtils.random(bubbleEmitter.cooldownMin, bubbleEmitter.cooldownMax);
            }
        }
    }
}
