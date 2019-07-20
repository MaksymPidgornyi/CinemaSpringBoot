package com.controller;

import java.time.LocalTime;

public interface ApplicationConstants {
    LocalTime MIN_SESSION_START_TIME = LocalTime.of(9, 0);
    LocalTime MAX_SESSION_START_TIME = LocalTime.of(22, 0);
}
