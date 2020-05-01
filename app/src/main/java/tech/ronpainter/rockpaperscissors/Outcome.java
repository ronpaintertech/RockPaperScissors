package tech.ronpainter.rockpaperscissors;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Enums and returns the outcome message
 */
public enum Outcome {
    WIN,
    LOSS,
    TIE;


    public static String getOutcomeMsg(@NonNull Context context, Outcome value) {
        switch (value) {
            case WIN:
                return context.getString(R.string.win);
            case LOSS:
                return context.getString(R.string.loss);
            case TIE:
                return context.getString(R.string.tie);
            default:
                return context.getString(R.string.unknown);
        }
    }
}
