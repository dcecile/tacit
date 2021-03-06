package tacit.jsShell

import org.scalajs.dom
import org.scalajs.dom.document.body
import org.scalajs.dom.html
import org.scalajs.dom.window
import scala.language.postfixOps
import scalacss.ScalatagsCss._

import tacit.core.ReadEvalPrintChain

import CssSettings.Defaults._
import HtmlSettings.Defaults._

object Repl extends Render[html.Div] {
  def render: RenderComponent = {
    val replOutput = ReplOutput()
    val replInput = ReplInput()
    val root = div(
      Style.root,
      replOutput.root,
      replInput.root
    )
    (root, new Component(_, replOutput, replInput))
  }

  final class Component(
    val root: Root,
    val replOutput: ReplOutput.Component,
    val replInput: ReplInput.Component
  ) {
    def handleSubmit(event: dom.Event): Unit = {
      event.preventDefault()
      handleInput(replInput.value)
    }

    def handleInput(input: String): Unit = {
      replOutput.echoInput(input)
      val outputBlock =
        ReadEvalPrintChain.readEvalPrint(input)
      replOutput.writeBlock(outputBlock)
      replInput.reset()
      scrollToEnd()
    }

    def scrollToEnd(): Unit =
      window.scroll(0, maxScroll.toInt)

    def maxScroll: Double =
      body.scrollHeight - window.innerHeight

    def autofocus(): Unit = {
      replInput.autofocus()
      scrollToEnd()
    }

    replInput.onSubmit(handleSubmit(_))
  }

  object Style extends StyleSheet.Inline {
    import Style.dsl._

    val root: StyleA = style(
      display.flex,
      flexDirection.column,
      padding(6 em, `0`),
      width(60 ch),
    )
  }
}
