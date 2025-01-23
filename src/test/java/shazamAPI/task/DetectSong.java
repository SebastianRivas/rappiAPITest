package shazamAPI.task;

import io.restassured.http.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.*;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DetectSong implements Task {
    private final Headers headers;
    private final String raw;

    public DetectSong(Headers headers, String raw) {
        this.headers = headers;
        this.raw = raw;
    }

    public static DetectSong withHeaders(Headers headers, String raw) {
        return instrumented(DetectSong.class, headers, raw);
    }

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Post.to("/detect")
            .with(request -> request.headers(headers).body(raw))
        );
    }
}