// Generated file using sbt> test:run-main lms.parsing.TestCSV
package lms

object LMSCSVDoubleParserGen extends CSVDoubleParseGen()

/*****************************************
  Emitting Generated Code
*******************************************/
case class CSVDoubleParseGen() extends ((Array[Char])=>ParseResultListDouble) {
def apply(x0:Array[Char]): ParseResultListDouble = {
var x2: scala.collection.immutable.List[Double] = null
var x3: Boolean = true
var x4: Int = -1
val x6 = x0.length
val x7 = 0 >= x6
val x1973 = if (x7) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x15 = x0(0)
val x16 = x15 == '['
val x1971 = if (x16) {
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
val x1965 = if (x63) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x70 = scala.collection.mutable.ListBuffer[Double]()
val x86 = null.asInstanceOf[Char]
val x87 = new OptionChar(x86,false)
val x1856 = {x78: (Int) =>
var x80: scala.collection.immutable.List[Double] = null
var x81: Boolean = true
var x82: Int = x78
val x84 = x78 >= x6
val x1850 = if (x84) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x99 = x0(x78)
val x100 = x99 == '-'
val x1848 = if (x100) {
val x101 = x78 + 1
val x105 = x101 >= x6
val x981 = if (x105) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x113 = x0(x101)
val x114 = x113 >= '0'
val x115 = x113 <= '9'
val x116 = x114 && x115
val x979 = if (x116) {
var x120: java.lang.String = ""
var x121: Boolean = false
val x117 = x101 + 1
var x122: Int = x117
var x124: Int = -1
var x125: Boolean = true
var x126: Int = x117
val x162 = while ({val x127 = x125
val x128 = x124
val x129 = x126
val x130 = x128 != x129
val x131 = x127 && x130
x131}) {
val x133 = x126
x124 = x133
val x135 = x133 >= x6
val x160 = if (x135) {
x125 = false
()
} else {
val x139 = x0(x133)
val x140 = x139 >= '0'
val x141 = x139 <= '9'
val x142 = x140 && x141
val x158 = if (x142) {
val x145 = x120
val x146 = x121
val x147 = x122
val x149 = x145+x139
x120 = x149
x121 = false
val x143 = x133 + 1
x122 = x143
x126 = x143
()
} else {
x125 = false
()
}
x158
}
x160
}
val x163 = x120
val x164 = x121
val x165 = x122
val x170 = if (x164) {
true
} else {
false
}
val x179 = if (x170) {
true
} else {
false
}
val x185 = if (x179) {
true
} else {
false
}
val x197 = if (x185) {
true
} else {
false
}
val x973 = if (x197) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x186 = if (x179) {
x78
} else {
val x171 = if (x164) {
x101
} else {
x165
}
val x180 = if (x170) {
x171
} else {
x171
}
x180
}
val x198 = if (x185) {
x186
} else {
x186
}
val x204 = x198 >= x6
val x971 = if (x204) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x211 = x0(x198)
val x212 = x211 == '.'
val x969 = if (x212) {
val x213 = x198 + 1
val x215 = x213 >= x6
val x963 = if (x215) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x223 = x0(x213)
val x224 = x223 >= '0'
val x225 = x223 <= '9'
val x226 = x224 && x225
val x961 = if (x226) {
var x230: java.lang.String = ""
var x231: Boolean = false
val x227 = x213 + 1
var x232: Int = x227
var x234: Int = -1
var x235: Boolean = true
var x236: Int = x227
val x272 = while ({val x237 = x235
val x238 = x234
val x239 = x236
val x240 = x238 != x239
val x241 = x237 && x240
x241}) {
val x243 = x236
x234 = x243
val x245 = x243 >= x6
val x270 = if (x245) {
x235 = false
()
} else {
val x249 = x0(x243)
val x250 = x249 >= '0'
val x251 = x249 <= '9'
val x252 = x250 && x251
val x268 = if (x252) {
val x255 = x230
val x256 = x231
val x257 = x232
val x259 = x255+x249
x230 = x259
x231 = false
val x253 = x243 + 1
x232 = x253
x236 = x253
()
} else {
x235 = false
()
}
x268
}
x270
}
val x273 = x230
val x274 = x231
val x275 = x232
val x280 = if (x274) {
true
} else {
false
}
val x289 = if (x280) {
true
} else {
false
}
val x295 = if (x289) {
true
} else {
false
}
val x306 = if (x295) {
true
} else {
false
}
val x955 = if (x306) {
x80 = null
x81 = true
x82 = x78
()
} else {
var x314: scala.collection.mutable.ListBuffer[Double] = x70
var x315: Boolean = false
val x296 = if (x289) {
x78
} else {
val x281 = if (x274) {
x213
} else {
x275
}
val x290 = if (x280) {
x281
} else {
x281
}
x290
}
val x307 = if (x295) {
x296
} else {
x296
}
var x316: Int = x307
var x318: Int = -1
var x319: Boolean = true
var x320: Int = x307
val x919 = while ({val x321 = x319
val x322 = x318
val x323 = x320
val x324 = x322 != x323
val x325 = x321 && x324
x325}) {
val x327 = x320
x318 = x327
var x330: java.lang.String = ""
var x331: Boolean = false
var x332: Int = x327
var x334: Int = -1
var x335: Boolean = true
var x336: Int = x327
val x371 = while ({val x337 = x335
val x338 = x334
val x339 = x336
val x340 = x338 != x339
val x341 = x337 && x340
x341}) {
val x343 = x336
x334 = x343
val x345 = x343 >= x6
val x369 = if (x345) {
x335 = false
()
} else {
val x349 = x0(x343)
val x350 = x349 == ' '
val x351 = x349 == '\n'
val x352 = x350 || x351
val x367 = if (x352) {
val x355 = x330
val x356 = x331
val x357 = x332
x330 = x355
x331 = false
val x353 = x343 + 1
x332 = x353
x336 = x353
()
} else {
x335 = false
()
}
x367
}
x369
}
val x372 = x330
val x373 = x331
val x374 = x332
val x917 = if (x373) {
x319 = false
()
} else {
val x381 = x374 >= x6
val x915 = if (x381) {
x319 = false
()
} else {
val x385 = x0(x374)
val x386 = x385 == ','
val x913 = if (x386) {
var x390: java.lang.String = ""
var x391: Boolean = false
val x387 = x374 + 1
var x392: Int = x387
var x394: Int = -1
var x395: Boolean = true
var x396: Int = x387
val x431 = while ({val x397 = x395
val x398 = x394
val x399 = x396
val x400 = x398 != x399
val x401 = x397 && x400
x401}) {
val x403 = x396
x394 = x403
val x405 = x403 >= x6
val x429 = if (x405) {
x395 = false
()
} else {
val x409 = x0(x403)
val x410 = x409 == ' '
val x411 = x409 == '\n'
val x412 = x410 || x411
val x427 = if (x412) {
val x415 = x390
val x416 = x391
val x417 = x392
x390 = x415
x391 = false
val x413 = x403 + 1
x392 = x413
x396 = x413
()
} else {
x395 = false
()
}
x427
}
x429
}
val x432 = x390
val x433 = x391
val x434 = x392
val x909 = if (x433) {
x319 = false
()
} else {
val x438 = x434 >= x6
val x907 = if (x438) {
x319 = false
()
} else {
val x448 = x0(x434)
val x449 = x448 == '-'
val x905 = if (x449) {
val x450 = x434 + 1
val x454 = x450 >= x6
val x683 = if (x454) {
x319 = false
()
} else {
val x460 = x0(x450)
val x461 = x460 >= '0'
val x462 = x460 <= '9'
val x463 = x461 && x462
val x681 = if (x463) {
var x467: java.lang.String = ""
var x468: Boolean = false
val x464 = x450 + 1
var x469: Int = x464
var x471: Int = -1
var x472: Boolean = true
var x473: Int = x464
val x509 = while ({val x474 = x472
val x475 = x471
val x476 = x473
val x477 = x475 != x476
val x478 = x474 && x477
x478}) {
val x480 = x473
x471 = x480
val x482 = x480 >= x6
val x507 = if (x482) {
x472 = false
()
} else {
val x486 = x0(x480)
val x487 = x486 >= '0'
val x488 = x486 <= '9'
val x489 = x487 && x488
val x505 = if (x489) {
val x492 = x467
val x493 = x468
val x494 = x469
val x496 = x492+x486
x467 = x496
x468 = false
val x490 = x480 + 1
x469 = x490
x473 = x490
()
} else {
x472 = false
()
}
x505
}
x507
}
val x510 = x467
val x511 = x468
val x512 = x469
val x517 = if (x511) {
true
} else {
false
}
val x526 = if (x517) {
true
} else {
false
}
val x532 = if (x526) {
true
} else {
false
}
val x544 = if (x532) {
true
} else {
false
}
val x677 = if (x544) {
x319 = false
()
} else {
val x533 = if (x526) {
x434
} else {
val x518 = if (x511) {
x450
} else {
x512
}
val x527 = if (x517) {
x518
} else {
x518
}
x527
}
val x545 = if (x532) {
x533
} else {
x533
}
val x549 = x545 >= x6
val x675 = if (x549) {
x319 = false
()
} else {
val x554 = x0(x545)
val x555 = x554 == '.'
val x673 = if (x555) {
val x556 = x545 + 1
val x558 = x556 >= x6
val x669 = if (x558) {
x319 = false
()
} else {
val x564 = x0(x556)
val x565 = x564 >= '0'
val x566 = x564 <= '9'
val x567 = x565 && x566
val x667 = if (x567) {
var x571: java.lang.String = ""
var x572: Boolean = false
val x568 = x556 + 1
var x573: Int = x568
var x575: Int = -1
var x576: Boolean = true
var x577: Int = x568
val x613 = while ({val x578 = x576
val x579 = x575
val x580 = x577
val x581 = x579 != x580
val x582 = x578 && x581
x582}) {
val x584 = x577
x575 = x584
val x586 = x584 >= x6
val x611 = if (x586) {
x576 = false
()
} else {
val x590 = x0(x584)
val x591 = x590 >= '0'
val x592 = x590 <= '9'
val x593 = x591 && x592
val x609 = if (x593) {
val x596 = x571
val x597 = x572
val x598 = x573
val x600 = x596+x590
x571 = x600
x572 = false
val x594 = x584 + 1
x573 = x594
x577 = x594
()
} else {
x576 = false
()
}
x609
}
x611
}
val x614 = x571
val x615 = x572
val x616 = x573
val x621 = if (x615) {
true
} else {
false
}
val x630 = if (x621) {
true
} else {
false
}
val x636 = if (x630) {
true
} else {
false
}
val x647 = if (x636) {
true
} else {
false
}
val x663 = if (x647) {
x319 = false
()
} else {
val x652 = x314
val x653 = x315
val x654 = x316
val x646 = if (x636) {
0.0
} else {
val x635 = if (x630) {
null
} else {
val x543 = if (x532) {
null
} else {
val x531 = if (x526) {
null
} else {
val x452 = new OptionChar(x448,true)
val x525 = if (x517) {
null
} else {
val x516 = if (x511) {
null
} else {
val x514 = new Tuple2CharString(x460,x510)
x514
}
val x521 = x516._1
val x522 = x516._2
val x523 = x521+x522
x523
}
val x529 = new Tuple2OptionCharString(x452,x525)
x529
}
val x536 = x531._1
val x538 = x536.defined
val x541 = if (x538) {
val x537 = x531._2
val x539 = x536.value
val x540 = x539+x537
x540
} else {
val x537 = x531._2
x537
}
x541
}
val x629 = if (x621) {
null
} else {
val x620 = if (x615) {
null
} else {
val x618 = new Tuple2CharString(x564,x614)
x618
}
val x625 = x620._1
val x626 = x620._2
val x627 = x625+x626
x627
}
val x633 = new Tuple2StringString(x543,x629)
x633
}
val x641 = x635._2
val x640 = x635._1
val x642 = x640+"."
val x643 = x642+x641
val x644 = x643.toDouble
x644
}
val x656 = x652 += x646
x314 = x656
x315 = false
val x637 = if (x630) {
x434
} else {
val x622 = if (x615) {
x556
} else {
x616
}
val x631 = if (x621) {
x622
} else {
x622
}
x631
}
val x648 = if (x636) {
x637
} else {
x637
}
x316 = x648
x320 = x648
()
}
x663
} else {
x319 = false
()
}
x667
}
x669
} else {
x319 = false
()
}
x673
}
x675
}
x677
} else {
x319 = false
()
}
x681
}
x683
} else {
val x685 = x448 >= '0'
val x686 = x448 <= '9'
val x687 = x685 && x686
val x903 = if (x687) {
var x689: java.lang.String = ""
var x690: Boolean = false
val x450 = x434 + 1
var x691: Int = x450
var x693: Int = -1
var x694: Boolean = true
var x695: Int = x450
val x731 = while ({val x696 = x694
val x697 = x693
val x698 = x695
val x699 = x697 != x698
val x700 = x696 && x699
x700}) {
val x702 = x695
x693 = x702
val x704 = x702 >= x6
val x729 = if (x704) {
x694 = false
()
} else {
val x708 = x0(x702)
val x709 = x708 >= '0'
val x710 = x708 <= '9'
val x711 = x709 && x710
val x727 = if (x711) {
val x714 = x689
val x715 = x690
val x716 = x691
val x718 = x714+x708
x689 = x718
x690 = false
val x712 = x702 + 1
x691 = x712
x695 = x712
()
} else {
x694 = false
()
}
x727
}
x729
}
val x732 = x689
val x733 = x690
val x734 = x691
val x739 = if (x733) {
true
} else {
false
}
val x748 = if (x739) {
true
} else {
false
}
val x754 = if (x748) {
true
} else {
false
}
val x766 = if (x754) {
true
} else {
false
}
val x899 = if (x766) {
x319 = false
()
} else {
val x755 = if (x748) {
x434
} else {
val x740 = if (x733) {
x434
} else {
x734
}
val x749 = if (x739) {
x740
} else {
x740
}
x749
}
val x767 = if (x754) {
x755
} else {
x755
}
val x771 = x767 >= x6
val x897 = if (x771) {
x319 = false
()
} else {
val x776 = x0(x767)
val x777 = x776 == '.'
val x895 = if (x777) {
val x778 = x767 + 1
val x780 = x778 >= x6
val x891 = if (x780) {
x319 = false
()
} else {
val x786 = x0(x778)
val x787 = x786 >= '0'
val x788 = x786 <= '9'
val x789 = x787 && x788
val x889 = if (x789) {
var x793: java.lang.String = ""
var x794: Boolean = false
val x790 = x778 + 1
var x795: Int = x790
var x797: Int = -1
var x798: Boolean = true
var x799: Int = x790
val x835 = while ({val x800 = x798
val x801 = x797
val x802 = x799
val x803 = x801 != x802
val x804 = x800 && x803
x804}) {
val x806 = x799
x797 = x806
val x808 = x806 >= x6
val x833 = if (x808) {
x798 = false
()
} else {
val x812 = x0(x806)
val x813 = x812 >= '0'
val x814 = x812 <= '9'
val x815 = x813 && x814
val x831 = if (x815) {
val x818 = x793
val x819 = x794
val x820 = x795
val x822 = x818+x812
x793 = x822
x794 = false
val x816 = x806 + 1
x795 = x816
x799 = x816
()
} else {
x798 = false
()
}
x831
}
x833
}
val x836 = x793
val x837 = x794
val x838 = x795
val x843 = if (x837) {
true
} else {
false
}
val x852 = if (x843) {
true
} else {
false
}
val x858 = if (x852) {
true
} else {
false
}
val x869 = if (x858) {
true
} else {
false
}
val x885 = if (x869) {
x319 = false
()
} else {
val x874 = x314
val x875 = x315
val x876 = x316
val x868 = if (x858) {
0.0
} else {
val x857 = if (x852) {
null
} else {
val x765 = if (x754) {
null
} else {
val x753 = if (x748) {
null
} else {
val x747 = if (x739) {
null
} else {
val x738 = if (x733) {
null
} else {
val x736 = new Tuple2CharString(x448,x732)
x736
}
val x743 = x738._1
val x744 = x738._2
val x745 = x743+x744
x745
}
val x751 = new Tuple2OptionCharString(x87,x747)
x751
}
val x758 = x753._1
val x760 = x758.defined
val x763 = if (x760) {
val x759 = x753._2
val x761 = x758.value
val x762 = x761+x759
x762
} else {
val x759 = x753._2
x759
}
x763
}
val x851 = if (x843) {
null
} else {
val x842 = if (x837) {
null
} else {
val x840 = new Tuple2CharString(x786,x836)
x840
}
val x847 = x842._1
val x848 = x842._2
val x849 = x847+x848
x849
}
val x855 = new Tuple2StringString(x765,x851)
x855
}
val x863 = x857._2
val x862 = x857._1
val x864 = x862+"."
val x865 = x864+x863
val x866 = x865.toDouble
x866
}
val x878 = x874 += x868
x314 = x878
x315 = false
val x859 = if (x852) {
x434
} else {
val x844 = if (x837) {
x778
} else {
x838
}
val x853 = if (x843) {
x844
} else {
x844
}
x853
}
val x870 = if (x858) {
x859
} else {
x859
}
x316 = x870
x320 = x870
()
}
x885
} else {
x319 = false
()
}
x889
}
x891
} else {
x319 = false
()
}
x895
}
x897
}
x899
} else {
x319 = false
()
}
x903
}
x905
}
x907
}
x909
} else {
x319 = false
()
}
x913
}
x915
}
x917
}
val x920 = x314
val x921 = x315
val x922 = x316
val x928 = if (x921) {
val x924 = new ParseResultListDouble(null,true,x922)
x924
} else {
val x925 = x920.toList
val x926 = new ParseResultListDouble(x925,false,x922)
x926
}
val x929 = x928.empty
val x935 = if (x929) {
val x94 = new ParseResultTuple2DoubleListDouble(null,true,x78)
x94
} else {
val x930 = x928.res
val x932 = x928.next
val x305 = if (x295) {
0.0
} else {
val x294 = if (x289) {
null
} else {
val x196 = if (x185) {
null
} else {
val x184 = if (x179) {
null
} else {
val x103 = new OptionChar(x99,true)
val x178 = if (x170) {
null
} else {
val x169 = if (x164) {
null
} else {
val x167 = new Tuple2CharString(x113,x163)
x167
}
val x174 = x169._1
val x175 = x169._2
val x176 = x174+x175
x176
}
val x182 = new Tuple2OptionCharString(x103,x178)
x182
}
val x189 = x184._1
val x191 = x189.defined
val x194 = if (x191) {
val x190 = x184._2
val x192 = x189.value
val x193 = x192+x190
x193
} else {
val x190 = x184._2
x190
}
x194
}
val x288 = if (x280) {
null
} else {
val x279 = if (x274) {
null
} else {
val x277 = new Tuple2CharString(x223,x273)
x277
}
val x284 = x279._1
val x285 = x279._2
val x286 = x284+x285
x286
}
val x292 = new Tuple2StringString(x196,x288)
x292
}
val x300 = x294._2
val x299 = x294._1
val x301 = x299+"."
val x302 = x301+x300
val x303 = x302.toDouble
x303
}
val x931 = new Tuple2DoubleListDouble(x305,x930)
val x933 = new ParseResultTuple2DoubleListDouble(x931,false,x932)
x933
}
val x936 = x935.empty
val x947 = if (x936) {
val x937 = x935.next
val x938 = new ParseResultListDouble(null,true,x937)
x938
} else {
val x940 = x935.res
val x941 = x940._1
val x942 = x940._2
val x944 = x935.next
val x943 = x941 :: x942
val x945 = new ParseResultListDouble(x943,false,x944)
x945
}
val x948 = x947.res
x80 = x948
val x950 = x947.empty
x81 = x950
val x952 = x947.next
x82 = x952
()
}
x955
} else {
x80 = null
x81 = true
x82 = x78
()
}
x961
}
x963
} else {
x80 = null
x81 = true
x82 = x78
()
}
x969
}
x971
}
x973
} else {
x80 = null
x81 = true
x82 = x78
()
}
x979
}
x981
} else {
val x983 = x99 >= '0'
val x984 = x99 <= '9'
val x985 = x983 && x984
val x1846 = if (x985) {
var x987: java.lang.String = ""
var x988: Boolean = false
val x101 = x78 + 1
var x989: Int = x101
var x991: Int = -1
var x992: Boolean = true
var x993: Int = x101
val x1029 = while ({val x994 = x992
val x995 = x991
val x996 = x993
val x997 = x995 != x996
val x998 = x994 && x997
x998}) {
val x1000 = x993
x991 = x1000
val x1002 = x1000 >= x6
val x1027 = if (x1002) {
x992 = false
()
} else {
val x1006 = x0(x1000)
val x1007 = x1006 >= '0'
val x1008 = x1006 <= '9'
val x1009 = x1007 && x1008
val x1025 = if (x1009) {
val x1012 = x987
val x1013 = x988
val x1014 = x989
val x1016 = x1012+x1006
x987 = x1016
x988 = false
val x1010 = x1000 + 1
x989 = x1010
x993 = x1010
()
} else {
x992 = false
()
}
x1025
}
x1027
}
val x1030 = x987
val x1031 = x988
val x1032 = x989
val x1037 = if (x1031) {
true
} else {
false
}
val x1046 = if (x1037) {
true
} else {
false
}
val x1052 = if (x1046) {
true
} else {
false
}
val x1064 = if (x1052) {
true
} else {
false
}
val x1840 = if (x1064) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x1053 = if (x1046) {
x78
} else {
val x1038 = if (x1031) {
x78
} else {
x1032
}
val x1047 = if (x1037) {
x1038
} else {
x1038
}
x1047
}
val x1065 = if (x1052) {
x1053
} else {
x1053
}
val x1071 = x1065 >= x6
val x1838 = if (x1071) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x1078 = x0(x1065)
val x1079 = x1078 == '.'
val x1836 = if (x1079) {
val x1080 = x1065 + 1
val x1082 = x1080 >= x6
val x1830 = if (x1082) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x1090 = x0(x1080)
val x1091 = x1090 >= '0'
val x1092 = x1090 <= '9'
val x1093 = x1091 && x1092
val x1828 = if (x1093) {
var x1097: java.lang.String = ""
var x1098: Boolean = false
val x1094 = x1080 + 1
var x1099: Int = x1094
var x1101: Int = -1
var x1102: Boolean = true
var x1103: Int = x1094
val x1139 = while ({val x1104 = x1102
val x1105 = x1101
val x1106 = x1103
val x1107 = x1105 != x1106
val x1108 = x1104 && x1107
x1108}) {
val x1110 = x1103
x1101 = x1110
val x1112 = x1110 >= x6
val x1137 = if (x1112) {
x1102 = false
()
} else {
val x1116 = x0(x1110)
val x1117 = x1116 >= '0'
val x1118 = x1116 <= '9'
val x1119 = x1117 && x1118
val x1135 = if (x1119) {
val x1122 = x1097
val x1123 = x1098
val x1124 = x1099
val x1126 = x1122+x1116
x1097 = x1126
x1098 = false
val x1120 = x1110 + 1
x1099 = x1120
x1103 = x1120
()
} else {
x1102 = false
()
}
x1135
}
x1137
}
val x1140 = x1097
val x1141 = x1098
val x1142 = x1099
val x1147 = if (x1141) {
true
} else {
false
}
val x1156 = if (x1147) {
true
} else {
false
}
val x1162 = if (x1156) {
true
} else {
false
}
val x1173 = if (x1162) {
true
} else {
false
}
val x1822 = if (x1173) {
x80 = null
x81 = true
x82 = x78
()
} else {
var x1181: scala.collection.mutable.ListBuffer[Double] = x70
var x1182: Boolean = false
val x1163 = if (x1156) {
x78
} else {
val x1148 = if (x1141) {
x1080
} else {
x1142
}
val x1157 = if (x1147) {
x1148
} else {
x1148
}
x1157
}
val x1174 = if (x1162) {
x1163
} else {
x1163
}
var x1183: Int = x1174
var x1185: Int = -1
var x1186: Boolean = true
var x1187: Int = x1174
val x1786 = while ({val x1188 = x1186
val x1189 = x1185
val x1190 = x1187
val x1191 = x1189 != x1190
val x1192 = x1188 && x1191
x1192}) {
val x1194 = x1187
x1185 = x1194
var x1197: java.lang.String = ""
var x1198: Boolean = false
var x1199: Int = x1194
var x1201: Int = -1
var x1202: Boolean = true
var x1203: Int = x1194
val x1238 = while ({val x1204 = x1202
val x1205 = x1201
val x1206 = x1203
val x1207 = x1205 != x1206
val x1208 = x1204 && x1207
x1208}) {
val x1210 = x1203
x1201 = x1210
val x1212 = x1210 >= x6
val x1236 = if (x1212) {
x1202 = false
()
} else {
val x1216 = x0(x1210)
val x1217 = x1216 == ' '
val x1218 = x1216 == '\n'
val x1219 = x1217 || x1218
val x1234 = if (x1219) {
val x1222 = x1197
val x1223 = x1198
val x1224 = x1199
x1197 = x1222
x1198 = false
val x1220 = x1210 + 1
x1199 = x1220
x1203 = x1220
()
} else {
x1202 = false
()
}
x1234
}
x1236
}
val x1239 = x1197
val x1240 = x1198
val x1241 = x1199
val x1784 = if (x1240) {
x1186 = false
()
} else {
val x1248 = x1241 >= x6
val x1782 = if (x1248) {
x1186 = false
()
} else {
val x1252 = x0(x1241)
val x1253 = x1252 == ','
val x1780 = if (x1253) {
var x1257: java.lang.String = ""
var x1258: Boolean = false
val x1254 = x1241 + 1
var x1259: Int = x1254
var x1261: Int = -1
var x1262: Boolean = true
var x1263: Int = x1254
val x1298 = while ({val x1264 = x1262
val x1265 = x1261
val x1266 = x1263
val x1267 = x1265 != x1266
val x1268 = x1264 && x1267
x1268}) {
val x1270 = x1263
x1261 = x1270
val x1272 = x1270 >= x6
val x1296 = if (x1272) {
x1262 = false
()
} else {
val x1276 = x0(x1270)
val x1277 = x1276 == ' '
val x1278 = x1276 == '\n'
val x1279 = x1277 || x1278
val x1294 = if (x1279) {
val x1282 = x1257
val x1283 = x1258
val x1284 = x1259
x1257 = x1282
x1258 = false
val x1280 = x1270 + 1
x1259 = x1280
x1263 = x1280
()
} else {
x1262 = false
()
}
x1294
}
x1296
}
val x1299 = x1257
val x1300 = x1258
val x1301 = x1259
val x1776 = if (x1300) {
x1186 = false
()
} else {
val x1305 = x1301 >= x6
val x1774 = if (x1305) {
x1186 = false
()
} else {
val x1315 = x0(x1301)
val x1316 = x1315 == '-'
val x1772 = if (x1316) {
val x1317 = x1301 + 1
val x1321 = x1317 >= x6
val x1550 = if (x1321) {
x1186 = false
()
} else {
val x1327 = x0(x1317)
val x1328 = x1327 >= '0'
val x1329 = x1327 <= '9'
val x1330 = x1328 && x1329
val x1548 = if (x1330) {
var x1334: java.lang.String = ""
var x1335: Boolean = false
val x1331 = x1317 + 1
var x1336: Int = x1331
var x1338: Int = -1
var x1339: Boolean = true
var x1340: Int = x1331
val x1376 = while ({val x1341 = x1339
val x1342 = x1338
val x1343 = x1340
val x1344 = x1342 != x1343
val x1345 = x1341 && x1344
x1345}) {
val x1347 = x1340
x1338 = x1347
val x1349 = x1347 >= x6
val x1374 = if (x1349) {
x1339 = false
()
} else {
val x1353 = x0(x1347)
val x1354 = x1353 >= '0'
val x1355 = x1353 <= '9'
val x1356 = x1354 && x1355
val x1372 = if (x1356) {
val x1359 = x1334
val x1360 = x1335
val x1361 = x1336
val x1363 = x1359+x1353
x1334 = x1363
x1335 = false
val x1357 = x1347 + 1
x1336 = x1357
x1340 = x1357
()
} else {
x1339 = false
()
}
x1372
}
x1374
}
val x1377 = x1334
val x1378 = x1335
val x1379 = x1336
val x1384 = if (x1378) {
true
} else {
false
}
val x1393 = if (x1384) {
true
} else {
false
}
val x1399 = if (x1393) {
true
} else {
false
}
val x1411 = if (x1399) {
true
} else {
false
}
val x1544 = if (x1411) {
x1186 = false
()
} else {
val x1400 = if (x1393) {
x1301
} else {
val x1385 = if (x1378) {
x1317
} else {
x1379
}
val x1394 = if (x1384) {
x1385
} else {
x1385
}
x1394
}
val x1412 = if (x1399) {
x1400
} else {
x1400
}
val x1416 = x1412 >= x6
val x1542 = if (x1416) {
x1186 = false
()
} else {
val x1421 = x0(x1412)
val x1422 = x1421 == '.'
val x1540 = if (x1422) {
val x1423 = x1412 + 1
val x1425 = x1423 >= x6
val x1536 = if (x1425) {
x1186 = false
()
} else {
val x1431 = x0(x1423)
val x1432 = x1431 >= '0'
val x1433 = x1431 <= '9'
val x1434 = x1432 && x1433
val x1534 = if (x1434) {
var x1438: java.lang.String = ""
var x1439: Boolean = false
val x1435 = x1423 + 1
var x1440: Int = x1435
var x1442: Int = -1
var x1443: Boolean = true
var x1444: Int = x1435
val x1480 = while ({val x1445 = x1443
val x1446 = x1442
val x1447 = x1444
val x1448 = x1446 != x1447
val x1449 = x1445 && x1448
x1449}) {
val x1451 = x1444
x1442 = x1451
val x1453 = x1451 >= x6
val x1478 = if (x1453) {
x1443 = false
()
} else {
val x1457 = x0(x1451)
val x1458 = x1457 >= '0'
val x1459 = x1457 <= '9'
val x1460 = x1458 && x1459
val x1476 = if (x1460) {
val x1463 = x1438
val x1464 = x1439
val x1465 = x1440
val x1467 = x1463+x1457
x1438 = x1467
x1439 = false
val x1461 = x1451 + 1
x1440 = x1461
x1444 = x1461
()
} else {
x1443 = false
()
}
x1476
}
x1478
}
val x1481 = x1438
val x1482 = x1439
val x1483 = x1440
val x1488 = if (x1482) {
true
} else {
false
}
val x1497 = if (x1488) {
true
} else {
false
}
val x1503 = if (x1497) {
true
} else {
false
}
val x1514 = if (x1503) {
true
} else {
false
}
val x1530 = if (x1514) {
x1186 = false
()
} else {
val x1519 = x1181
val x1520 = x1182
val x1521 = x1183
val x1513 = if (x1503) {
0.0
} else {
val x1502 = if (x1497) {
null
} else {
val x1410 = if (x1399) {
null
} else {
val x1398 = if (x1393) {
null
} else {
val x1319 = new OptionChar(x1315,true)
val x1392 = if (x1384) {
null
} else {
val x1383 = if (x1378) {
null
} else {
val x1381 = new Tuple2CharString(x1327,x1377)
x1381
}
val x1388 = x1383._1
val x1389 = x1383._2
val x1390 = x1388+x1389
x1390
}
val x1396 = new Tuple2OptionCharString(x1319,x1392)
x1396
}
val x1403 = x1398._1
val x1405 = x1403.defined
val x1408 = if (x1405) {
val x1404 = x1398._2
val x1406 = x1403.value
val x1407 = x1406+x1404
x1407
} else {
val x1404 = x1398._2
x1404
}
x1408
}
val x1496 = if (x1488) {
null
} else {
val x1487 = if (x1482) {
null
} else {
val x1485 = new Tuple2CharString(x1431,x1481)
x1485
}
val x1492 = x1487._1
val x1493 = x1487._2
val x1494 = x1492+x1493
x1494
}
val x1500 = new Tuple2StringString(x1410,x1496)
x1500
}
val x1508 = x1502._2
val x1507 = x1502._1
val x1509 = x1507+"."
val x1510 = x1509+x1508
val x1511 = x1510.toDouble
x1511
}
val x1523 = x1519 += x1513
x1181 = x1523
x1182 = false
val x1504 = if (x1497) {
x1301
} else {
val x1489 = if (x1482) {
x1423
} else {
x1483
}
val x1498 = if (x1488) {
x1489
} else {
x1489
}
x1498
}
val x1515 = if (x1503) {
x1504
} else {
x1504
}
x1183 = x1515
x1187 = x1515
()
}
x1530
} else {
x1186 = false
()
}
x1534
}
x1536
} else {
x1186 = false
()
}
x1540
}
x1542
}
x1544
} else {
x1186 = false
()
}
x1548
}
x1550
} else {
val x1552 = x1315 >= '0'
val x1553 = x1315 <= '9'
val x1554 = x1552 && x1553
val x1770 = if (x1554) {
var x1556: java.lang.String = ""
var x1557: Boolean = false
val x1317 = x1301 + 1
var x1558: Int = x1317
var x1560: Int = -1
var x1561: Boolean = true
var x1562: Int = x1317
val x1598 = while ({val x1563 = x1561
val x1564 = x1560
val x1565 = x1562
val x1566 = x1564 != x1565
val x1567 = x1563 && x1566
x1567}) {
val x1569 = x1562
x1560 = x1569
val x1571 = x1569 >= x6
val x1596 = if (x1571) {
x1561 = false
()
} else {
val x1575 = x0(x1569)
val x1576 = x1575 >= '0'
val x1577 = x1575 <= '9'
val x1578 = x1576 && x1577
val x1594 = if (x1578) {
val x1581 = x1556
val x1582 = x1557
val x1583 = x1558
val x1585 = x1581+x1575
x1556 = x1585
x1557 = false
val x1579 = x1569 + 1
x1558 = x1579
x1562 = x1579
()
} else {
x1561 = false
()
}
x1594
}
x1596
}
val x1599 = x1556
val x1600 = x1557
val x1601 = x1558
val x1606 = if (x1600) {
true
} else {
false
}
val x1615 = if (x1606) {
true
} else {
false
}
val x1621 = if (x1615) {
true
} else {
false
}
val x1633 = if (x1621) {
true
} else {
false
}
val x1766 = if (x1633) {
x1186 = false
()
} else {
val x1622 = if (x1615) {
x1301
} else {
val x1607 = if (x1600) {
x1301
} else {
x1601
}
val x1616 = if (x1606) {
x1607
} else {
x1607
}
x1616
}
val x1634 = if (x1621) {
x1622
} else {
x1622
}
val x1638 = x1634 >= x6
val x1764 = if (x1638) {
x1186 = false
()
} else {
val x1643 = x0(x1634)
val x1644 = x1643 == '.'
val x1762 = if (x1644) {
val x1645 = x1634 + 1
val x1647 = x1645 >= x6
val x1758 = if (x1647) {
x1186 = false
()
} else {
val x1653 = x0(x1645)
val x1654 = x1653 >= '0'
val x1655 = x1653 <= '9'
val x1656 = x1654 && x1655
val x1756 = if (x1656) {
var x1660: java.lang.String = ""
var x1661: Boolean = false
val x1657 = x1645 + 1
var x1662: Int = x1657
var x1664: Int = -1
var x1665: Boolean = true
var x1666: Int = x1657
val x1702 = while ({val x1667 = x1665
val x1668 = x1664
val x1669 = x1666
val x1670 = x1668 != x1669
val x1671 = x1667 && x1670
x1671}) {
val x1673 = x1666
x1664 = x1673
val x1675 = x1673 >= x6
val x1700 = if (x1675) {
x1665 = false
()
} else {
val x1679 = x0(x1673)
val x1680 = x1679 >= '0'
val x1681 = x1679 <= '9'
val x1682 = x1680 && x1681
val x1698 = if (x1682) {
val x1685 = x1660
val x1686 = x1661
val x1687 = x1662
val x1689 = x1685+x1679
x1660 = x1689
x1661 = false
val x1683 = x1673 + 1
x1662 = x1683
x1666 = x1683
()
} else {
x1665 = false
()
}
x1698
}
x1700
}
val x1703 = x1660
val x1704 = x1661
val x1705 = x1662
val x1710 = if (x1704) {
true
} else {
false
}
val x1719 = if (x1710) {
true
} else {
false
}
val x1725 = if (x1719) {
true
} else {
false
}
val x1736 = if (x1725) {
true
} else {
false
}
val x1752 = if (x1736) {
x1186 = false
()
} else {
val x1741 = x1181
val x1742 = x1182
val x1743 = x1183
val x1735 = if (x1725) {
0.0
} else {
val x1724 = if (x1719) {
null
} else {
val x1632 = if (x1621) {
null
} else {
val x1620 = if (x1615) {
null
} else {
val x1614 = if (x1606) {
null
} else {
val x1605 = if (x1600) {
null
} else {
val x1603 = new Tuple2CharString(x1315,x1599)
x1603
}
val x1610 = x1605._1
val x1611 = x1605._2
val x1612 = x1610+x1611
x1612
}
val x1618 = new Tuple2OptionCharString(x87,x1614)
x1618
}
val x1625 = x1620._1
val x1627 = x1625.defined
val x1630 = if (x1627) {
val x1626 = x1620._2
val x1628 = x1625.value
val x1629 = x1628+x1626
x1629
} else {
val x1626 = x1620._2
x1626
}
x1630
}
val x1718 = if (x1710) {
null
} else {
val x1709 = if (x1704) {
null
} else {
val x1707 = new Tuple2CharString(x1653,x1703)
x1707
}
val x1714 = x1709._1
val x1715 = x1709._2
val x1716 = x1714+x1715
x1716
}
val x1722 = new Tuple2StringString(x1632,x1718)
x1722
}
val x1730 = x1724._2
val x1729 = x1724._1
val x1731 = x1729+"."
val x1732 = x1731+x1730
val x1733 = x1732.toDouble
x1733
}
val x1745 = x1741 += x1735
x1181 = x1745
x1182 = false
val x1726 = if (x1719) {
x1301
} else {
val x1711 = if (x1704) {
x1645
} else {
x1705
}
val x1720 = if (x1710) {
x1711
} else {
x1711
}
x1720
}
val x1737 = if (x1725) {
x1726
} else {
x1726
}
x1183 = x1737
x1187 = x1737
()
}
x1752
} else {
x1186 = false
()
}
x1756
}
x1758
} else {
x1186 = false
()
}
x1762
}
x1764
}
x1766
} else {
x1186 = false
()
}
x1770
}
x1772
}
x1774
}
x1776
} else {
x1186 = false
()
}
x1780
}
x1782
}
x1784
}
val x1787 = x1181
val x1788 = x1182
val x1789 = x1183
val x1795 = if (x1788) {
val x1791 = new ParseResultListDouble(null,true,x1789)
x1791
} else {
val x1792 = x1787.toList
val x1793 = new ParseResultListDouble(x1792,false,x1789)
x1793
}
val x1796 = x1795.empty
val x1802 = if (x1796) {
val x94 = new ParseResultTuple2DoubleListDouble(null,true,x78)
x94
} else {
val x1797 = x1795.res
val x1799 = x1795.next
val x1172 = if (x1162) {
0.0
} else {
val x1161 = if (x1156) {
null
} else {
val x1063 = if (x1052) {
null
} else {
val x1051 = if (x1046) {
null
} else {
val x1045 = if (x1037) {
null
} else {
val x1036 = if (x1031) {
null
} else {
val x1034 = new Tuple2CharString(x99,x1030)
x1034
}
val x1041 = x1036._1
val x1042 = x1036._2
val x1043 = x1041+x1042
x1043
}
val x1049 = new Tuple2OptionCharString(x87,x1045)
x1049
}
val x1056 = x1051._1
val x1058 = x1056.defined
val x1061 = if (x1058) {
val x1057 = x1051._2
val x1059 = x1056.value
val x1060 = x1059+x1057
x1060
} else {
val x1057 = x1051._2
x1057
}
x1061
}
val x1155 = if (x1147) {
null
} else {
val x1146 = if (x1141) {
null
} else {
val x1144 = new Tuple2CharString(x1090,x1140)
x1144
}
val x1151 = x1146._1
val x1152 = x1146._2
val x1153 = x1151+x1152
x1153
}
val x1159 = new Tuple2StringString(x1063,x1155)
x1159
}
val x1167 = x1161._2
val x1166 = x1161._1
val x1168 = x1166+"."
val x1169 = x1168+x1167
val x1170 = x1169.toDouble
x1170
}
val x1798 = new Tuple2DoubleListDouble(x1172,x1797)
val x1800 = new ParseResultTuple2DoubleListDouble(x1798,false,x1799)
x1800
}
val x1803 = x1802.empty
val x1814 = if (x1803) {
val x1804 = x1802.next
val x1805 = new ParseResultListDouble(null,true,x1804)
x1805
} else {
val x1807 = x1802.res
val x1808 = x1807._1
val x1809 = x1807._2
val x1811 = x1802.next
val x1810 = x1808 :: x1809
val x1812 = new ParseResultListDouble(x1810,false,x1811)
x1812
}
val x1815 = x1814.res
x80 = x1815
val x1817 = x1814.empty
x81 = x1817
val x1819 = x1814.next
x82 = x1819
()
}
x1822
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1828
}
x1830
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1836
}
x1838
}
x1840
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1846
}
x1848
}
val x1851 = x80
val x1852 = x81
val x1853 = x82
val x1854 = new ParseResultListDouble(x1851,x1852,x1853)
x1854: ParseResultListDouble
}
val x71 = List()
val x1876 = {x72: (Int) =>
var x74: scala.collection.immutable.List[Double] = null
var x75: Boolean = true
var x76: Int = x72
val x1857 = x1856(x72)
val x1858 = x1857.empty
val x1870 = if (x1858) {
x74 = x71
x75 = false
x76 = x72
()
} else {
val x1864 = x1857.res
x74 = x1864
x75 = x1858
val x1867 = x1857.next
x76 = x1867
()
}
val x1871 = x74
val x1872 = x75
val x1873 = x76
val x1874 = new ParseResultListDouble(x1871,x1872,x1873)
x1874: ParseResultListDouble
}
val x1877 = x1876(x64)
val x1878 = x1877.empty
val x1963 = if (x1878) {
val x1879 = x1877.res
x2 = x1879
x3 = x1878
val x1882 = x1877.next
x4 = x1882
()
} else {
var x1886: java.lang.String = ""
var x1887: Boolean = false
val x1882 = x1877.next
var x1888: Int = x1882
var x1890: Int = -1
var x1891: Boolean = true
var x1892: Int = x1882
val x1927 = while ({val x1893 = x1891
val x1894 = x1890
val x1895 = x1892
val x1896 = x1894 != x1895
val x1897 = x1893 && x1896
x1897}) {
val x1899 = x1892
x1890 = x1899
val x1901 = x1899 >= x6
val x1925 = if (x1901) {
x1891 = false
()
} else {
val x1905 = x0(x1899)
val x1906 = x1905 == ' '
val x1907 = x1905 == '\n'
val x1908 = x1906 || x1907
val x1923 = if (x1908) {
val x1911 = x1886
val x1912 = x1887
val x1913 = x1888
x1886 = x1911
x1887 = false
val x1909 = x1899 + 1
x1888 = x1909
x1892 = x1909
()
} else {
x1891 = false
()
}
x1923
}
x1925
}
val x1928 = x1886
val x1929 = x1887
val x1930 = x1888
val x1961 = if (x1929) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x1931 = new ParseResultString(x1928,x1929,x1930)
val x1937 = x1931.next
val x1938 = x1937 >= x6
val x1959 = if (x1938) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x1944 = x0(x1937)
val x1945 = x1944 == ']'
val x1957 = if (x1945) {
val x1879 = x1877.res
x2 = x1879
x3 = false
val x1946 = x1937 + 1
x4 = x1946
()
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1957
}
x1959
}
x1961
}
x1963
}
x1965
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1971
}
val x1974 = x2
val x1975 = x3
val x1976 = x4
val x1977 = new ParseResultListDouble(x1974,x1975,x1976)
x1977
}
}
/*****************************************
  End of Generated Code
*******************************************/
