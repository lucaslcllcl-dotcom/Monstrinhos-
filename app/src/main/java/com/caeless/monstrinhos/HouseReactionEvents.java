package com.caeless.monstrinhos;

import android.content.Context;
import android.view.View;

/** Bridges casinha actions to the shared timed/accessibility reaction system. */
public final class HouseReactionEvents {
    private final ReactionController reactions;
    private final View host;

    public HouseReactionEvents(View host) {
        this.host = host;
        this.reactions = new ReactionController();
    }

    /** Performs care and reports the actual persisted result. */
    public boolean care(Context context, GameState state) {
        if (context == null || state == null) return false;
        int beforeLevel = state.houseLevel;
        boolean ok = state.careAtHouse(context);
        if (!ok) {
            reactions.show(host, "Vamos tentar o carinho outra vez! 💖", state.accessibilityAnnouncements == 1);
            return false;
        }
        if (state.houseLevel > beforeLevel) {
            reactions.show(host, "Carinho especial! A casinha evoluiu! ✨", state.accessibilityAnnouncements == 1);
        } else {
            reactions.show(host, "Carinho recebido! Seu monstrinho ficou feliz! 💖", state.accessibilityAnnouncements == 1);
        }
        return true;
    }

    /** Attempts a paid house evolution and never announces success on failure. */
    public boolean evolve(Context context, GameState state) {
        if (context == null || state == null) return false;
        if (state.houseLevel >= 5) {
            reactions.show(host, "Sua casinha já chegou ao nível máximo! 👑", state.accessibilityAnnouncements == 1);
            return false;
        }
        boolean ok = state.upgradeHouse(context);
        reactions.show(
            host,
            ok ? "Evolução concluída! A casinha ficou ainda mais especial! ✨"
               : "Junte mais moedas para evoluir a casinha! 🪙",
            state.accessibilityAnnouncements == 1
        );
        return ok;
    }

    /** Selects or buys a decor and reports whether the persisted action succeeded. */
    public boolean selectOrBuyDecor(Context context, GameState state, int decor) {
        if (context == null || state == null || decor < 0 || decor > 3) return false;
        boolean alreadyOwned = state.hasDecor(decor);
        boolean ok = state.selectOrBuyDecor(context, decor);
        if (ok) {
            reactions.show(
                host,
                alreadyOwned ? "Tema da casinha escolhido! 🎨" : "Nova decoração liberada e escolhida! 🎉",
                state.accessibilityAnnouncements == 1
            );
        } else {
            reactions.show(host, "Junte mais moedas para liberar essa decoração! 🪙", state.accessibilityAnnouncements == 1);
        }
        return ok;
    }

    public void draw(android.graphics.Canvas canvas, android.graphics.Paint paint,
                     float width, float height, float textScale) {
        reactions.draw(canvas, paint, width, height, textScale);
    }

    public void clear() {
        reactions.clear(host);
    }
}
