package views.html.helper

import model.domain.Costable
import java.math.BigDecimal

package object budget {

    def range(costable: Costable): String = {
        val min = toMoney(costable.getLowerEstimate());
        val max = toMoney(costable.getUpperEstimate());
        f"$min y $max"
    }

    def toMoney(cost: BigDecimal): String = {
        val value = cost.intValue();
        f"$$ $value%,d"
    }
}
