package com.caeless.monstrinhos;

/** Deterministic, offline rewards for collection milestones. No billing or ads. */
final class CollectionMilestoneRewards {
    private static final int[] MILESTONES = {4, 8, 16, 24, 32};
    private static final int[] COINS = {20, 35, 60, 90, 150};
    private static final int[] EGGS = {0, 1, 1, 2, 3};

    static int tierForCount(int count) {
        int tier = 0;
        for (int milestone : MILESTONES) if (count >= milestone) tier++;
        return tier;
    }

    static int nextMilestone(int claimedTier) {
        return claimedTier >= MILESTONES.length ? 0 : MILESTONES[Math.max(0, claimedTier)];
    }

    static boolean canClaim(GameState state) {
        return state.collectionRewardTier < tierForCount(state.collectionCount());
    }

    static boolean claimNext(GameState state, android.content.Context context) {
        if (!canClaim(state)) return false;
        int tier = state.collectionRewardTier;
        state.coins += COINS[tier];
        state.eggs += EGGS[tier];
        state.collectionRewardTier++;
        state.save(context);
        return true;
    }

    static String rewardDescription(GameState state) {
        if (!canClaim(state)) {
            int next = nextMilestone(state.collectionRewardTier);
            return next == 0 ? "Coleção completa. Todas as recompensas foram recebidas."
                    : "Próxima recompensa ao encontrar " + next + " monstrinhos.";
        }
        int tier = state.collectionRewardTier;
        String eggs = EGGS[tier] > 0 ? " e " + EGGS[tier] + (EGGS[tier] == 1 ? " ovo" : " ovos") : "";
        return "Recompensa disponível: " + COINS[tier] + " moedas" + eggs + ".";
    }

    private CollectionMilestoneRewards() {}
}
