package com.caeless.monstrinhos;

import android.content.Context;
import android.content.SharedPreferences;

/** Deterministic, offline rewards for collection milestones. No billing or ads. */
final class CollectionMilestoneRewards {
    private static final String PREFS = "monstrinhos_save_v1";
    private static final String KEY_TIER = "collection_reward_tier";
    private static final int[] MILESTONES = {4, 8, 16, 24, 32};
    private static final int[] COINS = {20, 35, 60, 90, 150};
    private static final int[] EGGS = {0, 1, 1, 2, 3};

    static int tierForCount(int count) {
        int tier = 0;
        for (int milestone : MILESTONES) if (count >= milestone) tier++;
        return tier;
    }

    static int claimedTier(Context context) {
        return Math.max(0, Math.min(MILESTONES.length,
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getInt(KEY_TIER, 0)));
    }

    static int nextMilestone(Context context) {
        int tier = claimedTier(context);
        return tier >= MILESTONES.length ? 0 : MILESTONES[tier];
    }

    static boolean canClaim(GameState state, Context context) {
        return claimedTier(context) < tierForCount(state.collectionCount());
    }

    static boolean claimNext(GameState state, Context context) {
        int tier = claimedTier(context);
        if (tier >= tierForCount(state.collectionCount()) || tier >= MILESTONES.length) return false;
        state.coins += COINS[tier];
        state.eggs += EGGS[tier];
        state.save(context);
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putInt(KEY_TIER, tier + 1).apply();
        return true;
    }

    static String rewardDescription(GameState state, Context context) {
        int tier = claimedTier(context);
        if (!canClaim(state, context)) {
            int next = nextMilestone(context);
            return next == 0 ? "Coleção completa. Todas as recompensas foram recebidas."
                    : "Próxima recompensa ao encontrar " + next + " monstrinhos.";
        }
        String eggs = EGGS[tier] > 0 ? " e " + EGGS[tier] + (EGGS[tier] == 1 ? " ovo" : " ovos") : "";
        return "Recompensa disponível: " + COINS[tier] + " moedas" + eggs + ".";
    }

    private CollectionMilestoneRewards() {}
}
