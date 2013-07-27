package views.html.helper

import play.api.templates.Html

object nonEmptyOrElse {
  def apply[T <: Seq[_]](t: T)(nonEmptyBlock: (T) => Html)(emptyBlock: => Html) = {
    if (t.nonEmpty) nonEmptyBlock(t) else emptyBlock
  }
}
