package com.nilunder.bdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.GameObject;

import javax.vecmath.Vector2f;

public class TFText extends GameObject {

    // Set on creation
    public BitmapFont baseFont;

    public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public FreeTypeFontGenerator generator;

    public String text;
    public int capacity;

    // Set on font generation
    private BitmapFont bitmapFont;
    private int size;

    public int size() {
        return size;
    }

    public void draw(SpriteBatch batch) {
        if (bitmapFont == null) {
            bitmapFont = generateFont();
        }

        Vector2f screenPos = scene.camera.screenPosition(position());
        Vector2f size = Bdx.display.size();
        float offset = bitmapFont.getCapHeight();

        bitmapFont.setColor(materials.size() == 0 ? Color.WHITE : materials.get(0).color());
        bitmapFont.draw(batch, text != null ? text : "", screenPos.x*size.x, screenPos.y*size.y + offset);
    }

    private BitmapFont generateFont() {
        float size;
        if (this.children.size() == 2) {
            size = Math.abs(scene.camera.screenPosition(children.get(0).position()).y
                    - scene.camera.screenPosition(children.get(1).position()).y) * Bdx.display.size().y;

            parameter.size = Math.round(size / (baseFont.getCapHeight() / 72));
            parameter.size = parameter.size < 0 ? 12 : parameter.size;
        } else {
            parameter.size = 72;
        }

        this.size = parameter.size;

        return generator.generateFont(parameter);
    }
}
