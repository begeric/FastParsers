package lms

object LMSCSVDoubleParserGen2 extends CSVDoubleParseGen2()

/*****************************************
  Emitting Generated Code
*******************************************/
case class CSVDoubleParseGen2() extends ((Array[Char])=>ParseResultListDouble) {
def apply(x0:Array[Char]): ParseResultListDouble = {
var x2: scala.collection.immutable.List[Double] = null
var x3: Boolean = true
var x4: Int = -1
val x6 = x0.length
val x7 = 0 >= x6
val x1990 = if (x7) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x15 = x0(0)
val x16 = x15 == '['
val x1988 = if (x16) {
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
val x1982 = if (x63) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x70 = scala.collection.mutable.ListBuffer[Double]()
val x86 = null.asInstanceOf[Char]
val x87 = new OptionChar(x86,false)
val x1873 = {x78: (Int) =>
var x80: scala.collection.immutable.List[Double] = null
var x81: Boolean = true
var x82: Int = x78
val x84 = x78 >= x6
val x1867 = if (x84) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x100 = x0(x78)
val x101 = x100 == '-'
val x1865 = if (x101) {
val x102 = x78 + 1
val x106 = x102 >= x6
val x990 = if (x106) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x115 = x0(x102)
val x116 = x115 >= '0'
val x117 = x115 <= '9'
val x118 = x116 && x117
val x988 = if (x118) {
var x122: Int = 0
var x123: Boolean = false
val x119 = x102 + 1
var x124: Int = x119
var x126: Int = -1
var x127: Boolean = true
var x128: Int = x119
val x164 = while ({val x129 = x127
val x130 = x126
val x131 = x128
val x132 = x130 != x131
val x133 = x129 && x132
x133}) {
val x135 = x128
x126 = x135
val x137 = x135 >= x6
val x162 = if (x137) {
x127 = false
()
} else {
val x141 = x0(x135)
val x142 = x141 >= '0'
val x143 = x141 <= '9'
val x144 = x142 && x143
val x160 = if (x144) {
val x147 = x122
val x148 = x123
val x149 = x124
val x151 = x147 + 1
x122 = x151
x123 = false
val x145 = x135 + 1
x124 = x145
x128 = x145
()
} else {
x127 = false
()
}
x160
}
x162
}
val x165 = x122
val x166 = x123
val x167 = x124
val x176 = x167 >= x6
val x982 = if (x176) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x183 = x0(x167)
val x184 = x183 == '.'
val x980 = if (x184) {
val x185 = x167 + 1
val x187 = x185 >= x6
val x974 = if (x187) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x195 = x0(x185)
val x196 = x195 >= '0'
val x197 = x195 <= '9'
val x198 = x196 && x197
val x972 = if (x198) {
var x202: java.lang.String = ""
var x203: Boolean = false
val x199 = x185 + 1
var x204: Int = x199
var x206: Int = -1
var x207: Boolean = true
var x208: Int = x199
val x244 = while ({val x209 = x207
val x210 = x206
val x211 = x208
val x212 = x210 != x211
val x213 = x209 && x212
x213}) {
val x215 = x208
x206 = x215
val x217 = x215 >= x6
val x242 = if (x217) {
x207 = false
()
} else {
val x221 = x0(x215)
val x222 = x221 >= '0'
val x223 = x221 <= '9'
val x224 = x222 && x223
val x240 = if (x224) {
val x227 = x202
val x228 = x203
val x229 = x204
val x231 = x227+x221
x202 = x231
x203 = false
val x225 = x215 + 1
x204 = x225
x208 = x225
()
} else {
x207 = false
()
}
x240
}
x242
}
val x245 = x202
val x246 = x203
val x247 = x204
val x252 = if (x246) {
true
} else {
false
}
val x261 = if (x252) {
true
} else {
false
}
val x267 = if (x261) {
true
} else {
false
}
val x281 = if (x267) {
true
} else {
false
}
val x287 = if (x281) {
true
} else {
false
}
val x302 = if (x287) {
true
} else {
false
}
val x310 = if (x302) {
true
} else {
false
}
val x966 = if (x310) {
x80 = null
x81 = true
x82 = x78
()
} else {
var x318: scala.collection.mutable.ListBuffer[Double] = x70
var x319: Boolean = false
val x288 = if (x281) {
x78
} else {
val x268 = if (x261) {
x102
} else {
val x253 = if (x246) {
x185
} else {
x247
}
val x262 = if (x252) {
x253
} else {
x253
}
x262
}
val x282 = if (x267) {
x268
} else {
x268
}
x282
}
val x303 = if (x287) {
x288
} else {
x288
}
val x311 = if (x302) {
x303
} else {
x303
}
var x320: Int = x311
var x322: Int = -1
var x323: Boolean = true
var x324: Int = x311
val x930 = while ({val x325 = x323
val x326 = x322
val x327 = x324
val x328 = x326 != x327
val x329 = x325 && x328
x329}) {
val x331 = x324
x322 = x331
var x334: java.lang.String = ""
var x335: Boolean = false
var x336: Int = x331
var x338: Int = -1
var x339: Boolean = true
var x340: Int = x331
val x375 = while ({val x341 = x339
val x342 = x338
val x343 = x340
val x344 = x342 != x343
val x345 = x341 && x344
x345}) {
val x347 = x340
x338 = x347
val x349 = x347 >= x6
val x373 = if (x349) {
x339 = false
()
} else {
val x353 = x0(x347)
val x354 = x353 == ' '
val x355 = x353 == '\n'
val x356 = x354 || x355
val x371 = if (x356) {
val x359 = x334
val x360 = x335
val x361 = x336
x334 = x359
x335 = false
val x357 = x347 + 1
x336 = x357
x340 = x357
()
} else {
x339 = false
()
}
x371
}
x373
}
val x376 = x334
val x377 = x335
val x378 = x336
val x928 = if (x377) {
x323 = false
()
} else {
val x385 = x378 >= x6
val x926 = if (x385) {
x323 = false
()
} else {
val x389 = x0(x378)
val x390 = x389 == ','
val x924 = if (x390) {
var x394: java.lang.String = ""
var x395: Boolean = false
val x391 = x378 + 1
var x396: Int = x391
var x398: Int = -1
var x399: Boolean = true
var x400: Int = x391
val x435 = while ({val x401 = x399
val x402 = x398
val x403 = x400
val x404 = x402 != x403
val x405 = x401 && x404
x405}) {
val x407 = x400
x398 = x407
val x409 = x407 >= x6
val x433 = if (x409) {
x399 = false
()
} else {
val x413 = x0(x407)
val x414 = x413 == ' '
val x415 = x413 == '\n'
val x416 = x414 || x415
val x431 = if (x416) {
val x419 = x394
val x420 = x395
val x421 = x396
x394 = x419
x395 = false
val x417 = x407 + 1
x396 = x417
x400 = x417
()
} else {
x399 = false
()
}
x431
}
x433
}
val x436 = x394
val x437 = x395
val x438 = x396
val x920 = if (x437) {
x323 = false
()
} else {
val x442 = x438 >= x6
val x918 = if (x442) {
x323 = false
()
} else {
val x453 = x0(x438)
val x454 = x453 == '-'
val x916 = if (x454) {
val x455 = x438 + 1
val x459 = x455 >= x6
val x691 = if (x459) {
x323 = false
()
} else {
val x466 = x0(x455)
val x467 = x466 >= '0'
val x468 = x466 <= '9'
val x469 = x467 && x468
val x689 = if (x469) {
var x473: Int = 0
var x474: Boolean = false
val x470 = x455 + 1
var x475: Int = x470
var x477: Int = -1
var x478: Boolean = true
var x479: Int = x470
val x515 = while ({val x480 = x478
val x481 = x477
val x482 = x479
val x483 = x481 != x482
val x484 = x480 && x483
x484}) {
val x486 = x479
x477 = x486
val x488 = x486 >= x6
val x513 = if (x488) {
x478 = false
()
} else {
val x492 = x0(x486)
val x493 = x492 >= '0'
val x494 = x492 <= '9'
val x495 = x493 && x494
val x511 = if (x495) {
val x498 = x473
val x499 = x474
val x500 = x475
val x502 = x498 + 1
x473 = x502
x474 = false
val x496 = x486 + 1
x475 = x496
x479 = x496
()
} else {
x478 = false
()
}
x511
}
x513
}
val x516 = x473
val x517 = x474
val x518 = x475
val x527 = x518 >= x6
val x685 = if (x527) {
x323 = false
()
} else {
val x532 = x0(x518)
val x533 = x532 == '.'
val x683 = if (x533) {
val x534 = x518 + 1
val x536 = x534 >= x6
val x679 = if (x536) {
x323 = false
()
} else {
val x542 = x0(x534)
val x543 = x542 >= '0'
val x544 = x542 <= '9'
val x545 = x543 && x544
val x677 = if (x545) {
var x549: java.lang.String = ""
var x550: Boolean = false
val x546 = x534 + 1
var x551: Int = x546
var x553: Int = -1
var x554: Boolean = true
var x555: Int = x546
val x591 = while ({val x556 = x554
val x557 = x553
val x558 = x555
val x559 = x557 != x558
val x560 = x556 && x559
x560}) {
val x562 = x555
x553 = x562
val x564 = x562 >= x6
val x589 = if (x564) {
x554 = false
()
} else {
val x568 = x0(x562)
val x569 = x568 >= '0'
val x570 = x568 <= '9'
val x571 = x569 && x570
val x587 = if (x571) {
val x574 = x549
val x575 = x550
val x576 = x551
val x578 = x574+x568
x549 = x578
x550 = false
val x572 = x562 + 1
x551 = x572
x555 = x572
()
} else {
x554 = false
()
}
x587
}
x589
}
val x592 = x549
val x593 = x550
val x594 = x551
val x599 = if (x593) {
true
} else {
false
}
val x608 = if (x599) {
true
} else {
false
}
val x614 = if (x608) {
true
} else {
false
}
val x628 = if (x614) {
true
} else {
false
}
val x634 = if (x628) {
true
} else {
false
}
val x649 = if (x634) {
true
} else {
false
}
val x657 = if (x649) {
true
} else {
false
}
val x673 = if (x657) {
x323 = false
()
} else {
val x662 = x318
val x663 = x319
val x664 = x320
val x656 = if (x649) {
0.0
} else {
val x648 = if (x634) {
null
} else {
val x633 = if (x628) {
null
} else {
val x457 = new OptionChar(x453,true)
val x627 = if (x614) {
null
} else {
val x613 = if (x608) {
null
} else {
val x524 = x516 + 1
val x525 = new Anon1680061013(x0,x455,x524)
val x607 = if (x599) {
null
} else {
val x598 = if (x593) {
null
} else {
val x596 = new Tuple2CharString(x542,x592)
x596
}
val x603 = x598._1
val x604 = x598._2
val x605 = x603+x604
x605
}
val x611 = new Tuple2Anon1680061013String(x525,x607)
x611
}
val x618 = x613._1
val x620 = x618.start
val x621 = x618.length
val x619 = x613._2
val x622 = x619.length
val x623 = x621 + x622
val x624 = x623 + 1
val x625 = new Anon1680061013(x0,x620,x624)
x625
}
val x631 = new Tuple2OptionCharAnon1680061013(x457,x627)
x631
}
val x638 = x633._1
val x640 = x638.defined
val x646 = if (x640) {
val x639 = x633._2
val x641 = x639.start
val x642 = x641 - 1
val x643 = x639.length
val x644 = x643 + 1
val x645 = new Anon1680061013(x0,x642,x644)
x645
} else {
val x639 = x633._2
x639
}
x646
}
val x653 = new String(x648.input,x648.start,x648.length)
val x654 = x653.toDouble
x654
}
val x666 = x662 += x656
x318 = x666
x319 = false
val x635 = if (x628) {
x438
} else {
val x615 = if (x608) {
x455
} else {
val x600 = if (x593) {
x534
} else {
x594
}
val x609 = if (x599) {
x600
} else {
x600
}
x609
}
val x629 = if (x614) {
x615
} else {
x615
}
x629
}
val x650 = if (x634) {
x635
} else {
x635
}
val x658 = if (x649) {
x650
} else {
x650
}
x320 = x658
x324 = x658
()
}
x673
} else {
x323 = false
()
}
x677
}
x679
} else {
x323 = false
()
}
x683
}
x685
} else {
x323 = false
()
}
x689
}
x691
} else {
val x693 = x453 >= '0'
val x694 = x453 <= '9'
val x695 = x693 && x694
val x914 = if (x695) {
var x698: Int = 0
var x699: Boolean = false
val x455 = x438 + 1
var x700: Int = x455
var x702: Int = -1
var x703: Boolean = true
var x704: Int = x455
val x740 = while ({val x705 = x703
val x706 = x702
val x707 = x704
val x708 = x706 != x707
val x709 = x705 && x708
x709}) {
val x711 = x704
x702 = x711
val x713 = x711 >= x6
val x738 = if (x713) {
x703 = false
()
} else {
val x717 = x0(x711)
val x718 = x717 >= '0'
val x719 = x717 <= '9'
val x720 = x718 && x719
val x736 = if (x720) {
val x723 = x698
val x724 = x699
val x725 = x700
val x727 = x723 + 1
x698 = x727
x699 = false
val x721 = x711 + 1
x700 = x721
x704 = x721
()
} else {
x703 = false
()
}
x736
}
x738
}
val x741 = x698
val x742 = x699
val x743 = x700
val x752 = x743 >= x6
val x910 = if (x752) {
x323 = false
()
} else {
val x757 = x0(x743)
val x758 = x757 == '.'
val x908 = if (x758) {
val x759 = x743 + 1
val x761 = x759 >= x6
val x904 = if (x761) {
x323 = false
()
} else {
val x767 = x0(x759)
val x768 = x767 >= '0'
val x769 = x767 <= '9'
val x770 = x768 && x769
val x902 = if (x770) {
var x774: java.lang.String = ""
var x775: Boolean = false
val x771 = x759 + 1
var x776: Int = x771
var x778: Int = -1
var x779: Boolean = true
var x780: Int = x771
val x816 = while ({val x781 = x779
val x782 = x778
val x783 = x780
val x784 = x782 != x783
val x785 = x781 && x784
x785}) {
val x787 = x780
x778 = x787
val x789 = x787 >= x6
val x814 = if (x789) {
x779 = false
()
} else {
val x793 = x0(x787)
val x794 = x793 >= '0'
val x795 = x793 <= '9'
val x796 = x794 && x795
val x812 = if (x796) {
val x799 = x774
val x800 = x775
val x801 = x776
val x803 = x799+x793
x774 = x803
x775 = false
val x797 = x787 + 1
x776 = x797
x780 = x797
()
} else {
x779 = false
()
}
x812
}
x814
}
val x817 = x774
val x818 = x775
val x819 = x776
val x824 = if (x818) {
true
} else {
false
}
val x833 = if (x824) {
true
} else {
false
}
val x839 = if (x833) {
true
} else {
false
}
val x853 = if (x839) {
true
} else {
false
}
val x859 = if (x853) {
true
} else {
false
}
val x874 = if (x859) {
true
} else {
false
}
val x882 = if (x874) {
true
} else {
false
}
val x898 = if (x882) {
x323 = false
()
} else {
val x887 = x318
val x888 = x319
val x889 = x320
val x881 = if (x874) {
0.0
} else {
val x873 = if (x859) {
null
} else {
val x858 = if (x853) {
null
} else {
val x852 = if (x839) {
null
} else {
val x838 = if (x833) {
null
} else {
val x749 = x741 + 1
val x750 = new Anon1680061013(x0,x438,x749)
val x832 = if (x824) {
null
} else {
val x823 = if (x818) {
null
} else {
val x821 = new Tuple2CharString(x767,x817)
x821
}
val x828 = x823._1
val x829 = x823._2
val x830 = x828+x829
x830
}
val x836 = new Tuple2Anon1680061013String(x750,x832)
x836
}
val x843 = x838._1
val x845 = x843.start
val x846 = x843.length
val x844 = x838._2
val x847 = x844.length
val x848 = x846 + x847
val x849 = x848 + 1
val x850 = new Anon1680061013(x0,x845,x849)
x850
}
val x856 = new Tuple2OptionCharAnon1680061013(x87,x852)
x856
}
val x863 = x858._1
val x865 = x863.defined
val x871 = if (x865) {
val x864 = x858._2
val x866 = x864.start
val x867 = x866 - 1
val x868 = x864.length
val x869 = x868 + 1
val x870 = new Anon1680061013(x0,x867,x869)
x870
} else {
val x864 = x858._2
x864
}
x871
}
val x878 = new String(x873.input,x873.start,x873.length)
val x879 = x878.toDouble
x879
}
val x891 = x887 += x881
x318 = x891
x319 = false
val x860 = if (x853) {
x438
} else {
val x840 = if (x833) {
x438
} else {
val x825 = if (x818) {
x759
} else {
x819
}
val x834 = if (x824) {
x825
} else {
x825
}
x834
}
val x854 = if (x839) {
x840
} else {
x840
}
x854
}
val x875 = if (x859) {
x860
} else {
x860
}
val x883 = if (x874) {
x875
} else {
x875
}
x320 = x883
x324 = x883
()
}
x898
} else {
x323 = false
()
}
x902
}
x904
} else {
x323 = false
()
}
x908
}
x910
} else {
x323 = false
()
}
x914
}
x916
}
x918
}
x920
} else {
x323 = false
()
}
x924
}
x926
}
x928
}
val x931 = x318
val x932 = x319
val x933 = x320
val x939 = if (x932) {
val x935 = new ParseResultListDouble(null,true,x933)
x935
} else {
val x936 = x931.toList
val x937 = new ParseResultListDouble(x936,false,x933)
x937
}
val x940 = x939.empty
val x946 = if (x940) {
val x95 = new ParseResultTuple2DoubleListDouble(null,true,x78)
x95
} else {
val x941 = x939.res
val x943 = x939.next
val x309 = if (x302) {
0.0
} else {
val x301 = if (x287) {
null
} else {
val x286 = if (x281) {
null
} else {
val x104 = new OptionChar(x100,true)
val x280 = if (x267) {
null
} else {
val x266 = if (x261) {
null
} else {
val x173 = x165 + 1
val x174 = new Anon1680061013(x0,x102,x173)
val x260 = if (x252) {
null
} else {
val x251 = if (x246) {
null
} else {
val x249 = new Tuple2CharString(x195,x245)
x249
}
val x256 = x251._1
val x257 = x251._2
val x258 = x256+x257
x258
}
val x264 = new Tuple2Anon1680061013String(x174,x260)
x264
}
val x271 = x266._1
val x273 = x271.start
val x274 = x271.length
val x272 = x266._2
val x275 = x272.length
val x276 = x274 + x275
val x277 = x276 + 1
val x278 = new Anon1680061013(x0,x273,x277)
x278
}
val x284 = new Tuple2OptionCharAnon1680061013(x104,x280)
x284
}
val x291 = x286._1
val x293 = x291.defined
val x299 = if (x293) {
val x292 = x286._2
val x294 = x292.start
val x295 = x294 - 1
val x296 = x292.length
val x297 = x296 + 1
val x298 = new Anon1680061013(x0,x295,x297)
x298
} else {
val x292 = x286._2
x292
}
x299
}
val x306 = new String(x301.input,x301.start,x301.length)
val x307 = x306.toDouble
x307
}
val x942 = new Tuple2DoubleListDouble(x309,x941)
val x944 = new ParseResultTuple2DoubleListDouble(x942,false,x943)
x944
}
val x947 = x946.empty
val x958 = if (x947) {
val x948 = x946.next
val x949 = new ParseResultListDouble(null,true,x948)
x949
} else {
val x951 = x946.res
val x952 = x951._1
val x953 = x951._2
val x955 = x946.next
val x954 = x952 :: x953
val x956 = new ParseResultListDouble(x954,false,x955)
x956
}
val x959 = x958.res
x80 = x959
val x961 = x958.empty
x81 = x961
val x963 = x958.next
x82 = x963
()
}
x966
} else {
x80 = null
x81 = true
x82 = x78
()
}
x972
}
x974
} else {
x80 = null
x81 = true
x82 = x78
()
}
x980
}
x982
} else {
x80 = null
x81 = true
x82 = x78
()
}
x988
}
x990
} else {
val x992 = x100 >= '0'
val x993 = x100 <= '9'
val x994 = x992 && x993
val x1863 = if (x994) {
var x997: Int = 0
var x998: Boolean = false
val x102 = x78 + 1
var x999: Int = x102
var x1001: Int = -1
var x1002: Boolean = true
var x1003: Int = x102
val x1039 = while ({val x1004 = x1002
val x1005 = x1001
val x1006 = x1003
val x1007 = x1005 != x1006
val x1008 = x1004 && x1007
x1008}) {
val x1010 = x1003
x1001 = x1010
val x1012 = x1010 >= x6
val x1037 = if (x1012) {
x1002 = false
()
} else {
val x1016 = x0(x1010)
val x1017 = x1016 >= '0'
val x1018 = x1016 <= '9'
val x1019 = x1017 && x1018
val x1035 = if (x1019) {
val x1022 = x997
val x1023 = x998
val x1024 = x999
val x1026 = x1022 + 1
x997 = x1026
x998 = false
val x1020 = x1010 + 1
x999 = x1020
x1003 = x1020
()
} else {
x1002 = false
()
}
x1035
}
x1037
}
val x1040 = x997
val x1041 = x998
val x1042 = x999
val x1051 = x1042 >= x6
val x1857 = if (x1051) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x1058 = x0(x1042)
val x1059 = x1058 == '.'
val x1855 = if (x1059) {
val x1060 = x1042 + 1
val x1062 = x1060 >= x6
val x1849 = if (x1062) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x1070 = x0(x1060)
val x1071 = x1070 >= '0'
val x1072 = x1070 <= '9'
val x1073 = x1071 && x1072
val x1847 = if (x1073) {
var x1077: java.lang.String = ""
var x1078: Boolean = false
val x1074 = x1060 + 1
var x1079: Int = x1074
var x1081: Int = -1
var x1082: Boolean = true
var x1083: Int = x1074
val x1119 = while ({val x1084 = x1082
val x1085 = x1081
val x1086 = x1083
val x1087 = x1085 != x1086
val x1088 = x1084 && x1087
x1088}) {
val x1090 = x1083
x1081 = x1090
val x1092 = x1090 >= x6
val x1117 = if (x1092) {
x1082 = false
()
} else {
val x1096 = x0(x1090)
val x1097 = x1096 >= '0'
val x1098 = x1096 <= '9'
val x1099 = x1097 && x1098
val x1115 = if (x1099) {
val x1102 = x1077
val x1103 = x1078
val x1104 = x1079
val x1106 = x1102+x1096
x1077 = x1106
x1078 = false
val x1100 = x1090 + 1
x1079 = x1100
x1083 = x1100
()
} else {
x1082 = false
()
}
x1115
}
x1117
}
val x1120 = x1077
val x1121 = x1078
val x1122 = x1079
val x1127 = if (x1121) {
true
} else {
false
}
val x1136 = if (x1127) {
true
} else {
false
}
val x1142 = if (x1136) {
true
} else {
false
}
val x1156 = if (x1142) {
true
} else {
false
}
val x1162 = if (x1156) {
true
} else {
false
}
val x1177 = if (x1162) {
true
} else {
false
}
val x1185 = if (x1177) {
true
} else {
false
}
val x1841 = if (x1185) {
x80 = null
x81 = true
x82 = x78
()
} else {
var x1193: scala.collection.mutable.ListBuffer[Double] = x70
var x1194: Boolean = false
val x1163 = if (x1156) {
x78
} else {
val x1143 = if (x1136) {
x78
} else {
val x1128 = if (x1121) {
x1060
} else {
x1122
}
val x1137 = if (x1127) {
x1128
} else {
x1128
}
x1137
}
val x1157 = if (x1142) {
x1143
} else {
x1143
}
x1157
}
val x1178 = if (x1162) {
x1163
} else {
x1163
}
val x1186 = if (x1177) {
x1178
} else {
x1178
}
var x1195: Int = x1186
var x1197: Int = -1
var x1198: Boolean = true
var x1199: Int = x1186
val x1805 = while ({val x1200 = x1198
val x1201 = x1197
val x1202 = x1199
val x1203 = x1201 != x1202
val x1204 = x1200 && x1203
x1204}) {
val x1206 = x1199
x1197 = x1206
var x1209: java.lang.String = ""
var x1210: Boolean = false
var x1211: Int = x1206
var x1213: Int = -1
var x1214: Boolean = true
var x1215: Int = x1206
val x1250 = while ({val x1216 = x1214
val x1217 = x1213
val x1218 = x1215
val x1219 = x1217 != x1218
val x1220 = x1216 && x1219
x1220}) {
val x1222 = x1215
x1213 = x1222
val x1224 = x1222 >= x6
val x1248 = if (x1224) {
x1214 = false
()
} else {
val x1228 = x0(x1222)
val x1229 = x1228 == ' '
val x1230 = x1228 == '\n'
val x1231 = x1229 || x1230
val x1246 = if (x1231) {
val x1234 = x1209
val x1235 = x1210
val x1236 = x1211
x1209 = x1234
x1210 = false
val x1232 = x1222 + 1
x1211 = x1232
x1215 = x1232
()
} else {
x1214 = false
()
}
x1246
}
x1248
}
val x1251 = x1209
val x1252 = x1210
val x1253 = x1211
val x1803 = if (x1252) {
x1198 = false
()
} else {
val x1260 = x1253 >= x6
val x1801 = if (x1260) {
x1198 = false
()
} else {
val x1264 = x0(x1253)
val x1265 = x1264 == ','
val x1799 = if (x1265) {
var x1269: java.lang.String = ""
var x1270: Boolean = false
val x1266 = x1253 + 1
var x1271: Int = x1266
var x1273: Int = -1
var x1274: Boolean = true
var x1275: Int = x1266
val x1310 = while ({val x1276 = x1274
val x1277 = x1273
val x1278 = x1275
val x1279 = x1277 != x1278
val x1280 = x1276 && x1279
x1280}) {
val x1282 = x1275
x1273 = x1282
val x1284 = x1282 >= x6
val x1308 = if (x1284) {
x1274 = false
()
} else {
val x1288 = x0(x1282)
val x1289 = x1288 == ' '
val x1290 = x1288 == '\n'
val x1291 = x1289 || x1290
val x1306 = if (x1291) {
val x1294 = x1269
val x1295 = x1270
val x1296 = x1271
x1269 = x1294
x1270 = false
val x1292 = x1282 + 1
x1271 = x1292
x1275 = x1292
()
} else {
x1274 = false
()
}
x1306
}
x1308
}
val x1311 = x1269
val x1312 = x1270
val x1313 = x1271
val x1795 = if (x1312) {
x1198 = false
()
} else {
val x1317 = x1313 >= x6
val x1793 = if (x1317) {
x1198 = false
()
} else {
val x1328 = x0(x1313)
val x1329 = x1328 == '-'
val x1791 = if (x1329) {
val x1330 = x1313 + 1
val x1334 = x1330 >= x6
val x1566 = if (x1334) {
x1198 = false
()
} else {
val x1341 = x0(x1330)
val x1342 = x1341 >= '0'
val x1343 = x1341 <= '9'
val x1344 = x1342 && x1343
val x1564 = if (x1344) {
var x1348: Int = 0
var x1349: Boolean = false
val x1345 = x1330 + 1
var x1350: Int = x1345
var x1352: Int = -1
var x1353: Boolean = true
var x1354: Int = x1345
val x1390 = while ({val x1355 = x1353
val x1356 = x1352
val x1357 = x1354
val x1358 = x1356 != x1357
val x1359 = x1355 && x1358
x1359}) {
val x1361 = x1354
x1352 = x1361
val x1363 = x1361 >= x6
val x1388 = if (x1363) {
x1353 = false
()
} else {
val x1367 = x0(x1361)
val x1368 = x1367 >= '0'
val x1369 = x1367 <= '9'
val x1370 = x1368 && x1369
val x1386 = if (x1370) {
val x1373 = x1348
val x1374 = x1349
val x1375 = x1350
val x1377 = x1373 + 1
x1348 = x1377
x1349 = false
val x1371 = x1361 + 1
x1350 = x1371
x1354 = x1371
()
} else {
x1353 = false
()
}
x1386
}
x1388
}
val x1391 = x1348
val x1392 = x1349
val x1393 = x1350
val x1402 = x1393 >= x6
val x1560 = if (x1402) {
x1198 = false
()
} else {
val x1407 = x0(x1393)
val x1408 = x1407 == '.'
val x1558 = if (x1408) {
val x1409 = x1393 + 1
val x1411 = x1409 >= x6
val x1554 = if (x1411) {
x1198 = false
()
} else {
val x1417 = x0(x1409)
val x1418 = x1417 >= '0'
val x1419 = x1417 <= '9'
val x1420 = x1418 && x1419
val x1552 = if (x1420) {
var x1424: java.lang.String = ""
var x1425: Boolean = false
val x1421 = x1409 + 1
var x1426: Int = x1421
var x1428: Int = -1
var x1429: Boolean = true
var x1430: Int = x1421
val x1466 = while ({val x1431 = x1429
val x1432 = x1428
val x1433 = x1430
val x1434 = x1432 != x1433
val x1435 = x1431 && x1434
x1435}) {
val x1437 = x1430
x1428 = x1437
val x1439 = x1437 >= x6
val x1464 = if (x1439) {
x1429 = false
()
} else {
val x1443 = x0(x1437)
val x1444 = x1443 >= '0'
val x1445 = x1443 <= '9'
val x1446 = x1444 && x1445
val x1462 = if (x1446) {
val x1449 = x1424
val x1450 = x1425
val x1451 = x1426
val x1453 = x1449+x1443
x1424 = x1453
x1425 = false
val x1447 = x1437 + 1
x1426 = x1447
x1430 = x1447
()
} else {
x1429 = false
()
}
x1462
}
x1464
}
val x1467 = x1424
val x1468 = x1425
val x1469 = x1426
val x1474 = if (x1468) {
true
} else {
false
}
val x1483 = if (x1474) {
true
} else {
false
}
val x1489 = if (x1483) {
true
} else {
false
}
val x1503 = if (x1489) {
true
} else {
false
}
val x1509 = if (x1503) {
true
} else {
false
}
val x1524 = if (x1509) {
true
} else {
false
}
val x1532 = if (x1524) {
true
} else {
false
}
val x1548 = if (x1532) {
x1198 = false
()
} else {
val x1537 = x1193
val x1538 = x1194
val x1539 = x1195
val x1531 = if (x1524) {
0.0
} else {
val x1523 = if (x1509) {
null
} else {
val x1508 = if (x1503) {
null
} else {
val x1332 = new OptionChar(x1328,true)
val x1502 = if (x1489) {
null
} else {
val x1488 = if (x1483) {
null
} else {
val x1399 = x1391 + 1
val x1400 = new Anon1680061013(x0,x1330,x1399)
val x1482 = if (x1474) {
null
} else {
val x1473 = if (x1468) {
null
} else {
val x1471 = new Tuple2CharString(x1417,x1467)
x1471
}
val x1478 = x1473._1
val x1479 = x1473._2
val x1480 = x1478+x1479
x1480
}
val x1486 = new Tuple2Anon1680061013String(x1400,x1482)
x1486
}
val x1493 = x1488._1
val x1495 = x1493.start
val x1496 = x1493.length
val x1494 = x1488._2
val x1497 = x1494.length
val x1498 = x1496 + x1497
val x1499 = x1498 + 1
val x1500 = new Anon1680061013(x0,x1495,x1499)
x1500
}
val x1506 = new Tuple2OptionCharAnon1680061013(x1332,x1502)
x1506
}
val x1513 = x1508._1
val x1515 = x1513.defined
val x1521 = if (x1515) {
val x1514 = x1508._2
val x1516 = x1514.start
val x1517 = x1516 - 1
val x1518 = x1514.length
val x1519 = x1518 + 1
val x1520 = new Anon1680061013(x0,x1517,x1519)
x1520
} else {
val x1514 = x1508._2
x1514
}
x1521
}
val x1528 = new String(x1523.input,x1523.start,x1523.length)
val x1529 = x1528.toDouble
x1529
}
val x1541 = x1537 += x1531
x1193 = x1541
x1194 = false
val x1510 = if (x1503) {
x1313
} else {
val x1490 = if (x1483) {
x1330
} else {
val x1475 = if (x1468) {
x1409
} else {
x1469
}
val x1484 = if (x1474) {
x1475
} else {
x1475
}
x1484
}
val x1504 = if (x1489) {
x1490
} else {
x1490
}
x1504
}
val x1525 = if (x1509) {
x1510
} else {
x1510
}
val x1533 = if (x1524) {
x1525
} else {
x1525
}
x1195 = x1533
x1199 = x1533
()
}
x1548
} else {
x1198 = false
()
}
x1552
}
x1554
} else {
x1198 = false
()
}
x1558
}
x1560
} else {
x1198 = false
()
}
x1564
}
x1566
} else {
val x1568 = x1328 >= '0'
val x1569 = x1328 <= '9'
val x1570 = x1568 && x1569
val x1789 = if (x1570) {
var x1573: Int = 0
var x1574: Boolean = false
val x1330 = x1313 + 1
var x1575: Int = x1330
var x1577: Int = -1
var x1578: Boolean = true
var x1579: Int = x1330
val x1615 = while ({val x1580 = x1578
val x1581 = x1577
val x1582 = x1579
val x1583 = x1581 != x1582
val x1584 = x1580 && x1583
x1584}) {
val x1586 = x1579
x1577 = x1586
val x1588 = x1586 >= x6
val x1613 = if (x1588) {
x1578 = false
()
} else {
val x1592 = x0(x1586)
val x1593 = x1592 >= '0'
val x1594 = x1592 <= '9'
val x1595 = x1593 && x1594
val x1611 = if (x1595) {
val x1598 = x1573
val x1599 = x1574
val x1600 = x1575
val x1602 = x1598 + 1
x1573 = x1602
x1574 = false
val x1596 = x1586 + 1
x1575 = x1596
x1579 = x1596
()
} else {
x1578 = false
()
}
x1611
}
x1613
}
val x1616 = x1573
val x1617 = x1574
val x1618 = x1575
val x1627 = x1618 >= x6
val x1785 = if (x1627) {
x1198 = false
()
} else {
val x1632 = x0(x1618)
val x1633 = x1632 == '.'
val x1783 = if (x1633) {
val x1634 = x1618 + 1
val x1636 = x1634 >= x6
val x1779 = if (x1636) {
x1198 = false
()
} else {
val x1642 = x0(x1634)
val x1643 = x1642 >= '0'
val x1644 = x1642 <= '9'
val x1645 = x1643 && x1644
val x1777 = if (x1645) {
var x1649: java.lang.String = ""
var x1650: Boolean = false
val x1646 = x1634 + 1
var x1651: Int = x1646
var x1653: Int = -1
var x1654: Boolean = true
var x1655: Int = x1646
val x1691 = while ({val x1656 = x1654
val x1657 = x1653
val x1658 = x1655
val x1659 = x1657 != x1658
val x1660 = x1656 && x1659
x1660}) {
val x1662 = x1655
x1653 = x1662
val x1664 = x1662 >= x6
val x1689 = if (x1664) {
x1654 = false
()
} else {
val x1668 = x0(x1662)
val x1669 = x1668 >= '0'
val x1670 = x1668 <= '9'
val x1671 = x1669 && x1670
val x1687 = if (x1671) {
val x1674 = x1649
val x1675 = x1650
val x1676 = x1651
val x1678 = x1674+x1668
x1649 = x1678
x1650 = false
val x1672 = x1662 + 1
x1651 = x1672
x1655 = x1672
()
} else {
x1654 = false
()
}
x1687
}
x1689
}
val x1692 = x1649
val x1693 = x1650
val x1694 = x1651
val x1699 = if (x1693) {
true
} else {
false
}
val x1708 = if (x1699) {
true
} else {
false
}
val x1714 = if (x1708) {
true
} else {
false
}
val x1728 = if (x1714) {
true
} else {
false
}
val x1734 = if (x1728) {
true
} else {
false
}
val x1749 = if (x1734) {
true
} else {
false
}
val x1757 = if (x1749) {
true
} else {
false
}
val x1773 = if (x1757) {
x1198 = false
()
} else {
val x1762 = x1193
val x1763 = x1194
val x1764 = x1195
val x1756 = if (x1749) {
0.0
} else {
val x1748 = if (x1734) {
null
} else {
val x1733 = if (x1728) {
null
} else {
val x1727 = if (x1714) {
null
} else {
val x1713 = if (x1708) {
null
} else {
val x1624 = x1616 + 1
val x1625 = new Anon1680061013(x0,x1313,x1624)
val x1707 = if (x1699) {
null
} else {
val x1698 = if (x1693) {
null
} else {
val x1696 = new Tuple2CharString(x1642,x1692)
x1696
}
val x1703 = x1698._1
val x1704 = x1698._2
val x1705 = x1703+x1704
x1705
}
val x1711 = new Tuple2Anon1680061013String(x1625,x1707)
x1711
}
val x1718 = x1713._1
val x1720 = x1718.start
val x1721 = x1718.length
val x1719 = x1713._2
val x1722 = x1719.length
val x1723 = x1721 + x1722
val x1724 = x1723 + 1
val x1725 = new Anon1680061013(x0,x1720,x1724)
x1725
}
val x1731 = new Tuple2OptionCharAnon1680061013(x87,x1727)
x1731
}
val x1738 = x1733._1
val x1740 = x1738.defined
val x1746 = if (x1740) {
val x1739 = x1733._2
val x1741 = x1739.start
val x1742 = x1741 - 1
val x1743 = x1739.length
val x1744 = x1743 + 1
val x1745 = new Anon1680061013(x0,x1742,x1744)
x1745
} else {
val x1739 = x1733._2
x1739
}
x1746
}
val x1753 = new String(x1748.input,x1748.start,x1748.length)
val x1754 = x1753.toDouble
x1754
}
val x1766 = x1762 += x1756
x1193 = x1766
x1194 = false
val x1735 = if (x1728) {
x1313
} else {
val x1715 = if (x1708) {
x1313
} else {
val x1700 = if (x1693) {
x1634
} else {
x1694
}
val x1709 = if (x1699) {
x1700
} else {
x1700
}
x1709
}
val x1729 = if (x1714) {
x1715
} else {
x1715
}
x1729
}
val x1750 = if (x1734) {
x1735
} else {
x1735
}
val x1758 = if (x1749) {
x1750
} else {
x1750
}
x1195 = x1758
x1199 = x1758
()
}
x1773
} else {
x1198 = false
()
}
x1777
}
x1779
} else {
x1198 = false
()
}
x1783
}
x1785
} else {
x1198 = false
()
}
x1789
}
x1791
}
x1793
}
x1795
} else {
x1198 = false
()
}
x1799
}
x1801
}
x1803
}
val x1806 = x1193
val x1807 = x1194
val x1808 = x1195
val x1814 = if (x1807) {
val x1810 = new ParseResultListDouble(null,true,x1808)
x1810
} else {
val x1811 = x1806.toList
val x1812 = new ParseResultListDouble(x1811,false,x1808)
x1812
}
val x1815 = x1814.empty
val x1821 = if (x1815) {
val x95 = new ParseResultTuple2DoubleListDouble(null,true,x78)
x95
} else {
val x1816 = x1814.res
val x1818 = x1814.next
val x1184 = if (x1177) {
0.0
} else {
val x1176 = if (x1162) {
null
} else {
val x1161 = if (x1156) {
null
} else {
val x1155 = if (x1142) {
null
} else {
val x1141 = if (x1136) {
null
} else {
val x1048 = x1040 + 1
val x1049 = new Anon1680061013(x0,x78,x1048)
val x1135 = if (x1127) {
null
} else {
val x1126 = if (x1121) {
null
} else {
val x1124 = new Tuple2CharString(x1070,x1120)
x1124
}
val x1131 = x1126._1
val x1132 = x1126._2
val x1133 = x1131+x1132
x1133
}
val x1139 = new Tuple2Anon1680061013String(x1049,x1135)
x1139
}
val x1146 = x1141._1
val x1148 = x1146.start
val x1149 = x1146.length
val x1147 = x1141._2
val x1150 = x1147.length
val x1151 = x1149 + x1150
val x1152 = x1151 + 1
val x1153 = new Anon1680061013(x0,x1148,x1152)
x1153
}
val x1159 = new Tuple2OptionCharAnon1680061013(x87,x1155)
x1159
}
val x1166 = x1161._1
val x1168 = x1166.defined
val x1174 = if (x1168) {
val x1167 = x1161._2
val x1169 = x1167.start
val x1170 = x1169 - 1
val x1171 = x1167.length
val x1172 = x1171 + 1
val x1173 = new Anon1680061013(x0,x1170,x1172)
x1173
} else {
val x1167 = x1161._2
x1167
}
x1174
}
val x1181 = new String(x1176.input,x1176.start,x1176.length)
val x1182 = x1181.toDouble
x1182
}
val x1817 = new Tuple2DoubleListDouble(x1184,x1816)
val x1819 = new ParseResultTuple2DoubleListDouble(x1817,false,x1818)
x1819
}
val x1822 = x1821.empty
val x1833 = if (x1822) {
val x1823 = x1821.next
val x1824 = new ParseResultListDouble(null,true,x1823)
x1824
} else {
val x1826 = x1821.res
val x1827 = x1826._1
val x1828 = x1826._2
val x1830 = x1821.next
val x1829 = x1827 :: x1828
val x1831 = new ParseResultListDouble(x1829,false,x1830)
x1831
}
val x1834 = x1833.res
x80 = x1834
val x1836 = x1833.empty
x81 = x1836
val x1838 = x1833.next
x82 = x1838
()
}
x1841
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1847
}
x1849
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1855
}
x1857
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1863
}
x1865
}
val x1868 = x80
val x1869 = x81
val x1870 = x82
val x1871 = new ParseResultListDouble(x1868,x1869,x1870)
x1871: ParseResultListDouble
}
val x71 = List()
val x1893 = {x72: (Int) =>
var x74: scala.collection.immutable.List[Double] = null
var x75: Boolean = true
var x76: Int = x72
val x1874 = x1873(x72)
val x1875 = x1874.empty
val x1887 = if (x1875) {
x74 = x71
x75 = false
x76 = x72
()
} else {
val x1881 = x1874.res
x74 = x1881
x75 = x1875
val x1884 = x1874.next
x76 = x1884
()
}
val x1888 = x74
val x1889 = x75
val x1890 = x76
val x1891 = new ParseResultListDouble(x1888,x1889,x1890)
x1891: ParseResultListDouble
}
val x1894 = x1893(x64)
val x1895 = x1894.empty
val x1980 = if (x1895) {
val x1896 = x1894.res
x2 = x1896
x3 = x1895
val x1899 = x1894.next
x4 = x1899
()
} else {
var x1903: java.lang.String = ""
var x1904: Boolean = false
val x1899 = x1894.next
var x1905: Int = x1899
var x1907: Int = -1
var x1908: Boolean = true
var x1909: Int = x1899
val x1944 = while ({val x1910 = x1908
val x1911 = x1907
val x1912 = x1909
val x1913 = x1911 != x1912
val x1914 = x1910 && x1913
x1914}) {
val x1916 = x1909
x1907 = x1916
val x1918 = x1916 >= x6
val x1942 = if (x1918) {
x1908 = false
()
} else {
val x1922 = x0(x1916)
val x1923 = x1922 == ' '
val x1924 = x1922 == '\n'
val x1925 = x1923 || x1924
val x1940 = if (x1925) {
val x1928 = x1903
val x1929 = x1904
val x1930 = x1905
x1903 = x1928
x1904 = false
val x1926 = x1916 + 1
x1905 = x1926
x1909 = x1926
()
} else {
x1908 = false
()
}
x1940
}
x1942
}
val x1945 = x1903
val x1946 = x1904
val x1947 = x1905
val x1978 = if (x1946) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x1948 = new ParseResultString(x1945,x1946,x1947)
val x1954 = x1948.next
val x1955 = x1954 >= x6
val x1976 = if (x1955) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x1961 = x0(x1954)
val x1962 = x1961 == ']'
val x1974 = if (x1962) {
val x1896 = x1894.res
x2 = x1896
x3 = false
val x1963 = x1954 + 1
x4 = x1963
()
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1974
}
x1976
}
x1978
}
x1980
}
x1982
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1988
}
val x1991 = x2
val x1992 = x3
val x1993 = x4
val x1994 = new ParseResultListDouble(x1991,x1992,x1993)
x1994
}
}
/*****************************************
  End of Generated Code
*******************************************/
