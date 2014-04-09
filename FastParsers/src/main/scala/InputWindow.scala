import scala.language.implicitConversions

object InputWindow {

  class InputWindow[Input](val in: Input,val start: Int,val end: Int)
  //
  implicit def str(window: InputWindow[String]): String = window.in.substring(window.start,window.end)

}