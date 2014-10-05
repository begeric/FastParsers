package fastparsers.tools

/**
 * Created by Eric on 10.04.14.

see
  http://stackoverflow.com/questions/5260298/how-can-i-obtain-the-default-value-for-a-type-in-scala
  http://missingfaktor.blogspot.ch/2011/08/emulating-cs-default-keyword-in-scala.html
 */
class Default[+A](val default: A)

trait LowerPriorityImplicits {
	// Stop AnyRefs from clashing with AnyVals
	implicit def defaultNull[A <: AnyRef]: Default[A] = new Default[A](null.asInstanceOf[A])
}

object Default extends LowerPriorityImplicits {
	implicit object DefaultDouble extends Default[Double](0.0)
	implicit object DefaultFloat extends Default[Float](0.0F)
	implicit object DefaultInt extends Default[Int](0)
	implicit object DefaultLong extends Default[Long](0L)
	implicit object DefaultShort extends Default[Short](0)
	implicit object DefaultByte extends Default[Byte](0)
	implicit object DefaultChar extends Default[Char]('\u0000')
	implicit object DefaultBoolean extends Default[Boolean](false)
	implicit object DefaultUnit extends Default[Unit](())
	implicit object DefaultAny extends Default[Any](null)

	def value[A](implicit value: Default[A]): A = value.default
}