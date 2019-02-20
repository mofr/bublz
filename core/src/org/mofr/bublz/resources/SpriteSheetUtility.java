package org.mofr.bublz.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheetUtility {
    static TextureRegion[] splitSpriteSheet(Texture texture, int columns, int rows) {
        TextureRegion[][] tmp = TextureRegion.split(
                texture,
                texture.getWidth() / columns,
                texture.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[columns * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        return frames;
    }
}
