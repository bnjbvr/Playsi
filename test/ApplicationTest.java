import controllers.routes;
import models.Stage;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;
import services.StageService;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
        Global.loadFixtures();
    }

    @Test
    public void testRechercheStages() {
        List<Stage> stages = StageService.chercherStages(null, null, null, null, null);
        assertThat(stages).isNotNull();
        assertThat(stages).isNotEmpty();
        assertThat(stages.get(0).getTitre()).isEqualTo("Implémentation d'un algorithme de kéké");

        assertThat(StageService.chercherStages(null, 5, null, null, null)).isNotEmpty();
        assertThat(StageService.chercherStages(null, null, 3, null, null)).isNotEmpty();
        assertThat(StageService.chercherStages(null, null, null, "Paris", null)).isNotEmpty();

        String[] mots_cles = {"implémentation", "algo"};
        assertThat(StageService.chercherStages(mots_cles, null, null, null, null)).hasSize(2);

        assertThat(StageService.chercherStages(mots_cles, null, null, "Paris", null)).isEmpty();
        assertThat(StageService.chercherStages(mots_cles, null, null, "Lyon", null)).hasSize(1);
    }

    @Test
    public void testLoginOk() {
        Result result = callAction(
                routes.ref.StaticPages.login("regular_auth", "root", "435b41068e8665513a20070c033b08b9c66e4332")
        );
        assert( status(result) == 200 );
        ObjectNode collectedJson = (ObjectNode) Json.parse(contentAsString(result));
        assertThat( collectedJson.has("statut") );
        assertThat( collectedJson.get("statut").asText() ).isEqualTo("ok");
        assertThat( session(result).containsKey("login") );
        assertThat( session(result).get("login") ).isEqualTo("root");
    }

    @Test
    public void testLoginNok() {
        Result result = callAction(
                routes.ref.StaticPages.login("regular_auth", "root", "tort")
                // et le tort tue
                // et le tue meurt
                // et le meurt trie
                // et le tri, c'est Raptor
                // et le tort tue, etc.
        );

        assert( status(result) == 200 );
        ObjectNode collectedJson = (ObjectNode) Json.parse(contentAsString(result));
        assertThat( collectedJson.has("statut") );
        assertThat( collectedJson.get("statut").asText() ).isNotEqualTo("ok");
        assertThat( ! session(result).containsKey("login") );
    }
}
