package kafkatomongo;

public class KafkaMessage {
    public String name;
    public String ip;

    KafkaMessage(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    KafkaMessage() {
        //For Jackson
    }
}
