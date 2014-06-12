// Generated file using sbt> test:run-main lms.parsing.TestCSV
package lms
/**
 * removed extra overhead related to parsing the double, which was accumulating the decimal part into a
 * string first
 */
object LMSCSVDoubleParserGen3 extends CSVDoubleParseGen3()

/*****************************************
  Emitting Generated Code
*******************************************/
case class CSVDoubleParseGen3() extends ((Array[Char]) => ParseResultListDouble) {
def apply(x0:Array[Char]): ParseResultListDouble = {
var x2: scala.collection.immutable.List[Double] = null
var x3: Boolean = true
var x4: Int = -1
val x6 = x0.length
val x7 = 0 >= x6
val x1698 = if (x7) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x15 = x0(0)
val x16 = x15 == '['
val x1696 = if (x16) {
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
val x1690 = if (x63) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x70 = scala.collection.mutable.ListBuffer[Double]()
val x1581 = {x78: (Int) =>
var x80: scala.collection.immutable.List[Double] = null
var x81: Boolean = true
var x82: Int = x78
val x84 = x78 >= x6
val x1575 = if (x84) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x100 = x0(x78)
val x101 = x100 == '-'
val x1573 = if (x101) {
val x102 = x78 + 1
val x106 = x102 >= x6
val x846 = if (x106) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x115 = x0(x102)
val x116 = x115 >= '0'
val x117 = x115 <= '9'
val x118 = x116 && x117
val x844 = if (x118) {
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
val x838 = if (x176) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x183 = x0(x167)
val x184 = x183 == '.'
val x836 = if (x184) {
val x185 = x167 + 1
val x187 = x185 >= x6
val x830 = if (x187) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x195 = x0(x185)
val x196 = x195 >= '0'
val x197 = x195 <= '9'
val x198 = x196 && x197
val x828 = if (x198) {
var x202: Int = 0
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
val x231 = x227 + 1
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
var x272: scala.collection.mutable.ListBuffer[Double] = x70
var x273: Boolean = false
var x274: Int = x247
var x276: Int = -1
var x277: Boolean = true
var x278: Int = x247
val x788 = while ({val x279 = x277
val x280 = x276
val x281 = x278
val x282 = x280 != x281
val x283 = x279 && x282
x283}) {
val x285 = x278
x276 = x285
var x288: java.lang.String = ""
var x289: Boolean = false
var x290: Int = x285
var x292: Int = -1
var x293: Boolean = true
var x294: Int = x285
val x329 = while ({val x295 = x293
val x296 = x292
val x297 = x294
val x298 = x296 != x297
val x299 = x295 && x298
x299}) {
val x301 = x294
x292 = x301
val x303 = x301 >= x6
val x327 = if (x303) {
x293 = false
()
} else {
val x307 = x0(x301)
val x308 = x307 == ' '
val x309 = x307 == '\n'
val x310 = x308 || x309
val x325 = if (x310) {
val x313 = x288
val x314 = x289
val x315 = x290
x288 = x313
x289 = false
val x311 = x301 + 1
x290 = x311
x294 = x311
()
} else {
x293 = false
()
}
x325
}
x327
}
val x330 = x288
val x331 = x289
val x332 = x290
val x786 = if (x331) {
x277 = false
()
} else {
val x339 = x332 >= x6
val x784 = if (x339) {
x277 = false
()
} else {
val x343 = x0(x332)
val x344 = x343 == ','
val x782 = if (x344) {
var x348: java.lang.String = ""
var x349: Boolean = false
val x345 = x332 + 1
var x350: Int = x345
var x352: Int = -1
var x353: Boolean = true
var x354: Int = x345
val x389 = while ({val x355 = x353
val x356 = x352
val x357 = x354
val x358 = x356 != x357
val x359 = x355 && x358
x359}) {
val x361 = x354
x352 = x361
val x363 = x361 >= x6
val x387 = if (x363) {
x353 = false
()
} else {
val x367 = x0(x361)
val x368 = x367 == ' '
val x369 = x367 == '\n'
val x370 = x368 || x369
val x385 = if (x370) {
val x373 = x348
val x374 = x349
val x375 = x350
x348 = x373
x349 = false
val x371 = x361 + 1
x350 = x371
x354 = x371
()
} else {
x353 = false
()
}
x385
}
x387
}
val x390 = x348
val x391 = x349
val x392 = x350
val x778 = if (x391) {
x277 = false
()
} else {
val x396 = x392 >= x6
val x776 = if (x396) {
x277 = false
()
} else {
val x407 = x0(x392)
val x408 = x407 == '-'
val x774 = if (x408) {
val x409 = x392 + 1
val x413 = x409 >= x6
val x599 = if (x413) {
x277 = false
()
} else {
val x420 = x0(x409)
val x421 = x420 >= '0'
val x422 = x420 <= '9'
val x423 = x421 && x422
val x597 = if (x423) {
var x427: Int = 0
var x428: Boolean = false
val x424 = x409 + 1
var x429: Int = x424
var x431: Int = -1
var x432: Boolean = true
var x433: Int = x424
val x469 = while ({val x434 = x432
val x435 = x431
val x436 = x433
val x437 = x435 != x436
val x438 = x434 && x437
x438}) {
val x440 = x433
x431 = x440
val x442 = x440 >= x6
val x467 = if (x442) {
x432 = false
()
} else {
val x446 = x0(x440)
val x447 = x446 >= '0'
val x448 = x446 <= '9'
val x449 = x447 && x448
val x465 = if (x449) {
val x452 = x427
val x453 = x428
val x454 = x429
val x456 = x452 + 1
x427 = x456
x428 = false
val x450 = x440 + 1
x429 = x450
x433 = x450
()
} else {
x432 = false
()
}
x465
}
x467
}
val x470 = x427
val x471 = x428
val x472 = x429
val x481 = x472 >= x6
val x593 = if (x481) {
x277 = false
()
} else {
val x486 = x0(x472)
val x487 = x486 == '.'
val x591 = if (x487) {
val x488 = x472 + 1
val x490 = x488 >= x6
val x587 = if (x490) {
x277 = false
()
} else {
val x496 = x0(x488)
val x497 = x496 >= '0'
val x498 = x496 <= '9'
val x499 = x497 && x498
val x585 = if (x499) {
var x503: Int = 0
var x504: Boolean = false
val x500 = x488 + 1
var x505: Int = x500
var x507: Int = -1
var x508: Boolean = true
var x509: Int = x500
val x545 = while ({val x510 = x508
val x511 = x507
val x512 = x509
val x513 = x511 != x512
val x514 = x510 && x513
x514}) {
val x516 = x509
x507 = x516
val x518 = x516 >= x6
val x543 = if (x518) {
x508 = false
()
} else {
val x522 = x0(x516)
val x523 = x522 >= '0'
val x524 = x522 <= '9'
val x525 = x523 && x524
val x541 = if (x525) {
val x528 = x503
val x529 = x504
val x530 = x505
val x532 = x528 + 1
x503 = x532
x504 = false
val x526 = x516 + 1
x505 = x526
x509 = x526
()
} else {
x508 = false
()
}
x541
}
x543
}
val x546 = x503
val x547 = x504
val x548 = x505
val x572 = x272
val x573 = x273
val x574 = x274
val x565 = x409 - 1
val x478 = x470 + 1
val x554 = x546 + 1
val x559 = x478 + x554
val x560 = x559 + 1
val x566 = x560 + 1
val x567 = new Anon1680061013(x0,x565,x566)
val x569 = new String(x567.input,x567.start,x567.length)
val x570 = x569.toDouble
val x576 = x572 += x570
x272 = x576
x273 = false
x274 = x548
x278 = x548
()
} else {
x277 = false
()
}
x585
}
x587
} else {
x277 = false
()
}
x591
}
x593
} else {
x277 = false
()
}
x597
}
x599
} else {
val x601 = x407 >= '0'
val x602 = x407 <= '9'
val x603 = x601 && x602
val x772 = if (x603) {
var x606: Int = 0
var x607: Boolean = false
val x409 = x392 + 1
var x608: Int = x409
var x610: Int = -1
var x611: Boolean = true
var x612: Int = x409
val x648 = while ({val x613 = x611
val x614 = x610
val x615 = x612
val x616 = x614 != x615
val x617 = x613 && x616
x617}) {
val x619 = x612
x610 = x619
val x621 = x619 >= x6
val x646 = if (x621) {
x611 = false
()
} else {
val x625 = x0(x619)
val x626 = x625 >= '0'
val x627 = x625 <= '9'
val x628 = x626 && x627
val x644 = if (x628) {
val x631 = x606
val x632 = x607
val x633 = x608
val x635 = x631 + 1
x606 = x635
x607 = false
val x629 = x619 + 1
x608 = x629
x612 = x629
()
} else {
x611 = false
()
}
x644
}
x646
}
val x649 = x606
val x650 = x607
val x651 = x608
val x660 = x651 >= x6
val x768 = if (x660) {
x277 = false
()
} else {
val x665 = x0(x651)
val x666 = x665 == '.'
val x766 = if (x666) {
val x667 = x651 + 1
val x669 = x667 >= x6
val x762 = if (x669) {
x277 = false
()
} else {
val x675 = x0(x667)
val x676 = x675 >= '0'
val x677 = x675 <= '9'
val x678 = x676 && x677
val x760 = if (x678) {
var x682: Int = 0
var x683: Boolean = false
val x679 = x667 + 1
var x684: Int = x679
var x686: Int = -1
var x687: Boolean = true
var x688: Int = x679
val x724 = while ({val x689 = x687
val x690 = x686
val x691 = x688
val x692 = x690 != x691
val x693 = x689 && x692
x693}) {
val x695 = x688
x686 = x695
val x697 = x695 >= x6
val x722 = if (x697) {
x687 = false
()
} else {
val x701 = x0(x695)
val x702 = x701 >= '0'
val x703 = x701 <= '9'
val x704 = x702 && x703
val x720 = if (x704) {
val x707 = x682
val x708 = x683
val x709 = x684
val x711 = x707 + 1
x682 = x711
x683 = false
val x705 = x695 + 1
x684 = x705
x688 = x705
()
} else {
x687 = false
()
}
x720
}
x722
}
val x725 = x682
val x726 = x683
val x727 = x684
val x747 = x272
val x748 = x273
val x749 = x274
val x657 = x649 + 1
val x733 = x725 + 1
val x738 = x657 + x733
val x739 = x738 + 1
val x740 = new Anon1680061013(x0,x392,x739)
val x744 = new String(x740.input,x740.start,x740.length)
val x745 = x744.toDouble
val x751 = x747 += x745
x272 = x751
x273 = false
x274 = x727
x278 = x727
()
} else {
x277 = false
()
}
x760
}
x762
} else {
x277 = false
()
}
x766
}
x768
} else {
x277 = false
()
}
x772
}
x774
}
x776
}
x778
} else {
x277 = false
()
}
x782
}
x784
}
x786
}
val x789 = x272
val x790 = x273
val x791 = x274
val x797 = if (x790) {
val x793 = new ParseResultListDouble(null,true,x791)
x793
} else {
val x794 = x789.toList
val x795 = new ParseResultListDouble(x794,false,x791)
x795
}
val x798 = x797.empty
val x804 = if (x798) {
val x95 = new ParseResultTuple2DoubleListDouble(null,true,x78)
x95
} else {
val x799 = x797.res
val x801 = x797.next
val x264 = x102 - 1
val x173 = x165 + 1
val x253 = x245 + 1
val x258 = x173 + x253
val x259 = x258 + 1
val x265 = x259 + 1
val x266 = new Anon1680061013(x0,x264,x265)
val x268 = new String(x266.input,x266.start,x266.length)
val x269 = x268.toDouble
val x800 = new Tuple2DoubleListDouble(x269,x799)
val x802 = new ParseResultTuple2DoubleListDouble(x800,false,x801)
x802
}
val x805 = x804.empty
val x816 = if (x805) {
val x806 = x804.next
val x807 = new ParseResultListDouble(null,true,x806)
x807
} else {
val x809 = x804.res
val x810 = x809._1
val x811 = x809._2
val x813 = x804.next
val x812 = x810 :: x811
val x814 = new ParseResultListDouble(x812,false,x813)
x814
}
val x817 = x816.res
x80 = x817
val x819 = x816.empty
x81 = x819
val x821 = x816.next
x82 = x821
()
} else {
x80 = null
x81 = true
x82 = x78
()
}
x828
}
x830
} else {
x80 = null
x81 = true
x82 = x78
()
}
x836
}
x838
} else {
x80 = null
x81 = true
x82 = x78
()
}
x844
}
x846
} else {
val x848 = x100 >= '0'
val x849 = x100 <= '9'
val x850 = x848 && x849
val x1571 = if (x850) {
var x853: Int = 0
var x854: Boolean = false
val x102 = x78 + 1
var x855: Int = x102
var x857: Int = -1
var x858: Boolean = true
var x859: Int = x102
val x895 = while ({val x860 = x858
val x861 = x857
val x862 = x859
val x863 = x861 != x862
val x864 = x860 && x863
x864}) {
val x866 = x859
x857 = x866
val x868 = x866 >= x6
val x893 = if (x868) {
x858 = false
()
} else {
val x872 = x0(x866)
val x873 = x872 >= '0'
val x874 = x872 <= '9'
val x875 = x873 && x874
val x891 = if (x875) {
val x878 = x853
val x879 = x854
val x880 = x855
val x882 = x878 + 1
x853 = x882
x854 = false
val x876 = x866 + 1
x855 = x876
x859 = x876
()
} else {
x858 = false
()
}
x891
}
x893
}
val x896 = x853
val x897 = x854
val x898 = x855
val x907 = x898 >= x6
val x1565 = if (x907) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x914 = x0(x898)
val x915 = x914 == '.'
val x1563 = if (x915) {
val x916 = x898 + 1
val x918 = x916 >= x6
val x1557 = if (x918) {
x80 = null
x81 = true
x82 = x78
()
} else {
val x926 = x0(x916)
val x927 = x926 >= '0'
val x928 = x926 <= '9'
val x929 = x927 && x928
val x1555 = if (x929) {
var x933: Int = 0
var x934: Boolean = false
val x930 = x916 + 1
var x935: Int = x930
var x937: Int = -1
var x938: Boolean = true
var x939: Int = x930
val x975 = while ({val x940 = x938
val x941 = x937
val x942 = x939
val x943 = x941 != x942
val x944 = x940 && x943
x944}) {
val x946 = x939
x937 = x946
val x948 = x946 >= x6
val x973 = if (x948) {
x938 = false
()
} else {
val x952 = x0(x946)
val x953 = x952 >= '0'
val x954 = x952 <= '9'
val x955 = x953 && x954
val x971 = if (x955) {
val x958 = x933
val x959 = x934
val x960 = x935
val x962 = x958 + 1
x933 = x962
x934 = false
val x956 = x946 + 1
x935 = x956
x939 = x956
()
} else {
x938 = false
()
}
x971
}
x973
}
val x976 = x933
val x977 = x934
val x978 = x935
var x999: scala.collection.mutable.ListBuffer[Double] = x70
var x1000: Boolean = false
var x1001: Int = x978
var x1003: Int = -1
var x1004: Boolean = true
var x1005: Int = x978
val x1515 = while ({val x1006 = x1004
val x1007 = x1003
val x1008 = x1005
val x1009 = x1007 != x1008
val x1010 = x1006 && x1009
x1010}) {
val x1012 = x1005
x1003 = x1012
var x1015: java.lang.String = ""
var x1016: Boolean = false
var x1017: Int = x1012
var x1019: Int = -1
var x1020: Boolean = true
var x1021: Int = x1012
val x1056 = while ({val x1022 = x1020
val x1023 = x1019
val x1024 = x1021
val x1025 = x1023 != x1024
val x1026 = x1022 && x1025
x1026}) {
val x1028 = x1021
x1019 = x1028
val x1030 = x1028 >= x6
val x1054 = if (x1030) {
x1020 = false
()
} else {
val x1034 = x0(x1028)
val x1035 = x1034 == ' '
val x1036 = x1034 == '\n'
val x1037 = x1035 || x1036
val x1052 = if (x1037) {
val x1040 = x1015
val x1041 = x1016
val x1042 = x1017
x1015 = x1040
x1016 = false
val x1038 = x1028 + 1
x1017 = x1038
x1021 = x1038
()
} else {
x1020 = false
()
}
x1052
}
x1054
}
val x1057 = x1015
val x1058 = x1016
val x1059 = x1017
val x1513 = if (x1058) {
x1004 = false
()
} else {
val x1066 = x1059 >= x6
val x1511 = if (x1066) {
x1004 = false
()
} else {
val x1070 = x0(x1059)
val x1071 = x1070 == ','
val x1509 = if (x1071) {
var x1075: java.lang.String = ""
var x1076: Boolean = false
val x1072 = x1059 + 1
var x1077: Int = x1072
var x1079: Int = -1
var x1080: Boolean = true
var x1081: Int = x1072
val x1116 = while ({val x1082 = x1080
val x1083 = x1079
val x1084 = x1081
val x1085 = x1083 != x1084
val x1086 = x1082 && x1085
x1086}) {
val x1088 = x1081
x1079 = x1088
val x1090 = x1088 >= x6
val x1114 = if (x1090) {
x1080 = false
()
} else {
val x1094 = x0(x1088)
val x1095 = x1094 == ' '
val x1096 = x1094 == '\n'
val x1097 = x1095 || x1096
val x1112 = if (x1097) {
val x1100 = x1075
val x1101 = x1076
val x1102 = x1077
x1075 = x1100
x1076 = false
val x1098 = x1088 + 1
x1077 = x1098
x1081 = x1098
()
} else {
x1080 = false
()
}
x1112
}
x1114
}
val x1117 = x1075
val x1118 = x1076
val x1119 = x1077
val x1505 = if (x1118) {
x1004 = false
()
} else {
val x1123 = x1119 >= x6
val x1503 = if (x1123) {
x1004 = false
()
} else {
val x1134 = x0(x1119)
val x1135 = x1134 == '-'
val x1501 = if (x1135) {
val x1136 = x1119 + 1
val x1140 = x1136 >= x6
val x1326 = if (x1140) {
x1004 = false
()
} else {
val x1147 = x0(x1136)
val x1148 = x1147 >= '0'
val x1149 = x1147 <= '9'
val x1150 = x1148 && x1149
val x1324 = if (x1150) {
var x1154: Int = 0
var x1155: Boolean = false
val x1151 = x1136 + 1
var x1156: Int = x1151
var x1158: Int = -1
var x1159: Boolean = true
var x1160: Int = x1151
val x1196 = while ({val x1161 = x1159
val x1162 = x1158
val x1163 = x1160
val x1164 = x1162 != x1163
val x1165 = x1161 && x1164
x1165}) {
val x1167 = x1160
x1158 = x1167
val x1169 = x1167 >= x6
val x1194 = if (x1169) {
x1159 = false
()
} else {
val x1173 = x0(x1167)
val x1174 = x1173 >= '0'
val x1175 = x1173 <= '9'
val x1176 = x1174 && x1175
val x1192 = if (x1176) {
val x1179 = x1154
val x1180 = x1155
val x1181 = x1156
val x1183 = x1179 + 1
x1154 = x1183
x1155 = false
val x1177 = x1167 + 1
x1156 = x1177
x1160 = x1177
()
} else {
x1159 = false
()
}
x1192
}
x1194
}
val x1197 = x1154
val x1198 = x1155
val x1199 = x1156
val x1208 = x1199 >= x6
val x1320 = if (x1208) {
x1004 = false
()
} else {
val x1213 = x0(x1199)
val x1214 = x1213 == '.'
val x1318 = if (x1214) {
val x1215 = x1199 + 1
val x1217 = x1215 >= x6
val x1314 = if (x1217) {
x1004 = false
()
} else {
val x1223 = x0(x1215)
val x1224 = x1223 >= '0'
val x1225 = x1223 <= '9'
val x1226 = x1224 && x1225
val x1312 = if (x1226) {
var x1230: Int = 0
var x1231: Boolean = false
val x1227 = x1215 + 1
var x1232: Int = x1227
var x1234: Int = -1
var x1235: Boolean = true
var x1236: Int = x1227
val x1272 = while ({val x1237 = x1235
val x1238 = x1234
val x1239 = x1236
val x1240 = x1238 != x1239
val x1241 = x1237 && x1240
x1241}) {
val x1243 = x1236
x1234 = x1243
val x1245 = x1243 >= x6
val x1270 = if (x1245) {
x1235 = false
()
} else {
val x1249 = x0(x1243)
val x1250 = x1249 >= '0'
val x1251 = x1249 <= '9'
val x1252 = x1250 && x1251
val x1268 = if (x1252) {
val x1255 = x1230
val x1256 = x1231
val x1257 = x1232
val x1259 = x1255 + 1
x1230 = x1259
x1231 = false
val x1253 = x1243 + 1
x1232 = x1253
x1236 = x1253
()
} else {
x1235 = false
()
}
x1268
}
x1270
}
val x1273 = x1230
val x1274 = x1231
val x1275 = x1232
val x1299 = x999
val x1300 = x1000
val x1301 = x1001
val x1292 = x1136 - 1
val x1205 = x1197 + 1
val x1281 = x1273 + 1
val x1286 = x1205 + x1281
val x1287 = x1286 + 1
val x1293 = x1287 + 1
val x1294 = new Anon1680061013(x0,x1292,x1293)
val x1296 = new String(x1294.input,x1294.start,x1294.length)
val x1297 = x1296.toDouble
val x1303 = x1299 += x1297
x999 = x1303
x1000 = false
x1001 = x1275
x1005 = x1275
()
} else {
x1004 = false
()
}
x1312
}
x1314
} else {
x1004 = false
()
}
x1318
}
x1320
} else {
x1004 = false
()
}
x1324
}
x1326
} else {
val x1328 = x1134 >= '0'
val x1329 = x1134 <= '9'
val x1330 = x1328 && x1329
val x1499 = if (x1330) {
var x1333: Int = 0
var x1334: Boolean = false
val x1136 = x1119 + 1
var x1335: Int = x1136
var x1337: Int = -1
var x1338: Boolean = true
var x1339: Int = x1136
val x1375 = while ({val x1340 = x1338
val x1341 = x1337
val x1342 = x1339
val x1343 = x1341 != x1342
val x1344 = x1340 && x1343
x1344}) {
val x1346 = x1339
x1337 = x1346
val x1348 = x1346 >= x6
val x1373 = if (x1348) {
x1338 = false
()
} else {
val x1352 = x0(x1346)
val x1353 = x1352 >= '0'
val x1354 = x1352 <= '9'
val x1355 = x1353 && x1354
val x1371 = if (x1355) {
val x1358 = x1333
val x1359 = x1334
val x1360 = x1335
val x1362 = x1358 + 1
x1333 = x1362
x1334 = false
val x1356 = x1346 + 1
x1335 = x1356
x1339 = x1356
()
} else {
x1338 = false
()
}
x1371
}
x1373
}
val x1376 = x1333
val x1377 = x1334
val x1378 = x1335
val x1387 = x1378 >= x6
val x1495 = if (x1387) {
x1004 = false
()
} else {
val x1392 = x0(x1378)
val x1393 = x1392 == '.'
val x1493 = if (x1393) {
val x1394 = x1378 + 1
val x1396 = x1394 >= x6
val x1489 = if (x1396) {
x1004 = false
()
} else {
val x1402 = x0(x1394)
val x1403 = x1402 >= '0'
val x1404 = x1402 <= '9'
val x1405 = x1403 && x1404
val x1487 = if (x1405) {
var x1409: Int = 0
var x1410: Boolean = false
val x1406 = x1394 + 1
var x1411: Int = x1406
var x1413: Int = -1
var x1414: Boolean = true
var x1415: Int = x1406
val x1451 = while ({val x1416 = x1414
val x1417 = x1413
val x1418 = x1415
val x1419 = x1417 != x1418
val x1420 = x1416 && x1419
x1420}) {
val x1422 = x1415
x1413 = x1422
val x1424 = x1422 >= x6
val x1449 = if (x1424) {
x1414 = false
()
} else {
val x1428 = x0(x1422)
val x1429 = x1428 >= '0'
val x1430 = x1428 <= '9'
val x1431 = x1429 && x1430
val x1447 = if (x1431) {
val x1434 = x1409
val x1435 = x1410
val x1436 = x1411
val x1438 = x1434 + 1
x1409 = x1438
x1410 = false
val x1432 = x1422 + 1
x1411 = x1432
x1415 = x1432
()
} else {
x1414 = false
()
}
x1447
}
x1449
}
val x1452 = x1409
val x1453 = x1410
val x1454 = x1411
val x1474 = x999
val x1475 = x1000
val x1476 = x1001
val x1384 = x1376 + 1
val x1460 = x1452 + 1
val x1465 = x1384 + x1460
val x1466 = x1465 + 1
val x1467 = new Anon1680061013(x0,x1119,x1466)
val x1471 = new String(x1467.input,x1467.start,x1467.length)
val x1472 = x1471.toDouble
val x1478 = x1474 += x1472
x999 = x1478
x1000 = false
x1001 = x1454
x1005 = x1454
()
} else {
x1004 = false
()
}
x1487
}
x1489
} else {
x1004 = false
()
}
x1493
}
x1495
} else {
x1004 = false
()
}
x1499
}
x1501
}
x1503
}
x1505
} else {
x1004 = false
()
}
x1509
}
x1511
}
x1513
}
val x1516 = x999
val x1517 = x1000
val x1518 = x1001
val x1524 = if (x1517) {
val x1520 = new ParseResultListDouble(null,true,x1518)
x1520
} else {
val x1521 = x1516.toList
val x1522 = new ParseResultListDouble(x1521,false,x1518)
x1522
}
val x1525 = x1524.empty
val x1531 = if (x1525) {
val x95 = new ParseResultTuple2DoubleListDouble(null,true,x78)
x95
} else {
val x1526 = x1524.res
val x1528 = x1524.next
val x904 = x896 + 1
val x984 = x976 + 1
val x989 = x904 + x984
val x990 = x989 + 1
val x991 = new Anon1680061013(x0,x78,x990)
val x995 = new String(x991.input,x991.start,x991.length)
val x996 = x995.toDouble
val x1527 = new Tuple2DoubleListDouble(x996,x1526)
val x1529 = new ParseResultTuple2DoubleListDouble(x1527,false,x1528)
x1529
}
val x1532 = x1531.empty
val x1543 = if (x1532) {
val x1533 = x1531.next
val x1534 = new ParseResultListDouble(null,true,x1533)
x1534
} else {
val x1536 = x1531.res
val x1537 = x1536._1
val x1538 = x1536._2
val x1540 = x1531.next
val x1539 = x1537 :: x1538
val x1541 = new ParseResultListDouble(x1539,false,x1540)
x1541
}
val x1544 = x1543.res
x80 = x1544
val x1546 = x1543.empty
x81 = x1546
val x1548 = x1543.next
x82 = x1548
()
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1555
}
x1557
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1563
}
x1565
} else {
x80 = null
x81 = true
x82 = x78
()
}
x1571
}
x1573
}
val x1576 = x80
val x1577 = x81
val x1578 = x82
val x1579 = new ParseResultListDouble(x1576,x1577,x1578)
x1579: ParseResultListDouble
}
val x71 = List()
val x1601 = {x72: (Int) =>
var x74: scala.collection.immutable.List[Double] = null
var x75: Boolean = true
var x76: Int = x72
val x1582 = x1581(x72)
val x1583 = x1582.empty
val x1595 = if (x1583) {
x74 = x71
x75 = false
x76 = x72
()
} else {
val x1589 = x1582.res
x74 = x1589
x75 = x1583
val x1592 = x1582.next
x76 = x1592
()
}
val x1596 = x74
val x1597 = x75
val x1598 = x76
val x1599 = new ParseResultListDouble(x1596,x1597,x1598)
x1599: ParseResultListDouble
}
val x1602 = x1601(x64)
val x1603 = x1602.empty
val x1688 = if (x1603) {
val x1604 = x1602.res
x2 = x1604
x3 = x1603
val x1607 = x1602.next
x4 = x1607
()
} else {
var x1611: java.lang.String = ""
var x1612: Boolean = false
val x1607 = x1602.next
var x1613: Int = x1607
var x1615: Int = -1
var x1616: Boolean = true
var x1617: Int = x1607
val x1652 = while ({val x1618 = x1616
val x1619 = x1615
val x1620 = x1617
val x1621 = x1619 != x1620
val x1622 = x1618 && x1621
x1622}) {
val x1624 = x1617
x1615 = x1624
val x1626 = x1624 >= x6
val x1650 = if (x1626) {
x1616 = false
()
} else {
val x1630 = x0(x1624)
val x1631 = x1630 == ' '
val x1632 = x1630 == '\n'
val x1633 = x1631 || x1632
val x1648 = if (x1633) {
val x1636 = x1611
val x1637 = x1612
val x1638 = x1613
x1611 = x1636
x1612 = false
val x1634 = x1624 + 1
x1613 = x1634
x1617 = x1634
()
} else {
x1616 = false
()
}
x1648
}
x1650
}
val x1653 = x1611
val x1654 = x1612
val x1655 = x1613
val x1686 = if (x1654) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x1656 = new ParseResultString(x1653,x1654,x1655)
val x1662 = x1656.next
val x1663 = x1662 >= x6
val x1684 = if (x1663) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x1669 = x0(x1662)
val x1670 = x1669 == ']'
val x1682 = if (x1670) {
val x1604 = x1602.res
x2 = x1604
x3 = false
val x1671 = x1662 + 1
x4 = x1671
()
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1682
}
x1684
}
x1686
}
x1688
}
x1690
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1696
}
val x1699 = x2
val x1700 = x3
val x1701 = x4
val x1702 = new ParseResultListDouble(x1699,x1700,x1701)
x1702
}
}
/*****************************************
  End of Generated Code
*******************************************/
