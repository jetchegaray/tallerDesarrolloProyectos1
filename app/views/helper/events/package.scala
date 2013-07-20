package views.html.helper

import play.data.Form

package object events {
    def formFor[T](event: T) : Form[T] = {
        Form.form(event.getClass().asInstanceOf[Class[T]]).fill(event)
    }
}
