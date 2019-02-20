package org.mofr.bublz.resources;

import com.badlogic.gdx.graphics.Texture;

public class BackgroundResources {
    private Texture background1;
    private Texture background2;

    public BackgroundResources() {
        background1 = new Texture("sky/sky_color.png");
        background2 = new Texture("underwater_bg.png");
        background1.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
        background2.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
    }

    public Texture getBackground1() {
        return background1;
    }

    public Texture getBackground2() {
        return background2;
    }
}
