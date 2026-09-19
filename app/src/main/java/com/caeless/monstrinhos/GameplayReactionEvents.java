package com.caeless.monstrinhos;

import android.view.View;

/**
 * Maps gameplay outcomes to the transient reaction system in one place.
 * Keeps child-facing copy, timing and accessibility behavior consistent.
 */
final class GameplayReactionEvents {
    private final ReactionController reactions;
    private int streak;

    GameplayReactionEvents(ReactionController reactions) {
        this.reactions = reactions == null ? new ReactionController() : reactions;
    }

    void resetRound() {
        streak = 0;
    }

    void onHit(View host, boolean announce) {
        streak++;
        reactions.show(host, ReactionCatalog.hit(streak), announce);
    }

    void onMiss(View host, int misses, boolean announce) {
        streak = 0;
        reactions.show(host, ReactionCatalog.miss(Math.max(0, misses)), announce);
    }

    void onLevelComplete(View host, int stars, boolean boss, boolean announce) {
        streak = 0;
        int safeStars = Math.max(1, Math.min(3, stars));
        reactions.show(host, ReactionCatalog.levelComplete(safeStars, boss), 2200L, announce);
    }

    void onChallengeComplete(View host, int score, boolean announce) {
        streak = 0;
        reactions.show(host, ReactionCatalog.challengeComplete(Math.max(0, score)), 2200L, announce);
    }

    void showMessage(View host, String message, boolean announce) {
        reactions.show(host, message, announce);
    }

    int streak() {
        return streak;
    }
}
