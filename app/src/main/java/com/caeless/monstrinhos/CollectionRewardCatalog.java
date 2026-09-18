package com.caeless.monstrinhos;

import android.content.Context;

/** UI/accessibility-ready catalog for the five offline album milestone rewards. */
final class CollectionRewardCatalog {
    private static final int[] MILESTONES = {4, 8, 16, 24, 32};
    private static final int[] COINS = {80, 140, 240, 380, 600};
    private static final int[] EGGS = {0, 1, 1, 2, 3};

    static final class Card {
        final int tier;
        final int milestone;
        final int coins;
        final int eggs;
        final boolean earned;
        final boolean claimed;
        final boolean claimable;
        final int progress;
        final String accessibilityLabel;

        Card(int tier, int milestone, int coins, int eggs, boolean earned,
             boolean claimed, boolean claimable, int progress, String accessibilityLabel) {
            this.tier = tier;
            this.milestone = milestone;
            this.coins = coins;
            this.eggs = eggs;
            this.earned = earned;
            this.claimed = claimed;
            this.claimable = claimable;
            this.progress = progress;
            this.accessibilityLabel = accessibilityLabel;
        }
    }

    static Card[] snapshot(GameState state, Context context) {
        int owned = Math.max(0, Math.min(32, state.collectionCount()));
        int claimedTier = Math.max(0, Math.min(5, CollectionMilestoneRewards.claimedTier(context)));
        Card[] cards = new Card[MILESTONES.length];
        for (int i = 0; i < cards.length; i++) {
            int tier = i + 1;
            int milestone = MILESTONES[i];
            boolean earned = owned >= milestone;
            boolean claimed = claimedTier >= tier;
            boolean claimable = earned && !claimed && claimedTier == i;
            int progress = Math.min(owned, milestone);
            StringBuilder label = new StringBuilder();
            label.append("Recompensa ").append(tier).append(" de 5. Marco de ")
                    .append(milestone).append(" monstrinhos. Prêmio: ")
                    .append(COINS[i]).append(" moedas");
            if (EGGS[i] > 0) label.append(" e ").append(EGGS[i]).append(EGGS[i] == 1 ? " ovo" : " ovos");
            label.append(". ");
            if (claimed) label.append("Já recebida.");
            else if (claimable) label.append("Disponível para resgate.");
            else if (earned) label.append("Aguardando o resgate da recompensa anterior.");
            else label.append("Progresso ").append(progress).append(" de ").append(milestone).append(".");
            cards[i] = new Card(tier, milestone, COINS[i], EGGS[i], earned, claimed,
                    claimable, progress, label.toString());
        }
        return cards;
    }

    static String accessibilitySummary(GameState state, Context context) {
        Card[] cards = snapshot(state, context);
        StringBuilder out = new StringBuilder("Recompensas do álbum. ");
        for (Card card : cards) out.append(card.accessibilityLabel).append(' ');
        return out.toString().trim();
    }

    private CollectionRewardCatalog() {}
}
