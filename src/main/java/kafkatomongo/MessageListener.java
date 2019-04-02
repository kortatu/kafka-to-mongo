package kafkatomongo;

import static io.micronaut.configuration.kafka.annotation.OffsetReset.LATEST;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.text.MessageFormat;

@KafkaListener(offsetReset = LATEST)
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    private final StatsController statsController;

    @Inject
    public MessageListener(StatsController statsController) {
        this.statsController = statsController;
    }

//    @Topic("aTopic")
    public void receive(KafkaMessage message) {
        log.info(MessageFormat.format("Message received through Kafka {0} ({1})", message.ip, message.name));
        this.statsController.lastStats().addInMessage(message.ip + "(" + ")" + message.name);
    }
}
