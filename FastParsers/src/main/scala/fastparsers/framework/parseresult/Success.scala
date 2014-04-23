package fastparsers.framework.parseresult

/**
 * Extractor for ParseResult in case of success
 */
object Success {
  def unapply[T,U](p: ParseResult[T,U]) =
    if (p.success) Some(p.result)
    else None
}
