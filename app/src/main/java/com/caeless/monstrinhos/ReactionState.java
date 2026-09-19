package com.caeless.monstrinhos;

/**
 * Small allocation-free state holder for transient gameplay feedback.
 * Keeps reaction timing independent from rendering so it survives future UI refactors.
 */
final class ReactionState {
    private static final long DEFAULT_DURATION_MS = 1600L;
    private String message = "";
    private long visibleUntilMs;

    void show(String value) {
        show(value, DEFAULT_DURATION_MS);
    }

    void show(String value, long durationMs) {
        String next = value == null ? "" : value.trim();
        if (next.isEmpty()) {
            clear();
            return;
        }
        message = next;
        long safeDuration = Math.max(400L, Math.min(5000L, durationMs));
        visibleUntilMs = android.os.SystemClock.uptimeMillis() + safeDuration;
    }

    String current() {
        if (!isVisible()) {
            clear();
            return "";
        }
        return message;
    }

    boolean isVisible() {
        return !message.isEmpty() && android.os.SystemClock.uptimeMillis() < visibleUntilMs;
    }

    long remainingMs() {
        if (!isVisible()) return 0L;
        return Math.max(0L, visibleUntilMs - android.os.SystemClock.uptimeMillis());
    }

    void clear() {
        message = "";
        visibleUntilMs = 0L;
    }
}