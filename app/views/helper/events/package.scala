package views.html.helper

import play.data.Form
import play.api.mvc.Call
import model.domain.Task
import model.domain.tasks._

package object events {

    def formFor[T](event: T) : Form[T] = {
        Form.form(event.getClass().asInstanceOf[Class[T]]).fill(event)
    }

    def routeFromName(eventName: String) : Call = {
        eventName match {
            case "Civil"     => controllers.routes.EventsController.show("CIVIL")
            case "Ceremonia" => controllers.routes.EventsController.show("CEREMONY")
            case "Fiesta"    => controllers.routes.EventsController.show("PARTY")
            case _ => controllers.routes.UserController.user()
        }
    }

    def titleFromType(eventType: String) : String = {
        eventType match {
            case "CIVIL"    => "Civil"
            case "CEREMONY" => "Ceremonia"
            case "PARTY"    => "Fiesta"
        }
    }
}
