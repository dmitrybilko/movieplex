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
package com.bilko.movieplex.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.bilko.movieplex.util.Constants.END_TIME_MAX_SIZE;
import static com.bilko.movieplex.util.Constants.START_TIME_MAX_SIZE;

@Entity
@Table(name = "TIMESLOTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timeslot.findAll", query = "SELECT t FROM Timeslot t"),
    @NamedQuery(name = "Timeslot.findById", query = "SELECT t FROM Timeslot t WHERE t.id = :id"),
    @NamedQuery(name = "Timeslot.findByStartTime", query = "SELECT t FROM Timeslot t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "Timeslot.findByEndTime", query = "SELECT t FROM Timeslot t WHERE t.endTime = :endTime")
})
public class Timeslot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 1, max = START_TIME_MAX_SIZE)
    @Column(name = "START_TIME")
    private String startTime;

    @NotNull
    @Size(min = 1, max = END_TIME_MAX_SIZE)
    @Column(name = "END_TIME")
    private String endTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeslot")
    private Collection<ShowTiming> showTimings;

    public Timeslot() { }

    public Timeslot(final Integer iId) {
        this.id = iId;
    }

    public Timeslot(final Integer iId, final String sStartTime, final String sEndTime) {
        this.id = iId;
        this.startTime = sStartTime;
        this.endTime = sEndTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer iId) {
        this.id = iId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(final String sStartTime) {
        this.startTime = sStartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(final String sEndTime) {
        this.endTime = sEndTime;
    }

    @XmlTransient
    public Collection<ShowTiming> getShowTimings() {
        return showTimings;
    }

    public void setShowTimings(final Collection<ShowTiming> aShowTimings) {
        this.showTimings = aShowTimings;
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
        if (!(object instanceof Timeslot)) {
            return false;
        }
        final Timeslot other = (Timeslot) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return startTime;
    }
}
