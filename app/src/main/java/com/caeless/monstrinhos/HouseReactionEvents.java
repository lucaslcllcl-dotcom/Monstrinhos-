package com.caeless.monstrinhos;

import android.content.Context;
import android.view.View;

/** Bridges casinha actions to the shared timed/accessibility reaction system. */
public final class HouseReactionEvents {
    private final ReactionController reactions;

    public HouseReactionEvents(View host) {
        reactions = new ReactionController(host);
    }

    /** Performs care and reports the actual persisted result. */
    public boolean care(Context context, GameState state) {
        if (context == null || state == null) return false;
        int beforeLevel = state.houseLevel;
        boolean ok = state.careAtHouse(context);
        if (!ok) {
            reactions.show("Vamos tentar o carinho outra vez! 💖");
            return false;
        }
        if (state.houseLevel > beforeLevel) {
            reactions.show("Carinho especial! A casinha evoluiu! ✨");
        } else {
            reactions.show("Carinho recebido! Seu monstrinho ficou feliz! 💖");
        }
        return true;
    }

    /** Attempts a paid house evolution and never announces success on failure. */
    public boolean evolve(Context context, GameState state) {
        if (context == null || state == null) return false;
        if (state.houseLevel >= 5) {
            reactions.show("Sua casinha já chegou ao nível máximo! 👑");
            return false;
        }
        boolean ok = state.upgradeHouse(context);
        reactions.show(ok
                ? "Evolução concluída! A casinha ficou ainda mais especial! ✨"
                : "Junte mais moedas para evoluir a casinha! 🪙");
        return ok;
    }

    /** Selects or buys a decor and reports whether the persisted action succeeded. */
    public boolean selectOrBuyDecor(Context context, GameState state, int decor) {
        if (context == null || state == null || decor < 0 || decor > 3) {
            reactions.show("Essa decoração não está disponível. ⭐");
            return false;
        }
        boolean alreadyOwned = state.hasDecor(decor);
        boolean ok = state.selectOrBuyDecor(context, decor);
        if (ok) {
            reactions.show(alreadyOwned
                    ? "Tema da casinha escolhido! 🎨"
                    : "Nova decoração liberada e escolhida! 🎉");
        } else {
            reactions.show("Junte mais moedas para liberar essa decoração! 🪙");
        }
        return ok;
    }

    public void draw(android.graphics.Canvas canvas, android.graphics.Paint paint,
                     float width, float height, float textScale) {
        reactions.draw(canvas, paint, width, height, textScale);
    }

    public void clear() {
        reactions.clear();
    }
}
