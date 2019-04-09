package kafkatomongo;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.reactivex.Single;

@KafkaClient("message-prod")
public interface MessageProducer {

    @Topic("aTopic")
    Single<KafkaMessage> sendMessage(KafkaMessage message);
}
