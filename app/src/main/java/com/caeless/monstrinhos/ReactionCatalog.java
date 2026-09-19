package com.caeless.monstrinhos;

/** Short child-friendly feedback strings kept deterministic for accessibility and tests. */
final class ReactionCatalog {
    static String hit(int streak) {
        if (streak >= 8) return "Incrível! Super sequência!";
        if (streak >= 5) return "Uau! Você está voando!";
        if (streak >= 3) return "Muito bem! Continue assim!";
        return "Boa!";
    }

    static String miss(int misses) {
        if (misses >= 2) return "Quase! Olhe com calma e tente de novo.";
        return "Ops! Tente mais uma vez.";
    }

    static String levelComplete(int stars, boolean boss) {
        String prefix = boss ? "Chefe vencido! " : "Fase concluída! ";
        if (stars >= 3) return prefix + "Três estrelas! Perfeito!";
        if (stars == 2) return prefix + "Duas estrelas! Muito bem!";
        return prefix + "Uma estrela! Você conseguiu!";
    }

    static String challengeComplete(int score) {
        if (score >= 10) return "Desafio perfeito! Dez pontos!";
        if (score >= 6) return "Ótimo desafio! " + score + " pontos!";
        return "Desafio encerrado. " + score + " pontos. Tente superar sua marca!";
    }

    private ReactionCatalog() {}
}
