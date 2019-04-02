package kafkatomongo

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.client.HttpClient
import io.micronaut.http.HttpRequest;
import io.micronaut.runtime.server.EmbeddedServer;
import spock.lang.*;

class StatsControllerSpec extends Specification{

    @Shared @AutoCleanup EmbeddedServer embeddedServer =
            ApplicationContext.run(EmbeddedServer)

    @Shared
    StatsClient client = embeddedServer.applicationContext.getBean(StatsClient)

    void "test stats response"() {
        expect:
        client.lastStats().blockingGet() != null

    }
}
