package kafkatomongo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/stats")
public class StatsController {
    private Stats lastStats = new Stats(0, 0, null);

    @Get(produces = MediaType.APPLICATION_JSON)
    public Stats lastStats() {
        return this.lastStats;
    }



}
