package lms

object LMSCSVStringLitParseGen extends CSVStringLitParseGen()

/*****************************************
  Emitting Generated Code
*******************************************/
case class CSVStringLitParseGen() extends ((Array[Char])=>ParseResultListAnon1680061013) {
def apply(x0:Array[Char]): ParseResultListAnon1680061013 = {
var x2: scala.collection.immutable.List[Anon1680061013] = null
var x3: Boolean = true
var x4: Int = -1
val x6 = x0.length
val x7 = 0 >= x6
val x712 = if (x7) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x15 = x0(0)
val x16 = x15 == '['
val x710 = if (x16) {
var x20: java.lang.String = ""
var x21: Boolean = false
val x17 = 0 + 1
var x22: Int = x17
var x24: Int = -1
var x25: Boolean = true
var x26: Int = x17
val x61 = while ({val x27 = x25
val x28 = x24
val x29 = x26
val x30 = x28 != x29
val x31 = x27 && x30
x31}) {
val x33 = x26
x24 = x33
val x35 = x33 >= x6
val x59 = if (x35) {
x25 = false
()
} else {
val x39 = x0(x33)
val x40 = x39 == ' '
val x41 = x39 == '\n'
val x42 = x40 || x41
val x57 = if (x42) {
val x45 = x20
val x46 = x21
val x47 = x22
x20 = x45
x21 = false
val x43 = x33 + 1
x22 = x43
x26 = x43
()
} else {
x25 = false
()
}
x57
}
x59
}
val x62 = x20
val x63 = x21
val x64 = x22
val x704 = if (x63) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x70 = scala.collection.mutable.ListBuffer[Anon1680061013]()
val x145 = {x102: (Int) =>
var x104: Int = 0
var x105: Boolean = true
var x106: Int = x102
val x108 = x102 >= x6
val x139 = if (x108) {
x104 = 0
x105 = true
x106 = x102
()
} else {
val x114 = x0(x102)
val x115 = x114 == '\\'
val x137 = if (x115) {
val x116 = x102 + 1
val x118 = x116 >= x6
val x131 = if (x118) {
x104 = 0
x105 = true
x106 = x116
()
} else {
x104 = x116
x105 = false
val x125 = x116 + 1
x106 = x125
()
}
x131
} else {
x104 = 0
x105 = true
x106 = x102
()
}
x137
}
val x140 = x104
val x141 = x105
val x142 = x106
val x143 = new ParseResultInt(x140,x141,x142)
x143: ParseResultInt
}
val x182 = {x96: (Int) =>
var x98: Int = 0
var x99: Boolean = true
var x100: Int = x96
val x146 = x145(x96)
val x147 = x146.empty
val x176 = if (x147) {
val x148 = x96 >= x6
val x168 = if (x148) {
x98 = 0
x99 = true
x100 = x96
()
} else {
val x153 = x0(x96)
val x155 = x153 == '"'
val x166 = if (x155) {
x98 = 0
x99 = true
x100 = x96
()
} else {
x98 = x96
x99 = false
val x160 = x96 + 1
x100 = x160
()
}
x166
}
x168
} else {
val x170 = x146.res
x98 = x170
x99 = x147
val x173 = x146.next
x100 = x173
()
}
val x177 = x98
val x178 = x99
val x179 = x100
val x180 = new ParseResultInt(x177,x178,x179)
x180: ParseResultInt
}
val x418 = {x375: (Int) =>
var x377: Int = 0
var x378: Boolean = true
var x379: Int = x375
val x381 = x375 >= x6
val x412 = if (x381) {
x377 = 0
x378 = true
x379 = x375
()
} else {
val x387 = x0(x375)
val x388 = x387 == '\\'
val x410 = if (x388) {
val x389 = x375 + 1
val x391 = x389 >= x6
val x404 = if (x391) {
x377 = 0
x378 = true
x379 = x389
()
} else {
x377 = x389
x378 = false
val x398 = x389 + 1
x379 = x398
()
}
x404
} else {
x377 = 0
x378 = true
x379 = x375
()
}
x410
}
val x413 = x377
val x414 = x378
val x415 = x379
val x416 = new ParseResultInt(x413,x414,x415)
x416: ParseResultInt
}
val x455 = {x369: (Int) =>
var x371: Int = 0
var x372: Boolean = true
var x373: Int = x369
val x419 = x418(x369)
val x420 = x419.empty
val x449 = if (x420) {
val x421 = x369 >= x6
val x441 = if (x421) {
x371 = 0
x372 = true
x373 = x369
()
} else {
val x426 = x0(x369)
val x428 = x426 == '"'
val x439 = if (x428) {
x371 = 0
x372 = true
x373 = x369
()
} else {
x371 = x369
x372 = false
val x433 = x369 + 1
x373 = x433
()
}
x439
}
x441
} else {
val x443 = x419.res
x371 = x443
x372 = x420
val x446 = x419.next
x373 = x446
()
}
val x450 = x371
val x451 = x372
val x452 = x373
val x453 = new ParseResultInt(x450,x451,x452)
x453: ParseResultInt
}
val x595 = {x78: (Int) =>
var x80: scala.collection.immutable.List[Anon1680061013] = null
var x81: Boolean = true
var x82: Int = x78
val x84 = x78 >= x6
val x589 = if (x84) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x92 = x0(x78)
val x93 = x92 == '"'
val x587 = if (x93) {
var x184: Int = 0
var x185: Boolean = false
val x94 = x78 + 1
var x186: Int = x94
var x188: Int = -1
var x189: Boolean = true
var x190: Int = x94
val x217 = while ({val x191 = x189
val x192 = x188
val x193 = x190
val x194 = x192 != x193
val x195 = x191 && x194
x195}) {
val x197 = x190
x188 = x197
val x199 = x182(x197)
val x200 = x199.empty
val x215 = if (x200) {
x189 = false
()
} else {
val x203 = x184
val x204 = x185
val x205 = x186
val x207 = x203 + 1
x184 = x207
x185 = false
val x208 = x199.next
x186 = x208
x190 = x208
()
}
x215
}
val x218 = x184
val x219 = x185
val x220 = x186
val x224 = x220 >= x6
val x581 = if (x224) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x230 = x0(x220)
val x231 = x230 == '"'
val x579 = if (x231) {
var x236: scala.collection.mutable.ListBuffer[Anon1680061013] = x70
var x237: Boolean = false
val x232 = x220 + 1
var x238: Int = x232
var x240: Int = -1
var x241: Boolean = true
var x242: Int = x232
val x539 = while ({val x243 = x241
val x244 = x240
val x245 = x242
val x246 = x244 != x245
val x247 = x243 && x246
x247}) {
val x249 = x242
x240 = x249
var x252: java.lang.String = ""
var x253: Boolean = false
var x254: Int = x249
var x256: Int = -1
var x257: Boolean = true
var x258: Int = x249
val x293 = while ({val x259 = x257
val x260 = x256
val x261 = x258
val x262 = x260 != x261
val x263 = x259 && x262
x263}) {
val x265 = x258
x256 = x265
val x267 = x265 >= x6
val x291 = if (x267) {
x257 = false
()
} else {
val x271 = x0(x265)
val x272 = x271 == ' '
val x273 = x271 == '\n'
val x274 = x272 || x273
val x289 = if (x274) {
val x277 = x252
val x278 = x253
val x279 = x254
x252 = x277
x253 = false
val x275 = x265 + 1
x254 = x275
x258 = x275
()
} else {
x257 = false
()
}
x289
}
x291
}
val x294 = x252
val x295 = x253
val x296 = x254
val x537 = if (x295) {
x241 = false
()
} else {
val x303 = x296 >= x6
val x535 = if (x303) {
x241 = false
()
} else {
val x307 = x0(x296)
val x308 = x307 == ','
val x533 = if (x308) {
var x312: java.lang.String = ""
var x313: Boolean = false
val x309 = x296 + 1
var x314: Int = x309
var x316: Int = -1
var x317: Boolean = true
var x318: Int = x309
val x353 = while ({val x319 = x317
val x320 = x316
val x321 = x318
val x322 = x320 != x321
val x323 = x319 && x322
x323}) {
val x325 = x318
x316 = x325
val x327 = x325 >= x6
val x351 = if (x327) {
x317 = false
()
} else {
val x331 = x0(x325)
val x332 = x331 == ' '
val x333 = x331 == '\n'
val x334 = x332 || x333
val x349 = if (x334) {
val x337 = x312
val x338 = x313
val x339 = x314
x312 = x337
x313 = false
val x335 = x325 + 1
x314 = x335
x318 = x335
()
} else {
x317 = false
()
}
x349
}
x351
}
val x354 = x312
val x355 = x313
val x356 = x314
val x529 = if (x355) {
x241 = false
()
} else {
val x360 = x356 >= x6
val x527 = if (x360) {
x241 = false
()
} else {
val x365 = x0(x356)
val x366 = x365 == '"'
val x525 = if (x366) {
var x457: Int = 0
var x458: Boolean = false
val x367 = x356 + 1
var x459: Int = x367
var x461: Int = -1
var x462: Boolean = true
var x463: Int = x367
val x490 = while ({val x464 = x462
val x465 = x461
val x466 = x463
val x467 = x465 != x466
val x468 = x464 && x467
x468}) {
val x470 = x463
x461 = x470
val x472 = x455(x470)
val x473 = x472.empty
val x488 = if (x473) {
x462 = false
()
} else {
val x476 = x457
val x477 = x458
val x478 = x459
val x480 = x476 + 1
x457 = x480
x458 = false
val x481 = x472.next
x459 = x481
x463 = x481
()
}
x488
}
val x491 = x457
val x492 = x458
val x493 = x459
val x497 = x493 >= x6
val x521 = if (x497) {
x241 = false
()
} else {
val x501 = x0(x493)
val x502 = x501 == '"'
val x519 = if (x502) {
val x506 = x236
val x507 = x237
val x508 = x238
val x495 = new Anon1680061013(x0,x367,x491)
val x510 = x506 += x495
x236 = x510
x237 = false
val x503 = x493 + 1
x238 = x503
x242 = x503
()
} else {
x241 = false
()
}
x519
}
x521
} else {
x241 = false
()
}
x525
}
x527
}
x529
} else {
x241 = false
()
}
x533
}
x535
}
x537
}
val x540 = x236
val x541 = x237
val x542 = x238
val x548 = if (x541) {
val x544 = new ParseResultListAnon1680061013(null,true,x542)
x544
} else {
val x545 = x540.toList
val x546 = new ParseResultListAnon1680061013(x545,false,x542)
x546
}
val x549 = x548.empty
val x555 = if (x549) {
val x87 = new ParseResultTuple2Anon1680061013ListAnon1680061013(null,true,x78)
x87
} else {
val x550 = x548.res
val x552 = x548.next
val x222 = new Anon1680061013(x0,x94,x218)
val x551 = new Tuple2Anon1680061013ListAnon1680061013(x222,x550)
val x553 = new ParseResultTuple2Anon1680061013ListAnon1680061013(x551,false,x552)
x553
}
val x556 = x555.empty
val x567 = if (x556) {
val x557 = x555.next
val x558 = new ParseResultListAnon1680061013(null,true,x557)
x558
} else {
val x560 = x555.res
val x561 = x560._1
val x562 = x560._2
val x564 = x555.next
val x563 = x561 :: x562
val x565 = new ParseResultListAnon1680061013(x563,false,x564)
x565
}
val x568 = x567.res
x80 = x568
val x570 = x567.empty
x81 = x570
val x572 = x567.next
x82 = x572
()
} else {
x80 = null
x81 = true
x82 = x78
()
}
x579
}
x581
} else {
x80 = null
x81 = true
x82 = x78
()
}
x587
}
val x590 = x80
val x591 = x81
val x592 = x82
val x593 = new ParseResultListAnon1680061013(x590,x591,x592)
x593: ParseResultListAnon1680061013
}
val x71 = List()
val x615 = {x72: (Int) =>
var x74: scala.collection.immutable.List[Anon1680061013] = null
var x75: Boolean = true
var x76: Int = x72
val x596 = x595(x72)
val x597 = x596.empty
val x609 = if (x597) {
x74 = x71
x75 = false
x76 = x72
()
} else {
val x603 = x596.res
x74 = x603
x75 = x597
val x606 = x596.next
x76 = x606
()
}
val x610 = x74
val x611 = x75
val x612 = x76
val x613 = new ParseResultListAnon1680061013(x610,x611,x612)
x613: ParseResultListAnon1680061013
}
val x616 = x615(x64)
val x617 = x616.empty
val x702 = if (x617) {
val x618 = x616.res
x2 = x618
x3 = x617
val x621 = x616.next
x4 = x621
()
} else {
var x625: java.lang.String = ""
var x626: Boolean = false
val x621 = x616.next
var x627: Int = x621
var x629: Int = -1
var x630: Boolean = true
var x631: Int = x621
val x666 = while ({val x632 = x630
val x633 = x629
val x634 = x631
val x635 = x633 != x634
val x636 = x632 && x635
x636}) {
val x638 = x631
x629 = x638
val x640 = x638 >= x6
val x664 = if (x640) {
x630 = false
()
} else {
val x644 = x0(x638)
val x645 = x644 == ' '
val x646 = x644 == '\n'
val x647 = x645 || x646
val x662 = if (x647) {
val x650 = x625
val x651 = x626
val x652 = x627
x625 = x650
x626 = false
val x648 = x638 + 1
x627 = x648
x631 = x648
()
} else {
x630 = false
()
}
x662
}
x664
}
val x667 = x625
val x668 = x626
val x669 = x627
val x700 = if (x668) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x670 = new ParseResultString(x667,x668,x669)
val x676 = x670.next
val x677 = x676 >= x6
val x698 = if (x677) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x683 = x0(x676)
val x684 = x683 == ']'
val x696 = if (x684) {
val x618 = x616.res
x2 = x618
x3 = false
val x685 = x676 + 1
x4 = x685
()
} else {
x2 = null
x3 = true
x4 = 0
()
}
x696
}
x698
}
x700
}
x702
}
x704
} else {
x2 = null
x3 = true
x4 = 0
()
}
x710
}
val x713 = x2
val x714 = x3
val x715 = x4
val x716 = new ParseResultListAnon1680061013(x713,x714,x715)
x716
}
}
/*****************************************
  End of Generated Code
*******************************************/
