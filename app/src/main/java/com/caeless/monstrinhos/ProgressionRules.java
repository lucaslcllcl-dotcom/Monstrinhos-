package com.caeless.monstrinhos;

/** Centralized child-friendly progression/balance rules for the reconstructed base. */
final class ProgressionRules {
    private ProgressionRules() {}
    static int worldCompletionPercent(GameState s,int world){
        int first=(Math.max(1,Math.min(3,world))-1)*10+1, earned=0;
        for(int n=first;n<first+10;n++) earned+=s.starsFor(n);
        return Math.min(100,(earned*100)/30);
    }
    static String nextGoal(GameState s){
        if(s.collectionCount()<8) return "Descubra "+(8-s.collectionCount())+" monstrinho(s) para o primeiro marco do álbum.";
        if(s.houseLevel<3) return "Evolua a casinha e fortaleça a amizade.";
        if(s.bossWins<3) return "Vença mais "+(3-s.bossWins)+" chefe(s) para liberar novas reações.";
        if(!s.collectionComplete()) return s.collectionMilestone();
        return "Coleção completa! Continue melhorando suas estrelas.";
    }
    static int safeRewardPreview(GameState s,int stage){return s.rewardForLevel(s.safeStage(stage),3);}
}
