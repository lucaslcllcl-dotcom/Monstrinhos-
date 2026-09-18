package com.caeless.monstrinhos;

import android.content.Context;
import android.content.SharedPreferences;

/** Persistent, append-only achievement evaluator compatible with monstrinhos_save_v1. */
final class AchievementSystem {
    static final int COUNT=8;
    static final String[] NAMES={"Primeiros Passos","Estrela Perfeita","Caçador de Chefes","Colecionador","Amigo dos Monstrinhos","Lar Doce Lar","Aventureiro","Mestre Monstrinho"};
    private AchievementSystem(){}

    static int evaluate(Context c){
        SharedPreferences p=c.getSharedPreferences("monstrinhos_save_v1",Context.MODE_PRIVATE);
        long mask=p.getLong("achievement_mask",0L), before=mask;
        int plays=p.getInt("total_plays",0), perfect=p.getInt("session_perfect",0)+p.getInt("perfect_bosses",0);
        int bosses=p.getInt("boss_wins",0), friendship=p.getInt("friendship_level",1), house=p.getInt("house_level",1), stars=p.getInt("stars",0);
        int collection=Long.bitCount(p.getLong("owned_mask",1L));
        mask=unlock(mask,0,plays>=1); mask=unlock(mask,1,perfect>=1); mask=unlock(mask,2,bosses>=3); mask=unlock(mask,3,collection>=8);
        mask=unlock(mask,4,friendship>=3); mask=unlock(mask,5,house>=3); mask=unlock(mask,6,stars>=30); mask=unlock(mask,7,bosses>=6&&collection>=24&&stars>=60);
        if(mask!=before)p.edit().putLong("achievement_mask",mask).apply();
        return Long.bitCount(mask)-Long.bitCount(before);
    }

    static boolean unlocked(Context c,int index){if(index<0||index>=COUNT)return false;long m=c.getSharedPreferences("monstrinhos_save_v1",Context.MODE_PRIVATE).getLong("achievement_mask",0L);return(m&(1L<<index))!=0;}
    private static long unlock(long m,int i,boolean ok){return ok?m|(1L<<i):m;}
}
