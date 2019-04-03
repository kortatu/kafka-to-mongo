package kafkatomongo;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface KafkaMessageDao {

    Maybe<KafkaMessage> findOne(String id);

    Flowable<KafkaMessage> findByIp(String ip);

    Single<KafkaMessage> insertOne(KafkaMessage kafkaMessage);


}
