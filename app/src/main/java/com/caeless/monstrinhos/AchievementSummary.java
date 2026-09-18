package com.caeless.monstrinhos;

import android.content.Context;

/** Presentation-ready summary of achievement progress for UI and accessibility. */
final class AchievementSummary {
    private AchievementSummary() {}

    static int unlockedCount(Context context) {
        int count = 0;
        for (AchievementProgress item : AchievementProgress.snapshot(context)) {
            if (item.unlocked) count++;
        }
        return count;
    }

    static int overallPercent(Context context) {
        AchievementProgress[] items = AchievementProgress.snapshot(context);
        if (items.length == 0) return 0;
        int total = 0;
        for (AchievementProgress item : items) total += item.percent();
        return Math.min(100, total / items.length);
    }

    static String accessibilityDescription(Context context) {
        AchievementProgress[] items = AchievementProgress.snapshot(context);
        StringBuilder text = new StringBuilder();
        text.append("Conquistas: ").append(unlockedCount(context)).append(" de ")
                .append(items.length).append(" desbloqueadas. ");
        for (int i = 0; i < items.length; i++) {
            AchievementProgress item = items[i];
            text.append(item.name).append(": ");
            if (item.unlocked) {
                text.append("concluída");
            } else {
                text.append(item.current).append(" de ").append(item.target)
                        .append(", ").append(item.percent()).append(" por cento");
            }
            if (i + 1 < items.length) text.append(". ");
        }
        return text.toString();
    }
}
