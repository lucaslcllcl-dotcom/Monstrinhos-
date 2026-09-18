package com.caeless.monstrinhos;

import android.content.Context;

/** Stable presentation model for the achievements screen. */
final class AchievementCatalog {
    static final class Card {
        final String title;
        final String description;
        final int current;
        final int target;
        final int percent;
        final boolean unlocked;

        Card(String title, String description, AchievementProgress progress) {
            this.title = title;
            this.description = description;
            this.current = progress.current;
            this.target = progress.target;
            this.percent = progress.percent();
            this.unlocked = progress.unlocked;
        }

        String accessibilityLabel() {
            if (unlocked) return title + ". Conquista desbloqueada. " + description;
            return title + ". " + description + ". Progresso: " + current + " de " + target + ", " + percent + " por cento.";
        }
    }

    private static final String[] DESCRIPTIONS = {
            "Complete sua primeira aventura.",
            "Termine uma fase sem errar.",
            "Supere os grandes chefes dos mundos.",
            "Descubra novos monstrinhos para o álbum.",
            "Aumente a amizade cuidando dos seus amigos.",
            "Faça a casinha crescer e ficar mais especial.",
            "Junte estrelas completando as fases com capricho.",
            "Domine as principais aventuras de Monstrinhos."
    };

    private AchievementCatalog() {}

    static Card[] snapshot(Context context) {
        AchievementProgress[] progress = AchievementProgress.snapshot(context);
        Card[] cards = new Card[progress.length];
        for (int i = 0; i < progress.length; i++) {
            String description = i < DESCRIPTIONS.length ? DESCRIPTIONS[i] : "Continue explorando para avançar.";
            cards[i] = new Card(progress[i].name, description, progress[i]);
        }
        return cards;
    }
}
