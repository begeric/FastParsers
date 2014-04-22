package fastparsers.framework.parseresult

/**
 * Extractor for ParseResult in case of Failure
 */
object Failure {
  def unapply[T](p: ParseResult[T]) =
    if (!p.success) Some(p.msg)
    else None
}
