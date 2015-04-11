import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable.Specification
import play.api.mvc.{ Result, EssentialAction }
import play.api.mvc.Codec.utf_8
import play.api.mvc.Results.Ok
import play.api.test.FakeRequest
import play.api.test.Helpers._
import concurrent.{ Future, Await }
import concurrent.duration.Duration
import play.api.http.MimeTypes._
import play.api.libs.iteratee.Done
import play.api.libs.json.Json
import julienrf.play.jsonp.Jsonp

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "render the index page" in new WithApplication {
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("Render Json")
    }

    val filter = new Jsonp()(utf_8, play.api.libs.concurrent.Execution.Implicits.defaultContext)

    val textAction = EssentialAction(_ => Done(Ok("foo")))

    val jsonAction = EssentialAction(_ => Done(Ok(Json.obj("bar" -> "baz"))))

    def run(uri: String)(action: EssentialAction): Future[Result] =
      filter(action)(FakeRequest("GET", uri)).run

    "leave non-JSON results untouched" in {
      val result = run("/")(textAction)
      contentType(result) must equalTo(Some(TEXT))
      contentAsString(result) must equalTo("foo")
    }

    "leave JSON results untouched if there is no callback parameter in the query string" in {
      val result = run("/")(jsonAction)
      contentType(result) must equalTo(Some(JSON))
      contentAsString(result) must equalTo("""{"bar":"baz"}""")
    }

    "transform JSON results into JavaScript if there is a callback parameter in the query string" in {
      val result = run("/?callback=foo")(jsonAction)
      contentType(result) must equalTo(Some(JAVASCRIPT))
      contentAsString(result) must equalTo("""foo({"bar":"baz"});""")
    }

    "leave non-JSON results untouched even if there is a callback parameter in the query string" in {
      val result = run("/?callback=foo")(textAction)
      contentType(result) must equalTo(Some(TEXT))
      contentAsString(result) must equalTo("foo")
    }
  }
}
