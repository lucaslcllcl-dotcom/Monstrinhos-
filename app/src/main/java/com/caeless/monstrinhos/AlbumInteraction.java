package com.caeless.monstrinhos;

import android.content.Context;

/** Centralizes album touch behavior so drawing, persistence and accessibility stay consistent. */
final class AlbumInteraction {
    static String selectFavorite(Context context, GameState state, float width, float height,
                                 int page, float touchX, float touchY) {
        int index = AlbumGridVisuals.hitTest(width, height, page, touchX, touchY);
        if (index < 0) return "";
        if ((state.ownedMask & (1L << index)) == 0L) {
            return "Monstrinho " + (index + 1) + " ainda não foi descoberto.";
        }
        if (state.favoriteMonster == index) {
            return "Monstrinho " + (index + 1) + " já é seu favorito.";
        }
        if (state.setFavorite(context, index)) {
            return "Monstrinho " + (index + 1) + " escolhido como favorito.";
        }
        return "Não foi possível escolher este monstrinho.";
    }

    private AlbumInteraction() {}
}
