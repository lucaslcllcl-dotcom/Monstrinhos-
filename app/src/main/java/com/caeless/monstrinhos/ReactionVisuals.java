package com.caeless.monstrinhos;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

/** Lightweight reaction overlay renderer. Keeps feedback readable without bitmap allocations. */
final class ReactionVisuals {
    private static final int NAVY = Color.rgb(35, 38, 92);
    private static final int CREAM = Color.rgb(255, 247, 218);

    static void draw(Canvas canvas, Paint paint, String message, float width, float height, float textScale) {
        if (canvas == null || paint == null || message == null || message.trim().isEmpty()) return;
        if (!(width > 0f) || !(height > 0f)) return;

        float scale = Math.max(0.85f, Math.min(1.45f, textScale));
        float left = width * .08f;
        float right = width * .92f;
        float top = height * .205f;
        float bottom = height * .285f;
        float radius = Math.max(18f, Math.min(width, height) * .025f);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(CREAM);
        canvas.drawRoundRect(new RectF(left, top, right, bottom), radius, radius, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Math.max(3f, width * .007f));
        paint.setColor(NAVY);
        canvas.drawRoundRect(new RectF(left, top, right, bottom), radius, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(NAVY);
        paint.setTypeface(Typeface.create("sans", Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(Math.max(14f, Math.min(22f, width * .042f)) * scale);

        String fitted = fit(message, paint, (right - left) * .90f);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float baseline = (top + bottom) * .5f - (fm.ascent + fm.descent) * .5f;
        canvas.drawText(fitted, width * .5f, baseline, paint);
        paint.setTextAlign(Paint.Align.LEFT);
    }

    static String fit(String message, Paint paint, float maxWidth) {
        if (message == null) return "";
        if (paint.measureText(message) <= maxWidth) return message;
        final String ellipsis = "…";
        int end = message.length();
        while (end > 1 && paint.measureText(message, 0, end) + paint.measureText(ellipsis) > maxWidth) end--;
        return message.substring(0, Math.max(1, end)).trim() + ellipsis;
    }

    private ReactionVisuals() {}
}
