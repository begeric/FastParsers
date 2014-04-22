package fastparsers.framework.parseresult

/**
 * Extractor for ParseResult in case of success
 */
object Success {
  def unapply[T](p: ParseResult[T]) =
    if (p.success) Some(p.result)
    else None
}
