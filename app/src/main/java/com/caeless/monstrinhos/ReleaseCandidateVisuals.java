package com.caeless.monstrinhos;

import android.graphics.*;

public final class ReleaseCandidateVisuals {
 private ReleaseCandidateVisuals(){}

 private static int mix(int a,int b,float t){
  return Color.rgb((int)(Color.red(a)+(Color.red(b)-Color.red(a))*t),(int)(Color.green(a)+(Color.green(b)-Color.green(a))*t),(int)(Color.blue(a)+(Color.blue(b)-Color.blue(a))*t));
 }
 private static void roundGradient(Canvas c,Paint p,float l,float t,float r,float b,float radius,int top,int bottom){
  Shader old=p.getShader();p.setShader(new LinearGradient(0,t,0,b,top,bottom,Shader.TileMode.CLAMP));p.setStyle(Paint.Style.FILL);c.drawRoundRect(l,t,r,b,radius,radius,p);p.setShader(old);
 }
 private static void cloud(Canvas c,Paint p,float x,float y,float s){
  p.setColor(0xeeffffff);c.drawCircle(x,y,28*s,p);c.drawCircle(x+28*s,y-10*s,35*s,p);c.drawCircle(x+62*s,y,27*s,p);c.drawOval(x-10*s,y,x+82*s,y+32*s,p);
 }
 private static void mountain(Canvas c,Paint p,float x,float base,float width,float height,int color){
  Path m=new Path();m.moveTo(x-width*.5f,base);m.lineTo(x,base-height);m.lineTo(x+width*.5f,base);m.close();p.setColor(color);c.drawPath(m,p);
  Path snow=new Path();snow.moveTo(x-width*.13f,base-height*.76f);snow.lineTo(x,base-height);snow.lineTo(x+width*.14f,base-height*.74f);snow.lineTo(x+width*.06f,base-height*.67f);snow.lineTo(x,base-height*.71f);snow.lineTo(x-width*.07f,base-height*.66f);snow.close();p.setColor(0xccffffff);c.drawPath(snow,p);
 }
 private static void tree(Canvas c,Paint p,float x,float y,float s){
  p.setColor(0xff8a572f);c.drawRoundRect(x-9*s,y,x+9*s,y+70*s,8*s,8*s,p);
  p.setColor(0xff1f8d49);c.drawCircle(x,y-5*s,35*s,p);p.setColor(0xff3fbf5c);c.drawCircle(x-24*s,y+12*s,28*s,p);c.drawCircle(x+25*s,y+10*s,30*s,p);p.setColor(0xff72d65f);c.drawCircle(x,y-25*s,26*s,p);
 }
 private static void flower(Canvas c,Paint p,float x,float y,float s){
  p.setColor(0xffffffff);for(int i=0;i<6;i++){double a=i*Math.PI/3;c.drawCircle(x+(float)Math.cos(a)*8*s,y+(float)Math.sin(a)*8*s,5*s,p);}p.setColor(0xffffca32);c.drawCircle(x,y,5*s,p);
 }
 private static void fence(Canvas c,Paint p,float y,float w){
  p.setColor(0xff8d5a35);float post=w*.08f;for(float x=-post;x<w+post;x+=w*.18f)c.drawRoundRect(x,y-28,x+18,y+72,6,6,p);c.drawRoundRect(0,y+10,w,y+24,5,5,p);
 }
 public static void drawSkyWorld(Canvas c,Paint p,float w,float h){
  Paint q=new Paint(Paint.ANTI_ALIAS_FLAG);q.setShader(new LinearGradient(0,0,0,h*.76f,0xff4bb8ff,0xffdff9ff,Shader.TileMode.CLAMP));c.drawRect(0,0,w,h,q);
  cloud(c,p,w*.06f,h*.09f,w/700f);cloud(c,p,w*.62f,h*.12f,w/800f);cloud(c,p,w*.28f,h*.20f,w/900f);
  mountain(c,p,w*.10f,h*.52f,w*.42f,h*.28f,0xff6d9ea2);mountain(c,p,w*.42f,h*.50f,w*.48f,h*.34f,0xff7697a9);mountain(c,p,w*.77f,h*.53f,w*.46f,h*.31f,0xff668b9d);
  p.setColor(0xff7dcf66);c.drawOval(-w*.2f,h*.52f,w*.62f,h*.84f,p);p.setColor(0xff49ad58);c.drawOval(w*.3f,h*.52f,w*1.2f,h*.86f,p);
  p.setColor(0xff6ccdf1);c.drawRoundRect(w*.17f,h*.39f,w*.29f,h*.67f,20,20,p);p.setColor(0xbbe8fbff);c.drawRoundRect(w*.195f,h*.39f,w*.245f,h*.67f,15,15,p);
  p.setColor(0xff40a9df);c.drawOval(w*.05f,h*.62f,w*.93f,h*.75f,p);p.setColor(0xff8be96b);c.drawOval(-w*.05f,h*.70f,w*1.05f,h*1.08f,p);
  for(int i=0;i<9;i++){float x=w*(.03f+i*.12f);tree(c,p,x,h*(.56f+(i%3)*.035f),.55f*w/420f);}
  fence(c,p,h*.91f,w);
  for(int i=0;i<12;i++)flower(c,p,w*(.04f+i*.085f),h*(.76f+(i%3)*.055f),w/700f);
 }
 public static void drawHomeWorld(Canvas c, Paint p, float w, float h){
  drawSkyWorld(c,p,w,h);
  // cottage on the right like the approved reference
  p.setColor(0xfff2d59b);c.drawRoundRect(w*.73f,h*.30f,w*.96f,h*.63f,24,24,p);
  p.setColor(0xffa96b34);c.drawRoundRect(w*.825f,h*.47f,w*.90f,h*.63f,18,18,p);
  Path roof=new Path();roof.moveTo(w*.68f,h*.34f);roof.lineTo(w*.84f,h*.22f);roof.lineTo(w*.99f,h*.34f);roof.close();p.setColor(0xffe95742);c.drawPath(roof,p);
  p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(6);p.setColor(0xff8f3d2d);c.drawPath(roof,p);p.setStyle(Paint.Style.FILL);
  p.setColor(0xff5a351d);c.drawCircle(w*.862f,h*.545f,4,p);
  woodSign(c,p,w*.73f,h*.39f,w*.965f,h*.465f,"MINHA CASINHA",Math.max(11,w*.027f));
  woodSign(c,p,w*.035f,h*.345f,w*.34f,h*.455f,"CAMPANHA • AVENTURA",Math.max(11,w*.026f));
 }
 public static void drawLogo(Canvas c, Paint p, float w, float h){
  p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.create("sans",Typeface.BOLD));
  float size=Math.max(38f,Math.min(76f,w*.13f));p.setTextSize(size);p.setStrokeWidth(Math.max(8f,w*.012f));p.setStyle(Paint.Style.STROKE);p.setColor(0xff6c22a4);p.setShadowLayer(8,0,5,0x77000000);c.drawText("MONSTRINHOS",w*.5f,h*.205f,p);
  p.setStyle(Paint.Style.FILL);p.setColor(0xffffc331);c.drawText("MONSTRINHOS",w*.5f,h*.205f,p);p.clearShadowLayer();
  woodSign(c,p,w*.25f,h*.215f,w*.75f,h*.262f,"Pequenas criaturas, grandes aventuras!",Math.max(10,w*.022f));p.setTextAlign(Paint.Align.LEFT);
 }
 public static void drawHud(Canvas c,Paint p,float w,float h,String title,int coins,int stars,int eggs){
  float y=h*.018f,hh=h*.082f;
  roundGradient(c,p,w*.025f,y,w*.38f,y+hh,22,0xffe8f6ff,0xffa9d7ff);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(3);p.setColor(0xffffffff);c.drawRoundRect(w*.025f,y,w*.38f,y+hh,22,22,p);p.setStyle(Paint.Style.FILL);
  drawMiniMascot(c,p,w*.085f,y+hh*.52f,w/520f,0xff35c7df);
  p.setTypeface(Typeface.DEFAULT_BOLD);p.setColor(0xff24337b);p.setTextSize(Math.max(13,w*.030f));c.drawText(title,w*.14f,y+hh*.43f,p);p.setTextSize(Math.max(11,w*.022f));c.drawText("★ "+stars+"/30",w*.14f,y+hh*.75f,p);
  pill(c,p,w*.41f,y+hh*.12f,w*.62f,y+hh*.78f,0xee17244f,"🪙 "+coins);
  pill(c,p,w*.64f,y+hh*.12f,w*.82f,y+hh*.78f,0xee17244f,"🥚 "+eggs);
  roundGradient(c,p,w*.84f,y,w*.975f,y+hh,20,0xff3dbbff,0xff1878e6);p.setColor(Color.WHITE);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(Math.max(22,w*.055f));c.drawText("⚙",w*.907f,y+hh*.54f,p);p.setTextSize(Math.max(8,w*.017f));c.drawText("OPÇÕES",w*.907f,y+hh*.83f,p);p.setTextAlign(Paint.Align.LEFT);
 }
 private static void pill(Canvas c,Paint p,float l,float t,float r,float b,int color,String label){p.setColor(color);c.drawRoundRect(l,t,r,b,(b-t)/2,(b-t)/2,p);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(Math.max(12,(b-t)*.38f));p.setColor(Color.WHITE);c.drawText(label,(l+r)/2,t+(b-t)*.66f,p);p.setTextAlign(Paint.Align.LEFT);}
 public static void drawGlossButton(Canvas c,Paint p,String label,float l,float t,float r,float b,int color,float textScale){
  float rad=Math.min((b-t)*.24f,32f);p.setColor(0x55000000);c.drawRoundRect(l+3,t+7,r+3,b+9,rad,rad,p);
  roundGradient(c,p,l,t,r,b,rad,mix(color,Color.WHITE,.18f),mix(color,Color.BLACK,.12f));
  p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(Math.max(3,(r-l)*.006f));p.setColor(mix(color,Color.BLACK,.35f));c.drawRoundRect(l,t,r,b,rad,rad,p);p.setStrokeWidth(2);p.setColor(0x99ffffff);c.drawRoundRect(l+5,t+4,r-5,b-7,Math.max(8,rad-4),Math.max(8,rad-4),p);p.setStyle(Paint.Style.FILL);
  p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.create("sans",Typeface.BOLD));p.setTextSize(Math.max(14,Math.min(30,(b-t)*.30f))*textScale);p.setColor(Color.WHITE);p.setShadowLayer(4,0,3,0xaa000000);c.drawText(label,(l+r)/2,t+(b-t)*.62f,p);p.clearShadowLayer();p.setTextAlign(Paint.Align.LEFT);
 }
 private static void woodSign(Canvas c,Paint p,float l,float t,float r,float b,String label,float size){
  p.setColor(0x55000000);c.drawRoundRect(l+3,t+5,r+3,b+6,12,12,p);roundGradient(c,p,l,t,r,b,12,0xffe9b467,0xffbd753a);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(3);p.setColor(0xff7a431e);c.drawRoundRect(l,t,r,b,12,12,p);p.setStyle(Paint.Style.FILL);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(size);p.setColor(0xff5b2b16);c.drawText(label,(l+r)/2,t+(b-t)*.62f,p);p.setTextAlign(Paint.Align.LEFT);
 }
 public static void drawReferenceLabel(Canvas c,Paint p,float l,float t,float r,float b,String label,float size){
  woodSign(c,p,l,t,r,b,label,size);
 }
 public static void drawReferenceStageNode(Canvas c,Paint p,float x,float y,float radius,int stage,int stars,boolean unlocked,boolean boss){
  int base=unlocked?(boss?0xffff6a55:0xff168cff):0xff9ca3ac;
  p.setColor(0x44000000);c.drawCircle(x+3,y+5,radius,p);
  Shader old=p.getShader();p.setShader(new RadialGradient(x-radius*.30f,y-radius*.35f,radius*1.25f,mix(base,Color.WHITE,.32f),mix(base,Color.BLACK,.18f),Shader.TileMode.CLAMP));c.drawCircle(x,y,radius,p);p.setShader(old);
  p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(Math.max(3f,radius*.08f));p.setColor(unlocked?0xffffffff:0xff666666);c.drawCircle(x,y,radius,p);p.setStyle(Paint.Style.FILL);
  p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.DEFAULT_BOLD);p.setTextSize(radius*.82f);p.setColor(Color.WHITE);p.setShadowLayer(3,0,2,0xaa000000);c.drawText(""+stage,x,y+radius*.28f,p);p.clearShadowLayer();
  float sy=y+radius*1.34f;for(int i=0;i<3;i++){p.setColor(i<stars?0xffffd134:0xff737982);p.setTextSize(radius*.48f);c.drawText("★",x+(i-1)*radius*.43f,sy,p);}p.setTextAlign(Paint.Align.LEFT);
 }

 public static void drawPlayBadge(Canvas c,Paint p,float w,float h,int stage){
  woodSign(c,p,w*.30f,h*.452f,w*.70f,h*.486f,"AVENTURA • FASE "+Math.max(1,stage),Math.max(12,w*.029f));
 }
 public static void drawMascot(Canvas c,Paint p,float x,float y,float s,int color){
  // ears/horns
  Path le=new Path();le.moveTo(x-49*s,y-36*s);le.lineTo(x-34*s,y-78*s);le.lineTo(x-12*s,y-43*s);le.close();p.setColor(0xff7d4bd5);c.drawPath(le,p);
  Path re=new Path();re.moveTo(x+49*s,y-36*s);re.lineTo(x+34*s,y-78*s);re.lineTo(x+12*s,y-43*s);re.close();c.drawPath(re,p);
  p.setColor(color);c.drawOval(x-58*s,y-52*s,x+58*s,y+62*s,p);c.drawCircle(x-43*s,y+36*s,24*s,p);c.drawCircle(x+43*s,y+36*s,24*s,p);
  p.setColor(mix(color,Color.WHITE,.45f));c.drawOval(x-27*s,y+22*s,x+27*s,y+60*s,p);
  p.setColor(Color.WHITE);c.drawOval(x-40*s,y-23*s,x-6*s,y+18*s,p);c.drawOval(x+6*s,y-23*s,x+40*s,y+18*s,p);
  p.setColor(0xff10204c);c.drawCircle(x-22*s,y-1*s,11*s,p);c.drawCircle(x+22*s,y-1*s,11*s,p);p.setColor(Color.WHITE);c.drawCircle(x-18*s,y-5*s,4*s,p);c.drawCircle(x+18*s,y-5*s,4*s,p);
  p.setColor(0xffff93a5);c.drawCircle(x-43*s,y+15*s,8*s,p);c.drawCircle(x+43*s,y+15*s,8*s,p);
  p.setColor(0xff7a1641);c.drawOval(x-20*s,y+15*s,x+20*s,y+40*s,p);p.setColor(0xffff5f79);c.drawOval(x-12*s,y+27*s,x+12*s,y+39*s,p);
  p.setColor(mix(color,Color.BLACK,.08f));Path tuft=new Path();tuft.moveTo(x-10*s,y-48*s);tuft.lineTo(x,y-66*s);tuft.lineTo(x+8*s,y-49*s);tuft.lineTo(x+18*s,y-61*s);tuft.lineTo(x+20*s,y-42*s);tuft.close();c.drawPath(tuft,p);
 }
 private static void drawMiniMascot(Canvas c,Paint p,float x,float y,float s,int color){drawMascot(c,p,x,y,s*.58f,color);}
 public static void drawMapBackdrop(Canvas c,Paint p,float w,float h,int world){
  drawSkyWorld(c,p,w,h);p.setColor(0xffd9a65f);Path path=new Path();path.moveTo(w*.88f,h*.19f);path.cubicTo(w*.58f,h*.29f,w*.72f,h*.38f,w*.42f,h*.46f);path.cubicTo(w*.12f,h*.55f,w*.38f,h*.64f,w*.16f,h*.76f);path.cubicTo(w*.08f,h*.82f,w*.23f,h*.88f,w*.08f,h*.94f);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(w*.085f);p.setStrokeCap(Paint.Cap.ROUND);c.drawPath(path,p);p.setStyle(Paint.Style.FILL);
  woodSign(c,p,w*.035f,h*.16f,w*.36f,h*.285f,"MUNDO "+Math.max(1,world)+" • VALE VERDE",Math.max(13,w*.032f));
  p.setColor(0xff31a9e5);c.drawRoundRect(w*.58f,h*.49f,w*.96f,h*.68f,30,30,p);p.setColor(0x99ffffff);c.drawRoundRect(w*.70f,h*.49f,w*.78f,h*.70f,18,18,p);
 }
 public static void drawGameplayArena(Canvas c,Paint p,float w,float h,boolean boss){
  drawSkyWorld(c,p,w,h);p.setColor(0xff6ed34f);c.drawRect(0,h*.48f,w,h,p);for(int i=0;i<5;i++){float x=w*(.18f+(i%3)*.31f),y=h*(.48f+(i/3)*.23f);p.setColor(0xff986332);c.drawOval(x-w*.10f,y,w*.10f+x,y+h*.055f,p);p.setColor(0xff5e3a22);c.drawOval(x-w*.07f,y+h*.012f,x+w*.07f,y+h*.045f,p);}
  if(boss)woodSign(c,p,w*.035f,h*.145f,w*.34f,h*.235f,"CHEFÃO! 👑",Math.max(15,w*.038f));else woodSign(c,p,w*.035f,h*.145f,w*.34f,h*.235f,"COMBO • AVENTURA",Math.max(13,w*.032f));
 }
 public static void drawHouseBackdrop(Canvas c,Paint p,float w,float h,int level){
  // cozy interior from the approved casinha reference
  Paint q=new Paint();q.setShader(new LinearGradient(0,0,0,h,0xfff5d08a,0xffb76b34,Shader.TileMode.CLAMP));c.drawRect(0,0,w,h,q);
  p.setColor(0xff8b562f);for(int i=0;i<7;i++)c.drawRect(0,h*(.18f+i*.12f),w,h*(.185f+i*.12f),p);
  p.setColor(0xfff5e4bd);c.drawRoundRect(w*.33f,h*.20f,w*.68f,h*.50f,80,80,p);p.setColor(0xff64c6f0);c.drawRoundRect(w*.39f,h*.24f,w*.62f,h*.42f,55,55,p);
  p.setColor(0xffdd6f37);c.drawRoundRect(w*.75f,h*.45f,w*.96f,h*.72f,18,18,p);p.setColor(0xffffc64e);c.drawOval(w*.80f,h*.55f,w*.92f,h*.69f,p);
  p.setColor(0xffe8b35f);c.drawRoundRect(w*.08f,h*.58f,w*.72f,h*.78f,40,40,p);p.setColor(0xff67a6dc);c.drawRoundRect(w*.11f,h*.62f,w*.69f,h*.73f,32,32,p);
  woodSign(c,p,w*.22f,h*.13f,w*.78f,h*.225f,"CASINHA • NÍVEL "+Math.max(1,level),Math.max(22,w*.055f));
 }
 public static void drawCollectionBackdrop(Canvas c,Paint p,float w,float h,int owned,int favorite){
  drawSkyWorld(c,p,w,h);woodSign(c,p,w*.25f,h*.12f,w*.75f,h*.205f,"ÁLBUM",Math.max(28,w*.07f));
  p.setColor(0xfff7e8c3);c.drawRoundRect(w*.035f,h*.24f,w*.965f,h*.82f,38,38,p);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(6);p.setColor(0xff8f5c34);c.drawRoundRect(w*.035f,h*.24f,w*.965f,h*.82f,38,38,p);p.setStyle(Paint.Style.FILL);
  woodSign(c,p,w*.16f,h*.255f,w*.58f,h*.305f,"DESCOBERTOS "+Math.max(0,owned)+"/32",Math.max(13,w*.032f));
  if(favorite>=0)woodSign(c,p,w*.61f,h*.255f,w*.91f,h*.305f,"★ FAVORITO #"+(favorite+1),Math.max(10,w*.025f));
 }
 public static void drawAchievementBackdrop(Canvas c,Paint p,float w,float h,int unlocked){
  drawSkyWorld(c,p,w,h);woodSign(c,p,w*.16f,h*.115f,w*.84f,h*.215f,"🏆 CONQUISTAS",Math.max(25,w*.062f));
  p.setColor(0xff9b632f);c.drawRoundRect(w*.035f,h*.245f,w*.965f,h*.82f,32,32,p);p.setColor(0xffffe5ae);c.drawRoundRect(w*.06f,h*.27f,w*.94f,h*.79f,26,26,p);
  woodSign(c,p,w*.30f,h*.225f,w*.70f,h*.275f,Math.max(0,unlocked)+"/8 DESBLOQUEADAS",Math.max(12,w*.029f));
 }
 public static void drawSettingsBackdrop(Canvas c,Paint p,float w,float h){
  drawSkyWorld(c,p,w,h);woodSign(c,p,w*.23f,h*.12f,w*.77f,h*.22f,"AJUSTES",Math.max(28,w*.07f));
 }
 public static void drawStoreBackdrop(Canvas c,Paint p,float w,float h){
  drawSkyWorld(c,p,w,h);p.setColor(0xffb06e35);c.drawRoundRect(w*.04f,h*.13f,w*.96f,h*.82f,35,35,p);p.setColor(0xfff5e5be);c.drawRoundRect(w*.065f,h*.22f,w*.935f,h*.80f,28,28,p);
  p.setColor(0xffe64955);c.drawRect(w*.05f,h*.13f,w*.95f,h*.22f,p);for(int i=0;i<8;i++){p.setColor(i%2==0?0xffffffff:0xfff25c66);c.drawRect(w*(.05f+i*.1125f),h*.13f,w*(.106f+i*.1125f),h*.22f,p);}
  woodSign(c,p,w*.18f,h*.08f,w*.58f,h*.155f,"🛍 LOJA",Math.max(26,w*.065f));
 }
 public static void drawHatchBackdrop(Canvas c,Paint p,float w,float h,boolean rare){
  drawSkyWorld(c,p,w,h);p.setColor(rare?0x99ffd53d:0x99ffffff);c.drawCircle(w*.5f,h*.48f,w*.35f,p);for(int i=0;i<18;i++){float x=w*(.08f+(i%6)*.17f),y=h*(.25f+(i%3)*.19f);p.setColor(new int[]{0xffffd43b,0xffff58c4,0xff40b8ff,0xff67d84e}[i%4]);c.drawRect(x,y,x+8,y+22,p);}
  woodSign(c,p,w*.15f,h*.12f,w*.85f,h*.22f,"NOVO MONSTRINHO!",Math.max(24,w*.060f));
 }
 public static void drawResultBackdrop(Canvas c,Paint p,float w,float h){
  drawSkyWorld(c,p,w,h);woodSign(c,p,w*.18f,h*.14f,w*.82f,h*.24f,"FASE CONCLUÍDA!",Math.max(25,w*.060f));p.setColor(0xfffff0bd);c.drawRoundRect(w*.10f,h*.28f,w*.90f,h*.72f,40,40,p);
 }
 public static void drawChallengeBackdrop(Canvas c,Paint p,float w,float h){
  drawGameplayArena(c,p,w,h,false);woodSign(c,p,w*.20f,h*.12f,w*.80f,h*.20f,"⚡ DESAFIO RÁPIDO",Math.max(20,w*.050f));
 }
}
