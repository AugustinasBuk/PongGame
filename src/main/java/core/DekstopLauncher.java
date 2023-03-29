package core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DekstopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setIdleFPS(60);
        config.useVsync(true);
        config.setTitle("Pong Game");

        config.setWindowedMode(960,640);

        new Lwjgl3Application(new Boot(),config);
    }
}