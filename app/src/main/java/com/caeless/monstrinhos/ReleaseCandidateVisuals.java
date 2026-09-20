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
  p.setColor(0xfffff2b0);c.drawRoundRect(w*.30f,h*.405f,w*.70f,h*.445f,18,18,p);
  p.setColor(0xff34345f);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(Math.max(13f,w*.034f));
  c.drawText("AVENTURA • FASE "+Math.max(1,stage),w*.5f,h*.432f,p);p.setTextAlign(Paint.Align.LEFT);
 }
}