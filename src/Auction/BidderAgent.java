package Auction;


import Agent.Agent;
import Classes.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class BidderAgent extends Agent implements Runnable {
    private String name;
    private BlockingQueue Rqueue = null;
    private BlockingQueue Pqueue = null;
    private Percept p;
    private Response response;
    private String InOrNot;
    private List<Item> ItemList = new ArrayList<>();
    private int buget;

    public String getName() {
        return name;
    }

    public BidderAgent(BlockingQueue pqueue, BlockingQueue rqueue, int buget, String name) {
        this.Pqueue = pqueue;
        this.Rqueue = rqueue;
        this.buget = buget;
        this.name = name;

    }

    @Override
    public void run() {

        while (true) {
            {
                waitForAuctioner();


                try {
                    p = (Percept) Pqueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Am primit " + p.getI().toString());

            if (p.getStatus().equals("SOLD")) {
                System.out.println("Eu " + Thread.currentThread().getName() + " am castigat!");
                ItemList.add(p.getI());
                break;
            }



            generateMaxBid(p.getI());

            if (InOrNot.equals("IN")) {
                System.out.println("Eu ma bag! "+this.getName());
                Response response1 = new Response();
                response1.setMessage("IN");
                response1.setBidderAgent(this);
                Rqueue.add(response1);

            } else {
                System.out.println("Eu " + Thread.currentThread().getName() + " nu ma mai bag!");
                break;

            }
            waitForAuctioner();

        }
    }



    @Override
    public void see(Percept p) {
        this.p = p;
    }

    @Override
    public void selectAction() {

    }
    public void waitForAuctioner()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void generateMaxBid(Item item)
    {
        Random r = new Random();
        int priority = r.nextInt(100)+1;
        int price = item.getPrice();
        int procent = 0;
        if (price > buget)
        {
            InOrNot = "NOT";
            return;
        }
        else
        {
            procent = (price*100)/buget;
        }
        if (priority<10)
        {
            InOrNot = "NOT";
            return;
        }
        if (priority<33)
        {
            if (procent<30)
            {
                InOrNot = "IN";
                return;
            }
            else
            {
                InOrNot = "NOT";
                return;
            }
        }
        if (priority<75)
        {
            if (procent<40)
            {
                InOrNot = "IN";
                return;
            }
            else
            {
                InOrNot = "NOT";
                return;
            }
        }
        if (priority<90) {
            if (procent < 70) {
                InOrNot = "IN";
                return;
            } else {
                InOrNot = "NOT";
                return;
            }
        }
        if (priority<100)
        {
            if (procent<85)
            {
                InOrNot = "IN";
                return;
            }
            else
            {
                InOrNot = "NOT";
                return;
            }
        }
        if (priority==100)
        {
            InOrNot = "IN";
            return;
        }
    }

    @Override
    public String toString() {
        return "BidderAgent{" +
                "name='" + name + '\'' +
                '}';
    }
}
