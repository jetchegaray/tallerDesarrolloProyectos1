package views.html

import play.api.templates.Html
import play.mvc.Http.Context

object nonEmptyOrElse {
  def apply[T <: Seq[_]](t: T)(nonEmptyBlock: (T) => Html)(emptyBlock: => Html) = {
    if (t.nonEmpty) nonEmptyBlock(t) else emptyBlock
  }
}
