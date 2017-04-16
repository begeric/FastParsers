// Generated file using sbt> test:run-main lms.parsing.TestCSV
package lms

object LMSCSVBooleanParseGen extends CSVBooleanParseGen(
  "false".length,
  "false".toArray,
  "true".length,
  "true".toArray,
  "false".toArray,
  "true".toArray
)

/*****************************************
  Emitting Generated Code
*******************************************/
case class CSVBooleanParseGen(px71:Int,px72:Array[Char],px74:Int,px75:Array[Char],px340:Array[Char],px341:Array[Char]) extends ((Array[Char])=>ParseResultAnon6507737) {
def apply(x0:Array[Char]): ParseResultAnon6507737 = {
var x2: Anon6507737 = null
var x3: Boolean = true
var x4: Int = -1
val x6 = x0.length
val x7 = 0 >= x6
val x650 = if (x7) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x16 = x0(0)
val x17 = x16 == '['
val x648 = if (x17) {
var x21: java.lang.String = ""
var x22: Boolean = false
val x18 = 0 + 1
var x23: Int = x18
var x25: Int = -1
var x26: Boolean = true
var x27: Int = x18
val x62 = while ({val x28 = x26
val x29 = x25
val x30 = x27
val x31 = x29 != x30
val x32 = x28 && x31
x32}) {
val x34 = x27
x25 = x34
val x36 = x34 >= x6
val x60 = if (x36) {
x26 = false
()
} else {
val x40 = x0(x34)
val x41 = x40 == ' '
val x42 = x40 == '\n'
val x43 = x41 || x42
val x58 = if (x43) {
val x46 = x21
val x47 = x22
val x48 = x23
x21 = x46
x22 = false
val x44 = x34 + 1
x23 = x44
x27 = x44
()
} else {
x26 = false
()
}
x58
}
x60
}
val x63 = x21
val x64 = x22
val x65 = x23
val x642 = if (x64) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x193 = scala.collection.mutable.ListBuffer[Anon6507737]()
val x71 = px71 // static data: 5
val x72 = px72 // static data: Array(f,a,l,s,e)
val x73 = new Anon6507737(1,null)
val x135 = {x83: (Int) =>
var x85: Anon6507737 = null
var x86: Boolean = true
var x87: Int = x83
val x89 = x83 + x71
val x90 = x89 > x6
val x129 = if (x90) {
x85 = null
x86 = true
x87 = x83
()
} else {
var x96: Int = 0
var x97: Boolean = true
val x115 = while ({val x98 = x97
val x99 = x96
val x100 = x99 < x71
val x101 = x98 && x100
x101}) {
val x103 = x96
val x104 = x83 + x103
val x105 = x0(x104)
val x106 = x72(x103)
val x108 = x105 == x106
val x111 = if (x108) {
()
} else {
x97 = false
()
}
val x112 = x103 + 1
x96 = x112
()
}
val x116 = x97
val x127 = if (x116) {
x85 = x73
x86 = false
x87 = x89
()
} else {
x85 = null
x86 = true
x87 = x83
()
}
x127
}
val x130 = x85
val x131 = x86
val x132 = x87
val x133 = new ParseResultAnon6507737(x130,x131,x132)
x133: ParseResultAnon6507737
}
val x74 = px74 // static data: 4
val x75 = px75 // static data: Array(t,r,u,e)
val x76 = new Anon6507737(2,null)
val x192 = {x77: (Int) =>
var x79: Anon6507737 = null
var x80: Boolean = true
var x81: Int = x77
val x136 = x135(x77)
val x137 = x136.empty
val x186 = if (x137) {
val x138 = x77 + x74
val x139 = x138 > x6
val x178 = if (x139) {
x79 = null
x80 = true
x81 = x77
()
} else {
var x145: Int = 0
var x146: Boolean = true
val x164 = while ({val x147 = x146
val x148 = x145
val x149 = x148 < x74
val x150 = x147 && x149
x150}) {
val x152 = x145
val x153 = x77 + x152
val x154 = x0(x153)
val x155 = x75(x152)
val x157 = x154 == x155
val x160 = if (x157) {
()
} else {
x146 = false
()
}
val x161 = x152 + 1
x145 = x161
()
}
val x165 = x146
val x176 = if (x165) {
x79 = x76
x80 = false
x81 = x138
()
} else {
x79 = null
x80 = true
x81 = x77
()
}
x176
}
x178
} else {
val x180 = x136.res
x79 = x180
x80 = x137
val x183 = x136.next
x81 = x183
()
}
val x187 = x79
val x188 = x80
val x189 = x81
val x190 = new ParseResultAnon6507737(x187,x188,x189)
x190: ParseResultAnon6507737
}
val x340 = px340 // static data: Array(f,a,l,s,e)
val x400 = {x348: (Int) =>
var x350: Anon6507737 = null
var x351: Boolean = true
var x352: Int = x348
val x354 = x348 + x71
val x355 = x354 > x6
val x394 = if (x355) {
x350 = null
x351 = true
x352 = x348
()
} else {
var x361: Int = 0
var x362: Boolean = true
val x380 = while ({val x363 = x362
val x364 = x361
val x365 = x364 < x71
val x366 = x363 && x365
x366}) {
val x368 = x361
val x369 = x348 + x368
val x370 = x0(x369)
val x371 = x340(x368)
val x373 = x370 == x371
val x376 = if (x373) {
()
} else {
x362 = false
()
}
val x377 = x368 + 1
x361 = x377
()
}
val x381 = x362
val x392 = if (x381) {
x350 = x73
x351 = false
x352 = x354
()
} else {
x350 = null
x351 = true
x352 = x348
()
}
x392
}
val x395 = x350
val x396 = x351
val x397 = x352
val x398 = new ParseResultAnon6507737(x395,x396,x397)
x398: ParseResultAnon6507737
}
val x341 = px341 // static data: Array(t,r,u,e)
val x457 = {x342: (Int) =>
var x344: Anon6507737 = null
var x345: Boolean = true
var x346: Int = x342
val x401 = x400(x342)
val x402 = x401.empty
val x451 = if (x402) {
val x403 = x342 + x74
val x404 = x403 > x6
val x443 = if (x404) {
x344 = null
x345 = true
x346 = x342
()
} else {
var x410: Int = 0
var x411: Boolean = true
val x429 = while ({val x412 = x411
val x413 = x410
val x414 = x413 < x74
val x415 = x412 && x414
x415}) {
val x417 = x410
val x418 = x342 + x417
val x419 = x0(x418)
val x420 = x341(x417)
val x422 = x419 == x420
val x425 = if (x422) {
()
} else {
x411 = false
()
}
val x426 = x417 + 1
x410 = x426
()
}
val x430 = x411
val x441 = if (x430) {
x344 = x76
x345 = false
x346 = x403
()
} else {
x344 = null
x345 = true
x346 = x342
()
}
x441
}
x443
} else {
val x445 = x401.res
x344 = x445
x345 = x402
val x448 = x401.next
x346 = x448
()
}
val x452 = x344
val x453 = x345
val x454 = x346
val x455 = new ParseResultAnon6507737(x452,x453,x454)
x455: ParseResultAnon6507737
}
val x530 = {x201: (Int) =>
var x203: scala.collection.immutable.List[Anon6507737] = null
var x204: Boolean = true
var x205: Int = x201
val x207 = x192(x201)
val x208 = x207.empty
val x524 = if (x208) {
x203 = null
x204 = true
x205 = x201
()
} else {
var x216: scala.collection.mutable.ListBuffer[Anon6507737] = x193
var x217: Boolean = false
val x214 = x207.next
var x218: Int = x214
var x220: Int = -1
var x221: Boolean = true
var x222: Int = x214
val x487 = while ({val x223 = x221
val x224 = x220
val x225 = x222
val x226 = x224 != x225
val x227 = x223 && x226
x227}) {
val x229 = x222
x220 = x229
var x232: java.lang.String = ""
var x233: Boolean = false
var x234: Int = x229
var x236: Int = -1
var x237: Boolean = true
var x238: Int = x229
val x273 = while ({val x239 = x237
val x240 = x236
val x241 = x238
val x242 = x240 != x241
val x243 = x239 && x242
x243}) {
val x245 = x238
x236 = x245
val x247 = x245 >= x6
val x271 = if (x247) {
x237 = false
()
} else {
val x251 = x0(x245)
val x252 = x251 == ' '
val x253 = x251 == '\n'
val x254 = x252 || x253
val x269 = if (x254) {
val x257 = x232
val x258 = x233
val x259 = x234
x232 = x257
x233 = false
val x255 = x245 + 1
x234 = x255
x238 = x255
()
} else {
x237 = false
()
}
x269
}
x271
}
val x274 = x232
val x275 = x233
val x276 = x234
val x485 = if (x275) {
x221 = false
()
} else {
val x283 = x276 >= x6
val x483 = if (x283) {
x221 = false
()
} else {
val x287 = x0(x276)
val x288 = x287 == ','
val x481 = if (x288) {
var x292: java.lang.String = ""
var x293: Boolean = false
val x289 = x276 + 1
var x294: Int = x289
var x296: Int = -1
var x297: Boolean = true
var x298: Int = x289
val x333 = while ({val x299 = x297
val x300 = x296
val x301 = x298
val x302 = x300 != x301
val x303 = x299 && x302
x303}) {
val x305 = x298
x296 = x305
val x307 = x305 >= x6
val x331 = if (x307) {
x297 = false
()
} else {
val x311 = x0(x305)
val x312 = x311 == ' '
val x313 = x311 == '\n'
val x314 = x312 || x313
val x329 = if (x314) {
val x317 = x292
val x318 = x293
val x319 = x294
x292 = x317
x293 = false
val x315 = x305 + 1
x294 = x315
x298 = x315
()
} else {
x297 = false
()
}
x329
}
x331
}
val x334 = x292
val x335 = x293
val x336 = x294
val x477 = if (x335) {
x221 = false
()
} else {
val x458 = x457(x336)
val x459 = x458.empty
val x475 = if (x459) {
x221 = false
()
} else {
val x462 = x216
val x463 = x217
val x464 = x218
val x466 = x458.res
val x467 = x462 += x466
x216 = x467
x217 = false
val x468 = x458.next
x218 = x468
x222 = x468
()
}
x475
}
x477
} else {
x221 = false
()
}
x481
}
x483
}
x485
}
val x488 = x216
val x489 = x217
val x490 = x218
val x496 = if (x489) {
val x492 = new ParseResultListAnon6507737(null,true,x490)
x492
} else {
val x493 = x488.toList
val x494 = new ParseResultListAnon6507737(x493,false,x490)
x494
}
val x497 = x496.empty
val x504 = if (x497) {
val x209 = new ParseResultTuple2Anon6507737ListAnon6507737(null,true,x201)
x209
} else {
val x499 = x496.res
val x501 = x496.next
val x498 = x207.res
val x500 = new Tuple2Anon6507737ListAnon6507737(x498,x499)
val x502 = new ParseResultTuple2Anon6507737ListAnon6507737(x500,false,x501)
x502
}
val x505 = x504.empty
val x516 = if (x505) {
val x506 = x504.next
val x507 = new ParseResultListAnon6507737(null,true,x506)
x507
} else {
val x509 = x504.res
val x510 = x509._1
val x511 = x509._2
val x513 = x504.next
val x512 = x510 :: x511
val x514 = new ParseResultListAnon6507737(x512,false,x513)
x514
}
val x517 = x516.res
x203 = x517
val x519 = x516.empty
x204 = x519
val x521 = x516.next
x205 = x521
()
}
val x525 = x203
val x526 = x204
val x527 = x205
val x528 = new ParseResultListAnon6507737(x525,x526,x527)
x528: ParseResultListAnon6507737
}
val x194 = List()
val x550 = {x195: (Int) =>
var x197: scala.collection.immutable.List[Anon6507737] = null
var x198: Boolean = true
var x199: Int = x195
val x531 = x530(x195)
val x532 = x531.empty
val x544 = if (x532) {
x197 = x194
x198 = false
x199 = x195
()
} else {
val x538 = x531.res
x197 = x538
x198 = x532
val x541 = x531.next
x199 = x541
()
}
val x545 = x197
val x546 = x198
val x547 = x199
val x548 = new ParseResultListAnon6507737(x545,x546,x547)
x548: ParseResultListAnon6507737
}
val x551 = x550(x65)
val x552 = x551.empty
val x640 = if (x552) {
x2 = null
x3 = true
val x553 = x551.next
x4 = x553
()
} else {
var x560: java.lang.String = ""
var x561: Boolean = false
val x553 = x551.next
var x562: Int = x553
var x564: Int = -1
var x565: Boolean = true
var x566: Int = x553
val x601 = while ({val x567 = x565
val x568 = x564
val x569 = x566
val x570 = x568 != x569
val x571 = x567 && x570
x571}) {
val x573 = x566
x564 = x573
val x575 = x573 >= x6
val x599 = if (x575) {
x565 = false
()
} else {
val x579 = x0(x573)
val x580 = x579 == ' '
val x581 = x579 == '\n'
val x582 = x580 || x581
val x597 = if (x582) {
val x585 = x560
val x586 = x561
val x587 = x562
x560 = x585
x561 = false
val x583 = x573 + 1
x562 = x583
x566 = x583
()
} else {
x565 = false
()
}
x597
}
x599
}
val x602 = x560
val x603 = x561
val x604 = x562
val x638 = if (x603) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x611 = x604 >= x6
val x636 = if (x611) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x617 = x0(x604)
val x618 = x617 == ']'
val x634 = if (x618) {
val x621 = x551.res
val x623 = x621.asInstanceOf[scala.collection.immutable.List[Anon6507737]]
val x624 = new Anon6507737(6,x623)
x2 = x624
x3 = false
val x619 = x604 + 1
x4 = x619
()
} else {
x2 = null
x3 = true
x4 = 0
()
}
x634
}
x636
}
x638
}
x640
}
x642
} else {
x2 = null
x3 = true
x4 = 0
()
}
x648
}
val x651 = x2
val x652 = x3
val x653 = x4
val x654 = new ParseResultAnon6507737(x651,x652,x653)
x654
}
}
/*****************************************
  End of Generated Code
*******************************************/
