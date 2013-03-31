/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.servicios;

import javax.persistence.EntityManager;

import practica1.modelo.Cliente;
import practica1.utils.JpaDAO;

/**
 *
 * @author Angel
 */
public class ClienteDAO extends JpaDAO<Cliente, Long> {

    public ClienteDAO(EntityManager em) {
        super(em);
    }
}
