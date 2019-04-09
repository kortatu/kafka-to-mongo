package kafkatomongo;

import static io.micronaut.configuration.kafka.annotation.OffsetReset.LATEST;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@KafkaListener(offsetReset = LATEST)
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    private final StatsController statsController;
    private final KafkaMessageDao kafkaMessageDao;

    @Inject
    public MessageListener(StatsController statsController, KafkaMessageDao kafkaMessageDao) {
        this.statsController = statsController;
        this.kafkaMessageDao = kafkaMessageDao;
    }

    @Topic("aTopic")
    public Single<KafkaMessage> receive(KafkaMessage message) {
        log.info("Message received through Kafka {} ({})", message.ip, message.name);
        this.statsController.lastStats().addInMessage(message.ip + "(" + ")" + message.name);
        return this.kafkaMessageDao.insertOne(message);
    }
}
