package com.caeless.monstrinhos;

/** Deterministic achievement evaluator. Achievement bits are append-only so old saves remain compatible. */
final class AchievementSystem {
    static final int COUNT = 8;
    static final String[] NAMES = {
        "Primeiros Passos", "Estrela Perfeita", "Caçador de Chefes", "Colecionador",
        "Amigo dos Monstrinhos", "Lar Doce Lar", "Aventureiro", "Mestre Monstrinho"
    };

    private AchievementSystem() {}

    static int evaluate(GameState s) {
        long before = s.achievementMask;
        unlock(s, 0, s.totalPlays >= 1);
        unlock(s, 1, s.sessionPerfect >= 1 || s.perfectBosses >= 1);
        unlock(s, 2, s.bossWins >= 3);
        unlock(s, 3, s.collectionCount() >= 8);
        unlock(s, 4, s.friendshipLevel >= 3);
        unlock(s, 5, s.houseLevel >= 3);
        unlock(s, 6, s.stars >= 30);
        unlock(s, 7, s.bossWins >= 6 && s.collectionCount() >= 24 && s.stars >= 60);
        return Long.bitCount(s.achievementMask) - Long.bitCount(before);
    }

    static boolean unlocked(GameState s, int index) {
        return index >= 0 && index < COUNT && (s.achievementMask & (1L << index)) != 0;
    }

    static int progress(GameState s, int index) {
        switch (index) {
            case 0: return pct(s.totalPlays, 1);
            case 1: return pct(s.sessionPerfect + s.perfectBosses, 1);
            case 2: return pct(s.bossWins, 3);
            case 3: return pct(s.collectionCount(), 8);
            case 4: return pct(s.friendshipLevel, 3);
            case 5: return pct(s.houseLevel, 3);
            case 6: return pct(s.stars, 30);
            case 7:
                int boss = pct(s.bossWins, 6), collection = pct(s.collectionCount(), 24), stars = pct(s.stars, 60);
                return Math.min(boss, Math.min(collection, stars));
            default: return 0;
        }
    }

    private static void unlock(GameState s, int index, boolean condition) {
        if (condition) s.achievementMask |= 1L << index;
    }

    private static int pct(int value, int target) {
        return Math.max(0, Math.min(100, value * 100 / Math.max(1, target)));
    }
}
