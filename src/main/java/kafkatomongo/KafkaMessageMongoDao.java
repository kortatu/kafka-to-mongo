package kafkatomongo;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class KafkaMessageMongoDao implements KafkaMessageDao {

    private final MongoClient mongoClient;

    @Inject
    public KafkaMessageMongoDao(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Maybe<KafkaMessage> findOne(String id) {
        return Flowable.fromPublisher(
                getMessageCollection()
                    .find(eq("_id", id))
                    .limit(1)
        ).firstElement();
    }

    @Override
    public Flowable<KafkaMessage> findByIp(String ip) {
        return Flowable.fromPublisher(
                getMessageCollection()
                        .find(eq("ip", ip))
                        .limit(100)
        );
    }

    @Override
    public Single<KafkaMessage> insertOne(KafkaMessage kafkaMessage) {
        kafkaMessage.generateId();
        return Single.fromPublisher(
                getMessageCollection().insertOne(kafkaMessage)
        ).map(success -> kafkaMessage);
    }

    private MongoCollection<KafkaMessage> getMessageCollection() {
        return mongoClient.getDatabase("kafka-messages")
                .getCollection("kafkaMessages", KafkaMessage.class);
    }
}
