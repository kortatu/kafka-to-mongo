package kafkatomongo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Controller("/messages")
public class MessageController {

    private final KafkaMessageDao kafkaMessageDao;

    public MessageController(KafkaMessageDao kafkaMessageDao) {
        this.kafkaMessageDao = kafkaMessageDao;
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    public Single<KafkaMessage> addKafkaMessage(@Body Single<KafkaMessage> message) {
        return message.flatMap(kafkaMessageDao::insertOne);
    }

    @Get(value = "/search/byIp/{ip}", produces = MediaType.APPLICATION_JSON)
    public Flowable<KafkaMessage> getMessagesByIp(String ip) {
        return kafkaMessageDao.findByIp(ip);
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Maybe<KafkaMessage> getMessage(String id) {
        return kafkaMessageDao.findOne(id);
    }
}
