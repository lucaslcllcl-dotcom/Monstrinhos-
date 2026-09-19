package com.caeless.monstrinhos;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Coordinates transient reaction state, drawing and accessibility announcements.
 * The view owns one instance and forwards gameplay events to it.
 */
final class ReactionController {
    private final ReactionState state = new ReactionState();

    void show(View host, String message, boolean announceForAccessibility) {
        show(host, message, 1600L, announceForAccessibility);
    }

    void show(View host, String message, long durationMs, boolean announceForAccessibility) {
        state.show(message, durationMs);
        String current = state.current();
        if (current.isEmpty()) return;
        if (host != null) {
            if (announceForAccessibility) host.announceForAccessibility(current);
            host.invalidate();
            long remaining = state.remainingMs();
            if (remaining > 0L) host.postInvalidateDelayed(remaining + 16L);
        }
    }

    void draw(Canvas canvas, Paint paint, float width, float height, float textScale) {
        String message = state.current();
        if (!message.isEmpty()) ReactionVisuals.draw(canvas, paint, message, width, height, textScale);
    }

    void clear(View host) {
        state.clear();
        if (host != null) host.invalidate();
    }

    boolean isVisible() {
        return state.isVisible();
    }
}
