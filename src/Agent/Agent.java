package Agent;

import Auction.Percept;

public abstract class Agent {

    public abstract void see(Percept p);

    public abstract void selectAction();

}
