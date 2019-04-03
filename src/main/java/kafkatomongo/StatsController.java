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
    private final KafkaMessageDao kafkaMessageDao;
    private Stats lastStats = new Stats(0, 0, null);

    public StatsController(MessageProducer messageProducer, KafkaMessageDao kafkaMessageDao) {
        this.messageProducer = messageProducer;
        this.kafkaMessageDao = kafkaMessageDao;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public Stats lastStats() {
        return this.lastStats;
    }



}
