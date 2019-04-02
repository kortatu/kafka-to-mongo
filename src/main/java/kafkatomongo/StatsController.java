package kafkatomongo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

@Controller("/stats")
public class StatsController {
    private final MessageProducer messageProducer;
    private Stats lastStats = new Stats(0, 0, null);

    public StatsController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public Stats lastStats() {
        return this.lastStats;
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    public Single<KafkaMessage> addKafkaMessage(@Body Single<KafkaMessage> message) {
            return message.flatMap(messageProducer::sendMessage);
    }

}
