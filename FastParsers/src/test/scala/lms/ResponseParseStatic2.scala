package lms

object HttpResponseParser2 extends ResponseParseStatic2(
      "content-length".toArray, //"content-length".length,
      "transfer-encoding".toArray, //"transfer-encoding".length,
      "chunked".toArray, //"chunked".length,
      "upgrade".toArray //, "upgrade".length
    )

case class ResponseParseStatic2(px820:Array[Char],px851:Array[Char],px880:Array[Char],px911:Array[Char]) extends ((Array[Char])=>(Unit)) {
def apply(x0:Array[Char]): Unit = {
var x2: Anon1323431030 = null
var x3: Boolean = true
var x4: Int = -1
val x7 = x0.length
val x8 = 0 >= x7
val x1137 = if (x8) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x21 = x0(0)
val x22 = x21 == 'H'
val x1135 = if (x22) {
val x23 = 0 + 1
val x25 = x23 >= x7
val x1129 = if (x25) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x33 = x0(x23)
val x34 = x33 == 'T'
val x1127 = if (x34) {
val x35 = x23 + 1
val x37 = x35 >= x7
val x1121 = if (x37) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x45 = x0(x35)
val x46 = x45 == 'T'
val x1119 = if (x46) {
val x47 = x35 + 1
val x49 = x47 >= x7
val x1113 = if (x49) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x57 = x0(x47)
val x58 = x57 == 'P'
val x1111 = if (x58) {
val x59 = x47 + 1
val x61 = x59 >= x7
val x1105 = if (x61) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x69 = x0(x59)
val x70 = x69 == '/'
val x1103 = if (x70) {
val x71 = x59 + 1
val x94 = x71 >= x7
val x1097 = if (x94) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x102 = x0(x71)
val x103 = x102 >= '0'
val x104 = x102 <= '9'
val x105 = x103 && x104
val x1095 = if (x105) {
val x108 = x102 - '0'
val x109 = x108.toInt
var x111: Int = x109
var x112: Boolean = false
val x106 = x71 + 1
var x113: Int = x106
var x115: Int = -1
var x116: Boolean = true
var x117: Int = x106
val x158 = while ({val x118 = x116
val x119 = x115
val x120 = x117
val x121 = x119 != x120
val x122 = x118 && x121
x122}) {
val x124 = x117
x115 = x124
val x126 = x124 >= x7
val x156 = if (x126) {
x116 = false
()
} else {
val x131 = x0(x124)
val x132 = x131 >= '0'
val x133 = x131 <= '9'
val x134 = x132 && x133
val x154 = if (x134) {
val x140 = x111
val x141 = x112
val x142 = x113
val x137 = x131 - '0'
val x138 = x137.toInt
val x144 = x140 * 10
val x145 = x144 + x138
x111 = x145
x112 = false
val x135 = x124 + 1
x113 = x135
x117 = x135
()
} else {
x116 = false
()
}
x154
}
x156
}
val x159 = x111
val x160 = x112
val x161 = x113
val x164 = if (x160) {
true
} else {
x160
}
val x1089 = if (x164) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x165 = if (x160) {
x71
} else {
x161
}
val x171 = x165 >= x7
val x1087 = if (x171) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x178 = x0(x165)
val x179 = x178 == '.'
val x1085 = if (x179) {
val x180 = x165 + 1
val x182 = x180 >= x7
val x1079 = if (x182) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x189 = x0(x180)
val x190 = x189 >= '0'
val x191 = x189 <= '9'
val x192 = x190 && x191
val x1077 = if (x192) {
val x195 = x189 - '0'
val x196 = x195.toInt
var x198: Int = x196
var x199: Boolean = false
val x193 = x180 + 1
var x200: Int = x193
var x202: Int = -1
var x203: Boolean = true
var x204: Int = x193
val x245 = while ({val x205 = x203
val x206 = x202
val x207 = x204
val x208 = x206 != x207
val x209 = x205 && x208
x209}) {
val x211 = x204
x202 = x211
val x213 = x211 >= x7
val x243 = if (x213) {
x203 = false
()
} else {
val x218 = x0(x211)
val x219 = x218 >= '0'
val x220 = x218 <= '9'
val x221 = x219 && x220
val x241 = if (x221) {
val x227 = x198
val x228 = x199
val x229 = x200
val x224 = x218 - '0'
val x225 = x224.toInt
val x231 = x227 * 10
val x232 = x231 + x225
x198 = x232
x199 = false
val x222 = x211 + 1
x200 = x222
x204 = x222
()
} else {
x203 = false
()
}
x241
}
x243
}
val x246 = x198
val x247 = x199
val x248 = x200
val x251 = if (x247) {
true
} else {
x247
}
val x257 = if (x251) {
true
} else {
false
}
val x263 = if (x257) {
true
} else {
false
}
val x1071 = if (x263) {
x2 = null
x3 = true
x4 = 0
()
} else {
var x271: java.lang.String = ""
var x272: Boolean = false
val x264 = if (x257) {
0
} else {
val x258 = if (x251) {
x71
} else {
val x252 = if (x247) {
x180
} else {
x248
}
x252
}
x258
}
var x273: Int = x264
var x275: Int = -1
var x276: Boolean = true
var x277: Int = x264
val x310 = while ({val x278 = x276
val x279 = x275
val x280 = x277
val x281 = x279 != x280
val x282 = x278 && x281
x282}) {
val x284 = x277
x275 = x284
val x286 = x284 >= x7
val x308 = if (x286) {
x276 = false
()
} else {
val x290 = x0(x284)
val x291 = x290 == ' '
val x306 = if (x291) {
val x294 = x271
val x295 = x272
val x296 = x273
x271 = x294
x272 = false
val x292 = x284 + 1
x273 = x292
x277 = x292
()
} else {
x276 = false
()
}
x306
}
x308
}
val x311 = x271
val x312 = x272
val x313 = x273
val x318 = if (x312) {
true
} else {
false
}
val x1069 = if (x318) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x319 = if (x312) {
0
} else {
x313
}
val x325 = x319 >= x7
val x1067 = if (x325) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x332 = x0(x319)
val x333 = x332 >= '0'
val x334 = x332 <= '9'
val x335 = x333 && x334
val x1065 = if (x335) {
val x338 = x332 - '0'
val x339 = x338.toInt
var x341: Int = x339
var x342: Boolean = false
val x336 = x319 + 1
var x343: Int = x336
var x345: Int = -1
var x346: Boolean = true
var x347: Int = x336
val x388 = while ({val x348 = x346
val x349 = x345
val x350 = x347
val x351 = x349 != x350
val x352 = x348 && x351
x352}) {
val x354 = x347
x345 = x354
val x356 = x354 >= x7
val x386 = if (x356) {
x346 = false
()
} else {
val x361 = x0(x354)
val x362 = x361 >= '0'
val x363 = x361 <= '9'
val x364 = x362 && x363
val x384 = if (x364) {
val x370 = x341
val x371 = x342
val x372 = x343
val x367 = x361 - '0'
val x368 = x367.toInt
val x374 = x370 * 10
val x375 = x374 + x368
x341 = x375
x342 = false
val x365 = x354 + 1
x343 = x365
x347 = x365
()
} else {
x346 = false
()
}
x384
}
x386
}
val x389 = x341
val x390 = x342
val x391 = x343
val x394 = if (x390) {
true
} else {
x390
}
val x1059 = if (x394) {
x2 = null
x3 = true
x4 = 0
()
} else {
var x402: Int = 0
var x403: Boolean = false
val x395 = if (x390) {
x319
} else {
x391
}
var x404: Int = x395
var x406: Int = -1
var x407: Boolean = true
var x408: Int = x395
val x443 = while ({val x409 = x407
val x410 = x406
val x411 = x408
val x412 = x410 != x411
val x413 = x409 && x412
x413}) {
val x415 = x408
x406 = x415
val x417 = x415 >= x7
val x441 = if (x417) {
x407 = false
()
} else {
val x421 = x0(x415)
val x423 = x421 == '\n'
val x439 = if (x423) {
x407 = false
()
} else {
val x428 = x402
val x429 = x403
val x430 = x404
val x432 = x428 + 1
x402 = x432
x403 = false
val x426 = x415 + 1
x404 = x426
x408 = x426
()
}
x439
}
x441
}
val x444 = x402
val x445 = x403
val x446 = x404
val x450 = x446 >= x7
val x1057 = if (x450) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x457 = x0(x446)
val x458 = x457 == '\n'
val x1055 = if (x458) {
var x465: Int = 200
var x466: Int = 0
var x467: java.lang.String = "close"
var x468: Boolean = false
var x469: Boolean = false
var x471: Boolean = false
val x459 = x446 + 1
var x472: Int = x459
var x474: Int = -1
var x475: Boolean = true
var x476: Int = x459
val x528 = {x491: (Int) =>
var x493: Int = 0
var x494: Boolean = true
var x495: Int = x491
val x497 = x491 >= x7
val x522 = if (x497) {
x493 = 0
x494 = true
x495 = x491
()
} else {
val x502 = x0(x491)
val x503 = x502 >= 'a'
val x504 = x502 <= 'z'
val x505 = x503 && x504
val x506 = x502 >= 'A'
val x507 = x502 <= 'Z'
val x508 = x506 && x507
val x509 = x505 || x508
val x520 = if (x509) {
x493 = x491
x494 = false
val x510 = x491 + 1
x495 = x510
()
} else {
x493 = 0
x494 = true
x495 = x491
()
}
x520
}
val x523 = x493
val x524 = x494
val x525 = x495
val x526 = new ParseResultInt(x523,x524,x525)
x526: ParseResultInt
}
val x564 = {x485: (Int) =>
var x487: Int = 0
var x488: Boolean = true
var x489: Int = x485
val x529 = x528(x485)
val x530 = x529.empty
val x558 = if (x530) {
val x531 = x485 >= x7
val x550 = if (x531) {
x487 = 0
x488 = true
x489 = x485
()
} else {
val x536 = x0(x485)
val x537 = x536 == '-'
val x548 = if (x537) {
x487 = x485
x488 = false
val x538 = x485 + 1
x489 = x538
()
} else {
x487 = 0
x488 = true
x489 = x485
()
}
x548
}
x550
} else {
val x552 = x529.res
x487 = x552
x488 = x530
val x555 = x529.next
x489 = x555
()
}
val x559 = x487
val x560 = x488
val x561 = x489
val x562 = new ParseResultInt(x559,x560,x561)
x562: ParseResultInt
}
val x820 = px820 // static data: Array(c,o,n,t,e,n,t,-,l,e,n,g,t,h)
val x821 = x820.length
val x851 = px851 // static data: Array(t,r,a,n,s,f,e,r,-,e,n,c,o,d,i,n,g)
val x852 = x851.length
val x880 = px880 // static data: Array(c,h,u,n,k,e,d)
val x881 = x880.length
val x911 = px911 // static data: Array(u,p,g,r,a,d,e)
val x912 = x911.length
val x997 = while ({val x477 = x475
val x478 = x474
val x479 = x476
val x480 = x478 != x479
val x481 = x477 && x480
x481}) {
val x483 = x476
x474 = x483
val x565 = x483 >= x7
val x995 = if (x565) {
x475 = false
()
} else {
val x572 = x0(x483)
val x573 = x572 >= 'a'
val x574 = x572 <= 'z'
val x575 = x573 && x574
val x576 = x572 >= 'A'
val x577 = x572 <= 'Z'
val x578 = x576 && x577
val x579 = x575 || x578
val x993 = if (x579) {
var x583: Int = 0
var x584: Boolean = false
val x580 = x483 + 1
var x585: Int = x580
var x587: Int = -1
var x588: Boolean = true
var x589: Int = x580
val x616 = while ({val x590 = x588
val x591 = x587
val x592 = x589
val x593 = x591 != x592
val x594 = x590 && x593
x594}) {
val x596 = x589
x587 = x596
val x598 = x564(x596)
val x599 = x598.empty
val x614 = if (x599) {
x588 = false
()
} else {
val x602 = x583
val x603 = x584
val x604 = x585
val x606 = x602 + 1
x583 = x606
x584 = false
val x607 = x598.next
x585 = x607
x589 = x607
()
}
x614
}
val x617 = x583
val x618 = x584
val x619 = x585
var x629: java.lang.String = ""
var x630: Boolean = false
var x631: Int = x619
var x633: Int = -1
var x634: Boolean = true
var x635: Int = x619
val x668 = while ({val x636 = x634
val x637 = x633
val x638 = x635
val x639 = x637 != x638
val x640 = x636 && x639
x640}) {
val x642 = x635
x633 = x642
val x644 = x642 >= x7
val x666 = if (x644) {
x634 = false
()
} else {
val x648 = x0(x642)
val x649 = x648 == ' '
val x664 = if (x649) {
val x652 = x629
val x653 = x630
val x654 = x631
x629 = x652
x630 = false
val x650 = x642 + 1
x631 = x650
x635 = x650
()
} else {
x634 = false
()
}
x664
}
x666
}
val x669 = x629
val x670 = x630
val x671 = x631
val x989 = if (x670) {
x475 = false
()
} else {
val x676 = x671 >= x7
val x987 = if (x676) {
x475 = false
()
} else {
val x682 = x0(x671)
val x683 = x682 == ':'
val x985 = if (x683) {
var x694: java.lang.String = ""
var x695: Boolean = false
val x684 = x671 + 1
var x696: Int = x684
var x698: Int = -1
var x699: Boolean = true
var x700: Int = x684
val x733 = while ({val x701 = x699
val x702 = x698
val x703 = x700
val x704 = x702 != x703
val x705 = x701 && x704
x705}) {
val x707 = x700
x698 = x707
val x709 = x707 >= x7
val x731 = if (x709) {
x699 = false
()
} else {
val x713 = x0(x707)
val x714 = x713 == ' '
val x729 = if (x714) {
val x717 = x694
val x718 = x695
val x719 = x696
x694 = x717
x695 = false
val x715 = x707 + 1
x696 = x715
x700 = x715
()
} else {
x699 = false
()
}
x729
}
x731
}
val x734 = x694
val x735 = x695
val x736 = x696
val x981 = if (x735) {
x475 = false
()
} else {
var x742: Int = 0
var x743: Boolean = false
var x744: Int = x736
var x746: Int = -1
var x747: Boolean = true
var x748: Int = x736
val x783 = while ({val x749 = x747
val x750 = x746
val x751 = x748
val x752 = x750 != x751
val x753 = x749 && x752
x753}) {
val x755 = x748
x746 = x755
val x757 = x755 >= x7
val x781 = if (x757) {
x747 = false
()
} else {
val x761 = x0(x755)
val x763 = x761 == '\n'
val x779 = if (x763) {
x747 = false
()
} else {
val x768 = x742
val x769 = x743
val x770 = x744
val x772 = x768 + 1
x742 = x772
x743 = false
val x766 = x755 + 1
x744 = x766
x748 = x766
()
}
x779
}
x781
}
val x784 = x742
val x785 = x743
val x786 = x744
val x790 = x786 >= x7
val x979 = if (x790) {
x475 = false
()
} else {
val x794 = x0(x786)
val x795 = x794 == '\n'
val x977 = if (x795) {
val x801 = x465
val x802 = x466
val x803 = x467
val x804 = x468
val x805 = x469
val x807 = x471
val x808 = x472
val x625 = x617 + 1
val x626 = new Anon1680061013(x0,x483,x625)
val x810 = new String(x626.input,x626.start,x626.length)
val x812 = x810 == "connection"
val x813 = x810 == "proxy-connection"
val x814 = x812 || x813
val x788 = new Anon1680061013(x0,x736,x784)
val x811 = new String(x788.input,x788.start,x788.length)
val x815 = x811 == "keep-alive"
val x816 = x811 == "close"
val x817 = x815 || x816
val x818 = x814 && x817
val x959 = if (x818) {
x801
} else {
val x822 = x625 == x821
val x848 = if (x822) {
var x823: Int = 0
var x824: Boolean = true
val x845 = while ({val x825 = x823
val x827 = x824
val x826 = x825 < x625
val x828 = x826 && x827
x828}) {
val x830 = x823
val x831 = x830 + x483
val x832 = x0(x831)
val x833 = x832.toInt
val x834 = x833 | 32
val x835 = x834.toChar
val x836 = x820(x830)
val x838 = x835 == x836
val x841 = if (x838) {
()
} else {
x824 = false
()
}
val x842 = x830 + 1
x823 = x842
()
}
val x846 = x824
x846
} else {
false
}
val x953 = if (x848) {
x801
} else {
val x853 = x625 == x852
val x879 = if (x853) {
var x854: Int = 0
var x855: Boolean = true
val x876 = while ({val x856 = x854
val x858 = x855
val x857 = x856 < x625
val x859 = x857 && x858
x859}) {
val x861 = x854
val x862 = x861 + x483
val x863 = x0(x862)
val x864 = x863.toInt
val x865 = x864 | 32
val x866 = x865.toChar
val x867 = x851(x861)
val x869 = x866 == x867
val x872 = if (x869) {
()
} else {
x855 = false
()
}
val x873 = x861 + 1
x854 = x873
()
}
val x877 = x855
x877
} else {
false
}
val x882 = x784 == x881
val x908 = if (x882) {
var x883: Int = 0
var x884: Boolean = true
val x905 = while ({val x885 = x883
val x887 = x884
val x886 = x885 < x784
val x888 = x886 && x887
x888}) {
val x890 = x883
val x891 = x890 + x736
val x892 = x0(x891)
val x893 = x892.toInt
val x894 = x893 | 32
val x895 = x894.toChar
val x896 = x880(x890)
val x898 = x895 == x896
val x901 = if (x898) {
()
} else {
x884 = false
()
}
val x902 = x890 + 1
x883 = x902
()
}
val x906 = x884
x906
} else {
false
}
val x909 = x879 && x908
val x947 = if (x909) {
x801
} else {
val x913 = x625 == x912
val x939 = if (x913) {
var x914: Int = 0
var x915: Boolean = true
val x936 = while ({val x916 = x914
val x918 = x915
val x917 = x916 < x625
val x919 = x917 && x918
x919}) {
val x921 = x914
val x922 = x921 + x483
val x923 = x0(x922)
val x924 = x923.toInt
val x925 = x924 | 32
val x926 = x925.toChar
val x927 = x911(x921)
val x929 = x926 == x927
val x932 = if (x929) {
()
} else {
x915 = false
()
}
val x933 = x921 + 1
x914 = x933
()
}
val x937 = x915
x937
} else {
false
}
val x941 = if (x939) {
x801
} else {
x801
}
x941
}
x947
}
x953
}
x465 = x959
val x960 = if (x818) {
x802
} else {
val x822 = x625 == x821
val x848 = if (x822) {
var x823: Int = 0
var x824: Boolean = true
val x845 = while ({val x825 = x823
val x827 = x824
val x826 = x825 < x625
val x828 = x826 && x827
x828}) {
val x830 = x823
val x831 = x830 + x483
val x832 = x0(x831)
val x833 = x832.toInt
val x834 = x833 | 32
val x835 = x834.toChar
val x836 = x820(x830)
val x838 = x835 == x836
val x841 = if (x838) {
()
} else {
x824 = false
()
}
val x842 = x830 + 1
x823 = x842
()
}
val x846 = x824
x846
} else {
false
}
val x954 = if (x848) {
val x849 = x811.toInt
x849
} else {
val x853 = x625 == x852
val x879 = if (x853) {
var x854: Int = 0
var x855: Boolean = true
val x876 = while ({val x856 = x854
val x858 = x855
val x857 = x856 < x625
val x859 = x857 && x858
x859}) {
val x861 = x854
val x862 = x861 + x483
val x863 = x0(x862)
val x864 = x863.toInt
val x865 = x864 | 32
val x866 = x865.toChar
val x867 = x851(x861)
val x869 = x866 == x867
val x872 = if (x869) {
()
} else {
x855 = false
()
}
val x873 = x861 + 1
x854 = x873
()
}
val x877 = x855
x877
} else {
false
}
val x882 = x784 == x881
val x908 = if (x882) {
var x883: Int = 0
var x884: Boolean = true
val x905 = while ({val x885 = x883
val x887 = x884
val x886 = x885 < x784
val x888 = x886 && x887
x888}) {
val x890 = x883
val x891 = x890 + x736
val x892 = x0(x891)
val x893 = x892.toInt
val x894 = x893 | 32
val x895 = x894.toChar
val x896 = x880(x890)
val x898 = x895 == x896
val x901 = if (x898) {
()
} else {
x884 = false
()
}
val x902 = x890 + 1
x883 = x902
()
}
val x906 = x884
x906
} else {
false
}
val x909 = x879 && x908
val x948 = if (x909) {
x802
} else {
val x913 = x625 == x912
val x939 = if (x913) {
var x914: Int = 0
var x915: Boolean = true
val x936 = while ({val x916 = x914
val x918 = x915
val x917 = x916 < x625
val x919 = x917 && x918
x919}) {
val x921 = x914
val x922 = x921 + x483
val x923 = x0(x922)
val x924 = x923.toInt
val x925 = x924 | 32
val x926 = x925.toChar
val x927 = x911(x921)
val x929 = x926 == x927
val x932 = if (x929) {
()
} else {
x915 = false
()
}
val x933 = x921 + 1
x914 = x933
()
}
val x937 = x915
x937
} else {
false
}
val x942 = if (x939) {
x802
} else {
x802
}
x942
}
x948
}
x954
}
x466 = x960
val x961 = if (x818) {
x811
} else {
val x822 = x625 == x821
val x848 = if (x822) {
var x823: Int = 0
var x824: Boolean = true
val x845 = while ({val x825 = x823
val x827 = x824
val x826 = x825 < x625
val x828 = x826 && x827
x828}) {
val x830 = x823
val x831 = x830 + x483
val x832 = x0(x831)
val x833 = x832.toInt
val x834 = x833 | 32
val x835 = x834.toChar
val x836 = x820(x830)
val x838 = x835 == x836
val x841 = if (x838) {
()
} else {
x824 = false
()
}
val x842 = x830 + 1
x823 = x842
()
}
val x846 = x824
x846
} else {
false
}
val x955 = if (x848) {
x803
} else {
val x853 = x625 == x852
val x879 = if (x853) {
var x854: Int = 0
var x855: Boolean = true
val x876 = while ({val x856 = x854
val x858 = x855
val x857 = x856 < x625
val x859 = x857 && x858
x859}) {
val x861 = x854
val x862 = x861 + x483
val x863 = x0(x862)
val x864 = x863.toInt
val x865 = x864 | 32
val x866 = x865.toChar
val x867 = x851(x861)
val x869 = x866 == x867
val x872 = if (x869) {
()
} else {
x855 = false
()
}
val x873 = x861 + 1
x854 = x873
()
}
val x877 = x855
x877
} else {
false
}
val x882 = x784 == x881
val x908 = if (x882) {
var x883: Int = 0
var x884: Boolean = true
val x905 = while ({val x885 = x883
val x887 = x884
val x886 = x885 < x784
val x888 = x886 && x887
x888}) {
val x890 = x883
val x891 = x890 + x736
val x892 = x0(x891)
val x893 = x892.toInt
val x894 = x893 | 32
val x895 = x894.toChar
val x896 = x880(x890)
val x898 = x895 == x896
val x901 = if (x898) {
()
} else {
x884 = false
()
}
val x902 = x890 + 1
x883 = x902
()
}
val x906 = x884
x906
} else {
false
}
val x909 = x879 && x908
val x949 = if (x909) {
x803
} else {
val x913 = x625 == x912
val x939 = if (x913) {
var x914: Int = 0
var x915: Boolean = true
val x936 = while ({val x916 = x914
val x918 = x915
val x917 = x916 < x625
val x919 = x917 && x918
x919}) {
val x921 = x914
val x922 = x921 + x483
val x923 = x0(x922)
val x924 = x923.toInt
val x925 = x924 | 32
val x926 = x925.toChar
val x927 = x911(x921)
val x929 = x926 == x927
val x932 = if (x929) {
()
} else {
x915 = false
()
}
val x933 = x921 + 1
x914 = x933
()
}
val x937 = x915
x937
} else {
false
}
val x943 = if (x939) {
x803
} else {
x803
}
x943
}
x949
}
x955
}
x467 = x961
val x962 = if (x818) {
x804
} else {
val x822 = x625 == x821
val x848 = if (x822) {
var x823: Int = 0
var x824: Boolean = true
val x845 = while ({val x825 = x823
val x827 = x824
val x826 = x825 < x625
val x828 = x826 && x827
x828}) {
val x830 = x823
val x831 = x830 + x483
val x832 = x0(x831)
val x833 = x832.toInt
val x834 = x833 | 32
val x835 = x834.toChar
val x836 = x820(x830)
val x838 = x835 == x836
val x841 = if (x838) {
()
} else {
x824 = false
()
}
val x842 = x830 + 1
x823 = x842
()
}
val x846 = x824
x846
} else {
false
}
val x956 = if (x848) {
x804
} else {
val x853 = x625 == x852
val x879 = if (x853) {
var x854: Int = 0
var x855: Boolean = true
val x876 = while ({val x856 = x854
val x858 = x855
val x857 = x856 < x625
val x859 = x857 && x858
x859}) {
val x861 = x854
val x862 = x861 + x483
val x863 = x0(x862)
val x864 = x863.toInt
val x865 = x864 | 32
val x866 = x865.toChar
val x867 = x851(x861)
val x869 = x866 == x867
val x872 = if (x869) {
()
} else {
x855 = false
()
}
val x873 = x861 + 1
x854 = x873
()
}
val x877 = x855
x877
} else {
false
}
val x882 = x784 == x881
val x908 = if (x882) {
var x883: Int = 0
var x884: Boolean = true
val x905 = while ({val x885 = x883
val x887 = x884
val x886 = x885 < x784
val x888 = x886 && x887
x888}) {
val x890 = x883
val x891 = x890 + x736
val x892 = x0(x891)
val x893 = x892.toInt
val x894 = x893 | 32
val x895 = x894.toChar
val x896 = x880(x890)
val x898 = x895 == x896
val x901 = if (x898) {
()
} else {
x884 = false
()
}
val x902 = x890 + 1
x883 = x902
()
}
val x906 = x884
x906
} else {
false
}
val x909 = x879 && x908
val x950 = if (x909) {
true
} else {
val x913 = x625 == x912
val x939 = if (x913) {
var x914: Int = 0
var x915: Boolean = true
val x936 = while ({val x916 = x914
val x918 = x915
val x917 = x916 < x625
val x919 = x917 && x918
x919}) {
val x921 = x914
val x922 = x921 + x483
val x923 = x0(x922)
val x924 = x923.toInt
val x925 = x924 | 32
val x926 = x925.toChar
val x927 = x911(x921)
val x929 = x926 == x927
val x932 = if (x929) {
()
} else {
x915 = false
()
}
val x933 = x921 + 1
x914 = x933
()
}
val x937 = x915
x937
} else {
false
}
val x944 = if (x939) {
x804
} else {
x804
}
x944
}
x950
}
x956
}
x468 = x962
val x963 = if (x818) {
x805
} else {
val x822 = x625 == x821
val x848 = if (x822) {
var x823: Int = 0
var x824: Boolean = true
val x845 = while ({val x825 = x823
val x827 = x824
val x826 = x825 < x625
val x828 = x826 && x827
x828}) {
val x830 = x823
val x831 = x830 + x483
val x832 = x0(x831)
val x833 = x832.toInt
val x834 = x833 | 32
val x835 = x834.toChar
val x836 = x820(x830)
val x838 = x835 == x836
val x841 = if (x838) {
()
} else {
x824 = false
()
}
val x842 = x830 + 1
x823 = x842
()
}
val x846 = x824
x846
} else {
false
}
val x957 = if (x848) {
x805
} else {
val x853 = x625 == x852
val x879 = if (x853) {
var x854: Int = 0
var x855: Boolean = true
val x876 = while ({val x856 = x854
val x858 = x855
val x857 = x856 < x625
val x859 = x857 && x858
x859}) {
val x861 = x854
val x862 = x861 + x483
val x863 = x0(x862)
val x864 = x863.toInt
val x865 = x864 | 32
val x866 = x865.toChar
val x867 = x851(x861)
val x869 = x866 == x867
val x872 = if (x869) {
()
} else {
x855 = false
()
}
val x873 = x861 + 1
x854 = x873
()
}
val x877 = x855
x877
} else {
false
}
val x882 = x784 == x881
val x908 = if (x882) {
var x883: Int = 0
var x884: Boolean = true
val x905 = while ({val x885 = x883
val x887 = x884
val x886 = x885 < x784
val x888 = x886 && x887
x888}) {
val x890 = x883
val x891 = x890 + x736
val x892 = x0(x891)
val x893 = x892.toInt
val x894 = x893 | 32
val x895 = x894.toChar
val x896 = x880(x890)
val x898 = x895 == x896
val x901 = if (x898) {
()
} else {
x884 = false
()
}
val x902 = x890 + 1
x883 = x902
()
}
val x906 = x884
x906
} else {
false
}
val x909 = x879 && x908
val x951 = if (x909) {
x805
} else {
val x913 = x625 == x912
val x939 = if (x913) {
var x914: Int = 0
var x915: Boolean = true
val x936 = while ({val x916 = x914
val x918 = x915
val x917 = x916 < x625
val x919 = x917 && x918
x919}) {
val x921 = x914
val x922 = x921 + x483
val x923 = x0(x922)
val x924 = x923.toInt
val x925 = x924 | 32
val x926 = x925.toChar
val x927 = x911(x921)
val x929 = x926 == x927
val x932 = if (x929) {
()
} else {
x915 = false
()
}
val x933 = x921 + 1
x914 = x933
()
}
val x937 = x915
x937
} else {
false
}
val x945 = if (x939) {
true
} else {
x805
}
x945
}
x951
}
x957
}
x469 = x963
x471 = false
val x796 = x786 + 1
x472 = x796
x476 = x796
()
} else {
x475 = false
()
}
x977
}
x979
}
x981
} else {
x475 = false
()
}
x985
}
x987
}
x989
} else {
x475 = false
()
}
x993
}
x995
}
val x998 = x465
val x999 = x466
val x1000 = x467
val x1001 = x468
val x1002 = x469
val x1004 = x471
val x1005 = x472
val x1010 = if (x1004) {
true
} else {
false
}
val x1049 = if (x1010) {
x2 = null
x3 = true
val x1011 = if (x1004) {
0
} else {
x1005
}
x4 = x1011
()
} else {
val x1011 = if (x1004) {
0
} else {
x1005
}
val x1018 = x1011 >= x7
val x1047 = if (x1018) {
x2 = null
x3 = true
x4 = 0
()
} else {
val x1024 = x0(x1011)
val x1025 = x1024 == '\n'
val x1045 = if (x1025) {
val x1009 = if (x1004) {
null
} else {
val x393 = if (x390) {
0
} else {
x389
}
val x1003 = new Anon1323431030(x998,x999,x1000,x1001,x1002)
val x1007 = new Tuple2IntAnon1323431030(x393,x1003)
x1007
}
val x1029 = x1009._1
val x1030 = x1009._2
val x1031 = x1030.contentLength
val x1032 = x1030.connection
val x1033 = x1030.chunked
val x1034 = x1030.upgrade
val x1035 = new Anon1323431030(x1029,x1031,x1032,x1033,x1034)
x2 = x1035
x3 = false
val x1026 = x1011 + 1
x4 = x1026
()
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1045
}
x1047
}
x1049
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1055
}
x1057
}
x1059
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1065
}
x1067
}
x1069
}
x1071
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1077
}
x1079
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1085
}
x1087
}
x1089
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1095
}
x1097
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1103
}
x1105
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1111
}
x1113
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1119
}
x1121
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1127
}
x1129
} else {
x2 = null
x3 = true
x4 = 0
()
}
x1135
}
val x1138 = x2
val x1139 = x3
val x1140 = x4
val x1141 = new ParseResultAnon1323431030(x1138,x1139,x1140)
//val x1142 = println(x1141)
x1142
}
}
/*****************************************
  End of Generated Code
*******************************************/