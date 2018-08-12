package org.universe.realestatebiddingsystem.app.util;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ONE;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.ZERO;

public final class StringUtils {
    public static String capitalize(String word) {
        return Character.toUpperCase(word.charAt(ZERO)) + word.substring(ONE);
    }

    private StringUtils() {
    }
}
