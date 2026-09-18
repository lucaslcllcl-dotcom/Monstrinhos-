package com.caeless.monstrinhos;

/** Read-only collection progression model for the Album UI. */
final class CollectionProgress {
    static final int TOTAL = 32;
    static final int RARE_START = 24;

    static final class Snapshot {
        final int owned, total, rareOwned, rareTotal, percent, nextLocked;
        final boolean complete;
        Snapshot(int owned,int rareOwned,int nextLocked){
            this.owned=owned; this.total=TOTAL; this.rareOwned=rareOwned;
            this.rareTotal=TOTAL-RARE_START; this.nextLocked=nextLocked;
            this.percent=Math.max(0,Math.min(100,(owned*100)/TOTAL));
            this.complete=owned>=TOTAL;
        }
        String accessibilityText(){
            if(complete) return "Álbum completo. 32 de 32 monstrinhos encontrados.";
            return "Álbum: "+owned+" de "+total+" monstrinhos, "+percent+" por cento. Raros: "+rareOwned+" de "+rareTotal+".";
        }
    }

    private CollectionProgress(){}

    static Snapshot from(GameState state){
        int owned=Long.bitCount(state.ownedMask);
        int rare=0, next=-1;
        for(int i=0;i<TOTAL;i++){
            boolean has=(state.ownedMask&(1L<<i))!=0;
            if(!has && next<0) next=i;
            if(i>=RARE_START && has) rare++;
        }
        return new Snapshot(owned,rare,next);
    }

    static int pageCount(int pageSize){
        pageSize=Math.max(1,pageSize);
        return (TOTAL+pageSize-1)/pageSize;
    }

    static int clampPage(int page,int pageSize){
        return Math.max(0,Math.min(pageCount(pageSize)-1,page));
    }
}
