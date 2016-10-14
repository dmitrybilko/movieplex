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
package com.bilko.movieplex.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bilko.movieplex.entities.Movie;

/**
 * Implementation class of {@link AbstractFacade} related to {@link Movie} JPA entity.
 */
@Named
@Stateless
@Path("movies")
public class MovieFacadeRest extends AbstractFacade<Movie> {

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Public constructor for {@code MovieFacadeRest} class.
     */
    public MovieFacadeRest() {
        super(Movie.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(final Movie movie) {
        super.create(movie);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    @Path("{id}")
    public void edit(final Movie movie) {
        super.edit(movie);
    }

    /**
     * @see AbstractFacade#remove(Object)
     * @param id of {@link Movie} to be removed
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") final Integer id) {
        super.remove(super.find(id));
    }

    /**
     * @see AbstractFacade#find(Object)
     * @param id of required {@link Movie}
     * @return found {@link Movie} instance
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Movie find(@PathParam("id") final Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Movie> getAll() {
        return super.getAll();
    }

    /**
     * @see AbstractFacade#findRange(int[])
     * @param from value for range
     * @param to value for range
     * @return {@link List} of found {@link Movie}s
     */
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Movie> findRange(@PathParam("from") final Integer from, @PathParam("to") final Integer to) {
        return super.findRange(new int[] {from, to});
    }

    /**
     * Returns amount of stored {@link Movie}s
     * @return amount of stored {@link Movie}s
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public String getCount() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
