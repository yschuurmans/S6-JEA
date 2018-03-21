/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.PermissionGroup;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author frankcoenen
 */
@Stateless
public class PermissionGroupDAO extends DaoFacade<PermissionGroup>  {

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    public PermissionGroupDAO() {
        super(PermissionGroup.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
