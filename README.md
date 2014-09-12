FastParsers [![Build Status](https://travis-ci.org/begeric/FastParsers.svg)](https://travis-ci.org/begeric/FastParsers)
===========

FastParsers is a Scala parser library which uses macros to transform easy-to-write parser combinators into efficient recursive-descent backtracking parsers. 
The generated parsers are about 20x faster than [Scala's parser combinator library](https://github.com/scala/scala-parser-combinators) even though its interface stay about the same.

Here is an example of a basic JSON parser 
```scala
val jsonParser = FastParser {
 def value: Parser[Any] = obj | arr | stringLit |
                          decimalNumber | "null" | "true" | "false"
 def obj: Parser[Any] = lit("{") ~> repsep (member, ",") <~ "}"
 def arr: Parser[Any] = lit("[") ~> repsep (value , ",") <~ "]"
 def member: Parser[Any] = stringLit ~ (lit(":") ~> value)
}
```
This generate a *Parser Object" which allow you to call any of the transformed rules.
```scala
val cnt = "{\"firstName \": \"John\" , \"age\": 25}"
jsonParser.value (cnt) match {
      case Success ( result ) =>
        println ("success : " + result )
      case Failure ( error ) =>
        println (" failure : " + error )
}
```

For a more comprehensive presentation please take a look at the [presentation slides](https://github.com/begeric/FastParsers/blob/experiment/Acceleration%20Parser%20Combinators%20with%20Macros%20-%20Uppsala%202014.pdf).

To have a better understanding of the internals please read the [paper describing the implementation](http://infoscience.epfl.ch/record/200905?ln=en).
