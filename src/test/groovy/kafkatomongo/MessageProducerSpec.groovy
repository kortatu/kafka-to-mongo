package kafkatomongo

import io.micronaut.configuration.kafka.config.AbstractKafkaConfiguration
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification


class MessageProducerSpec extends Specification{

    @Shared Map<String, Object> config = Collections.singletonMap(AbstractKafkaConfiguration.EMBEDDED, true);

    @Shared @AutoCleanup
    ApplicationContext ctx = ApplicationContext.run(config)


    void "test stats response"() {
        when:
        MessageProducer messageProducer = ctx.getBean(MessageProducer.class)

        then:
        messageProducer.sendMessage(new KafkaMessage("test", "testIP")) != null

    }
}

