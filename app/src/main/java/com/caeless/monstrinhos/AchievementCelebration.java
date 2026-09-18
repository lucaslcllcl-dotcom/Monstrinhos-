package com.caeless.monstrinhos;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Tracks newly unlocked achievements so the UI can celebrate them once.
 * This deliberately lives beside, not inside, the save schema: old saves stay compatible.
 */
final class AchievementCelebration {
    private static final String PREFS = "monstrinhos_achievement_ui_v1";
    private static final String KEY_SEEN_MASK = "seen_mask";

    static final class Event {
        final int index;
        final AchievementCatalog.Card card;

        Event(int index, AchievementCatalog.Card card) {
            this.index = index;
            this.card = card;
        }
    }

    private AchievementCelebration() {}

    static Event next(Context context) {
        AchievementCatalog.Card[] cards = AchievementCatalog.snapshot(context);
        int unlockedMask = 0;
        for (int i = 0; i < cards.length && i < 31; i++) {
            if (cards[i].unlocked) unlockedMask |= (1 << i);
        }

        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        int seenMask = prefs.getInt(KEY_SEEN_MASK, 0);
        int pending = unlockedMask & ~seenMask;
        if (pending == 0) return null;

        int index = Integer.numberOfTrailingZeros(pending);
        return new Event(index, cards[index]);
    }

    static void markSeen(Context context, Event event) {
        if (event == null || event.index < 0 || event.index >= 31) return;
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        int seenMask = prefs.getInt(KEY_SEEN_MASK, 0);
        prefs.edit().putInt(KEY_SEEN_MASK, seenMask | (1 << event.index)).apply();
    }

    static String accessibilityAnnouncement(Event event) {
        if (event == null) return "";
        return "Conquista desbloqueada: " + event.card.title + ". " + event.card.description;
    }
}
