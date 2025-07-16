package unl.dance.base.controller.data_struct.tree.search_no_data;

import unl.dance.base.controller.data_struct.list.LinkedList;
import unl.dance.base.models.jug.Jarra;

public class TreeJug {

    private NodeJug first;
    private NodeJug end;
    private LinkedList<NodeJug> nodes;
    private LinkedList<NodeJug> node_list;

    public TreeJug(NodeJug first, NodeJug end) {
        this.first = first;
        this.end = end;
        node_list = new LinkedList<>();
        nodes = new LinkedList<>();
    }

    public NodeJug search() throws Exception {
        if (isFound(first, end)) {
            nodes.add(end);
            return end;
        } else {
            nodes = new LinkedList<>();
            node_list = new LinkedList<>();
            node_list.add(first);
            while (!node_list.isEmpty()) {
                NodeJug current = node_list.delete(0);
                if (isFound(current, end)) {
                    return current;
                } else {
                    LinkedList<NodeJug> rules = Rules.rules(current);
                    
                    
                    rules = deleteNodeJug(rules);
                    current.addSucesors(rules);
                    if (!rules.isEmpty()) {
                        NodeJug[] auxArray = rules.toArray();
                        for (NodeJug aux : auxArray) {
                            nodes.add(aux);
                            node_list.add(aux);
                            if (isFound(aux, end)) {
                                return aux;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private LinkedList<NodeJug> deleteNodeJug(LinkedList<NodeJug> rules) throws Exception {
        LinkedList<NodeJug> resp = new LinkedList<>();
        if (!rules.isEmpty()) {
            NodeJug[] rulesArray = rules.toArray();
            if(!nodes.isEmpty()) {
                NodeJug[] nodeArray = nodes.toArray();
                for (NodeJug rule : rulesArray){
                    Boolean band = true;
                    for(NodeJug aux:  nodeArray){
                        if (isFound(rule, aux)){
                            band = false;
                            break;
                        }
                    }
                    if (band) {
                        resp.add(rule);
                    }
                }
            } else{
                return rules;
            }
        }
        return resp;
    }

    public LinkedList<NodeJug> route(NodeJug searched) {
        LinkedList<NodeJug> route = new LinkedList<>();
        while (searched != null) {
            route.add(searched);
            searched = searched.getParent();
        }
        return route;
    }

    private Boolean isFound(NodeJug sf, NodeJug se) {
        return (sf.getJg().getCapacidad_actual() == se.getJg().getCapacidad_actual()
                && sf.getJp().getCapacidad_actual() == se.getJp().getCapacidad_actual());
    }

    public static void main(String[] args) {
        try {
            //State Initial
            Jarra jg = new Jarra(4);
            jg.setCapacidad_actual(0);
            Jarra jp = new Jarra(3);
            jp.setCapacidad_actual(0);
            NodeJug ei = new NodeJug(jg, jp);
            //State End
            Jarra jgf = new Jarra(4);
            jgf.setCapacidad_actual(2);
            Jarra jpf = new Jarra(3);
            jpf.setCapacidad_actual(3);
            NodeJug ef = new NodeJug(jgf, jpf);
            //1,0
            System.out.println("Estado Inicial: " + ei);
            System.out.println("Estado Final: " + ef);
            TreeJug tj = new TreeJug(ei, ef);
            NodeJug result = tj.search();
            LinkedList<NodeJug> route = tj.route(result);
            System.out.println("EL CAMINO ES:");
            System.out.println(route.print());
        } catch (Exception e) {
            System.out.println("No se ha encontrado el resultado: " + e.getMessage());
        }
    }
}
