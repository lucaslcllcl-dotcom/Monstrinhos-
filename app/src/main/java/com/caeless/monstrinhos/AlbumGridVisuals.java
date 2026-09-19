package com.caeless.monstrinhos;

import android.graphics.Canvas;
import android.graphics.Paint;

/** Responsive visual grid for the 32-creature collection. Keeps drawing logic out of MonstrinhosView. */
final class AlbumGridVisuals {
    static void draw(Canvas canvas, Paint paint, float width, float height, int page, long ownedMask) {
        final int start = Math.max(0, Math.min(1, page)) * 16;
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
            boolean rare = index == 7 || index == 15 || index == 23 || index == 31;
            boolean favorite = owned && index == 0;
            AlbumVisuals.drawCard(canvas, paint, x, y, radius, index, owned, rare, favorite);
        }
    }

    static String accessibilitySummary(int page, long ownedMask) {
        int start = Math.max(0, Math.min(1, page)) * 16;
        int owned = 0;
        int rareOwned = 0;
        for (int i = 0; i < 16; i++) {
            int index = start + i;
            if ((ownedMask & (1L << index)) != 0L) {
                owned++;
                if (index == 7 || index == 15 || index == 23 || index == 31) rareOwned++;
            }
        }
        return "Página " + (page + 1) + " do álbum. " + owned + " de 16 monstrinhos descobertos nesta página. " + rareOwned + " raros descobertos.";
    }

    private AlbumGridVisuals() {}
}
