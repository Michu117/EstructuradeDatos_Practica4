package unl.dance.base.models.jug;

public class Jarra {
    private Integer capacidad;
    private Integer capacidad_actual;

    public Jarra(){}
    
    public Jarra(Integer capacidad){
        this.capacidad = capacidad;
        this.capacidad_actual = 0;
    }

    public Integer getCapacidad() {
        return this.capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getCapacidad_actual() {
        return this.capacidad_actual;
    }

    public void setCapacidad_actual(Integer capacidad_actual) {
        this.capacidad_actual = capacidad_actual;
    }

}
