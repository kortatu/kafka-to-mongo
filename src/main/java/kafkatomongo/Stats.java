package kafkatomongo;

public class Stats {
    public int numberOfInMessages;
    public int numberOfValidMessages;
    public String lastMessage;

    public Stats(int numberOfInMessages, int numberOfValidMessages, String lastMessage) {
        this.numberOfInMessages = numberOfInMessages;
        this.numberOfValidMessages = numberOfValidMessages;
        this.lastMessage = lastMessage;
    }

    public void addInMessage(String message) {
        this.lastMessage = message;
        this.numberOfInMessages++;
    }
}
