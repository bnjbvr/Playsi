package controllers;

import models.Stage;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.StageService;
import views.html.etudiants.evenements;
import views.html.etudiants.stages;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Benjamin
 * Date: 27/02/13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class Etudiants extends Controller {

    public static Result entretiens() {
        // TODO
        return TODO;
    }

    public static Result stages() {
        return ok(stages.render());
    }

    public static Result apiStages(String mots_cles_str, Integer annee, String lieu, String entreprise) {

        String[] mots_cles = null;
        if( !mots_cles_str.isEmpty() ) {
            mots_cles = mots_cles_str.split(" ");
        }

        if( annee == 0 ) {
            annee = null;
        }

        if( lieu.isEmpty() ) {
            lieu = null;
        }

        if( entreprise.isEmpty() ) {
            entreprise = null;
        }

        Logger.info("Recherche de stages: mots_cles = " + mots_cles + " / annee = " + annee + " / lieu = " + lieu + " / entreprise = " + entreprise);
        List<Stage> stages = StageService.chercherStages(mots_cles, annee, null, lieu, entreprise);

        // conversion en json
        ObjectNode json = Json.newObject();
        json.put("code", "ok");
        ArrayNode jsonStages = new ArrayNode(JsonNodeFactory.instance);
        for( Stage s : stages ) {
            jsonStages.add( s.toJson() );
        }
        json.put("stages", jsonStages);
        return ok(json);
    }

    public static Result cvs() {
        // TODO
        return TODO;
    }

    public static Result evenements() {
        return ok(evenements.render());
    }
}
