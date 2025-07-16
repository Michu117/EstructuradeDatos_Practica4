package unl.dance.base.controller.data_struct.tree.search_no_data;

import unl.dance.base.controller.data_struct.list.LinkedList;
import unl.dance.base.models.jug.Jarra;

public class Rules {
    public static LinkedList<NodeJug> rules(NodeJug node){
        Jarra jg = node.getJg();
        Jarra jp = node.getJp();
        Integer x = jg.getCapacidad_actual();
        Integer y = jp.getCapacidad_actual();
        LinkedList<NodeJug> list = new LinkedList<>();
        //1. Lennar la jarra grande al tope
        if(x < jg.getCapacidad()){
            list.add(new NodeJug(jg.getCapacidad(), y));
        }
        //2. Llenar la jarra pequeña al tope
        if(y < jp.getCapacidad()){
            list.add(new NodeJug(x, jp.getCapacidad()));
        }
        
        //3. Vaciar la jarra grande al suelo
        if(x > 0){
            list.add(new NodeJug(0, y));
        }
        
        //4. Vaciar la jarra pequeña al suelo
        if(y > 0){
            list.add(new NodeJug(x, 0));
        }

        //5. Vaciar de la jarra pequeña a la jarra grande
        if((x+y) <= jg.getCapacidad() && (y > 0)){
            list.add(new NodeJug(x + y, 0));
        }

        //6. Vaciar de la jarra grande a la jarra pequeña
        if((x+y) <= jp.getCapacidad() && (x > 0)){
            list.add(new NodeJug(0, x + y));
        }

        //7. Llenar la jarra grande con la jarra pequeña
        if((x + y) >= jg.getCapacidad() && (x < jg.getCapacidad()) && (y > 0)){
            list.add(new NodeJug(jg.getCapacidad(), y - (jg.getCapacidad() - x)));
        }

        //8. Llenar la jarra pequeña con la jarra grande
        if((x + y) >= jp.getCapacidad() && (y < jp.getCapacidad()) && (x > 0)){
            list.add(new NodeJug(x - (jp.getCapacidad() - y), jp.getCapacidad()));
        }

        return list;
    }

    
}
