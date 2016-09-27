package com.bilko.movieplex.booking;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.bilko.movieplex.entities.Movie;
import com.bilko.movieplex.entities.ShowTiming;

@Named
@FlowScoped("booking")
public class Booking implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    private int movieId;
    private String startTime;
    private int startTimeId;

    public final int getMovieId() {
        return movieId;
    }

    public final void setMovieId(final int iMovieId) {
        movieId = iMovieId;
    }

    public final String getMovieName() {
        try {
            return entityManager
                .createNamedQuery("Movie.findById", Movie.class)
                .setParameter("id", movieId)
                .getSingleResult()
                .getName();
        } catch (final NoResultException e) {
            return "";
        }
    }

    public final String getStartTime() {
        return startTime;
    }

    public final void setStartTime(final String sStartTime) {
        final StringTokenizer tokens = new StringTokenizer(sStartTime, ",");
        startTimeId = Integer.parseInt(tokens.nextToken());
        startTime = tokens.nextToken();
    }

    public final int getStartTimeId() {
        return startTimeId;
    }

    public final String getTheater() {
        try {
            final List<ShowTiming> showTimings = entityManager
                .createNamedQuery("ShowTiming.findByMovieAndTimeslotId", ShowTiming.class)
                .setParameter("movieId", movieId)
                .setParameter("timeslotId", startTimeId)
                .getResultList();
            if (showTimings.isEmpty()) {
                return "none";
            }
            return showTimings
                .get(0)
                .getTheater()
                .getId()
                .toString();
        } catch (final NoResultException e) {
            return "none";
        }
    }
}
