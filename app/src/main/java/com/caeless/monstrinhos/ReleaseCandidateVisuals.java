package com.caeless.monstrinhos;

import android.graphics.*;

public final class ReleaseCandidateVisuals {
 private ReleaseCandidateVisuals(){}
 public static void drawHomeWorld(Canvas c, Paint p, float w, float h){
  if(c==null||p==null||w<=0||h<=0)return;
  p.setStyle(Paint.Style.FILL);
  p.setColor(0xffb9efff);c.drawCircle(w*.16f,h*.19f,w*.12f,p);
  p.setColor(0xffd9f7ff);c.drawCircle(w*.29f,h*.16f,w*.10f,p);
  p.setColor(0xff4fae63);c.drawOval(-w*.18f,h*.60f,w*.64f,h*.91f,p);
  p.setColor(0xff3b9657);c.drawOval(w*.37f,h*.58f,w*1.18f,h*.93f,p);
  p.setColor(0xff8ed36c);c.drawOval(w*.18f,h*.64f,w*.82f,h*.94f,p);
  // distant playful treehouse silhouette
  p.setColor(0xff8a5a38);c.drawRect(w*.76f,h*.35f,w*.80f,h*.60f,p);
  p.setColor(0xfff1a653);c.drawRoundRect(w*.66f,h*.30f,w*.90f,h*.47f,18,18,p);
  Path roof=new Path();roof.moveTo(w*.63f,h*.31f);roof.lineTo(w*.78f,h*.22f);roof.lineTo(w*.93f,h*.31f);roof.close();
  p.setColor(0xffd85d55);c.drawPath(roof,p);
 }
 public static void drawLogo(Canvas c, Paint p, float w, float h){
  if(c==null||p==null)return;
  p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.create("sans",Typeface.BOLD));
  p.setTextSize(Math.max(30f,Math.min(52f,w*.105f)));p.setColor(0xffffffff);p.setShadowLayer(7,0,4,0x77000000);
  c.drawText("MONSTRINHOS",w*.5f,h*.145f,p);p.clearShadowLayer();p.setTextAlign(Paint.Align.LEFT);
 }
 public static void drawPlayBadge(Canvas c,Paint p,float w,float h,int stage){
  if(c==null||p==null)return;
  p.setColor(0xfffff2b0);c.drawRoundRect(w*.30f,h*.442f,w*.70f,h*.478f,18,18,p);
  p.setColor(0xff34345f);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(Math.max(13f,w*.034f));
  c.drawText("AVENTURA • FASE "+Math.max(1,stage),w*.5f,h*.467f,p);p.setTextAlign(Paint.Align.LEFT);
 }
 public static void drawMapBackdrop(Canvas c,Paint p,float w,float h,int world){
  if(c==null||p==null)return;
  int accent=world==1?0xff69c86c:world==2?0xffb67de0:0xffef9b55;
  p.setColor(0x44ffffff);for(int i=0;i<5;i++)c.drawCircle(w*(.10f+i*.22f),h*(.18f+(i%2)*.06f),w*.08f,p);
  p.setColor(accent);c.drawRoundRect(w*.04f,h*.14f,w*.96f,h*.72f,42,42,p);
  p.setColor(0x55ffffff);c.drawRoundRect(w*.07f,h*.17f,w*.93f,h*.69f,34,34,p);
 }
 public static void drawGameplayArena(Canvas c,Paint p,float w,float h,boolean boss){
  if(c==null||p==null)return;
  p.setColor(boss?0xffffd0c6:0xffd8f6ff);c.drawRoundRect(w*.05f,h*.15f,w*.95f,h*.84f,46,46,p);
  p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(Math.max(5f,w*.012f));p.setColor(boss?0xffe65e56:0xff66b9dc);c.drawRoundRect(w*.05f,h*.15f,w*.95f,h*.84f,46,46,p);p.setStyle(Paint.Style.FILL);
  if(boss){p.setColor(0xffffc83d);Path crown=new Path();crown.moveTo(w*.42f,h*.19f);crown.lineTo(w*.46f,h*.14f);crown.lineTo(w*.50f,h*.19f);crown.lineTo(w*.55f,h*.14f);crown.lineTo(w*.59f,h*.19f);crown.close();c.drawPath(crown,p);}
 }
 public static void drawHouseBackdrop(Canvas c,Paint p,float w,float h,int level){
  if(c==null||p==null)return;
  p.setColor(0xffbdeaff);c.drawRoundRect(w*.04f,h*.14f,w*.96f,h*.72f,44,44,p);
  p.setColor(0xff72c96a);c.drawOval(w*.03f,h*.55f,w*.97f,h*.79f,p);
  p.setColor(0xfffff0bd);c.drawRoundRect(w*.12f,h*.17f,w*.38f,h*.22f,18,18,p);
  p.setColor(0xff34345f);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(Math.max(13f,w*.035f));c.drawText("CASINHA • NÍVEL "+Math.max(1,level),w*.25f,h*.205f,p);p.setTextAlign(Paint.Align.LEFT);
 }
 public static void drawCollectionBackdrop(Canvas c,Paint p,float w,float h,int owned,int favorite){
  if(c==null||p==null)return;p.setColor(0xfffff4d6);c.drawRoundRect(w*.035f,h*.135f,w*.965f,h*.805f,42,42,p);
  p.setColor(0xff6f56b8);c.drawRoundRect(w*.08f,h*.145f,w*.92f,h*.205f,22,22,p);
  p.setColor(Color.WHITE);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(Math.max(14f,w*.038f));
  c.drawText("DESCOBERTOS "+Math.max(0,owned)+"/32"+(favorite>=0?"   ★ FAVORITO #"+(favorite+1):""),w*.5f,h*.184f,p);p.setTextAlign(Paint.Align.LEFT);
 }
 public static void drawAchievementBackdrop(Canvas c,Paint p,float w,float h,int unlocked){
  if(c==null||p==null)return;p.setColor(0xfffff1b8);c.drawRoundRect(w*.035f,h*.135f,w*.965f,h*.80f,42,42,p);
  p.setColor(0xffffc83d);c.drawCircle(w*.5f,h*.17f,w*.07f,p);p.setColor(0xff34345f);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);
  p.setTextSize(Math.max(14f,w*.04f));c.drawText(Math.max(0,unlocked)+"/8 LIBERADAS",w*.5f,h*.225f,p);p.setTextAlign(Paint.Align.LEFT);
 }
}
