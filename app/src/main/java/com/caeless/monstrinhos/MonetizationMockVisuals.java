package com.caeless.monstrinhos;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

/** Draws an explicitly disabled preview of future store/ad surfaces. */
final class MonetizationMockVisuals {
    private MonetizationMockVisuals() {}

    static void drawStorePreview(Canvas canvas, Paint paint, float width, float height, float textScale) {
        if (canvas == null || paint == null || width <= 0f || height <= 0f) return;
        float scale = Math.max(0.85f, Math.min(1.6f, textScale));
        float pad = Math.max(16f, width * 0.05f);
        float top = Math.max(130f, height * 0.18f);
        float gap = Math.max(10f, height * 0.018f);
        MonetizationMockCatalog.Offer[] offers = MonetizationMockCatalog.storeSnapshot();
        float available = Math.max(180f, height * 0.58f);
        float cardHeight = Math.max(76f, (available - gap * Math.max(0, offers.length - 1)) / Math.max(1, offers.length));

        paint.setTypeface(Typeface.create("sans", Typeface.BOLD));
        paint.setTextAlign(Paint.Align.LEFT);
        for (int i = 0; i < offers.length; i++) {
            MonetizationMockCatalog.Offer offer = offers[i];
            float y = top + i * (cardHeight + gap);
            paint.setColor(Color.WHITE);
            canvas.drawRoundRect(new RectF(pad, y, width - pad, y + cardHeight), 26f, 26f, paint);
            paint.setColor(Color.rgb(35, 38, 92));
            paint.setTextSize(20f * scale);
            canvas.drawText(offer.title, pad + 22f, y + 34f * scale, paint);
            paint.setTypeface(Typeface.create("sans", Typeface.NORMAL));
            paint.setTextSize(14f * scale);
            canvas.drawText(offer.description, pad + 22f, y + 58f * scale, paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            paint.setTypeface(Typeface.create("sans", Typeface.BOLD));
            paint.setColor(Color.rgb(102, 74, 190));
            canvas.drawText(offer.displayPrice, width - pad - 22f, y + 34f * scale, paint);
            paint.setTextAlign(Paint.Align.LEFT);
        }

        paint.setTypeface(Typeface.create("sans", Typeface.BOLD));
        paint.setTextSize(13f * scale);
        paint.setColor(Color.rgb(95, 70, 45));
        canvas.drawText("Prévia somente — sem cobrança e sem anúncios reais", pad, Math.min(height - 22f, top + available + 42f), paint);
    }

    static int hitOffer(float width, float height, float x, float y) {
        if (width <= 0f || height <= 0f || Float.isNaN(x) || Float.isNaN(y)) return -1;
        MonetizationMockCatalog.Offer[] offers = MonetizationMockCatalog.storeSnapshot();
        float pad = Math.max(16f, width * 0.05f);
        float top = Math.max(130f, height * 0.18f);
        float gap = Math.max(10f, height * 0.018f);
        float available = Math.max(180f, height * 0.58f);
        float cardHeight = Math.max(76f, (available - gap * Math.max(0, offers.length - 1)) / Math.max(1, offers.length));
        if (x < pad || x > width - pad) return -1;
        for (int i = 0; i < offers.length; i++) {
            float topCard = top + i * (cardHeight + gap);
            if (y >= topCard && y <= topCard + cardHeight) return i;
        }
        return -1;
    }

    static String disabledOfferMessage(int index) {
        MonetizationMockCatalog.Offer[] offers = MonetizationMockCatalog.storeSnapshot();
        if (index < 0 || index >= offers.length) return "";
        return offers[index].accessibilityLabel();
    }
}
