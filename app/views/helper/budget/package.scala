package views.html.helper

import model.domain.Event
import scala.util.Random

package object budget {

    def range(event: Event): String = {
        // TODO: Extract budget from event (Ideally instead of event we should receive a Budgeteable or similar)
        val random = new Random();
        val min = random.nextInt(150) * 100 + 10000;
        val max = random.nextInt(350) * 100 + 25000;
        f"$$ $min%,d y $$ $max%,d"
    }
}
