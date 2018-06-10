package Agent;

public abstract class Action {
    public abstract State execute(Agent a, State s);

    public abstract String toString();
}
