package org.gdramis.jboss.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager em;

}
