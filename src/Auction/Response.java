package Auction;

public class Response {
    private BidderAgent bidderAgent;
    private String message;

    public Response(BidderAgent bidderAgent, String message) {
        this.bidderAgent = bidderAgent;
        this.message = message;
    }

    public Response() {
    }

    public BidderAgent getBidderAgent() {
        return bidderAgent;
    }

    public void setBidderAgent(BidderAgent bidderAgent) {
        this.bidderAgent = bidderAgent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return " Response{" +
                "bidderAgent=" + bidderAgent +
                ", message='" + message + '\'' +
                '}';
    }
}
