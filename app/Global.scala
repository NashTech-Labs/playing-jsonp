
import play.api.mvc.WithFilters
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import julienrf.play.jsonp.Jsonp
import play.api.Logger
import play.api.Application
import play.api.GlobalSettings

object Global extends WithFilters(new Jsonp) with GlobalSettings {
  
  val log: Logger = Logger(this.getClass)
  
  /**
   * When application starts, the onStart method will be called. 
   *
   * @param app 
   */
  override def onStart(app: Application): Unit = {
    log.warn("Application Start.......")
  }

  /**
   * When application stops, the onStop method will be called. 
   * 
   * @param app
   */
  override def onStop(app: Application): Unit = {
    log.warn("Application shutdown.......")
  }
  
}
