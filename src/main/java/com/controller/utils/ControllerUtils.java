package com.controller.utils;

import com.model.entity.Session;
import com.model.entity.enums.Genre;
import org.springframework.validation.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import static com.constant.ApplicationConstants.*;


public class ControllerUtils {
    /**
     * This method returns Map of Genre values and their i18ned names
     *
     * @return Map of Genre values and their names
     */
    public static Map<String, String> getGenreMap() {
        Map<String, String> genreGenreName = new LinkedHashMap<>();

        genreGenreName.put(Genre.ACTION.name(), "page.addmovie.genre.action");
        genreGenreName.put(Genre.ADVENTURE.name(), "page.addmovie.genre.adventure");
        genreGenreName.put(Genre.COMEDY.name(), "page.addmovie.genre.comedy");
        genreGenreName.put(Genre.CRIME.name(), "page.addmovie.genre.crime");
        genreGenreName.put(Genre.DRAMA.name(), "page.addmovie.genre.drama");
        genreGenreName.put(Genre.HISTORICAL.name(), "page.addmovie.genre.historical");
        genreGenreName.put(Genre.HORROR.name(), "page.addmovie.genre.horror");
        genreGenreName.put(Genre.MUSICAL.name(), "page.addmovie.genre.musical");
        genreGenreName.put(Genre.SCIENCE_FICTION.name(), "page.addmovie.genre.scifi");
        genreGenreName.put(Genre.WAR.name(), "page.addmovie.genre.war");
        genreGenreName.put(Genre.WESTERN.name(), "page.addmovie.genre.western");

        return genreGenreName;
    }

    /**
     * This method converts list of field errors from binding result to Map of fieldName + "Error" and error itself.
     *
     * @param bindingResult BindingResult, which can contain errors
     * @return Map of fieldName + "Error" and error text
     */
    public static Map<String, String> getErrorsMap(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldList -> fieldList.getField() + "Error", FieldError::getDefaultMessage));
    }

    public static List<LocalDate> getCurrentMonthDates(LocalDate date) {

        return Stream
                .iterate(date.withDayOfMonth(1), d -> d.plusDays(1))
                .limit(date.lengthOfMonth())
                .collect(Collectors.toList());

    }


    public static LocalTime calculateMinStartTime(List<Session> sessions) {
        if (sessions.size() == 0) {
            return MIN_SESSION_START_TIME;
        } else {
            LocalTime lastSessionEndTime = sessions.get(sessions.size() - 1).getEndTime();

            LocalTime plusFifteen = lastSessionEndTime.plusMinutes(15);
            int remainder = plusFifteen.getMinute() % MIN_SESSION_DELAY;

            if (remainder == 0) {
                return plusFifteen;
            } else {
                int minutesToAdd = MIN_SESSION_DELAY - remainder;
                return plusFifteen.plusMinutes(minutesToAdd);
            }
        }
    }

    /**
     * Method checks whether there is minimal gap between sessions (e.g. if there is one and half
     * an hour between two sessions).
     *
     * @param sessions List of sessions(grouped by day)
     * @return true if gap exists, false if not
     */
    public static boolean checkIfGapBetweenSessionsExists(List<Session> sessions) {
        if(sessions.isEmpty())
            return true;

        Session prev = sessions.get(0);
        Session last = sessions.get(sessions.size() - 1);
        LocalTime prevEnd = prev.getEndTime();
        LocalTime prevStart = prev.getStartTime();
        boolean hasGap = true;

        int minStartTimeMinutes = MIN_SESSION_START_TIME.getHour() * 60 + MIN_SESSION_START_TIME.getMinute();


        if (prev.getStartTime().minusMinutes(minStartTimeMinutes).isAfter(MIN_SESSION_START_TIME))
            return true;

        if(last.getEndTime().plusMinutes(MIN_SESSION_DELAY).isBefore(MAX_SESSION_START_TIME))
            return true;

//        for (Session s : sessions) {
//            int prevEndMinutes = prev.getEndTime().getHour() * 60 + prev.getEndTime().getMinute();
//            if (prevEnd.isAfter(s.getStartTime()) ||
//                    s.getStartTime().minusMinutes(prevEndMinutes).isBefore(MIN_GAP_BETWEEN_SESSIONS)) {
//                hasGap = false;
//            }
//            s = prev;
//        }
//
//        Collections.reverse(sessions);
//
//        for (Session s : sessions) {
//            if (prev.getStartTime().isAfter(s.getEndTime()))
//                hasGap = false;
//            s = prev;
//        }

        return hasGap;
    }
}
