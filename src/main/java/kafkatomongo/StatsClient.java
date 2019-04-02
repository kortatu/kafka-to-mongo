package kafkatomongo;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/stats")
public interface StatsClient {

    @Get
    Single<Stats> lastStats();
}
