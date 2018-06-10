import Auction.AuctioneerAgent;
import Auction.BidderAgent;
import Auction.Percept;
import Classes.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BlockingQueue Rqueue = new ArrayBlockingQueue(1024);
        BlockingQueue Pqueue = new ArrayBlockingQueue(1024);
        AuctioneerAgent producer = new AuctioneerAgent(Pqueue,Rqueue);
        Item i = new Item("Pizza",1);
        Percept p = new Percept(i);
        producer.see(p);
        BidderAgent consumer1 = new BidderAgent(Pqueue,Rqueue,20,"m");
        BidderAgent consumer2 = new BidderAgent(Pqueue,Rqueue,23,"g");
        BidderAgent consumer3 = new BidderAgent(Pqueue,Rqueue,50,"a");
        BidderAgent consumer4 = new BidderAgent(Pqueue,Rqueue,50,"d");
        List<BidderAgent> bidderAgents = new ArrayList<>();
        bidderAgents.add(consumer1);
        bidderAgents.add(consumer2);
        bidderAgents.add(consumer3);
        bidderAgents.add(consumer4);
        producer.setListAgent(bidderAgents);

        Thread a = new Thread(producer);
        a.setName("ag");
        a.start();

        Thread b1 = new Thread(consumer1);
        b1.setName("m");
        b1.start();

        Thread b2 = new Thread(consumer2);
        b2.setName("g");
        b2.start();

        Thread b3 = new Thread(consumer3);
        b3.setName("a");
        b3.start();

        Thread b4 = new Thread(consumer4);
        b4.setName("d");
        b4.start();

//        Thread.sleep(4000);
    }
}