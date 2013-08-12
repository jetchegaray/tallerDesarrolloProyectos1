package views.html.helper

import model.domain.Wedding
import model.domain.Event

import collection.JavaConversions._

package object wedding {

    def nameCouple(wedding: Wedding): String = {
        val left = wedding.bridesName;
        val right = wedding.groomsName;
        f"$left y $right"
    }

    def expenseTypeOptions(wedding: Wedding): Seq[(String, String)] = {
        val events: Seq[Event] = wedding.getEvents()

        Seq("" -> "General") ++ events.map { event =>
            (event.getTypeName(), event.name)
        }
    }
}
