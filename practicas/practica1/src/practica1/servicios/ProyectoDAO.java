/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.servicios;

import javax.persistence.EntityManager;

import practica1.modelo.Proyecto;
import practica1.utils.JpaDAO;

/**
 *
 * @author Angel
 */
public class ProyectoDAO extends JpaDAO<Proyecto, Long> {
    public ProyectoDAO(EntityManager em) {
        super(em);
    }
}
