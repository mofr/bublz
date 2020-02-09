package org.mofr.bublz;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import org.mofr.bublz.components.ClickComponent;
import org.mofr.bublz.factories.BalloonFactory;
import org.mofr.bublz.factories.BubbleFactory;
import org.mofr.bublz.factories.SparkleFactory;
import org.mofr.bublz.levels.Level;
import org.mofr.bublz.levels.Level1;
import org.mofr.bublz.levels.Level2;
import org.mofr.bublz.systems.*;

public class Game extends ApplicationAdapter implements InputProcessor {
	private OrthographicCamera camera;
	private PooledEngine engine;
	private Assets assets;
	private Level currentLevel = null;
	private Level level_1;
	private Level level_2;
	private Background background;
	private TweenManager tweenManager;

	@Override
	public void create () {
		engine = new PooledEngine();
		camera = new OrthographicCamera();
		assets = new Assets();
		tweenManager = new TweenManager();
		Tween.setCombinedAttributesLimit(4);
		Tween.registerAccessor(Entity.class, new EntityTweenAccessor());
		SparkleFactory sparkleFactory = new SparkleFactory(engine, assets.sparkle);
		BalloonFactory balloonFactory = new BalloonFactory(engine, assets.balloon);
		BubbleFactory bubbleFactory = new BubbleFactory(engine, assets.bubble, tweenManager);
		Gdx.input.setInputProcessor(this);
		CollisionSystem collisionSystem = new CollisionSystem(engine, sparkleFactory, assets);
		BubbleEmitterSystem bubbleEmitterSystem = new BubbleEmitterSystem(bubbleFactory);
		BalloonEmitterSystem balloonEmitterSystem = new BalloonEmitterSystem(balloonFactory);
		AnimationSystem animationSystem = new AnimationSystem();
		LimitedLifetimeSystem limitedLifetimeSystem = new LimitedLifetimeSystem();
		BalloonMovementSystem balloonMovementSystem = new BalloonMovementSystem();
		LinearMovementSystem linearMovementSystem = new LinearMovementSystem();
		BackgroundRenderSystem backgroundRenderSystem = new BackgroundRenderSystem(camera);
		RenderSystem renderSystem = new RenderSystem(camera);
		engine.addSystem(collisionSystem);
		engine.addSystem(bubbleEmitterSystem);
		engine.addSystem(balloonEmitterSystem);
		engine.addSystem(animationSystem);
		engine.addSystem(limitedLifetimeSystem);
		engine.addSystem(balloonMovementSystem);
		engine.addSystem(linearMovementSystem);
		engine.addSystem(backgroundRenderSystem);
		engine.addSystem(renderSystem);

		background = new Background(engine, tweenManager);

		level_1 = new Level1(engine, assets);
		level_2 = new Level2(engine, assets);
		startLevel(level_1);
	}

	private void startLevel(Level level) {
		if (currentLevel == level) {
			return;
		}
		if (currentLevel != null) {
			currentLevel.stop();
		}
		level.start();
		currentLevel = level;
		background.crossfadeTo(level.getBackground(), level.getBackgroundColor());
		// TODO cross fade music
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		camera.setToOrtho(false, width, height);
	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(Gdx.graphics.getRawDeltaTime());
		tweenManager.update(Gdx.graphics.getRawDeltaTime());
	}
	
	@Override
	public void dispose () {
		assets.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE) {
			Gdx.app.exit();
		}
		else if (keycode == Input.Keys.NUM_1) {
			startLevel(level_1);
		}
		else if (keycode == Input.Keys.NUM_2) {
			startLevel(level_2);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 pos = camera.unproject(new Vector3(screenX, screenY, 0));
		Entity entity = engine.createEntity();
		ClickComponent clickComponent = engine.createComponent(ClickComponent.class);
		clickComponent.x = pos.x;
		clickComponent.y = pos.y;
		entity.add(clickComponent);
		engine.addEntity(entity);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
