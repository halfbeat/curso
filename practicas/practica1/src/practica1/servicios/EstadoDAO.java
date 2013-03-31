/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.servicios;

import javax.persistence.EntityManager;

import practica1.modelo.Estado;
import practica1.utils.JpaDAO;

/**
 *
 * @author Angel
 */
public class EstadoDAO extends JpaDAO<Estado, String> {

    public EstadoDAO(EntityManager em) {
        super(em);
    }
}
