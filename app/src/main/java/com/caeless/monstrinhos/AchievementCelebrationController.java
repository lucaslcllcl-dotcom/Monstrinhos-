package com.caeless.monstrinhos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

/** Presents newly unlocked achievements one at a time without changing save schema. */
final class AchievementCelebrationController {
    private static boolean showing;
    private AchievementCelebrationController() {}

    static void showNext(final Activity activity, final View anchor) {
        if (activity == null || activity.isFinishing() || showing) return;
        final AchievementCelebration.Event event = AchievementCelebration.next(activity);
        if (event == null) return;
        showing = true;

        final String announcement = AchievementCelebration.accessibilityAnnouncement(event);
        if (anchor != null) anchor.announceForAccessibility(announcement);

        new AlertDialog.Builder(activity)
                .setTitle("Conquista desbloqueada! ⭐")
                .setMessage(event.card.title + "\n\n" + event.card.description)
                .setPositiveButton("Legal!", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        AchievementCelebration.markSeen(activity, event);
                        showing = false;
                        dialog.dismiss();
                        if (anchor != null) anchor.post(new Runnable() {
                            @Override public void run() { showNext(activity, anchor); }
                        });
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override public void onCancel(DialogInterface dialog) {
                        AchievementCelebration.markSeen(activity, event);
                        showing = false;
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialog) { showing = false; }
                })
                .show();
    }
}
