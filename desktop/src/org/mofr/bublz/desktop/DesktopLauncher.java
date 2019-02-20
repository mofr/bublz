package org.mofr.bublz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.mofr.bublz.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
//		config.width = 800;
//		config.height = 600;
		config.fullscreen = true;
		config.samples = 8;
		config.useHDPI = true;
		new LwjglApplication(new Game(), config);
	}
}
