package kafkatomongo;

import java.util.UUID;

public class KafkaMessage {
    public String name;
    public String ip;
    public String id;

    KafkaMessage(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    void generateId() {
        id = UUID.randomUUID().toString();
    }

    public KafkaMessage() {
        //For Jackson and Bson
    }
}
