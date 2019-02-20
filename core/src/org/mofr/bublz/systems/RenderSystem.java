package org.mofr.bublz.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.mofr.bublz.components.TextureRegionComponent;
import org.mofr.bublz.components.TransformComponent;
import org.mofr.bublz.components.ZComparator;

import java.util.Comparator;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Comparator<Entity> comparator;
    private Array<Entity> renderQueue = new Array<Entity>();

    public RenderSystem (OrthographicCamera camera) {
        batch = new SpriteBatch();
        this.camera = camera;
        comparator = new ZComparator();
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, TextureRegionComponent.class).get());
    }

    @Override
    public void update (float deltaTime) {
        updateRenderQueue();
        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        for (Entity entity : entities) {
            TransformComponent transformComponent = TransformComponent.mapper.get(entity);
            TextureRegionComponent textureRegionComponent = TextureRegionComponent.mapper.get(entity);

            TextureRegion textureRegion = textureRegionComponent.textureRegion;
            float w = textureRegion.getRegionWidth() * textureRegionComponent.scaleX;
            float h = textureRegion.getRegionHeight() * textureRegionComponent.scaleY;
            float x = transformComponent.x - w * textureRegionComponent.originX;
            float y = transformComponent.y - h * textureRegionComponent.originY;
            batch.draw(textureRegion, x, y, w, h);
        }

        batch.end();
    }

    private void updateRenderQueue() {
        renderQueue.clear();
        for (Entity entity : entities) {
            renderQueue.add(entity);
        }
        renderQueue.sort(comparator);
    }
}
