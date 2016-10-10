/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * Portions Copyright 2016 Dmitry Bilko
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(final int iMovieId) {
        movieId = iMovieId;
    }

    public String getMovieName() {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(final String sStartTime) {
        final StringTokenizer tokens = new StringTokenizer(sStartTime, ",");
        startTimeId = Integer.parseInt(tokens.nextToken());
        startTime = tokens.nextToken();
    }

    public int getStartTimeId() {
        return startTimeId;
    }

    public String getTheater() {
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
