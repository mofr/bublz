package org.mofr.bublz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import org.mofr.bublz.resources.BackgroundResources;
import org.mofr.bublz.resources.BalloonResources;
import org.mofr.bublz.resources.BubbleResources;
import org.mofr.bublz.resources.SparkleResources;

public class Assets {
    public SparkleResources sparkle;
    public BalloonResources balloon;
    public BubbleResources bubble;
    public BackgroundResources background;
    public Music music1;

    public Assets() {
        sparkle = new SparkleResources();
        balloon = new BalloonResources();
        bubble = new BubbleResources();
        background = new BackgroundResources();
        music1 = Gdx.audio.newMusic(Gdx.files.internal("music/TRG_Banks_-_13_-_The_star_of_Bethlehem.mp3"));
        music1.setLooping(true);
    }

    public void dispose() {
        sparkle.dispose();
        balloon.dispose();
        bubble.dispose();
    }
}
