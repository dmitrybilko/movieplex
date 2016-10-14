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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * JPA entity corresponding database table {@code THEATERS}.
 *
 * @see ShowTiming
 * @since 1.0
 */
@Entity
@Table(name = "THEATERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Theater.findAll", query = "SELECT t FROM Theater t"),
    @NamedQuery(name = "Theater.findById", query = "SELECT t FROM Theater t WHERE t.id = :id"),
    @NamedQuery(name = "Theater.findByCapacity", query = "SELECT t FROM Theater t WHERE t.capacity = :capacity")
})
public class Theater implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private Integer id;

    @NotNull
    private int capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theater")
    private Collection<ShowTiming> showTimings;

    /**
     * Constructor for {@code Theater}
     */
    public Theater() { }

    /**
     * Constructor for {@code Theater}
     * @param id of {@code Theater}
     */
    public Theater(final Integer id) {
        this.id = id;
    }

    /**
     * Constructor for {@code Theater}
     * @param id of {@code Theater}
     * @param capacity of {@code Theater}
     */
    public Theater(final Integer id, final int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    /**
     * Returns value of {@link Theater#id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets value of {@link Theater#id}
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Returns value of {@link Theater#capacity}
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets value of {@link Theater#capacity}
     */
    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns value of {@link Theater#showTimings}
     */
    @XmlTransient
    public Collection<ShowTiming> getShowTimings() {
        return showTimings;
    }

    /**
     * Sets value of {@link Theater#showTimings}
     */
    public void setShowTimings(final Collection<ShowTiming> showTimings) {
        this.showTimings = showTimings;
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
        if (!(object instanceof Theater)) {
            return false;
        }
        final Theater other = (Theater) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Theater[id=" + id + "]";
    }
}
