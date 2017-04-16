package fastparsers.framework.parseresult

/**
 * Extractor for ParseResult in case of Failure
 */
object Failure {
  def unapply[T,U](p: ParseResult[T,U]) =
    if (!p.success) Some(p.error)
    else None
}
