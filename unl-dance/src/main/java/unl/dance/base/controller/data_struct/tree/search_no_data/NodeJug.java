package unl.dance.base.controller.data_struct.tree.search_no_data;

import unl.dance.base.controller.data_struct.list.LinkedList;
import unl.dance.base.models.jug.Jarra;

public class NodeJug {
    private Jarra jg;
    private Jarra jp;
    private NodeJug parent;
    private LinkedList<NodeJug> sucesors = new LinkedList<>();

    public NodeJug(Jarra jg, Jarra jp) {
        this.jg = jg;
        this.jp = jp;
    }

    public NodeJug(Integer x, Integer y) {
        this.jg = new Jarra(4);
        this.jp = new Jarra(3);
        jg.setCapacidad_actual(x);
        jp.setCapacidad_actual(y);
    }

    public Jarra getJg() {
        return this.jg;
    }

    public void setJg(Jarra jg) {
        this.jg = jg;
    }

    public Jarra getJp() {
        return this.jp;
    }

    public void setJp(Jarra jp) {
        this.jp = jp;
    }

    public NodeJug getParent() {
        return this.parent;
    }

    public void setParent(NodeJug parent) {
        this.parent = parent;
    }

    public LinkedList<NodeJug> getSucesors() {
        return this.sucesors;
    }

    public void setSucesors(LinkedList<NodeJug> sucesors) {
        this.sucesors = sucesors;
    }

    @Override
    public String toString() {
        return "(JG="+jg.getCapacidad_actual()+":JP="+jp.getCapacidad_actual()+")";
    }

    public void addSucesors(LinkedList<NodeJug> rules){
        sucesors = new LinkedList<>();
        if(!rules.isEmpty()){
            NodeJug[] rulesArrays = rules.toArray();
            for(NodeJug aux: rulesArrays){
                aux.setParent(this);
                sucesors.add(aux);
            }
        }
    }
}
