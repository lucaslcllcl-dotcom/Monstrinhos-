package com.caeless.monstrinhos;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/** Lightweight child-friendly album artwork with no external asset dependency. */
final class AlbumVisuals {
    private static final int[] COLORS = {
            0xff49b6f2, 0xff69cd60, 0xffff7d8d, 0xffffc437,
            0xff8065d4, 0xffff9557, 0xff54cfc1, 0xffef6fb2
    };

    static int colorFor(int index) { return COLORS[Math.floorMod(index, COLORS.length)]; }

    static void drawCard(Canvas c, Paint p, float x, float y, float radius, int index,
                         boolean owned, boolean rare, boolean favorite) {
        p.setStyle(Paint.Style.FILL);
        p.setColor(owned ? 0xffffffff : 0xffdfe5ec);
        c.drawRoundRect(x-radius, y-radius, x+radius, y+radius, radius*.28f, radius*.28f, p);
        p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(Math.max(2f,radius*.07f));
        p.setColor(rare ? 0xffffc437 : 0x44333a66);
        c.drawRoundRect(x-radius, y-radius, x+radius, y+radius, radius*.28f, radius*.28f, p);
        p.setStyle(Paint.Style.FILL);
        if (!owned) {
            p.setColor(0xff9aa3ad); c.drawCircle(x,y,radius*.38f,p);
            p.setColor(Color.WHITE); p.setTextAlign(Paint.Align.CENTER); p.setTextSize(radius*.62f);
            c.drawText("?",x,y+radius*.22f,p); p.setTextAlign(Paint.Align.LEFT); return;
        }
        p.setColor(colorFor(index));
        c.drawOval(x-radius*.46f,y-radius*.34f,x+radius*.46f,y+radius*.48f,p);
        Path ear=new Path(); ear.moveTo(x-radius*.34f,y-radius*.25f); ear.lineTo(x-radius*.48f,y-radius*.64f); ear.lineTo(x-radius*.10f,y-radius*.38f); ear.close(); c.drawPath(ear,p);
        ear=new Path(); ear.moveTo(x+radius*.34f,y-radius*.25f); ear.lineTo(x+radius*.48f,y-radius*.64f); ear.lineTo(x+radius*.10f,y-radius*.38f); ear.close(); c.drawPath(ear,p);
        p.setColor(Color.WHITE); c.drawCircle(x-radius*.17f,y-radius*.05f,radius*.11f,p); c.drawCircle(x+radius*.17f,y-radius*.05f,radius*.11f,p);
        p.setColor(0xff23265c); c.drawCircle(x-radius*.17f,y-radius*.05f,radius*.05f,p); c.drawCircle(x+radius*.17f,y-radius*.05f,radius*.05f,p);
        if (favorite) { p.setColor(0xffff5f91); p.setTextAlign(Paint.Align.CENTER); p.setTextSize(radius*.42f); c.drawText("♥",x+radius*.62f,y-radius*.55f,p); p.setTextAlign(Paint.Align.LEFT); }
    }
    private AlbumVisuals() {}
}
