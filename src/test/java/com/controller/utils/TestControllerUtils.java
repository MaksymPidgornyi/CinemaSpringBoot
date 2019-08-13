package com.controller.utils;

import com.model.entity.Session;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class TestControllerUtils {

    @Test
    public void testCheckIfGapBetweenSessionsExists(){
        List<Session> sessions = new ArrayList<>();

        Session session = new Session();
        session.setStartTime(LocalTime.of(9,0));
        session.setEndTime(LocalTime.of(10, 30));

        for(int i = 0; i < 8; i++){
            sessions.add(session);

            LocalTime start = session.getStartTime();
            LocalTime end = session.getEndTime();

            session = new Session();
            session.setStartTime(start.plusMinutes(100));
            session.setEndTime(end.plusMinutes(100));
        }

        boolean hasGap = ControllerUtils.checkIfGapBetweenSessionsExists(sessions);

//        sessions.forEach(s -> System.out.println(s.getStartTime() + ", " + s.getEndTime() + ";"));

        assertFalse(hasGap);

        sessions.remove(2);

        hasGap = ControllerUtils.checkIfGapBetweenSessionsExists(sessions);

        assertTrue(hasGap);
    }
}
