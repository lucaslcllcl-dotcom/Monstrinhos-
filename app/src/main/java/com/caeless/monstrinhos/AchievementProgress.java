package com.caeless.monstrinhos;

import android.content.Context;
import android.content.SharedPreferences;

/** Read-only achievement progress model for UI/accessibility. */
final class AchievementProgress {
    final String name;
    final int current;
    final int target;
    final boolean unlocked;

    AchievementProgress(String name,int current,int target,boolean unlocked){
        this.name=name; this.current=Math.max(0,current); this.target=Math.max(1,target); this.unlocked=unlocked;
    }

    int percent(){ return unlocked?100:Math.min(99,(current*100)/target); }

    static AchievementProgress[] snapshot(Context c){
        SharedPreferences p=c.getSharedPreferences("monstrinhos_save_v1",Context.MODE_PRIVATE);
        int plays=p.getInt("total_plays",0);
        int perfect=p.getInt("session_perfect",0)+p.getInt("perfect_bosses",0);
        int bosses=p.getInt("boss_wins",0);
        int collection=Long.bitCount(p.getLong("owned_mask",1L));
        int friendship=p.getInt("friendship_level",1);
        int house=p.getInt("house_level",1);
        int stars=p.getInt("stars",0);
        boolean master=bosses>=6&&collection>=24&&stars>=60;
        int masterSteps=(bosses>=6?1:0)+(collection>=24?1:0)+(stars>=60?1:0);
        int[] now={plays,perfect,bosses,collection,friendship,house,stars,masterSteps};
        int[] goal={1,1,3,8,3,3,30,3};
        AchievementProgress[] out=new AchievementProgress[AchievementSystem.COUNT];
        for(int i=0;i<out.length;i++){
            boolean unlocked=AchievementSystem.unlocked(c,i)||(i==7&&master);
            out[i]=new AchievementProgress(AchievementSystem.NAMES[i],now[i],goal[i],unlocked);
        }
        return out;
    }
}
