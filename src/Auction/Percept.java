package Auction;

import Agent.Agent;
import Classes.Item;

public class Percept {
    private Item i;
    private Agent a;
    private String status = "NOT SOLD";

    public Percept(Item i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return null;
    }

    public Item getI() {
        return i;
    }

    public void setI(Item i) {
        this.i = i;
    }

    public Agent getA() {
        return a;
    }

    public void setA(Agent a) {
        this.a = a;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
