package unl.dance.base.controller.services;

import org.springframework.transaction.annotation.Transactional;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import unl.dance.base.controller.data_struct.list.LinkedList;
import unl.dance.base.controller.data_struct.tree.search_no_data.NodeJug;
import unl.dance.base.controller.data_struct.tree.search_no_data.TreeJug;
import unl.dance.base.models.jug.Jarra;

@BrowserCallable
@Transactional
@AnonymousAllowed
public class TreeJugService {

    public String solveJugProblemSimple(Integer jgCapacidad, Integer jgInicial, Integer jpCapacidad, Integer jpInicial, Integer jgFinalCapacidad, Integer jgFinal, Integer jpFinalCapacidad, Integer jpFinal) {
        try {
            // Crear estado inicial
            Jarra jg = new Jarra(jgCapacidad);
            jg.setCapacidad_actual(jgInicial);
            Jarra jp = new Jarra(jpCapacidad);
            jp.setCapacidad_actual(jpInicial);
            NodeJug estadoInicial = new NodeJug(jg, jp);

            // Crear estado final
            Jarra jgf = new Jarra(jgFinalCapacidad);
            jgf.setCapacidad_actual(jgFinal);
            Jarra jpf = new Jarra(jpFinalCapacidad);
            jpf.setCapacidad_actual(jpFinal);
            NodeJug estadoFinal = new NodeJug(jgf, jpf);

            // Ejecutar búsqueda
            TreeJug tj = new TreeJug(estadoInicial, estadoFinal);
            NodeJug result = tj.search();

            if (result != null) {
                LinkedList<NodeJug> route = tj.route(result);
                return "SOLUCIÓN ENCONTRADA:\n" + route.print();
            } else {
                return "No se encontró solución para los estados dados";
            }

        } catch (Exception e) {
            return "Error al buscar la solución: " + e.getMessage();
        }
    }

   
}
