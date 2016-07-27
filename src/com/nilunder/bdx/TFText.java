package com.nilunder.bdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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
    private GlyphLayout glyphLayout;

    public float screenHeight() {
        return font().getCapHeight();
    }

    private Float spaceHeight;
    public float spaceHeight() {
        if (spaceHeight == null) {
            if (children.size() == 2) {
                spaceHeight = children.get(0).position().minus(children.get(1).position()).length();
            } else {
                spaceHeight = scale().length();
            }
        }

        return spaceHeight;
    }

    public BitmapFont font() {
        if (bitmapFont == null) {
            bitmapFont = generateFont();
        }
        return bitmapFont;
    }

    public GlyphLayout glyphLayout() {
        return glyphLayout;
    }

    public void draw(SpriteBatch batch) {
        Vector2f screenPos = scene.camera.screenPosition(position());
        Vector2f size = Bdx.display.size();

        BitmapFont bitmapFont = font();
        float offset = bitmapFont.getCapHeight();

        bitmapFont.setColor(materials.size() == 0 ? Color.WHITE : materials.get(0).color());
        glyphLayout = bitmapFont.draw(batch, text != null ? text : "", screenPos.x*size.x, screenPos.y*size.y + offset);
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

        return generator.generateFont(parameter);
    }
}
