package kafkatomongo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/stats")
public class StatsController {
    private Stats lastStats = new Stats(0, 0, null);

    @Get(produces = MediaType.APPLICATION_JSON)
    public Single<Stats> lastStats() {
        return Single.just(this.lastStats);
    }



}
