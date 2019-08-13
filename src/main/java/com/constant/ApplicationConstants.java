package com.constant;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public interface ApplicationConstants {
    LocalTime MAX_SESSION_START_TIME = LocalTime.of(22, 0);
    LocalTime MIN_SESSION_START_TIME = LocalTime.of(9, 0);

    int MIN_SESSION_DELAY = 15;
    LocalTime MIN_GAP_BETWEEN_SESSIONS = LocalTime.of(1, 30);
    Set<Integer> SIZES = new LinkedHashSet<>(Arrays.asList(1, 2, 5));

    int SEATS_PER_ROW = 10;
    int ROWS = 3;
}
