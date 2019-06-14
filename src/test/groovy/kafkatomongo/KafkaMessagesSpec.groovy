package kafkatomongo

import io.micronaut.configuration.kafka.config.AbstractKafkaConfiguration
import io.micronaut.context.ApplicationContext
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class KafkaMessagesSpec extends Specification{

    @Shared Map<String, Object> config = kafkaEmbeddedConfig()

    @Shared @AutoCleanup
    ApplicationContext ctx = ApplicationContext.run(config)

    @Inject
    KafkaMessageDao kafkaMessageDao

    void "test message produced and consumed"() {
        given:
        MessageProducer messageProducer = ctx.getBean(MessageProducer.class)

        def message1 = new KafkaMessage("test", "testIP")
        when:
        Single<KafkaMessage> singleMessage = messageProducer.sendMessage(message1)
        Thread.sleep(500)

        then:
        singleMessage.blockingGet() != null
        println "${new Date()} Message 1 sent"
        kafkaMessageDao.insertOne(message1) >> Single.just(message1)

        def message2 = new KafkaMessage("test", "testIP2")
        when:
        Single<KafkaMessage> secondMessage = messageProducer.sendMessage(message2)
        Thread.sleep(500)

        then:
        def message = secondMessage.blockingGet()
        println "Message 2 sent"
        message != null
//        1 * kafkaMessageDao.insertOne(message2)

    }

    Map<String, Object> kafkaEmbeddedConfig() {
        def map = new HashMap<String, Object>()
        map.put(AbstractKafkaConfiguration.EMBEDDED, true)
        map.put(AbstractKafkaConfiguration.EMBEDDED_TOPICS, Collections.singletonList("aTopic"))
        return map
    };

    @MockBean(KafkaMessageMongoDao.class)
    KafkaMessageDao kafkaMessageDao() {
        Mock(KafkaMessageDao)
    }

}

