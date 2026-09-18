package com.caeless.monstrinhos;

/** Read-only collection progression model for the Album UI. */
final class CollectionProgress {
    static final int TOTAL = 32;
    static final int RARE_START = 24;
    static final int[] MILESTONES = {4,8,16,24,32};

    static final class Snapshot {
        final int owned, total, rareOwned, rareTotal, percent, nextLocked;
        final int nextMilestone, untilMilestone;
        final boolean complete;
        Snapshot(int owned,int rareOwned,int nextLocked){
            this.owned=owned; this.total=TOTAL; this.rareOwned=rareOwned;
            this.rareTotal=TOTAL-RARE_START; this.nextLocked=nextLocked;
            this.percent=Math.max(0,Math.min(100,(owned*100)/TOTAL));
            this.complete=owned>=TOTAL;
            this.nextMilestone=findNextMilestone(owned);
            this.untilMilestone=complete?0:Math.max(0,nextMilestone-owned);
        }
        String accessibilityText(){
            if(complete) return "Álbum completo. 32 de 32 monstrinhos encontrados. Todos os marcos da coleção foram alcançados.";
            return "Álbum: "+owned+" de "+total+" monstrinhos, "+percent+" por cento. Raros: "+rareOwned+" de "+rareTotal+". Faltam "+untilMilestone+" para o próximo marco de "+nextMilestone+".";
        }
        String milestoneText(){
            if(complete) return "Coleção completa!";
            if(untilMilestone==1) return "Falta 1 monstrinho para o próximo marco!";
            return "Faltam "+untilMilestone+" monstrinhos para o marco "+nextMilestone+".";
        }
    }

    static final class Card {
        final int index;
        final boolean owned, rare;
        final String name;
        Card(int index, boolean owned){
            this.index=index; this.owned=owned; this.rare=index>=RARE_START;
            this.name=owned ? "Monstrinho "+(index+1) : "Monstrinho misterioso";
        }
        String accessibilityText(){
            if(!owned) return "Monstrinho "+(index+1)+" ainda não encontrado"+(rare?", raro.":".");
            return name+" encontrado"+(rare?", raro.":".");
        }
    }

    private CollectionProgress(){}

    private static int findNextMilestone(int owned){
        for(int milestone:MILESTONES) if(owned<milestone) return milestone;
        return TOTAL;
    }

    static boolean reachedMilestone(int previousOwned,int currentOwned){
        if(currentOwned<=previousOwned) return false;
        for(int milestone:MILESTONES) if(previousOwned<milestone && currentOwned>=milestone) return true;
        return false;
    }

    static int highestReachedMilestone(int owned){
        int reached=0;
        for(int milestone:MILESTONES) if(owned>=milestone) reached=milestone;
        return reached;
    }

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

    static Card[] page(GameState state,int page,int pageSize){
        pageSize=Math.max(1,pageSize); page=clampPage(page,pageSize);
        int start=page*pageSize, end=Math.min(TOTAL,start+pageSize);
        Card[] cards=new Card[end-start];
        for(int i=start;i<end;i++) cards[i-start]=new Card(i,(state.ownedMask&(1L<<i))!=0);
        return cards;
    }

    static String pageAccessibilityText(GameState state,int page,int pageSize){
        Snapshot summary=from(state); Card[] cards=page(state,page,pageSize);
        StringBuilder out=new StringBuilder(summary.accessibilityText()).append(' ');
        for(Card card:cards) out.append(card.accessibilityText()).append(' ');
        return out.toString();
    }

    static int pageCount(int pageSize){
        pageSize=Math.max(1,pageSize);
        return (TOTAL+pageSize-1)/pageSize;
    }

    static int clampPage(int page,int pageSize){
        return Math.max(0,Math.min(pageCount(pageSize)-1,page));
    }
}
