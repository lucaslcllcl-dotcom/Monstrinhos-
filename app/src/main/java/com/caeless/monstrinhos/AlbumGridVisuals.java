package com.caeless.monstrinhos;

import android.graphics.Canvas;
import android.graphics.Paint;

/** Responsive visual grid for the 32-creature collection. Keeps drawing and hit-testing geometry consistent. */
final class AlbumGridVisuals {
    static void draw(Canvas canvas, Paint paint, float width, float height, int page, long ownedMask) {
        drawPage(canvas, paint, width, height, page, ownedMask, -1);
    }

    /** Draws one 4x4 album page and honors the persisted favorite monster when available. */
    static void drawPage(Canvas canvas, Paint paint, float width, float height, int page, long ownedMask, int favoriteMonster) {
        if (canvas == null || paint == null || width <= 0f || height <= 0f) return;
        final int start = safePage(page) * 16;
        final float left = width * .055f;
        final float right = width * .945f;
        final float top = height * .175f;
        final float bottom = height * .775f;
        final float cellW = (right - left) / 4f;
        final float cellH = (bottom - top) / 4f;
        final float radius = Math.min(cellW, cellH) * .43f;
        for (int i = 0; i < 16; i++) {
            int index = start + i;
            int row = i / 4;
            int col = i % 4;
            float x = left + cellW * (col + .5f);
            float y = top + cellH * (row + .5f);
            boolean owned = (ownedMask & (1L << index)) != 0L;
            boolean rare = isRare(index);
            boolean favorite = owned && index == favoriteMonster;
            AlbumVisuals.drawCard(canvas, paint, x, y, radius, index, owned, rare, favorite);
        }
    }

    /** Returns the zero-based monster index under a touch, or -1 outside the 4x4 card grid. */
    static int hitTest(float width, float height, int page, float touchX, float touchY) {
        if (width <= 0f || height <= 0f || Float.isNaN(touchX) || Float.isNaN(touchY)) return -1;
        final float left = width * .055f;
        final float right = width * .945f;
        final float top = height * .175f;
        final float bottom = height * .775f;
        if (touchX < left || touchX >= right || touchY < top || touchY >= bottom) return -1;
        final float cellW = (right - left) / 4f;
        final float cellH = (bottom - top) / 4f;
        int col = Math.min(3, Math.max(0, (int)((touchX - left) / cellW)));
        int row = Math.min(3, Math.max(0, (int)((touchY - top) / cellH)));
        return safePage(page) * 16 + row * 4 + col;
    }

    static String accessibilitySummary(int page, long ownedMask) {
        int safePage = safePage(page);
        int start = safePage * 16;
        int owned = 0;
        int rareOwned = 0;
        for (int i = 0; i < 16; i++) {
            int index = start + i;
            if ((ownedMask & (1L << index)) != 0L) {
                owned++;
                if (isRare(index)) rareOwned++;
            }
        }
        return "Página " + (safePage + 1) + " do álbum. " + owned + " de 16 monstrinhos descobertos nesta página. " + rareOwned + " raros descobertos.";
    }

    private static int safePage(int page) { return Math.max(0, Math.min(1, page)); }
    private static boolean isRare(int index) { return index >= 24 && index < 32; }
    private AlbumGridVisuals() {}
}
