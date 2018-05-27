package tacit.jsShell

import org.scalajs.dom.html
import scala.language.postfixOps
import scalacss.ScalatagsCss._
import scalatags.JsDom.all.{
  Double2CssNumber => _,
  Int2CssNumber => _,
  _
}

import CssSettings.Defaults._

object HomeShell extends Render[html.Div] {
  def render: RenderComponent = {
    val root = div(
      Style.root,
      HomeTitle().root
    )
    (root, new Component(_))
  }

  final class Component(
    val root: Root
  ) extends Shell.Component {}

  object Style extends StyleSheet.Inline {
    import Style.dsl._

    val root: StyleA = style(
      display.flex,
      flexDirection.column,
      padding.vertical(2.6 em),
      padding.horizontal(8 ch)
    )
  }
}
