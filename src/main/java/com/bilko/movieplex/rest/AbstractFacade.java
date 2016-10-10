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

abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(final Class<T> oEntityClass) {
        this.entityClass = oEntityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(final T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(final T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(final T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(final Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        final CriteriaQuery oCriteriaQuery = getEntityManager()
                .getCriteriaBuilder()
                .createQuery();
        oCriteriaQuery.select(oCriteriaQuery.from(entityClass));

        return getEntityManager()
                .createQuery(oCriteriaQuery)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findRange(final int[] aRange) {
        final CriteriaQuery oCriteriaQuery = getEntityManager()
                .getCriteriaBuilder()
                .createQuery();
        oCriteriaQuery.select(oCriteriaQuery.from(entityClass));

        return getEntityManager()
                .createQuery(oCriteriaQuery)
                .setMaxResults(aRange[1] - aRange[0])
                .setFirstResult(aRange[0])
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public int count() {
        final CriteriaQuery oCriteriaQuery = getEntityManager()
                .getCriteriaBuilder()
                .createQuery();
        oCriteriaQuery.select(getEntityManager().getCriteriaBuilder().count(oCriteriaQuery.from(entityClass)));

        return ((Long) getEntityManager().createQuery(oCriteriaQuery).getSingleResult()).intValue();
    }
}
