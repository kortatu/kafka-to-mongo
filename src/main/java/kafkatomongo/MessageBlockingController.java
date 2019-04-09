package kafkatomongo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

@Controller("/messagesB")
public class MessageBlockingController {

    private final KafkaMessageDao kafkaMessageDao;

    public MessageBlockingController(KafkaMessageDao kafkaMessageDao) {
        this.kafkaMessageDao = kafkaMessageDao;
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    public KafkaMessage addKafkaMessage(@Body Single<KafkaMessage> message) {
        return message.flatMap(kafkaMessageDao::insertOne).blockingGet();
    }

    @Get(value = "/search/byIp/{ip}", produces = MediaType.APPLICATION_JSON)
    public Iterable<KafkaMessage> getMessagesByIp(String ip) {
        return kafkaMessageDao.findByIp(ip).blockingIterable();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public KafkaMessage getMessage(String id) {
        return kafkaMessageDao.findOne(id).blockingGet();
    }
}
