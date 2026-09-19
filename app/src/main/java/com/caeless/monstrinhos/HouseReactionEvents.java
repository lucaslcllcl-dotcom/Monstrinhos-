package com.caeless.monstrinhos;

import android.content.Context;
import android.view.View;

/** Bridges casinha actions to the shared timed/accessibility reaction system. */
public final class HouseReactionEvents {
    private final ReactionController reactions;

    public HouseReactionEvents(View host) {
        reactions = new ReactionController(host);
    }

    public void onCare(GameState state) {
        if (state == null) return;
        reactions.show("Carinho recebido! Seu monstrinho ficou feliz! 💖");
    }

    public void onEvolution(GameState state) {
        if (state == null) return;
        reactions.show("Evolução concluída! A casinha ficou ainda mais especial! ✨");
    }

    public void onDecorChanged(GameState state) {
        if (state == null) return;
        reactions.show("Novo visual escolhido para a casinha! 🎨");
    }

    public void onDecorUnavailable() {
        reactions.show("Continue brincando para liberar mais decorações! ⭐");
    }

    public void draw(android.graphics.Canvas canvas, android.graphics.Paint paint,
                     float width, float height, float textScale) {
        reactions.draw(canvas, paint, width, height, textScale);
    }

    public void clear() {
        reactions.clear();
    }
}
