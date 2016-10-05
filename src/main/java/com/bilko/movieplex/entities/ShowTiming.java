/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
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
/*
 * Portions Copyright 2016 Dmitry Bilko
 */
package com.bilko.movieplex.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "SHOW_TIMINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShowTiming.findAll", query = "SELECT s FROM ShowTiming s"),
    @NamedQuery(name = "ShowTiming.findById", query = "SELECT s FROM ShowTiming s WHERE s.id = :id"),
    @NamedQuery(name = "ShowTiming.findByMovieAndTimeslotId",
        query = "SELECT s FROM ShowTiming s WHERE s.movie.id = :movieId AND s.timeslot.id = :timeslotId"),
    @NamedQuery(name = "ShowTiming.findByDay", query = "SELECT s FROM ShowTiming s WHERE s.day = :day")
})
public class ShowTiming implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private Integer id;

    @NotNull
    private int day;

    @JoinColumn(name = "TIMESLOT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Timeslot timeslot;

    @JoinColumn(name = "THEATER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Theater theater;

    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Movie movie;

    public ShowTiming() { }

    public ShowTiming(final Integer iId) {
        this.id = iId;
    }

    public ShowTiming(final Integer iId, final int iDay) {
        this.id = iId;
        this.day = iDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer iId) {
        this.id = iId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(final int iDay) {
        this.day = iDay;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(final Timeslot oTimeslot) {
        this.timeslot = oTimeslot;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(final Theater oTheater) {
        this.theater = oTheater;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie oMovie) {
        this.movie = oMovie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (id != null) {
            hash += id.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof ShowTiming)) {
            return false;
        }
        final ShowTiming other = (ShowTiming) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return movie.getName() + ", " + timeslot.getStartTime();
    }
}
