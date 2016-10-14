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

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Abstract EJB component to perform CRUD operations over JPA entities.
 * @param <T> JPA entity class
 */
abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    /**
     * Public constructor for {@code AbstractFacade} class.
     * @param entityClass of current implementation
     */
    public AbstractFacade(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Returns {@link EntityManager}'s instance for current {@link AbstractFacade}'s implementation.
     * @return {@link EntityManager}'s instance
     */
    protected abstract EntityManager getEntityManager();

    /**
     * @see EntityManager#persist(Object)
     * @param entity instance
     */
    public void create(final T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * @see EntityManager#merge(Object)
     * @param entity instance
     */
    public void edit(final T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * @see EntityManager#remove(Object)
     * @param entity instance
     */
    public void remove(final T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * @see EntityManager#find(Class, Object)
     * @param id of required entity
     * @return found entity instance or null if entity with given id doesn't exist
     */
    public T find(final Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Returns all the entities instances stored in database.
     * @return {@link List} of found entities instances
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        final CriteriaQuery criteriaQuery = getEntityManager()
                .getCriteriaBuilder()
                .createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));

        return getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }

    /**
     * Returns all the entities instances according to specified range.
     * @param range of found entities instances
     * @return {@link List} of found entities instances
     */
    @SuppressWarnings("unchecked")
    public List<T> findRange(final int[] range) {
        final CriteriaQuery criteriaQuery = getEntityManager()
                .getCriteriaBuilder()
                .createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));

        return getEntityManager()
                .createQuery(criteriaQuery)
                .setMaxResults(range[1] - range[0])
                .setFirstResult(range[0])
                .getResultList();
    }

    /**
     * Returns amount of stored entity instances.
     * @return amount of stored entity instances
     */
    @SuppressWarnings("unchecked")
    public int count() {
        final CriteriaQuery criteriaQuery = getEntityManager()
                .getCriteriaBuilder()
                .createQuery();
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(criteriaQuery.from(entityClass)));

        return ((Long) getEntityManager().createQuery(criteriaQuery).getSingleResult()).intValue();
    }
}
