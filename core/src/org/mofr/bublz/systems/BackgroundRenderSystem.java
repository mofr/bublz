package org.mofr.bublz.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mofr.bublz.components.BackgroundComponent;

public class BackgroundRenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    public BackgroundRenderSystem(OrthographicCamera camera) {
        batch = new SpriteBatch();
        this.camera = camera;
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BackgroundComponent.class).get());
    }

    @Override
    public void update (float deltaTime) {
        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        for (Entity entity : entities) {
            BackgroundComponent backgroundComponent = BackgroundComponent.mapper.get(entity);

            batch.setColor(backgroundComponent.color);
            float textureWidth = backgroundComponent.texture.getWidth();
            float textureHeight = backgroundComponent.texture.getHeight();
            batch.draw(backgroundComponent.texture, 0, 0, width, height, width / textureWidth, height / textureHeight, 0.0f, 0.0f);
        }

        batch.end();
    }
}
