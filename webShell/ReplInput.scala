package tacit.webShell

import org.scalajs.dom
import org.scalajs.dom.html
import scalacss.ScalatagsCss._
import scalatags.JsDom.all._

import CssSettings.Defaults._

final class ReplInput(
  val root: html.Form,
  val textInput: TextInput
) {
  def onSubmit(handler: dom.Event => Unit): Unit =
    root.onsubmit = handler

  def value: String = textInput.value

  def reset(): Unit = textInput.value = ""
}

object ReplInput {
  def apply(xs: Modifier*): ReplInput = {
    val textInput = TextInput()
    val root = form(
      Style.root,
      textInput.root,
      xs
    )
    new ReplInput(root.render, textInput)
  }

  object Style extends StyleSheet.Inline {
    import Style.dsl._

    val root: StyleA = style(
      backgroundColor(black),
      color(white)
    )
  }
}