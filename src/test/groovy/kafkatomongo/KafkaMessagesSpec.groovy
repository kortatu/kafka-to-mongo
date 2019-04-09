package kafkatomongo

import io.micronaut.configuration.kafka.config.AbstractKafkaConfiguration
import io.micronaut.context.ApplicationContext
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class KafkaMessagesSpec extends Specification{

    @Shared Map<String, Object> config = kafkaEmbeddedConfig()

    @Shared @AutoCleanup
    ApplicationContext ctx = ApplicationContext.run(config)

    void "test message produced and consumed"() {
        given:
        MessageProducer messageProducer = ctx.getBean(MessageProducer.class)

        when:
        Single<KafkaMessage> singleMessage = messageProducer.sendMessage(new KafkaMessage("test", "testIP"))

        then:
        singleMessage.blockingGet() != null

        when:
        Single<KafkaMessage> secondMessage = messageProducer.sendMessage(new KafkaMessage("test", "testIP2"))

        then:
        def message = secondMessage.blockingGet()
        message != null

    }

    Map<String, Object> kafkaEmbeddedConfig() {
        def map = new HashMap<String, Object>()
        map.put(AbstractKafkaConfiguration.EMBEDDED, true)
        map.put(AbstractKafkaConfiguration.EMBEDDED_TOPICS, Collections.singletonList("aTopic"))
        return map
    };

}

