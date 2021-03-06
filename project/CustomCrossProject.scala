package tacit.sbt

import org.scalajs.sbtplugin.cross._
import sbt._

object CustomCrossProject {
  object CustomCrossType extends CrossType {
    def projectDir(
      crossBase: File,
      projectType: String
    ): File =
      crossBase / s"target-$projectType"

    def sharedSrcDir(
      projectBase: File,
      conf: String
    ): Option[File] =
      Some(projectBase.getParentFile)
  }
}
