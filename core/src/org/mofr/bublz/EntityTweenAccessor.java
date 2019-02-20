package org.mofr.bublz;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.ashley.core.Entity;
import org.mofr.bublz.components.BackgroundComponent;
import org.mofr.bublz.components.TextureRegionComponent;

public class EntityTweenAccessor implements TweenAccessor<Entity> {
    public static final int BACKGROUND_COLOR = 1;
    public static final int SCALE = 2;

    @Override
    public int getValues(Entity entity, int tweenType, float[] returnValues) {
        int result = 0;
        switch(tweenType){
            case BACKGROUND_COLOR:
                if(BackgroundComponent.mapper.has(entity)){
                    BackgroundComponent backgroundComponent = BackgroundComponent.mapper.get(entity);
                    returnValues[0] = backgroundComponent.color.r;
                    returnValues[1] = backgroundComponent.color.g;
                    returnValues[2] = backgroundComponent.color.b;
                    returnValues[3] = backgroundComponent.color.a;
                    result = 4;
                }
                break;
            case SCALE:
                if(TextureRegionComponent.mapper.has(entity)){
                    TextureRegionComponent textureRegionComponent = TextureRegionComponent.mapper.get(entity);
                    returnValues[0] = textureRegionComponent.scaleX;
                    returnValues[1] = textureRegionComponent.scaleY;
                    result = 2;
                }
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public void setValues(Entity entity, int tweenType, float[] newValues) {
        switch(tweenType){
            case BACKGROUND_COLOR:
                if(BackgroundComponent.mapper.has(entity)){
                    BackgroundComponent backgroundComponent = BackgroundComponent.mapper.get(entity);
                    backgroundComponent.color.set(newValues[0], newValues[1], newValues[2], newValues[3]);
                }
                break;
            case SCALE:
                if(TextureRegionComponent.mapper.has(entity)){
                    TextureRegionComponent textureRegionComponent = TextureRegionComponent.mapper.get(entity);
                    textureRegionComponent.scaleX = newValues[0];
                    textureRegionComponent.scaleY = newValues[1];
                }
                break;
            default:
                break;
        }
    }
}
