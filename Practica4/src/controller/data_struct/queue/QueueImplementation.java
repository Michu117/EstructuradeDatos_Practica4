/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.data_struct.queue;
import controller.data_struct.list.LinkedList;
/**
 *
 * @author fabricio
 */

public class QueueImplementation<E> extends LinkedList<E> {
    private Integer top;

    public Integer getTop() {
        return this.top;
    }

    public QueueImplementation(Integer top){
        this.top = top;
    }

    protected Boolean isFullQueue() {
        return this.top >= getLength();
    }

    protected void queue(E info) throws Exception {
        if(!isFullQueue()) {
            add(info);
        } else {
            throw new ArrayIndexOutOfBoundsException("Queue full");
        }
    }
    protected E dequeue() throws Exception {       
        return deleteFirst();
        
    }
}
