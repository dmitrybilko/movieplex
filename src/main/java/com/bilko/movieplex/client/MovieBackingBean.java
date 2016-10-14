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
package com.bilko.movieplex.client;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.bilko.movieplex.entities.Movie;

/**
 * This class used to read characteristics of chosen {@link Movie}.
 *
 * @since 1.0
 */
@Named
@RequestScoped
public class MovieBackingBean {

    private int movieId;
    private String movieName;
    private String actors;

    /**
     * Returns movie's id.
     * @return movie's id
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Sets movie's id.
     */
    public void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    /**
     * Returns movie's name.
     * @return movie's name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets movie's name.
     */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    /**
     * Returns list of actors played in considered movie.
     * @return list of movie's actors
     */
    public String getActors() {
        return actors;
    }

    /**
     * Sets list of actors played in considered movie.
     */
    public void setActors(final String actors) {
        this.actors = actors;
    }
}
