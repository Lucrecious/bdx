package com.nilunder.bdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.nio.ByteBuffer;

public class ScreenshotFactory {

    private static int counter = 1;
    public static void saveScreenshot(){
        try{
            FileHandle fh;
            do{
                fh = new FileHandle(Gdx.files.getLocalStoragePath() + "screenshot" + counter++ + ".png");
            }while (fh.exists());
            Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            PixmapIO.writePNG(fh, pixmap);
            pixmap.dispose();
        }catch (Exception e){
        }
    }

    private static Pixmap getScreenshot(int x, int y, int w, int h){
        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);

        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);

        return pixmap;
    }
}

