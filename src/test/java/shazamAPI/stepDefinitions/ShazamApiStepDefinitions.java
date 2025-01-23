package shazamAPI.stepDefinitions;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.*;
import net.serenitybdd.screenplay.rest.interactions.*;
import net.thucydides.model.util.EnvironmentVariables;
import shazamAPI.task.*;

public class ShazamApiStepDefinitions {
    String apiURL;
    DetectSong detectSong;
    DetailSong detailSong;
    EnvironmentVariables environmentVariables;
    
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Given("{actor} desea buscar una cancion en la api de shazam")
    public void buscar_una_cancion_en_la_api_de_shazam(Actor actor) {
        apiURL = environmentVariables.optionalProperty("restapi.baseurl").orElse("https://shazam.p.rapidapi.com/songs/v2");
        actor.whoCan(CallAnApi.at(apiURL));
    }

    @Given("{actor} desea buscar el detalle de una cancion en la api de shazam")
    public void buscar_el_detalle_de_una_cancion_en_la_api_de_shazam(Actor actor) {
        apiURL = environmentVariables.optionalProperty("restapi.baseurl").orElse("https://shazam.p.rapidapi.com/songs/v2");
        actor.whoCan(CallAnApi.at(apiURL));
    }

    @When("{actor} utiliza el endpoint {string} con {string}")
    public void se_utiliza_el_endpoint(Actor actor, String endPoint, String raw) {
        switch (endPoint) {
            case "songs/detect":
                Header rapidapi_key = new Header("x-rapidapi-key", "fc41260419msh01ac5d2d1bc3f6ep1def72jsnfee4ea638cca");
                Header rapidapi_host = new Header("x-rapidapi-host", "shazam.p.rapidapi.com");
                Header Content_Type = new Header("Content-Type", "text/plain");
                Headers headers = new Headers(rapidapi_key, rapidapi_host, Content_Type);

                detectSong = new DetectSong(headers, raw);

                actor.attemptsTo(
                    DetectSong.withHeaders(headers, raw)
                );

                break;
        
            case "get-details":
                actor.attemptsTo(
                    DetailSong.withId(raw)
                );

                break;
        
            default:
                break;
        }
    }

    @Then("{actor} verifica que se retorna una lista de posibles canciones")
    public void se_retorna_una_lista_de_posibles_canciones(Actor actor) {
        
    }

    @Then("{actor} verifica que se retorna una lista vacia")
    public void se_retorna_una_lista_vacia(Actor actor) {
        
    }

    @Then("{actor} verifica que se retorna el detalle de la cancion")
    public void se_retorna_el_detalle_de_la_cancion(Actor actor) {
        
    }

    @Then("{actor} verifica que se retorna el detalle vacio")
    public void se_retorna_el_detalle_vacio(Actor actor) {

    }
}