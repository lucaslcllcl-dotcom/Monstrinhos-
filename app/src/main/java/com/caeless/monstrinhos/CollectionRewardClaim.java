package com.caeless.monstrinhos;

import android.content.Context;

/** Coordinates one album milestone claim and returns UI/accessibility-ready feedback. */
final class CollectionRewardClaim {
    final boolean claimed;
    final int owned;
    final int claimedTier;
    final int coinsBefore;
    final int coinsAfter;
    final int eggsBefore;
    final int eggsAfter;
    final String message;

    private CollectionRewardClaim(boolean claimed, int owned, int claimedTier,
                                  int coinsBefore, int coinsAfter, int eggsBefore, int eggsAfter,
                                  String message) {
        this.claimed = claimed;
        this.owned = owned;
        this.claimedTier = claimedTier;
        this.coinsBefore = coinsBefore;
        this.coinsAfter = coinsAfter;
        this.eggsBefore = eggsBefore;
        this.eggsAfter = eggsAfter;
        this.message = message;
    }

    static CollectionRewardClaim claimNext(GameState state, Context context) {
        int owned = Math.max(0, Math.min(32, state.collectionCount()));
        int beforeTier = CollectionMilestoneRewards.claimedTier(context);
        int coinsBefore = state.coins;
        int eggsBefore = state.eggs;
        boolean claimed = CollectionMilestoneRewards.claimNext(state, context);
        int afterTier = CollectionMilestoneRewards.claimedTier(context);
        int coinsAfter = state.coins;
        int eggsAfter = state.eggs;

        String message;
        if (claimed) {
            int coinGain = Math.max(0, coinsAfter - coinsBefore);
            int eggGain = Math.max(0, eggsAfter - eggsBefore);
            message = "Recompensa da coleção recebida: " + coinGain + " moedas";
            if (eggGain > 0) message += " e " + eggGain + (eggGain == 1 ? " ovo" : " ovos");
            message += ". Marco " + afterTier + " de 5 concluído.";
        } else {
            CollectionRewardStatus status = CollectionRewardStatus.snapshot(state, context);
            message = status.complete
                    ? "Todas as recompensas da coleção já foram recebidas."
                    : status.accessibilityLabel;
        }
        return new CollectionRewardClaim(claimed, owned, Math.max(beforeTier, afterTier),
                coinsBefore, coinsAfter, eggsBefore, eggsAfter, message);
    }

    private CollectionRewardClaim() {
        this(false, 0, 0, 0, 0, 0, 0, "");
    }
}
