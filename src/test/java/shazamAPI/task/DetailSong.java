package shazamAPI.task;

import io.restassured.http.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.*;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DetailSong implements Task{
    private final String id;

    public DetailSong(String id) {
        this.id = id;
    }

    public static DetailSong withId(String id) {
        return instrumented(DetailSong.class, id);
    }

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource("/get-details?id={id}")
            .with(request -> request.pathParam("id", id))
        );
    }
}