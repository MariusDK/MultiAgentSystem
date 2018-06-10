package Auction;

import Agent.Agent;
import Classes.Item;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class AuctioneerAgent extends Agent implements Runnable{

    private BlockingQueue Pqueue = null;
    private BlockingQueue Rqueue = null;
    private List<BidderAgent> ListAgent = new ArrayList<>();
    private Item item;
    private Percept p;
    private BidderAgent bestBidder;
    private int Round=1;

    public AuctioneerAgent(BlockingQueue pqueue,BlockingQueue rqueue) {
        this.Pqueue = pqueue;
        this.Rqueue = rqueue;
    }

    @Override
    public void see(Percept p) {
        this.p = p;
    }

    @Override
    public void selectAction() {

    }

    @Override
    public void run() {

        while (ListAgent.size() > 1) {

            System.out.println("Runda "+Round);
                for (BidderAgent ba : ListAgent) {
                    try {
                        Pqueue.put(p);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            ListAgent = new ArrayList<>();
            waitForBidders();
            while (!Rqueue.isEmpty()) {
                Response r;
                try {
                    r = (Response) Rqueue.take();
                    if (r.getMessage().equals("IN")) {

                        ListAgent.add(r.getBidderAgent());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            bestBidder = ListAgent.get(ListAgent.size() - 1);
            if (ListAgent.size() <= 1) {
                System.out.println("Castigatorul este " + bestBidder.getName());
                p.setStatus("SOLD");
                try {
                    Pqueue.put(p);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                int price = p.getI().getPrice();
                price++;
                p.getI().setPrice(price);
            }
            Round++;
        }
    }



    public List<BidderAgent> getListAgent() {
        return ListAgent;
    }

    public void setListAgent(List<BidderAgent> listAgent) {
        ListAgent = listAgent;
    }

    public void waitForBidders()
    {
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setP(Percept p) {
        this.p = p;
    }
}
