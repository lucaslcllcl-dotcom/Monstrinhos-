package com.caeless.monstrinhos;

import android.content.Context;

/** Read-only presentation model for album milestone rewards. */
final class CollectionRewardStatus {
    final int owned;
    final int claimedTier;
    final int earnedTier;
    final int nextMilestone;
    final boolean claimAvailable;
    final boolean complete;
    final String accessibilityLabel;

    private CollectionRewardStatus(int owned, int claimedTier, int earnedTier,
                                   int nextMilestone, boolean claimAvailable,
                                   boolean complete, String accessibilityLabel) {
        this.owned = owned;
        this.claimedTier = claimedTier;
        this.earnedTier = earnedTier;
        this.nextMilestone = nextMilestone;
        this.claimAvailable = claimAvailable;
        this.complete = complete;
        this.accessibilityLabel = accessibilityLabel;
    }

    static CollectionRewardStatus snapshot(GameState state, Context context) {
        int owned = Math.max(0, Math.min(32, state.collectionCount()));
        int claimed = CollectionMilestoneRewards.claimedTier(context);
        int earned = CollectionMilestoneRewards.tierForCount(owned);
        boolean available = claimed < earned;
        int next = CollectionMilestoneRewards.nextMilestone(context);
        boolean complete = claimed >= 5;
        String label;
        if (available) {
            label = "Álbum com " + owned + " de 32 monstrinhos. "
                    + CollectionMilestoneRewards.rewardDescription(state, context)
                    + " Toque em resgatar para receber.";
        } else if (complete) {
            label = "Álbum completo. Todas as cinco recompensas da coleção foram recebidas.";
        } else {
            label = "Álbum com " + owned + " de 32 monstrinhos. Próxima recompensa com "
                    + next + " monstrinhos.";
        }
        return new CollectionRewardStatus(owned, claimed, earned, next, available, complete, label);
    }

    private CollectionRewardStatus() { this(0,0,0,4,false,false,""); }
}
