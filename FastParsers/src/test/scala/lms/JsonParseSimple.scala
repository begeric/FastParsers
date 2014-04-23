package lms.parsing.gen

import java.io.{ BufferedReader, FileReader, Serializable }
import scala.collection.mutable.ArrayBuffer

object LMSJsonParser extends JsonParseSimple(
      "false".length, "false".toArray,
      "true".length, "true".toArray,
      "null".toArray //,
    //print = false
   )
	
case class JsonParseSimple(px1899: Int, px1900: Array[Char], px1902: Int, px1903: Array[Char], px1998: Array[Char]) extends ((Array[Char]) => ParseResultAnon6507737) {
  def apply(x0: Array[Char]): ParseResultAnon6507737 = {
    var x1: scala.Function1[Int, ParseResultAnon6507737] = null
    var x2887: scala.Function1[Int, ParseResultAnon6507737] = null
    var x2881: scala.Function1[Int, ParseResultAnon6507737] = null
    var x1898: scala.Function1[Int, ParseResultAnon6507737] = null
    var x1492: scala.Function1[Int, ParseResultAnon6507737] = null
    var x1389: scala.Function1[Int, ParseResultListAnon6507737] = null
    var x1383: scala.Function1[Int, ParseResultListAnon6507737] = null
    var x1791: scala.Function1[Int, ParseResultListAnon6507737] = null
    var x1785: scala.Function1[Int, ParseResultListAnon6507737] = null
    x1 = { x2: (Int) =>
      val x2888 = x2887(x2)
      x2888: ParseResultAnon6507737
    }
    val x5 = x0.length
    var x110: Boolean = false
    var x105: Int = -1
    var x106: Boolean = true
    var x109: java.lang.String = ""
    val x154 = { x122: (Int) =>
      val x123 = x122 >= x5
      var x136: Boolean = false
      var x137: Int = 0
      if (x123) {
        x136 = true
        x137 = x122
      } else {
        val x125 = x0(x122)
        val x126 = x125 == '\\'
        var x131: Boolean = false
        var x132: Int = 0
        if (x126) {
          val x127 = x122 + 1
          x131 = false
          x132 = x127
        } else {
          x131 = true
          x132 = x122
        }
        x136 = x131
        x137 = x132
      }
      var x150: Char = ' '
      var x151: Boolean = false
      var x152: Int = 0
      if (x136) {
        x150 = ' '
        x151 = true
        x152 = x122
      } else {
        val x139 = x137 >= x5
        var x145: Char = ' '
        var x146: Boolean = false
        var x147: Int = 0
        if (x139) {
          x145 = ' '
          x146 = true
          x147 = x137
        } else {
          val x141 = x0(x137)
          val x142 = x137 + 1
          x145 = x141
          x146 = false
          x147 = x142
        }
        x150 = x145
        x151 = x146
        x152 = x147
      }
      val x153 = new ParseResultChar(x150, x151, x152)
      x153: ParseResultChar
    }
    val x175 = { x121: (Int) =>
      val x155 = x154(x121)
      val x156 = x155.empty
      val x174 = if (x156) {
        val x157 = x121 >= x5
        var x170: Char = ' '
        var x171: Boolean = false
        var x172: Int = 0
        if (x157) {
          x170 = ' '
          x171 = true
          x172 = x121
        } else {
          val x159 = x0(x121)
          val x161 = x159 == '"'
          var x165: Char = ' '
          var x166: Boolean = false
          var x167: Int = 0
          if (x161) {
            x165 = ' '
            x166 = true
            x167 = x121
          } else {
            val x162 = x121 + 1
            x165 = x159
            x166 = false
            x167 = x162
          }
          x170 = x165
          x171 = x166
          x172 = x167
        }
        val x173 = new ParseResultChar(x170, x171, x172)
        x173
      } else {
        x155
      }
      x174: ParseResultChar
    }
    var x387: java.lang.String = ""
    var x221: Int = 0
    val x222 = x221
    var x223: Int = x222
    var x388: Int = 0
    var x449: Boolean = false
    var x444: Int = -1
    var x445: Boolean = true
    var x448: java.lang.String = ""
    var x519: Boolean = false
    var x514: Int = -1
    var x515: Boolean = true
    var x518: java.lang.String = ""
    val x84 = List()
    var x730: Boolean = false
    var x725: Int = -1
    var x726: Boolean = true
    var x729: java.lang.String = ""
    var x809: Boolean = false
    var x804: Int = -1
    var x805: Boolean = true
    var x808: java.lang.String = ""
    val x853 = { x821: (Int) =>
      val x822 = x821 >= x5
      var x835: Boolean = false
      var x836: Int = 0
      if (x822) {
        x835 = true
        x836 = x821
      } else {
        val x824 = x0(x821)
        val x825 = x824 == '\\'
        var x830: Boolean = false
        var x831: Int = 0
        if (x825) {
          val x826 = x821 + 1
          x830 = false
          x831 = x826
        } else {
          x830 = true
          x831 = x821
        }
        x835 = x830
        x836 = x831
      }
      var x849: Char = ' '
      var x850: Boolean = false
      var x851: Int = 0
      if (x835) {
        x849 = ' '
        x850 = true
        x851 = x821
      } else {
        val x838 = x836 >= x5
        var x844: Char = ' '
        var x845: Boolean = false
        var x846: Int = 0
        if (x838) {
          x844 = ' '
          x845 = true
          x846 = x836
        } else {
          val x840 = x0(x836)
          val x841 = x836 + 1
          x844 = x840
          x845 = false
          x846 = x841
        }
        x849 = x844
        x850 = x845
        x851 = x846
      }
      val x852 = new ParseResultChar(x849, x850, x851)
      x852: ParseResultChar
    }
    val x874 = { x820: (Int) =>
      val x854 = x853(x820)
      val x855 = x854.empty
      val x873 = if (x855) {
        val x856 = x820 >= x5
        var x869: Char = ' '
        var x870: Boolean = false
        var x871: Int = 0
        if (x856) {
          x869 = ' '
          x870 = true
          x871 = x820
        } else {
          val x858 = x0(x820)
          val x860 = x858 == '"'
          var x864: Char = ' '
          var x865: Boolean = false
          var x866: Int = 0
          if (x860) {
            x864 = ' '
            x865 = true
            x866 = x820
          } else {
            val x861 = x820 + 1
            x864 = x858
            x865 = false
            x866 = x861
          }
          x869 = x864
          x870 = x865
          x871 = x866
        }
        val x872 = new ParseResultChar(x869, x870, x871)
        x872
      } else {
        x854
      }
      x873: ParseResultChar
    }
    var x1086: java.lang.String = ""
    var x920: Int = 0
    val x921 = x920
    var x922: Int = x921
    var x1087: Int = 0
    var x1148: Boolean = false
    var x1143: Int = -1
    var x1144: Boolean = true
    var x1147: java.lang.String = ""
    var x1218: Boolean = false
    var x1213: Int = -1
    var x1214: Boolean = true
    var x1217: java.lang.String = ""
    x1383 = { x86: (Int) =>
      val x87 = x86 >= x5
      var x100: Boolean = false
      var x101: Int = 0
      if (x87) {
        x100 = true
        x101 = x86
      } else {
        val x89 = x0(x86)
        val x90 = x89 == '"'
        var x95: Boolean = false
        var x96: Int = 0
        if (x90) {
          val x91 = x86 + 1
          x95 = false
          x96 = x91
        } else {
          x95 = true
          x96 = x86
        }
        x100 = x95
        x101 = x96
      }
      var x414: Boolean = false
      var x415: Int = 0
      var x413: java.lang.String = null
      if (x100) {
        x414 = true
        x415 = x86
        x413 = null
      } else {
        var x107: Int = x101
        var x111: Int = x101
        val x194 = while ({
          val x113 = x106
          val x114 = x105
          val x115 = x107
          val x116 = x114 != x115
          val x117 = x113 && x116
          x117
        }) {
          val x119 = x107
          x105 = x119
          val x176 = x175(x119)
          val x177 = x176.empty
          val x192 = if (x177) {
            x106 = false
            ()
          } else {
            val x180 = x109
            val x181 = x110
            val x182 = x111
            x109 = x180
            x110 = false
            val x185 = x176.next
            x111 = x185
            x107 = x185
            ()
          }
          x192
        }
        val x196 = x110
        var x203: Boolean = false
        var x202: Int = 0
        var x204: Int = 0
        if (x196) {
          x203 = true
          x202 = 0
          x204 = x101
        } else {
          val x197 = x111
          x203 = false
          x202 = x101
          x204 = x197
        }
        var x212: Boolean = false
        var x211: Tuple2IntInt = null
        var x213: Int = 0
        if (x203) {
          x212 = true
          x211 = null
          x213 = x101
        } else {
          val x208 = new Tuple2IntInt(x202, x204)
          x212 = false
          x211 = x208
          x213 = x204
        }
        var x408: Boolean = false
        var x407: java.lang.String = null
        if (x212) {
          x408 = true
          x407 = null
        } else {
          val x216 = x211._1
          val x217 = x211._2
          val x224 = x217 - x216
          val x225 = new Array[Char](x224)
          var x218: Int = x216
          val x219 = x218
          var x220: Int = x219
          val x384 = while ({
            val x226 = x220
            val x227 = x226 < x217
            x227
          }) {
            val x229 = x220
            val x230 = x0(x229)
            val x231 = x230 == '\\'
            val x232 = x229 + 1
            val x233 = x232 < x217
            val x234 = x231 && x233
            val x381 = if (x234) {
              val x235 = x0(x232)
              val x236 = x235 == 'u'
              val x237 = x229 + 5
              val x238 = x237 < x217
              val x239 = x236 && x238
              val x374 = if (x239) {
                val x325 = x223
                val x240 = x229 + 2
                val x241 = x0(x240)
                val x242 = x241 >= '0'
                val x243 = x241 < '9'
                val x244 = x242 && x243
                val x259 = if (x244) {
                  val x245 = x241 - '0'
                  val x246 = x245.toInt
                  x246
                } else {
                  val x247 = x241 >= 'A'
                  val x248 = x241 < 'F'
                  val x249 = x247 && x248
                  val x258 = if (x249) {
                    val x250 = x241 - '7'
                    val x251 = x250.toInt
                    x251
                  } else {
                    val x252 = x241 >= 'a'
                    val x253 = x241 < 'f'
                    val x254 = x252 && x253
                    val x257 = if (x254) {
                      val x255 = x241 - 'W'
                      val x256 = x255.toInt
                      x256
                    } else {
                      0
                    }
                    x257
                  }
                  x258
                }
                val x260 = x259 * 4096
                val x261 = x229 + 3
                val x262 = x0(x261)
                val x263 = x262 >= '0'
                val x264 = x262 < '9'
                val x265 = x263 && x264
                val x280 = if (x265) {
                  val x266 = x262 - '0'
                  val x267 = x266.toInt
                  x267
                } else {
                  val x268 = x262 >= 'A'
                  val x269 = x262 < 'F'
                  val x270 = x268 && x269
                  val x279 = if (x270) {
                    val x271 = x262 - '7'
                    val x272 = x271.toInt
                    x272
                  } else {
                    val x273 = x262 >= 'a'
                    val x274 = x262 < 'f'
                    val x275 = x273 && x274
                    val x278 = if (x275) {
                      val x276 = x262 - 'W'
                      val x277 = x276.toInt
                      x277
                    } else {
                      0
                    }
                    x278
                  }
                  x279
                }
                val x281 = x280 * 256
                val x282 = x260 + x281
                val x283 = x229 + 4
                val x284 = x0(x283)
                val x285 = x284 >= '0'
                val x286 = x284 < '9'
                val x287 = x285 && x286
                val x302 = if (x287) {
                  val x288 = x284 - '0'
                  val x289 = x288.toInt
                  x289
                } else {
                  val x290 = x284 >= 'A'
                  val x291 = x284 < 'F'
                  val x292 = x290 && x291
                  val x301 = if (x292) {
                    val x293 = x284 - '7'
                    val x294 = x293.toInt
                    x294
                  } else {
                    val x295 = x284 >= 'a'
                    val x296 = x284 < 'f'
                    val x297 = x295 && x296
                    val x300 = if (x297) {
                      val x298 = x284 - 'W'
                      val x299 = x298.toInt
                      x299
                    } else {
                      0
                    }
                    x300
                  }
                  x301
                }
                val x303 = x302 * 16
                val x304 = x282 + x303
                val x305 = x0(x237)
                val x306 = x305 >= '0'
                val x307 = x305 < '9'
                val x308 = x306 && x307
                val x323 = if (x308) {
                  val x309 = x305 - '0'
                  val x310 = x309.toInt
                  x310
                } else {
                  val x311 = x305 >= 'A'
                  val x312 = x305 < 'F'
                  val x313 = x311 && x312
                  val x322 = if (x313) {
                    val x314 = x305 - '7'
                    val x315 = x314.toInt
                    x315
                  } else {
                    val x316 = x305 >= 'a'
                    val x317 = x305 < 'f'
                    val x318 = x316 && x317
                    val x321 = if (x318) {
                      val x319 = x305 - 'W'
                      val x320 = x319.toInt
                      x320
                    } else {
                      0
                    }
                    x321
                  }
                  x322
                }
                val x324 = x304 + x323
                val x326 = x324.toChar
                val x327 = x225(x325) = x326
                val x328 = x220 += 4
                ()
              } else {
                val x330 = x235 == 'b'
                val x371 = if (x330) {
                  val x331 = x223
                  val x332 = x225(x331) = ''
                  x332
                } else {
                  val x334 = x235 == 'f'
                  val x367 = if (x334) {
                    val x335 = x223
                    val x336 = x225(x335) = ''
                    x336
                  } else {
                    val x338 = x235 == 't'
                    val x363 = if (x338) {
                      val x339 = x223
                      val x340 = x225(x339) = '	'
                      x340
                    } else {
                      val x342 = x235 == 'n'
                      val x359 = if (x342) {
                        val x343 = x223
                        val x344 = x225(x343) = '\n'
                        x344
                      } else {
                        val x346 = x235 == 'r'
                        val x355 = if (x346) {
                          val x347 = x223
                          val x348 = x225(x347) = '
'
                          x348
                        } else {
                          val x350 = x223
                          val x351 = x225(x350) = x235
                          x351
                        }
                        x355
                      }
                      x359
                    }
                    x363
                  }
                  x367
                }
                x371
              }
              val x375 = x220 += 2
              ()
            } else {
              val x377 = x223
              val x378 = x225(x377) = x230
              val x379 = x220 += 1
              ()
            }
            val x382 = x223 += 1
            ()
          }
          val x385 = x223
          val x401 = while ({
            val x389 = x388
            val x390 = x389 < x385
            x390
          }) {
            val x392 = x387
            val x393 = x388
            val x394 = 0 + x393
            val x395 = x225(x394)
            val x396 = x392 + x395
            x387 = x396
            val x398 = x393 + 1
            x388 = x398
            ()
          }
          val x402 = x387
          x408 = false
          x407 = x402
        }
        x414 = x408
        x415 = x213
        x413 = x407
      }
      var x441: Boolean = false
      var x442: Int = 0
      var x440: java.lang.String = null
      if (x414) {
        x441 = x414
        x442 = x415
        x440 = x413
      } else {
        val x417 = x415 >= x5
        var x430: Boolean = false
        var x431: Int = 0
        if (x417) {
          x430 = true
          x431 = x415
        } else {
          val x419 = x0(x415)
          val x420 = x419 == '"'
          var x425: Boolean = false
          var x426: Int = 0
          if (x420) {
            val x421 = x415 + 1
            x425 = false
            x426 = x421
          } else {
            x425 = true
            x426 = x415
          }
          x430 = x425
          x431 = x426
        }
        var x436: Boolean = false
        var x437: Int = 0
        var x435: java.lang.String = null
        if (x430) {
          x436 = true
          x437 = x86
          x435 = null
        } else {
          x436 = false
          x437 = x431
          x435 = x413
        }
        x441 = x436
        x442 = x437
        x440 = x435
      }
      var x603: Boolean = false
      var x604: Int = 0
      var x602: java.lang.String = null
      if (x441) {
        x603 = x441
        x604 = x442
        x602 = x440
      } else {
        var x446: Int = x442
        var x450: Int = x442
        val x492 = while ({
          val x452 = x445
          val x453 = x444
          val x454 = x446
          val x455 = x453 != x454
          val x456 = x452 && x455
          x456
        }) {
          val x458 = x446
          x444 = x458
          val x460 = x458 >= x5
          var x475: Boolean = false
          var x476: Int = 0
          if (x460) {
            x475 = true
            x476 = x458
          } else {
            val x462 = x0(x458)
            val x463 = x462 == ' '
            val x464 = x462 == '\n'
            val x465 = x463 || x464
            var x470: Boolean = false
            var x471: Int = 0
            if (x465) {
              val x466 = x458 + 1
              x470 = false
              x471 = x466
            } else {
              x470 = true
              x471 = x458
            }
            x475 = x470
            x476 = x471
          }
          val x490 = if (x475) {
            x445 = false
            ()
          } else {
            val x480 = x448
            val x481 = x449
            val x482 = x450
            x448 = x480
            x449 = false
            x450 = x476
            x446 = x476
            ()
          }
          x490
        }
        val x494 = x449
        var x590: Boolean = false
        var x591: Int = 0
        if (x494) {
          val x495 = x450
          x590 = x494
          x591 = x495
        } else {
          val x495 = x450
          val x497 = x495 >= x5
          var x510: Boolean = false
          var x511: Int = 0
          if (x497) {
            x510 = true
            x511 = x495
          } else {
            val x499 = x0(x495)
            val x500 = x499 == ':'
            var x505: Boolean = false
            var x506: Int = 0
            if (x500) {
              val x501 = x495 + 1
              x505 = false
              x506 = x501
            } else {
              x505 = true
              x506 = x495
            }
            x510 = x505
            x511 = x506
          }
          var x578: Boolean = false
          var x579: Int = 0
          if (x510) {
            x578 = true
            x579 = x495
          } else {
            var x516: Int = x511
            var x520: Int = x511
            val x562 = while ({
              val x522 = x515
              val x523 = x514
              val x524 = x516
              val x525 = x523 != x524
              val x526 = x522 && x525
              x526
            }) {
              val x528 = x516
              x514 = x528
              val x530 = x528 >= x5
              var x545: Boolean = false
              var x546: Int = 0
              if (x530) {
                x545 = true
                x546 = x528
              } else {
                val x532 = x0(x528)
                val x533 = x532 == ' '
                val x534 = x532 == '\n'
                val x535 = x533 || x534
                var x540: Boolean = false
                var x541: Int = 0
                if (x535) {
                  val x536 = x528 + 1
                  x540 = false
                  x541 = x536
                } else {
                  x540 = true
                  x541 = x528
                }
                x545 = x540
                x546 = x541
              }
              val x560 = if (x545) {
                x515 = false
                ()
              } else {
                val x550 = x518
                val x551 = x519
                val x552 = x520
                x518 = x550
                x519 = false
                x520 = x546
                x516 = x546
                ()
              }
              x560
            }
            val x564 = x519
            var x571: Boolean = false
            var x572: Int = 0
            if (x564) {
              x571 = true
              x572 = x495
            } else {
              val x565 = x520
              x571 = false
              x572 = x565
            }
            x578 = x571
            x579 = x572
          }
          var x585: Boolean = false
          var x586: Int = 0
          if (x578) {
            x585 = true
            x586 = x442
          } else {
            x585 = false
            x586 = x579
          }
          x590 = x585
          x591 = x586
        }
        var x596: Boolean = false
        var x597: Int = 0
        var x595: java.lang.String = null
        if (x590) {
          x596 = true
          x597 = x86
          x595 = null
        } else {
          x596 = false
          x597 = x591
          x595 = x440
        }
        x603 = x596
        x604 = x597
        x602 = x595
      }
      var x620: Unit = ()
      var x621: Tuple2StringAnon6507737 = null
      var x623: Int = 0
      var x622: Boolean = false
      if (x603) {
        x620 = ()
        x621 = null
        x623 = x86
        x622 = true
      } else {
        val x607 = x1(x604)
        val x608 = x607.empty
        var x614: Tuple2StringAnon6507737 = null
        var x616: Int = 0
        var x615: Boolean = false
        if (x608) {
          x614 = null
          x616 = x86
          x615 = true
        } else {
          val x609 = x607.res
          val x610 = new Tuple2StringAnon6507737(x602, x609)
          val x611 = x607.next
          x614 = x610
          x616 = x611
          x615 = false
        }
        x620 = ()
        x621 = x614
        x623 = x616
        x622 = x615
      }
      var x629: Anon6507737 = null
      var x630: Boolean = false
      if (x622) {
        x629 = null
        x630 = true
      } else {
        val x626 = new Anon6507737(7, x621)
        x629 = x626
        x630 = false
      }
      var x1368: Unit = ()
      var x1369: Tuple2Anon6507737ListAnon6507737 = null
      var x1370: Boolean = false
      var x1371: Int = 0
      if (x630) {
        x1368 = ()
        x1369 = null
        x1370 = true
        x1371 = x86
      } else {
        var x638: Boolean = false
        var x633: Int = -1
        var x634: Boolean = true
        var x635: Int = x623
        var x637: scala.collection.immutable.List[Anon6507737] = x84
        var x639: Int = x623
        val x1354 = while ({
          val x641 = x634
          val x642 = x633
          val x643 = x635
          val x644 = x642 != x643
          val x645 = x641 && x644
          x645
        }) {
          val x647 = x635
          x633 = x647
          var x649: Int = -1
          var x650: Boolean = true
          var x651: Int = x647
          var x653: java.lang.String = ""
          var x654: Boolean = false
          var x655: Int = x647
          val x697 = while ({
            val x657 = x650
            val x658 = x649
            val x659 = x651
            val x660 = x658 != x659
            val x661 = x657 && x660
            x661
          }) {
            val x663 = x651
            x649 = x663
            val x665 = x663 >= x5
            var x681: Int = 0
            var x680: Boolean = false
            if (x665) {
              x681 = x663
              x680 = true
            } else {
              val x667 = x0(x663)
              val x668 = x667 == ' '
              val x669 = x667 == '\n'
              val x670 = x668 || x669
              var x676: Int = 0
              var x675: Boolean = false
              if (x670) {
                val x671 = x663 + 1
                x676 = x671
                x675 = false
              } else {
                x676 = x663
                x675 = true
              }
              x681 = x676
              x680 = x675
            }
            val x695 = if (x680) {
              x650 = false
              ()
            } else {
              val x685 = x653
              val x686 = x654
              val x687 = x655
              x653 = x685
              x654 = false
              x655 = x681
              x651 = x681
              ()
            }
            x695
          }
          val x698 = x653
          val x699 = x654
          val x700 = x655
          var x722: Int = 0
          var x721: Boolean = false
          if (x699) {
            x722 = x647
            x721 = true
          } else {
            val x703 = x700 >= x5
            var x717: Int = 0
            var x716: Boolean = false
            if (x703) {
              x717 = x700
              x716 = true
            } else {
              val x705 = x0(x700)
              val x706 = x705 == ','
              var x712: Int = 0
              var x711: Boolean = false
              if (x706) {
                val x707 = x700 + 1
                x712 = x707
                x711 = false
              } else {
                x712 = x700
                x711 = true
              }
              x717 = x712
              x716 = x711
            }
            x722 = x717
            x721 = x716
          }
          var x783: Int = 0
          var x782: Boolean = false
          if (x721) {
            x783 = x647
            x782 = true
          } else {
            var x727: Int = x722
            var x731: Int = x722
            val x773 = while ({
              val x733 = x726
              val x734 = x725
              val x735 = x727
              val x736 = x734 != x735
              val x737 = x733 && x736
              x737
            }) {
              val x739 = x727
              x725 = x739
              val x741 = x739 >= x5
              var x757: Int = 0
              var x756: Boolean = false
              if (x741) {
                x757 = x739
                x756 = true
              } else {
                val x743 = x0(x739)
                val x744 = x743 == ' '
                val x745 = x743 == '\n'
                val x746 = x744 || x745
                var x752: Int = 0
                var x751: Boolean = false
                if (x746) {
                  val x747 = x739 + 1
                  x752 = x747
                  x751 = false
                } else {
                  x752 = x739
                  x751 = true
                }
                x757 = x752
                x756 = x751
              }
              val x771 = if (x756) {
                x726 = false
                ()
              } else {
                val x761 = x729
                val x762 = x730
                val x763 = x731
                x729 = x761
                x730 = false
                x731 = x757
                x727 = x757
                ()
              }
              x771
            }
            val x775 = x730
            val x776 = x731
            x783 = x776
            x782 = x775
          }
          var x1333: Unit = ()
          var x1336: Int = 0
          var x1334: Anon6507737 = null
          var x1335: Boolean = false
          if (x782) {
            x1333 = ()
            x1336 = x647
            x1334 = null
            x1335 = true
          } else {
            val x786 = x783 >= x5
            var x800: Int = 0
            var x799: Boolean = false
            if (x786) {
              x800 = x783
              x799 = true
            } else {
              val x788 = x0(x783)
              val x789 = x788 == '"'
              var x795: Int = 0
              var x794: Boolean = false
              if (x789) {
                val x790 = x783 + 1
                x795 = x790
                x794 = false
              } else {
                x795 = x783
                x794 = true
              }
              x800 = x795
              x799 = x794
            }
            var x1112: java.lang.String = null
            var x1114: Int = 0
            var x1113: Boolean = false
            if (x799) {
              x1112 = null
              x1114 = x783
              x1113 = true
            } else {
              var x806: Int = x800
              var x810: Int = x800
              val x893 = while ({
                val x812 = x805
                val x813 = x804
                val x814 = x806
                val x815 = x813 != x814
                val x816 = x812 && x815
                x816
              }) {
                val x818 = x806
                x804 = x818
                val x875 = x874(x818)
                val x876 = x875.empty
                val x891 = if (x876) {
                  x805 = false
                  ()
                } else {
                  val x879 = x808
                  val x880 = x809
                  val x881 = x810
                  x808 = x879
                  x809 = false
                  val x884 = x875.next
                  x810 = x884
                  x806 = x884
                  ()
                }
                x891
              }
              val x895 = x809
              var x903: Int = 0
              var x901: Int = 0
              var x902: Boolean = false
              if (x895) {
                x903 = x800
                x901 = 0
                x902 = true
              } else {
                val x896 = x810
                x903 = x896
                x901 = x800
                x902 = false
              }
              var x912: Int = 0
              var x910: Tuple2IntInt = null
              var x911: Boolean = false
              if (x902) {
                x912 = x800
                x910 = null
                x911 = true
              } else {
                val x907 = new Tuple2IntInt(x901, x903)
                x912 = x903
                x910 = x907
                x911 = false
              }
              var x1106: java.lang.String = null
              var x1107: Boolean = false
              if (x911) {
                x1106 = null
                x1107 = true
              } else {
                val x915 = x910._1
                val x916 = x910._2
                val x923 = x916 - x915
                val x924 = new Array[Char](x923)
                var x917: Int = x915
                val x918 = x917
                var x919: Int = x918
                val x1083 = while ({
                  val x925 = x919
                  val x926 = x925 < x916
                  x926
                }) {
                  val x928 = x919
                  val x929 = x0(x928)
                  val x930 = x929 == '\\'
                  val x931 = x928 + 1
                  val x932 = x931 < x916
                  val x933 = x930 && x932
                  val x1080 = if (x933) {
                    val x934 = x0(x931)
                    val x935 = x934 == 'u'
                    val x936 = x928 + 5
                    val x937 = x936 < x916
                    val x938 = x935 && x937
                    val x1073 = if (x938) {
                      val x1024 = x922
                      val x939 = x928 + 2
                      val x940 = x0(x939)
                      val x941 = x940 >= '0'
                      val x942 = x940 < '9'
                      val x943 = x941 && x942
                      val x958 = if (x943) {
                        val x944 = x940 - '0'
                        val x945 = x944.toInt
                        x945
                      } else {
                        val x946 = x940 >= 'A'
                        val x947 = x940 < 'F'
                        val x948 = x946 && x947
                        val x957 = if (x948) {
                          val x949 = x940 - '7'
                          val x950 = x949.toInt
                          x950
                        } else {
                          val x951 = x940 >= 'a'
                          val x952 = x940 < 'f'
                          val x953 = x951 && x952
                          val x956 = if (x953) {
                            val x954 = x940 - 'W'
                            val x955 = x954.toInt
                            x955
                          } else {
                            0
                          }
                          x956
                        }
                        x957
                      }
                      val x959 = x958 * 4096
                      val x960 = x928 + 3
                      val x961 = x0(x960)
                      val x962 = x961 >= '0'
                      val x963 = x961 < '9'
                      val x964 = x962 && x963
                      val x979 = if (x964) {
                        val x965 = x961 - '0'
                        val x966 = x965.toInt
                        x966
                      } else {
                        val x967 = x961 >= 'A'
                        val x968 = x961 < 'F'
                        val x969 = x967 && x968
                        val x978 = if (x969) {
                          val x970 = x961 - '7'
                          val x971 = x970.toInt
                          x971
                        } else {
                          val x972 = x961 >= 'a'
                          val x973 = x961 < 'f'
                          val x974 = x972 && x973
                          val x977 = if (x974) {
                            val x975 = x961 - 'W'
                            val x976 = x975.toInt
                            x976
                          } else {
                            0
                          }
                          x977
                        }
                        x978
                      }
                      val x980 = x979 * 256
                      val x981 = x959 + x980
                      val x982 = x928 + 4
                      val x983 = x0(x982)
                      val x984 = x983 >= '0'
                      val x985 = x983 < '9'
                      val x986 = x984 && x985
                      val x1001 = if (x986) {
                        val x987 = x983 - '0'
                        val x988 = x987.toInt
                        x988
                      } else {
                        val x989 = x983 >= 'A'
                        val x990 = x983 < 'F'
                        val x991 = x989 && x990
                        val x1000 = if (x991) {
                          val x992 = x983 - '7'
                          val x993 = x992.toInt
                          x993
                        } else {
                          val x994 = x983 >= 'a'
                          val x995 = x983 < 'f'
                          val x996 = x994 && x995
                          val x999 = if (x996) {
                            val x997 = x983 - 'W'
                            val x998 = x997.toInt
                            x998
                          } else {
                            0
                          }
                          x999
                        }
                        x1000
                      }
                      val x1002 = x1001 * 16
                      val x1003 = x981 + x1002
                      val x1004 = x0(x936)
                      val x1005 = x1004 >= '0'
                      val x1006 = x1004 < '9'
                      val x1007 = x1005 && x1006
                      val x1022 = if (x1007) {
                        val x1008 = x1004 - '0'
                        val x1009 = x1008.toInt
                        x1009
                      } else {
                        val x1010 = x1004 >= 'A'
                        val x1011 = x1004 < 'F'
                        val x1012 = x1010 && x1011
                        val x1021 = if (x1012) {
                          val x1013 = x1004 - '7'
                          val x1014 = x1013.toInt
                          x1014
                        } else {
                          val x1015 = x1004 >= 'a'
                          val x1016 = x1004 < 'f'
                          val x1017 = x1015 && x1016
                          val x1020 = if (x1017) {
                            val x1018 = x1004 - 'W'
                            val x1019 = x1018.toInt
                            x1019
                          } else {
                            0
                          }
                          x1020
                        }
                        x1021
                      }
                      val x1023 = x1003 + x1022
                      val x1025 = x1023.toChar
                      val x1026 = x924(x1024) = x1025
                      val x1027 = x919 += 4
                      ()
                    } else {
                      val x1029 = x934 == 'b'
                      val x1070 = if (x1029) {
                        val x1030 = x922
                        val x1031 = x924(x1030) = ''
                        x1031
                      } else {
                        val x1033 = x934 == 'f'
                        val x1066 = if (x1033) {
                          val x1034 = x922
                          val x1035 = x924(x1034) = ''
                          x1035
                        } else {
                          val x1037 = x934 == 't'
                          val x1062 = if (x1037) {
                            val x1038 = x922
                            val x1039 = x924(x1038) = '	'
                            x1039
                          } else {
                            val x1041 = x934 == 'n'
                            val x1058 = if (x1041) {
                              val x1042 = x922
                              val x1043 = x924(x1042) = '\n'
                              x1043
                            } else {
                              val x1045 = x934 == 'r'
                              val x1054 = if (x1045) {
                                val x1046 = x922
                                val x1047 = x924(x1046) = '
'
                                x1047
                              } else {
                                val x1049 = x922
                                val x1050 = x924(x1049) = x934
                                x1050
                              }
                              x1054
                            }
                            x1058
                          }
                          x1062
                        }
                        x1066
                      }
                      x1070
                    }
                    val x1074 = x919 += 2
                    ()
                  } else {
                    val x1076 = x922
                    val x1077 = x924(x1076) = x929
                    val x1078 = x919 += 1
                    ()
                  }
                  val x1081 = x922 += 1
                  ()
                }
                val x1084 = x922
                val x1100 = while ({
                  val x1088 = x1087
                  val x1089 = x1088 < x1084
                  x1089
                }) {
                  val x1091 = x1086
                  val x1092 = x1087
                  val x1093 = 0 + x1092
                  val x1094 = x924(x1093)
                  val x1095 = x1091 + x1094
                  x1086 = x1095
                  val x1097 = x1092 + 1
                  x1087 = x1097
                  ()
                }
                val x1101 = x1086
                x1106 = x1101
                x1107 = false
              }
              x1112 = x1106
              x1114 = x912
              x1113 = x1107
            }
            var x1139: java.lang.String = null
            var x1141: Int = 0
            var x1140: Boolean = false
            if (x1113) {
              x1139 = x1112
              x1141 = x1114
              x1140 = x1113
            } else {
              val x1116 = x1114 >= x5
              var x1130: Int = 0
              var x1129: Boolean = false
              if (x1116) {
                x1130 = x1114
                x1129 = true
              } else {
                val x1118 = x0(x1114)
                val x1119 = x1118 == '"'
                var x1125: Int = 0
                var x1124: Boolean = false
                if (x1119) {
                  val x1120 = x1114 + 1
                  x1125 = x1120
                  x1124 = false
                } else {
                  x1125 = x1114
                  x1124 = true
                }
                x1130 = x1125
                x1129 = x1124
              }
              var x1134: java.lang.String = null
              var x1136: Int = 0
              var x1135: Boolean = false
              if (x1129) {
                x1134 = null
                x1136 = x783
                x1135 = true
              } else {
                x1134 = x1112
                x1136 = x1130
                x1135 = false
              }
              x1139 = x1134
              x1141 = x1136
              x1140 = x1135
            }
            var x1301: java.lang.String = null
            var x1303: Int = 0
            var x1302: Boolean = false
            if (x1140) {
              x1301 = x1139
              x1303 = x1141
              x1302 = x1140
            } else {
              var x1145: Int = x1141
              var x1149: Int = x1141
              val x1191 = while ({
                val x1151 = x1144
                val x1152 = x1143
                val x1153 = x1145
                val x1154 = x1152 != x1153
                val x1155 = x1151 && x1154
                x1155
              }) {
                val x1157 = x1145
                x1143 = x1157
                val x1159 = x1157 >= x5
                var x1175: Int = 0
                var x1174: Boolean = false
                if (x1159) {
                  x1175 = x1157
                  x1174 = true
                } else {
                  val x1161 = x0(x1157)
                  val x1162 = x1161 == ' '
                  val x1163 = x1161 == '\n'
                  val x1164 = x1162 || x1163
                  var x1170: Int = 0
                  var x1169: Boolean = false
                  if (x1164) {
                    val x1165 = x1157 + 1
                    x1170 = x1165
                    x1169 = false
                  } else {
                    x1170 = x1157
                    x1169 = true
                  }
                  x1175 = x1170
                  x1174 = x1169
                }
                val x1189 = if (x1174) {
                  x1144 = false
                  ()
                } else {
                  val x1179 = x1147
                  val x1180 = x1148
                  val x1181 = x1149
                  x1147 = x1179
                  x1148 = false
                  x1149 = x1175
                  x1145 = x1175
                  ()
                }
                x1189
              }
              val x1193 = x1148
              var x1290: Int = 0
              var x1289: Boolean = false
              if (x1193) {
                val x1194 = x1149
                x1290 = x1194
                x1289 = x1193
              } else {
                val x1194 = x1149
                val x1196 = x1194 >= x5
                var x1210: Int = 0
                var x1209: Boolean = false
                if (x1196) {
                  x1210 = x1194
                  x1209 = true
                } else {
                  val x1198 = x0(x1194)
                  val x1199 = x1198 == ':'
                  var x1205: Int = 0
                  var x1204: Boolean = false
                  if (x1199) {
                    val x1200 = x1194 + 1
                    x1205 = x1200
                    x1204 = false
                  } else {
                    x1205 = x1194
                    x1204 = true
                  }
                  x1210 = x1205
                  x1209 = x1204
                }
                var x1278: Int = 0
                var x1277: Boolean = false
                if (x1209) {
                  x1278 = x1194
                  x1277 = true
                } else {
                  var x1215: Int = x1210
                  var x1219: Int = x1210
                  val x1261 = while ({
                    val x1221 = x1214
                    val x1222 = x1213
                    val x1223 = x1215
                    val x1224 = x1222 != x1223
                    val x1225 = x1221 && x1224
                    x1225
                  }) {
                    val x1227 = x1215
                    x1213 = x1227
                    val x1229 = x1227 >= x5
                    var x1245: Int = 0
                    var x1244: Boolean = false
                    if (x1229) {
                      x1245 = x1227
                      x1244 = true
                    } else {
                      val x1231 = x0(x1227)
                      val x1232 = x1231 == ' '
                      val x1233 = x1231 == '\n'
                      val x1234 = x1232 || x1233
                      var x1240: Int = 0
                      var x1239: Boolean = false
                      if (x1234) {
                        val x1235 = x1227 + 1
                        x1240 = x1235
                        x1239 = false
                      } else {
                        x1240 = x1227
                        x1239 = true
                      }
                      x1245 = x1240
                      x1244 = x1239
                    }
                    val x1259 = if (x1244) {
                      x1214 = false
                      ()
                    } else {
                      val x1249 = x1217
                      val x1250 = x1218
                      val x1251 = x1219
                      x1217 = x1249
                      x1218 = false
                      x1219 = x1245
                      x1215 = x1245
                      ()
                    }
                    x1259
                  }
                  val x1263 = x1218
                  var x1271: Int = 0
                  var x1270: Boolean = false
                  if (x1263) {
                    x1271 = x1194
                    x1270 = true
                  } else {
                    val x1264 = x1219
                    x1271 = x1264
                    x1270 = false
                  }
                  x1278 = x1271
                  x1277 = x1270
                }
                var x1285: Int = 0
                var x1284: Boolean = false
                if (x1277) {
                  x1285 = x1141
                  x1284 = true
                } else {
                  x1285 = x1278
                  x1284 = false
                }
                x1290 = x1285
                x1289 = x1284
              }
              var x1294: java.lang.String = null
              var x1296: Int = 0
              var x1295: Boolean = false
              if (x1289) {
                x1294 = null
                x1296 = x783
                x1295 = true
              } else {
                x1294 = x1139
                x1296 = x1290
                x1295 = false
              }
              x1301 = x1294
              x1303 = x1296
              x1302 = x1295
            }
            var x1319: Unit = ()
            var x1322: Int = 0
            var x1320: Tuple2StringAnon6507737 = null
            var x1321: Boolean = false
            if (x1302) {
              x1319 = ()
              x1322 = x783
              x1320 = null
              x1321 = true
            } else {
              val x1306 = x1(x1303)
              val x1307 = x1306.empty
              var x1315: Int = 0
              var x1313: Tuple2StringAnon6507737 = null
              var x1314: Boolean = false
              if (x1307) {
                x1315 = x783
                x1313 = null
                x1314 = true
              } else {
                val x1308 = x1306.res
                val x1309 = new Tuple2StringAnon6507737(x1301, x1308)
                val x1310 = x1306.next
                x1315 = x1310
                x1313 = x1309
                x1314 = false
              }
              x1319 = ()
              x1322 = x1315
              x1320 = x1313
              x1321 = x1314
            }
            var x1328: Anon6507737 = null
            var x1329: Boolean = false
            if (x1321) {
              x1328 = null
              x1329 = true
            } else {
              val x1325 = new Anon6507737(7, x1320)
              x1328 = x1325
              x1329 = false
            }
            x1333 = ()
            x1336 = x1322
            x1334 = x1328
            x1335 = x1329
          }
          val x1352 = if (x1335) {
            x634 = false
            ()
          } else {
            val x1340 = x637
            val x1341 = x638
            val x1342 = x639
            val x1344 = List(x1334)
            val x1345 = x1340 ::: x1344
            x637 = x1345
            x638 = false
            x639 = x1336
            x635 = x1336
            ()
          }
          x1352
        }
        val x1356 = x638
        val x1357 = x639
        val x1355 = x637
        var x1362: Tuple2Anon6507737ListAnon6507737 = null
        var x1363: Boolean = false
        var x1364: Int = 0
        if (x1356) {
          x1362 = null
          x1363 = true
          x1364 = x86
        } else {
          val x1359 = new Tuple2Anon6507737ListAnon6507737(x629, x1355)
          x1362 = x1359
          x1363 = false
          x1364 = x1357
        }
        x1368 = ()
        x1369 = x1362
        x1370 = x1363
        x1371 = x1364
      }
      var x1380: Boolean = false
      var x1379: scala.collection.immutable.List[Anon6507737] = null
      if (x1370) {
        x1380 = true
        x1379 = null
      } else {
        val x1374 = x1369._1
        val x1375 = x1369._2
        val x1376 = x1374 :: x1375
        x1380 = false
        x1379 = x1376
      }
      val x1381 = new ParseResultListAnon6507737(x1379, x1380, x1371)
      x1381: ParseResultListAnon6507737
    }
    x1389 = { x85: (Int) =>
      val x1384 = x1383(x85)
      val x1385 = x1384.empty
      val x1387 = if (x1385) {
        val x1386 = new ParseResultListAnon6507737(x84, false, x85)
        x1386
      } else {
        x1384
      }
      x1387: ParseResultListAnon6507737
    }
    var x28: Boolean = false
    var x23: Int = -1
    var x24: Boolean = true
    var x27: java.lang.String = ""
    x1492 = { x4: (Int) =>
      val x6 = x4 >= x5
      var x19: Boolean = false
      var x20: Int = 0
      if (x6) {
        x19 = true
        x20 = x4
      } else {
        val x8 = x0(x4)
        val x9 = x8 == '{'
        var x14: Boolean = false
        var x15: Int = 0
        if (x9) {
          val x10 = x4 + 1
          x14 = false
          x15 = x10
        } else {
          x14 = true
          x15 = x4
        }
        x19 = x14
        x20 = x15
      }
      var x80: Boolean = false
      var x81: Int = 0
      if (x19) {
        x80 = true
        x81 = x4
      } else {
        var x25: Int = x20
        var x29: Int = x20
        val x71 = while ({
          val x31 = x24
          val x32 = x23
          val x33 = x25
          val x34 = x32 != x33
          val x35 = x31 && x34
          x35
        }) {
          val x37 = x25
          x23 = x37
          val x39 = x37 >= x5
          var x54: Boolean = false
          var x55: Int = 0
          if (x39) {
            x54 = true
            x55 = x37
          } else {
            val x41 = x0(x37)
            val x42 = x41 == ' '
            val x43 = x41 == '\n'
            val x44 = x42 || x43
            var x49: Boolean = false
            var x50: Int = 0
            if (x44) {
              val x45 = x37 + 1
              x49 = false
              x50 = x45
            } else {
              x49 = true
              x50 = x37
            }
            x54 = x49
            x55 = x50
          }
          val x69 = if (x54) {
            x24 = false
            ()
          } else {
            val x59 = x27
            val x60 = x28
            val x61 = x29
            x27 = x59
            x28 = false
            x29 = x55
            x25 = x55
            ()
          }
          x69
        }
        val x73 = x28
        val x74 = x29
        x80 = x73
        x81 = x74
      }
      val x1393 = if (x80) {
        val x83 = new ParseResultListAnon6507737(null, true, x4)
        x83
      } else {
        val x1390 = x1389(x81)
        x1390
      }
      val x1394 = x1393.empty
      val x1480 = if (x1394) {
        x1393
      } else {
        var x1396: Int = -1
        var x1397: Boolean = true
        val x1395 = x1393.next
        var x1398: Int = x1395
        var x1400: java.lang.String = ""
        var x1401: Boolean = false
        var x1402: Int = x1395
        val x1444 = while ({
          val x1404 = x1397
          val x1405 = x1396
          val x1406 = x1398
          val x1407 = x1405 != x1406
          val x1408 = x1404 && x1407
          x1408
        }) {
          val x1410 = x1398
          x1396 = x1410
          val x1412 = x1410 >= x5
          var x1428: Int = 0
          var x1427: Boolean = false
          if (x1412) {
            x1428 = x1410
            x1427 = true
          } else {
            val x1414 = x0(x1410)
            val x1415 = x1414 == ' '
            val x1416 = x1414 == '\n'
            val x1417 = x1415 || x1416
            var x1423: Int = 0
            var x1422: Boolean = false
            if (x1417) {
              val x1418 = x1410 + 1
              x1423 = x1418
              x1422 = false
            } else {
              x1423 = x1410
              x1422 = true
            }
            x1428 = x1423
            x1427 = x1422
          }
          val x1442 = if (x1427) {
            x1397 = false
            ()
          } else {
            val x1432 = x1400
            val x1433 = x1401
            val x1434 = x1402
            x1400 = x1432
            x1401 = false
            x1402 = x1428
            x1398 = x1428
            ()
          }
          x1442
        }
        val x1445 = x1400
        val x1446 = x1401
        val x1447 = x1402
        var x1469: Int = 0
        var x1468: Boolean = false
        if (x1446) {
          x1469 = x1395
          x1468 = true
        } else {
          val x1450 = x1447 >= x5
          var x1464: Int = 0
          var x1463: Boolean = false
          if (x1450) {
            x1464 = x1447
            x1463 = true
          } else {
            val x1452 = x0(x1447)
            val x1453 = x1452 == '}'
            var x1459: Int = 0
            var x1458: Boolean = false
            if (x1453) {
              val x1454 = x1447 + 1
              x1459 = x1454
              x1458 = false
            } else {
              x1459 = x1447
              x1458 = true
            }
            x1464 = x1459
            x1463 = x1458
          }
          x1469 = x1464
          x1468 = x1463
        }
        var x1476: Int = 0
        var x1475: Boolean = false
        var x1474: scala.collection.immutable.List[Anon6507737] = null
        if (x1468) {
          x1476 = x4
          x1475 = true
          x1474 = null
        } else {
          val x1471 = x1393.res
          x1476 = x1469
          x1475 = false
          x1474 = x1471
        }
        val x1477 = new ParseResultListAnon6507737(x1474, x1475, x1476)
        x1477
      }
      val x1482 = x1480.next
      val x1481 = x1480.empty
      var x1489: Boolean = false
      var x1488: Anon6507737 = null
      if (x1481) {
        x1489 = true
        x1488 = null
      } else {
        val x1484 = x1480.res
        val x1485 = new Anon6507737(8, x1484)
        x1489 = false
        x1488 = x1485
      }
      val x1490 = new ParseResultAnon6507737(x1488, x1489, x1482)
      x1490: ParseResultAnon6507737
    }
    var x1676: Boolean = false
    var x1671: Int = -1
    var x1672: Boolean = true
    var x1675: java.lang.String = ""
    x1785 = { x1574: (Int) =>
      val x1575 = x1(x1574)
      val x1576 = x1575.empty
      var x1770: Unit = ()
      var x1771: Tuple2Anon6507737ListAnon6507737 = null
      var x1772: Boolean = false
      var x1773: Int = 0
      if (x1576) {
        x1770 = ()
        x1771 = null
        x1772 = true
        x1773 = x1574
      } else {
        var x1584: Boolean = false
        var x1579: Int = -1
        var x1580: Boolean = true
        val x1578 = x1575.next
        var x1581: Int = x1578
        var x1583: scala.collection.immutable.List[Anon6507737] = x84
        var x1585: Int = x1578
        val x1755 = while ({
          val x1587 = x1580
          val x1588 = x1579
          val x1589 = x1581
          val x1590 = x1588 != x1589
          val x1591 = x1587 && x1590
          x1591
        }) {
          val x1593 = x1581
          x1579 = x1593
          var x1595: Int = -1
          var x1596: Boolean = true
          var x1597: Int = x1593
          var x1599: java.lang.String = ""
          var x1600: Boolean = false
          var x1601: Int = x1593
          val x1643 = while ({
            val x1603 = x1596
            val x1604 = x1595
            val x1605 = x1597
            val x1606 = x1604 != x1605
            val x1607 = x1603 && x1606
            x1607
          }) {
            val x1609 = x1597
            x1595 = x1609
            val x1611 = x1609 >= x5
            var x1627: Int = 0
            var x1626: Boolean = false
            if (x1611) {
              x1627 = x1609
              x1626 = true
            } else {
              val x1613 = x0(x1609)
              val x1614 = x1613 == ' '
              val x1615 = x1613 == '\n'
              val x1616 = x1614 || x1615
              var x1622: Int = 0
              var x1621: Boolean = false
              if (x1616) {
                val x1617 = x1609 + 1
                x1622 = x1617
                x1621 = false
              } else {
                x1622 = x1609
                x1621 = true
              }
              x1627 = x1622
              x1626 = x1621
            }
            val x1641 = if (x1626) {
              x1596 = false
              ()
            } else {
              val x1631 = x1599
              val x1632 = x1600
              val x1633 = x1601
              x1599 = x1631
              x1600 = false
              x1601 = x1627
              x1597 = x1627
              ()
            }
            x1641
          }
          val x1644 = x1599
          val x1645 = x1600
          val x1646 = x1601
          var x1668: Int = 0
          var x1667: Boolean = false
          if (x1645) {
            x1668 = x1593
            x1667 = true
          } else {
            val x1649 = x1646 >= x5
            var x1663: Int = 0
            var x1662: Boolean = false
            if (x1649) {
              x1663 = x1646
              x1662 = true
            } else {
              val x1651 = x0(x1646)
              val x1652 = x1651 == ','
              var x1658: Int = 0
              var x1657: Boolean = false
              if (x1652) {
                val x1653 = x1646 + 1
                x1658 = x1653
                x1657 = false
              } else {
                x1658 = x1646
                x1657 = true
              }
              x1663 = x1658
              x1662 = x1657
            }
            x1668 = x1663
            x1667 = x1662
          }
          var x1729: Int = 0
          var x1728: Boolean = false
          if (x1667) {
            x1729 = x1593
            x1728 = true
          } else {
            var x1673: Int = x1668
            var x1677: Int = x1668
            val x1719 = while ({
              val x1679 = x1672
              val x1680 = x1671
              val x1681 = x1673
              val x1682 = x1680 != x1681
              val x1683 = x1679 && x1682
              x1683
            }) {
              val x1685 = x1673
              x1671 = x1685
              val x1687 = x1685 >= x5
              var x1703: Int = 0
              var x1702: Boolean = false
              if (x1687) {
                x1703 = x1685
                x1702 = true
              } else {
                val x1689 = x0(x1685)
                val x1690 = x1689 == ' '
                val x1691 = x1689 == '\n'
                val x1692 = x1690 || x1691
                var x1698: Int = 0
                var x1697: Boolean = false
                if (x1692) {
                  val x1693 = x1685 + 1
                  x1698 = x1693
                  x1697 = false
                } else {
                  x1698 = x1685
                  x1697 = true
                }
                x1703 = x1698
                x1702 = x1697
              }
              val x1717 = if (x1702) {
                x1672 = false
                ()
              } else {
                val x1707 = x1675
                val x1708 = x1676
                val x1709 = x1677
                x1675 = x1707
                x1676 = false
                x1677 = x1703
                x1673 = x1703
                ()
              }
              x1717
            }
            val x1721 = x1676
            val x1722 = x1677
            x1729 = x1722
            x1728 = x1721
          }
          val x1735 = if (x1728) {
            val x1731 = new ParseResultAnon6507737(null, true, x1593)
            x1731
          } else {
            val x1732 = x1(x1729)
            x1732
          }
          val x1736 = x1735.empty
          val x1753 = if (x1736) {
            x1580 = false
            ()
          } else {
            val x1739 = x1583
            val x1740 = x1584
            val x1741 = x1585
            val x1743 = x1735.res
            val x1744 = List(x1743)
            val x1745 = x1739 ::: x1744
            x1583 = x1745
            x1584 = false
            val x1746 = x1735.next
            x1585 = x1746
            x1581 = x1746
            ()
          }
          x1753
        }
        val x1757 = x1584
        val x1758 = x1585
        val x1756 = x1583
        var x1764: Tuple2Anon6507737ListAnon6507737 = null
        var x1765: Boolean = false
        var x1766: Int = 0
        if (x1757) {
          x1764 = null
          x1765 = true
          x1766 = x1574
        } else {
          val x1760 = x1575.res
          val x1761 = new Tuple2Anon6507737ListAnon6507737(x1760, x1756)
          x1764 = x1761
          x1765 = false
          x1766 = x1758
        }
        x1770 = ()
        x1771 = x1764
        x1772 = x1765
        x1773 = x1766
      }
      var x1782: Boolean = false
      var x1781: scala.collection.immutable.List[Anon6507737] = null
      if (x1772) {
        x1782 = true
        x1781 = null
      } else {
        val x1776 = x1771._1
        val x1777 = x1771._2
        val x1778 = x1776 :: x1777
        x1782 = false
        x1781 = x1778
      }
      val x1783 = new ParseResultListAnon6507737(x1781, x1782, x1773)
      x1783: ParseResultListAnon6507737
    }
    x1791 = { x1573: (Int) =>
      val x1786 = x1785(x1573)
      val x1787 = x1786.empty
      val x1789 = if (x1787) {
        val x1788 = new ParseResultListAnon6507737(x84, false, x1573)
        x1788
      } else {
        x1786
      }
      x1789: ParseResultListAnon6507737
    }
    var x1517: Boolean = false
    var x1512: Int = -1
    var x1513: Boolean = true
    var x1516: java.lang.String = ""
    x1898 = { x3: (Int) =>
      val x1493 = x1492(x3)
      val x1494 = x1493.empty
      val x1896 = if (x1494) {
        val x1495 = x3 >= x5
        var x1508: Boolean = false
        var x1509: Int = 0
        if (x1495) {
          x1508 = true
          x1509 = x3
        } else {
          val x1497 = x0(x3)
          val x1498 = x1497 == '['
          var x1503: Boolean = false
          var x1504: Int = 0
          if (x1498) {
            val x1499 = x3 + 1
            x1503 = false
            x1504 = x1499
          } else {
            x1503 = true
            x1504 = x3
          }
          x1508 = x1503
          x1509 = x1504
        }
        var x1569: Boolean = false
        var x1570: Int = 0
        if (x1508) {
          x1569 = true
          x1570 = x3
        } else {
          var x1514: Int = x1509
          var x1518: Int = x1509
          val x1560 = while ({
            val x1520 = x1513
            val x1521 = x1512
            val x1522 = x1514
            val x1523 = x1521 != x1522
            val x1524 = x1520 && x1523
            x1524
          }) {
            val x1526 = x1514
            x1512 = x1526
            val x1528 = x1526 >= x5
            var x1543: Boolean = false
            var x1544: Int = 0
            if (x1528) {
              x1543 = true
              x1544 = x1526
            } else {
              val x1530 = x0(x1526)
              val x1531 = x1530 == ' '
              val x1532 = x1530 == '\n'
              val x1533 = x1531 || x1532
              var x1538: Boolean = false
              var x1539: Int = 0
              if (x1533) {
                val x1534 = x1526 + 1
                x1538 = false
                x1539 = x1534
              } else {
                x1538 = true
                x1539 = x1526
              }
              x1543 = x1538
              x1544 = x1539
            }
            val x1558 = if (x1543) {
              x1513 = false
              ()
            } else {
              val x1548 = x1516
              val x1549 = x1517
              val x1550 = x1518
              x1516 = x1548
              x1517 = false
              x1518 = x1544
              x1514 = x1544
              ()
            }
            x1558
          }
          val x1562 = x1517
          val x1563 = x1518
          x1569 = x1562
          x1570 = x1563
        }
        val x1795 = if (x1569) {
          val x1572 = new ParseResultListAnon6507737(null, true, x3)
          x1572
        } else {
          val x1792 = x1791(x1570)
          x1792
        }
        val x1796 = x1795.empty
        val x1882 = if (x1796) {
          x1795
        } else {
          var x1798: Int = -1
          var x1799: Boolean = true
          val x1797 = x1795.next
          var x1800: Int = x1797
          var x1802: java.lang.String = ""
          var x1803: Boolean = false
          var x1804: Int = x1797
          val x1846 = while ({
            val x1806 = x1799
            val x1807 = x1798
            val x1808 = x1800
            val x1809 = x1807 != x1808
            val x1810 = x1806 && x1809
            x1810
          }) {
            val x1812 = x1800
            x1798 = x1812
            val x1814 = x1812 >= x5
            var x1830: Int = 0
            var x1829: Boolean = false
            if (x1814) {
              x1830 = x1812
              x1829 = true
            } else {
              val x1816 = x0(x1812)
              val x1817 = x1816 == ' '
              val x1818 = x1816 == '\n'
              val x1819 = x1817 || x1818
              var x1825: Int = 0
              var x1824: Boolean = false
              if (x1819) {
                val x1820 = x1812 + 1
                x1825 = x1820
                x1824 = false
              } else {
                x1825 = x1812
                x1824 = true
              }
              x1830 = x1825
              x1829 = x1824
            }
            val x1844 = if (x1829) {
              x1799 = false
              ()
            } else {
              val x1834 = x1802
              val x1835 = x1803
              val x1836 = x1804
              x1802 = x1834
              x1803 = false
              x1804 = x1830
              x1800 = x1830
              ()
            }
            x1844
          }
          val x1847 = x1802
          val x1848 = x1803
          val x1849 = x1804
          var x1871: Int = 0
          var x1870: Boolean = false
          if (x1848) {
            x1871 = x1797
            x1870 = true
          } else {
            val x1852 = x1849 >= x5
            var x1866: Int = 0
            var x1865: Boolean = false
            if (x1852) {
              x1866 = x1849
              x1865 = true
            } else {
              val x1854 = x0(x1849)
              val x1855 = x1854 == ']'
              var x1861: Int = 0
              var x1860: Boolean = false
              if (x1855) {
                val x1856 = x1849 + 1
                x1861 = x1856
                x1860 = false
              } else {
                x1861 = x1849
                x1860 = true
              }
              x1866 = x1861
              x1865 = x1860
            }
            x1871 = x1866
            x1870 = x1865
          }
          var x1878: Int = 0
          var x1877: Boolean = false
          var x1876: scala.collection.immutable.List[Anon6507737] = null
          if (x1870) {
            x1878 = x3
            x1877 = true
            x1876 = null
          } else {
            val x1873 = x1795.res
            x1878 = x1871
            x1877 = false
            x1876 = x1873
          }
          val x1879 = new ParseResultListAnon6507737(x1876, x1877, x1878)
          x1879
        }
        val x1884 = x1882.next
        val x1883 = x1882.empty
        var x1892: Boolean = false
        var x1891: Anon6507737 = null
        if (x1883) {
          x1892 = true
          x1891 = null
        } else {
          val x1886 = x1882.res
          val x1887 = x1886.asInstanceOf[scala.collection.immutable.List[Anon6507737]]
          val x1888 = new Anon6507737(6, x1887)
          x1892 = false
          x1891 = x1888
        }
        val x1893 = new ParseResultAnon6507737(x1891, x1892, x1884)
        x1893
      } else {
        x1493
      }
      x1896: ParseResultAnon6507737
    }
    x2881 = { x2878: (Int) =>
      val x2879 = x1898(x2878)
      x2879: ParseResultAnon6507737
    }
    val x1899 = px1899 // static data: 5
    var x1911: Boolean = true
    var x1910: Int = 0
    val x1900 = px1900 // static data: Array(f,a,l,s,e)
    val x1901 = new Anon6507737(1, null)
    val x1950 = { x1906: (Int) =>
      val x1907 = x1906 + x1899
      val x1908 = x1907 > x5
      var x1942: Int = 0
      var x1941: Boolean = false
      if (x1908) {
        x1942 = x1906
        x1941 = true
      } else {
        val x1929 = while ({
          val x1912 = x1911
          val x1913 = x1910
          val x1914 = x1913 < x1899
          val x1915 = x1912 && x1914
          x1915
        }) {
          val x1917 = x1910
          val x1918 = x1906 + x1917
          val x1919 = x0(x1918)
          val x1920 = x1900(x1917)
          val x1922 = x1919 == x1920
          val x1925 = if (x1922) {
            ()
          } else {
            x1911 = false
            ()
          }
          val x1926 = x1917 + 1
          x1910 = x1926
          ()
        }
        val x1930 = x1911
        var x1935: Int = 0
        var x1934: Boolean = false
        if (x1930) {
          x1935 = x1907
          x1934 = false
        } else {
          x1935 = x1906
          x1934 = true
        }
        x1942 = x1935
        x1941 = x1934
      }
      var x1947: Anon6507737 = null
      var x1948: Boolean = false
      if (x1941) {
        x1947 = null
        x1948 = true
      } else {
        x1947 = x1901
        x1948 = false
      }
      val x1949 = new ParseResultAnon6507737(x1947, x1948, x1942)
      x1949: ParseResultAnon6507737
    }
    val x1902 = px1902 // static data: 4
    var x1957: Boolean = true
    var x1956: Int = 0
    val x1903 = px1903 // static data: Array(t,r,u,e)
    val x1904 = new Anon6507737(2, null)
    val x1997 = { x1905: (Int) =>
      val x1951 = x1950(x1905)
      val x1952 = x1951.empty
      val x1996 = if (x1952) {
        val x1953 = x1905 + x1902
        val x1954 = x1953 > x5
        var x1988: Int = 0
        var x1987: Boolean = false
        if (x1954) {
          x1988 = x1905
          x1987 = true
        } else {
          val x1975 = while ({
            val x1958 = x1957
            val x1959 = x1956
            val x1960 = x1959 < x1902
            val x1961 = x1958 && x1960
            x1961
          }) {
            val x1963 = x1956
            val x1964 = x1905 + x1963
            val x1965 = x0(x1964)
            val x1966 = x1903(x1963)
            val x1968 = x1965 == x1966
            val x1971 = if (x1968) {
              ()
            } else {
              x1957 = false
              ()
            }
            val x1972 = x1963 + 1
            x1956 = x1972
            ()
          }
          val x1976 = x1957
          var x1981: Int = 0
          var x1980: Boolean = false
          if (x1976) {
            x1981 = x1953
            x1980 = false
          } else {
            x1981 = x1905
            x1980 = true
          }
          x1988 = x1981
          x1987 = x1980
        }
        var x1993: Anon6507737 = null
        var x1994: Boolean = false
        if (x1987) {
          x1993 = null
          x1994 = true
        } else {
          x1993 = x1904
          x1994 = false
        }
        val x1995 = new ParseResultAnon6507737(x1993, x1994, x1988)
        x1995
      } else {
        x1951
      }
      x1996: ParseResultAnon6507737
    }
    val x2003 = { x2001: (Int) =>
      val x2002 = x1997(x2001)
      x2002: ParseResultAnon6507737
    }
    var x2010: Boolean = true
    var x2009: Int = 0
    val x1998 = px1998 // static data: Array(n,u,l,l)
    val x1999 = new Anon6507737(0, null)
    val x2050 = { x2000: (Int) =>
      val x2004 = x2003(x2000)
      val x2005 = x2004.empty
      val x2049 = if (x2005) {
        val x2006 = x2000 + x1902
        val x2007 = x2006 > x5
        var x2041: Int = 0
        var x2040: Boolean = false
        if (x2007) {
          x2041 = x2000
          x2040 = true
        } else {
          val x2028 = while ({
            val x2011 = x2010
            val x2012 = x2009
            val x2013 = x2012 < x1902
            val x2014 = x2011 && x2013
            x2014
          }) {
            val x2016 = x2009
            val x2017 = x2000 + x2016
            val x2018 = x0(x2017)
            val x2019 = x1998(x2016)
            val x2021 = x2018 == x2019
            val x2024 = if (x2021) {
              ()
            } else {
              x2010 = false
              ()
            }
            val x2025 = x2016 + 1
            x2009 = x2025
            ()
          }
          val x2029 = x2010
          var x2034: Int = 0
          var x2033: Boolean = false
          if (x2029) {
            x2034 = x2006
            x2033 = false
          } else {
            x2034 = x2000
            x2033 = true
          }
          x2041 = x2034
          x2040 = x2033
        }
        var x2046: Anon6507737 = null
        var x2047: Boolean = false
        if (x2040) {
          x2046 = null
          x2047 = true
        } else {
          x2046 = x1999
          x2047 = false
        }
        val x2048 = new ParseResultAnon6507737(x2046, x2047, x2041)
        x2048
      } else {
        x2004
      }
      x2049: ParseResultAnon6507737
    }
    val x2054 = { x2052: (Int) =>
      val x2053 = x2050(x2052)
      x2053: ParseResultAnon6507737
    }
    var x2105: Boolean = false
    var x2100: Int = -1
    var x2101: Boolean = true
    var x2104: java.lang.String = ""
    val x2073 = null.asInstanceOf[Char]
    val x2074 = new OptionChar(x2073, false)
    var x2238: Boolean = false
    var x2233: Int = -1
    var x2234: Boolean = true
    var x2237: java.lang.String = ""
    val x2346 = { x2051: (Int) =>
      val x2055 = x2054(x2051)
      val x2056 = x2055.empty
      val x2345 = if (x2056) {
        val x2057 = x2051 >= x5
        var x2071: Int = 0
        var x2070: Boolean = false
        var x2069: Char = ' '
        if (x2057) {
          x2071 = x2051
          x2070 = true
          x2069 = ' '
        } else {
          val x2059 = x0(x2051)
          val x2060 = x2059 == '-'
          var x2066: Int = 0
          var x2065: Boolean = false
          var x2064: Char = ' '
          if (x2060) {
            val x2061 = x2051 + 1
            x2066 = x2061
            x2065 = false
            x2064 = x2059
          } else {
            x2066 = x2051
            x2065 = true
            x2064 = ' '
          }
          x2071 = x2066
          x2070 = x2065
          x2069 = x2064
        }
        val x2081 = x2071 >= x5
        var x2096: Boolean = false
        var x2097: Int = 0
        var x2095: Char = ' '
        if (x2081) {
          x2096 = true
          x2097 = x2071
          x2095 = ' '
        } else {
          val x2083 = x0(x2071)
          val x2084 = x2083 >= '0'
          val x2085 = x2083 <= '9'
          val x2086 = x2084 && x2085
          var x2091: Boolean = false
          var x2092: Int = 0
          var x2090: Char = ' '
          if (x2086) {
            val x2087 = x2071 + 1
            x2091 = false
            x2092 = x2087
            x2090 = x2083
          } else {
            x2091 = true
            x2092 = x2071
            x2090 = ' '
          }
          x2096 = x2091
          x2097 = x2092
          x2095 = x2090
        }
        var x2165: Boolean = false
        var x2166: Int = 0
        var x2164: Tuple2CharString = null
        if (x2096) {
          x2165 = true
          x2166 = x2071
          x2164 = null
        } else {
          var x2102: Int = x2097
          var x2106: Int = x2097
          val x2149 = while ({
            val x2108 = x2101
            val x2109 = x2100
            val x2110 = x2102
            val x2111 = x2109 != x2110
            val x2112 = x2108 && x2111
            x2112
          }) {
            val x2114 = x2102
            x2100 = x2114
            val x2116 = x2114 >= x5
            var x2131: Boolean = false
            var x2130: Char = ' '
            var x2132: Int = 0
            if (x2116) {
              x2131 = true
              x2130 = ' '
              x2132 = x2114
            } else {
              val x2118 = x0(x2114)
              val x2119 = x2118 >= '0'
              val x2120 = x2118 <= '9'
              val x2121 = x2119 && x2120
              var x2126: Boolean = false
              var x2125: Char = ' '
              var x2127: Int = 0
              if (x2121) {
                val x2122 = x2114 + 1
                x2126 = false
                x2125 = x2118
                x2127 = x2122
              } else {
                x2126 = true
                x2125 = ' '
                x2127 = x2114
              }
              x2131 = x2126
              x2130 = x2125
              x2132 = x2127
            }
            val x2147 = if (x2131) {
              x2101 = false
              ()
            } else {
              val x2136 = x2104
              val x2137 = x2105
              val x2138 = x2106
              val x2140 = x2136 + x2130
              x2104 = x2140
              x2105 = false
              x2106 = x2132
              x2102 = x2132
              ()
            }
            x2147
          }
          val x2151 = x2105
          var x2158: Boolean = false
          var x2159: Int = 0
          var x2157: Tuple2CharString = null
          if (x2151) {
            x2158 = true
            x2159 = x2071
            x2157 = null
          } else {
            val x2152 = x2106
            val x2150 = x2104
            val x2154 = new Tuple2CharString(x2095, x2150)
            x2158 = false
            x2159 = x2152
            x2157 = x2154
          }
          x2165 = x2158
          x2166 = x2159
          x2164 = x2157
        }
        var x2175: Boolean = false
        var x2174: java.lang.String = null
        if (x2165) {
          x2175 = true
          x2174 = null
        } else {
          val x2169 = x2164._1
          val x2170 = x2164._2
          val x2171 = x2169 + x2170
          x2175 = false
          x2174 = x2171
        }
        var x2182: Boolean = false
        var x2183: Int = 0
        var x2181: Tuple2OptionCharString = null
        if (x2175) {
          x2182 = true
          x2183 = x2051
          x2181 = null
        } else {
          val x2079 = if (x2070) {
            x2074
          } else {
            val x2076 = new OptionChar(x2069, true)
            x2076
          }
          val x2178 = new Tuple2OptionCharString(x2079, x2174)
          x2182 = false
          x2183 = x2166
          x2181 = x2178
        }
        var x2195: Boolean = false
        var x2194: java.lang.String = null
        if (x2182) {
          x2195 = true
          x2194 = null
        } else {
          val x2186 = x2181._1
          val x2188 = x2186.defined
          val x2191 = if (x2188) {
            val x2187 = x2181._2
            val x2189 = x2186.value
            val x2190 = x2189 + x2187
            x2190
          } else {
            val x2187 = x2181._2
            x2187
          }
          x2195 = false
          x2194 = x2191
        }
        var x2325: Int = 0
        var x2324: Boolean = false
        var x2323: Tuple2StringString = null
        if (x2195) {
          x2325 = x2051
          x2324 = true
          x2323 = null
        } else {
          val x2198 = x2183 >= x5
          var x2211: Boolean = false
          var x2212: Int = 0
          if (x2198) {
            x2211 = true
            x2212 = x2183
          } else {
            val x2200 = x0(x2183)
            val x2201 = x2200 == '.'
            var x2206: Boolean = false
            var x2207: Int = 0
            if (x2201) {
              val x2202 = x2183 + 1
              x2206 = false
              x2207 = x2202
            } else {
              x2206 = true
              x2207 = x2183
            }
            x2211 = x2206
            x2212 = x2207
          }
          var x2312: Boolean = false
          var x2313: Int = 0
          var x2311: java.lang.String = null
          if (x2211) {
            x2312 = true
            x2313 = x2183
            x2311 = null
          } else {
            val x2214 = x2212 >= x5
            var x2229: Boolean = false
            var x2230: Int = 0
            var x2228: Char = ' '
            if (x2214) {
              x2229 = true
              x2230 = x2212
              x2228 = ' '
            } else {
              val x2216 = x0(x2212)
              val x2217 = x2216 >= '0'
              val x2218 = x2216 <= '9'
              val x2219 = x2217 && x2218
              var x2224: Boolean = false
              var x2225: Int = 0
              var x2223: Char = ' '
              if (x2219) {
                val x2220 = x2212 + 1
                x2224 = false
                x2225 = x2220
                x2223 = x2216
              } else {
                x2224 = true
                x2225 = x2212
                x2223 = ' '
              }
              x2229 = x2224
              x2230 = x2225
              x2228 = x2223
            }
            var x2298: Boolean = false
            var x2299: Int = 0
            var x2297: Tuple2CharString = null
            if (x2229) {
              x2298 = true
              x2299 = x2212
              x2297 = null
            } else {
              var x2235: Int = x2230
              var x2239: Int = x2230
              val x2282 = while ({
                val x2241 = x2234
                val x2242 = x2233
                val x2243 = x2235
                val x2244 = x2242 != x2243
                val x2245 = x2241 && x2244
                x2245
              }) {
                val x2247 = x2235
                x2233 = x2247
                val x2249 = x2247 >= x5
                var x2264: Boolean = false
                var x2263: Char = ' '
                var x2265: Int = 0
                if (x2249) {
                  x2264 = true
                  x2263 = ' '
                  x2265 = x2247
                } else {
                  val x2251 = x0(x2247)
                  val x2252 = x2251 >= '0'
                  val x2253 = x2251 <= '9'
                  val x2254 = x2252 && x2253
                  var x2259: Boolean = false
                  var x2258: Char = ' '
                  var x2260: Int = 0
                  if (x2254) {
                    val x2255 = x2247 + 1
                    x2259 = false
                    x2258 = x2251
                    x2260 = x2255
                  } else {
                    x2259 = true
                    x2258 = ' '
                    x2260 = x2247
                  }
                  x2264 = x2259
                  x2263 = x2258
                  x2265 = x2260
                }
                val x2280 = if (x2264) {
                  x2234 = false
                  ()
                } else {
                  val x2269 = x2237
                  val x2270 = x2238
                  val x2271 = x2239
                  val x2273 = x2269 + x2263
                  x2237 = x2273
                  x2238 = false
                  x2239 = x2265
                  x2235 = x2265
                  ()
                }
                x2280
              }
              val x2284 = x2238
              var x2291: Boolean = false
              var x2292: Int = 0
              var x2290: Tuple2CharString = null
              if (x2284) {
                x2291 = true
                x2292 = x2212
                x2290 = null
              } else {
                val x2285 = x2239
                val x2283 = x2237
                val x2287 = new Tuple2CharString(x2228, x2283)
                x2291 = false
                x2292 = x2285
                x2290 = x2287
              }
              x2298 = x2291
              x2299 = x2292
              x2297 = x2290
            }
            var x2308: Boolean = false
            var x2307: java.lang.String = null
            if (x2298) {
              x2308 = true
              x2307 = null
            } else {
              val x2302 = x2297._1
              val x2303 = x2297._2
              val x2304 = x2302 + x2303
              x2308 = false
              x2307 = x2304
            }
            x2312 = x2308
            x2313 = x2299
            x2311 = x2307
          }
          var x2320: Int = 0
          var x2319: Boolean = false
          var x2318: Tuple2StringString = null
          if (x2312) {
            x2320 = x2051
            x2319 = true
            x2318 = null
          } else {
            val x2315 = new Tuple2StringString(x2194, x2311)
            x2320 = x2313
            x2319 = false
            x2318 = x2315
          }
          x2325 = x2320
          x2324 = x2319
          x2323 = x2318
        }
        var x2336: Boolean = false
        var x2335: Double = 0.0
        if (x2324) {
          x2336 = true
          x2335 = 0.0
        } else {
          val x2329 = x2323._2
          val x2328 = x2323._1
          val x2330 = x2328 + "."
          val x2331 = x2330 + x2329
          val x2332 = x2331.toDouble
          x2336 = false
          x2335 = x2332
        }
        var x2342: Anon6507737 = null
        var x2343: Boolean = false
        if (x2336) {
          x2342 = null
          x2343 = true
        } else {
          val x2339 = new Anon6507737(4, x2335)
          x2342 = x2339
          x2343 = false
        }
        val x2344 = new ParseResultAnon6507737(x2342, x2343, x2325)
        x2344
      } else {
        x2055
      }
      x2345: ParseResultAnon6507737
    }
    val x2350 = { x2348: (Int) =>
      val x2349 = x2346(x2348)
      x2349: ParseResultAnon6507737
    }
    var x2402: Int = -1
    var x2403: Boolean = true
    var x2407: Boolean = false
    val x2504 = { x2347: (Int) =>
      val x2351 = x2350(x2347)
      val x2352 = x2351.empty
      val x2503 = if (x2352) {
        val x2353 = x2347 >= x5
        var x2367: Int = 0
        var x2366: Boolean = false
        var x2365: Char = ' '
        if (x2353) {
          x2367 = x2347
          x2366 = true
          x2365 = ' '
        } else {
          val x2355 = x0(x2347)
          val x2356 = x2355 == '-'
          var x2362: Int = 0
          var x2361: Boolean = false
          var x2360: Char = ' '
          if (x2356) {
            val x2357 = x2347 + 1
            x2362 = x2357
            x2361 = false
            x2360 = x2355
          } else {
            x2362 = x2347
            x2361 = true
            x2360 = ' '
          }
          x2367 = x2362
          x2366 = x2361
          x2365 = x2360
        }
        val x2375 = x2367 >= x5
        var x2390: Boolean = false
        var x2391: Int = 0
        var x2389: Char = ' '
        if (x2375) {
          x2390 = true
          x2391 = x2367
          x2389 = ' '
        } else {
          val x2377 = x0(x2367)
          val x2378 = x2377 >= '0'
          val x2379 = x2377 <= '9'
          val x2380 = x2378 && x2379
          var x2385: Boolean = false
          var x2386: Int = 0
          var x2384: Char = ' '
          if (x2380) {
            val x2381 = x2367 + 1
            x2385 = false
            x2386 = x2381
            x2384 = x2377
          } else {
            x2385 = true
            x2386 = x2367
            x2384 = ' '
          }
          x2390 = x2385
          x2391 = x2386
          x2389 = x2384
        }
        var x2399: Boolean = false
        var x2398: Int = 0
        if (x2390) {
          x2399 = true
          x2398 = 0
        } else {
          val x2394 = x2389 - '0'
          val x2395 = x2394.toInt
          x2399 = false
          x2398 = x2395
        }
        var x2474: Boolean = false
        var x2475: Int = 0
        var x2473: Int = 0
        if (x2399) {
          x2474 = true
          x2475 = x2367
          x2473 = 0
        } else {
          var x2406: Int = x2398
          var x2404: Int = x2391
          var x2408: Int = x2391
          val x2460 = while ({
            val x2410 = x2403
            val x2411 = x2402
            val x2412 = x2404
            val x2413 = x2411 != x2412
            val x2414 = x2410 && x2413
            x2414
          }) {
            val x2416 = x2404
            x2402 = x2416
            val x2418 = x2416 >= x5
            var x2433: Boolean = false
            var x2432: Char = ' '
            var x2434: Int = 0
            if (x2418) {
              x2433 = true
              x2432 = ' '
              x2434 = x2416
            } else {
              val x2420 = x0(x2416)
              val x2421 = x2420 >= '0'
              val x2422 = x2420 <= '9'
              val x2423 = x2421 && x2422
              var x2428: Boolean = false
              var x2427: Char = ' '
              var x2429: Int = 0
              if (x2423) {
                val x2424 = x2416 + 1
                x2428 = false
                x2427 = x2420
                x2429 = x2424
              } else {
                x2428 = true
                x2427 = ' '
                x2429 = x2416
              }
              x2433 = x2428
              x2432 = x2427
              x2434 = x2429
            }
            var x2442: Boolean = false
            var x2441: Int = 0
            if (x2433) {
              x2442 = true
              x2441 = 0
            } else {
              val x2437 = x2432 - '0'
              val x2438 = x2437.toInt
              x2442 = false
              x2441 = x2438
            }
            val x2458 = if (x2442) {
              x2403 = false
              ()
            } else {
              val x2446 = x2406
              val x2447 = x2407
              val x2448 = x2408
              val x2450 = x2446 * 10
              val x2451 = x2450 + x2441
              x2406 = x2451
              x2407 = false
              x2408 = x2434
              x2404 = x2434
              ()
            }
            x2458
          }
          val x2462 = x2407
          var x2467: Boolean = false
          var x2468: Int = 0
          var x2466: Int = 0
          if (x2462) {
            x2467 = true
            x2468 = x2367
            x2466 = 0
          } else {
            val x2461 = x2406
            val x2463 = x2408
            x2467 = x2462
            x2468 = x2463
            x2466 = x2461
          }
          x2474 = x2467
          x2475 = x2468
          x2473 = x2466
        }
        var x2483: Int = 0
        var x2482: Boolean = false
        var x2481: Tuple2OptionCharInt = null
        if (x2474) {
          x2483 = x2347
          x2482 = true
          x2481 = null
        } else {
          val x2373 = if (x2366) {
            x2074
          } else {
            val x2370 = new OptionChar(x2365, true)
            x2370
          }
          val x2478 = new Tuple2OptionCharInt(x2373, x2473)
          x2483 = x2475
          x2482 = false
          x2481 = x2478
        }
        var x2494: Boolean = false
        var x2493: Int = 0
        if (x2482) {
          x2494 = true
          x2493 = 0
        } else {
          val x2486 = x2481._1
          val x2488 = x2486.defined
          val x2490 = if (x2488) {
            val x2487 = x2481._2
            val x2489 = -1 * x2487
            x2489
          } else {
            val x2487 = x2481._2
            x2487
          }
          x2494 = false
          x2493 = x2490
        }
        var x2500: Anon6507737 = null
        var x2501: Boolean = false
        if (x2494) {
          x2500 = null
          x2501 = true
        } else {
          val x2497 = new Anon6507737(3, x2493)
          x2500 = x2497
          x2501 = false
        }
        val x2502 = new ParseResultAnon6507737(x2500, x2501, x2483)
        x2502
      } else {
        x2351
      }
      x2503: ParseResultAnon6507737
    }
    val x2508 = { x2506: (Int) =>
      val x2507 = x2504(x2506)
      x2507: ParseResultAnon6507737
    }
    var x2534: Boolean = false
    var x2529: Int = -1
    var x2530: Boolean = true
    var x2533: java.lang.String = ""
    val x2578 = { x2546: (Int) =>
      val x2547 = x2546 >= x5
      var x2560: Boolean = false
      var x2561: Int = 0
      if (x2547) {
        x2560 = true
        x2561 = x2546
      } else {
        val x2549 = x0(x2546)
        val x2550 = x2549 == '\\'
        var x2555: Boolean = false
        var x2556: Int = 0
        if (x2550) {
          val x2551 = x2546 + 1
          x2555 = false
          x2556 = x2551
        } else {
          x2555 = true
          x2556 = x2546
        }
        x2560 = x2555
        x2561 = x2556
      }
      var x2574: Char = ' '
      var x2575: Boolean = false
      var x2576: Int = 0
      if (x2560) {
        x2574 = ' '
        x2575 = true
        x2576 = x2546
      } else {
        val x2563 = x2561 >= x5
        var x2569: Char = ' '
        var x2570: Boolean = false
        var x2571: Int = 0
        if (x2563) {
          x2569 = ' '
          x2570 = true
          x2571 = x2561
        } else {
          val x2565 = x0(x2561)
          val x2566 = x2561 + 1
          x2569 = x2565
          x2570 = false
          x2571 = x2566
        }
        x2574 = x2569
        x2575 = x2570
        x2576 = x2571
      }
      val x2577 = new ParseResultChar(x2574, x2575, x2576)
      x2577: ParseResultChar
    }
    val x2599 = { x2545: (Int) =>
      val x2579 = x2578(x2545)
      val x2580 = x2579.empty
      val x2598 = if (x2580) {
        val x2581 = x2545 >= x5
        var x2594: Char = ' '
        var x2595: Boolean = false
        var x2596: Int = 0
        if (x2581) {
          x2594 = ' '
          x2595 = true
          x2596 = x2545
        } else {
          val x2583 = x0(x2545)
          val x2585 = x2583 == '"'
          var x2589: Char = ' '
          var x2590: Boolean = false
          var x2591: Int = 0
          if (x2585) {
            x2589 = ' '
            x2590 = true
            x2591 = x2545
          } else {
            val x2586 = x2545 + 1
            x2589 = x2583
            x2590 = false
            x2591 = x2586
          }
          x2594 = x2589
          x2595 = x2590
          x2596 = x2591
        }
        val x2597 = new ParseResultChar(x2594, x2595, x2596)
        x2597
      } else {
        x2579
      }
      x2598: ParseResultChar
    }
    var x2811: java.lang.String = ""
    var x2645: Int = 0
    val x2646 = x2645
    var x2647: Int = x2646
    var x2812: Int = 0
    val x2876 = { x2505: (Int) =>
      val x2509 = x2508(x2505)
      val x2510 = x2509.empty
      val x2875 = if (x2510) {
        val x2511 = x2505 >= x5
        var x2524: Boolean = false
        var x2525: Int = 0
        if (x2511) {
          x2524 = true
          x2525 = x2505
        } else {
          val x2513 = x0(x2505)
          val x2514 = x2513 == '"'
          var x2519: Boolean = false
          var x2520: Int = 0
          if (x2514) {
            val x2515 = x2505 + 1
            x2519 = false
            x2520 = x2515
          } else {
            x2519 = true
            x2520 = x2505
          }
          x2524 = x2519
          x2525 = x2520
        }
        var x2838: Boolean = false
        var x2839: Int = 0
        var x2837: java.lang.String = null
        if (x2524) {
          x2838 = true
          x2839 = x2505
          x2837 = null
        } else {
          var x2531: Int = x2525
          var x2535: Int = x2525
          val x2618 = while ({
            val x2537 = x2530
            val x2538 = x2529
            val x2539 = x2531
            val x2540 = x2538 != x2539
            val x2541 = x2537 && x2540
            x2541
          }) {
            val x2543 = x2531
            x2529 = x2543
            val x2600 = x2599(x2543)
            val x2601 = x2600.empty
            val x2616 = if (x2601) {
              x2530 = false
              ()
            } else {
              val x2604 = x2533
              val x2605 = x2534
              val x2606 = x2535
              x2533 = x2604
              x2534 = false
              val x2609 = x2600.next
              x2535 = x2609
              x2531 = x2609
              ()
            }
            x2616
          }
          val x2620 = x2534
          var x2627: Boolean = false
          var x2626: Int = 0
          var x2628: Int = 0
          if (x2620) {
            x2627 = true
            x2626 = 0
            x2628 = x2525
          } else {
            val x2621 = x2535
            x2627 = false
            x2626 = x2525
            x2628 = x2621
          }
          var x2636: Boolean = false
          var x2635: Tuple2IntInt = null
          var x2637: Int = 0
          if (x2627) {
            x2636 = true
            x2635 = null
            x2637 = x2525
          } else {
            val x2632 = new Tuple2IntInt(x2626, x2628)
            x2636 = false
            x2635 = x2632
            x2637 = x2628
          }
          var x2832: Boolean = false
          var x2831: java.lang.String = null
          if (x2636) {
            x2832 = true
            x2831 = null
          } else {
            val x2640 = x2635._1
            val x2641 = x2635._2
            val x2648 = x2641 - x2640
            val x2649 = new Array[Char](x2648)
            var x2642: Int = x2640
            val x2643 = x2642
            var x2644: Int = x2643
            val x2808 = while ({
              val x2650 = x2644
              val x2651 = x2650 < x2641
              x2651
            }) {
              val x2653 = x2644
              val x2654 = x0(x2653)
              val x2655 = x2654 == '\\'
              val x2656 = x2653 + 1
              val x2657 = x2656 < x2641
              val x2658 = x2655 && x2657
              val x2805 = if (x2658) {
                val x2659 = x0(x2656)
                val x2660 = x2659 == 'u'
                val x2661 = x2653 + 5
                val x2662 = x2661 < x2641
                val x2663 = x2660 && x2662
                val x2798 = if (x2663) {
                  val x2749 = x2647
                  val x2664 = x2653 + 2
                  val x2665 = x0(x2664)
                  val x2666 = x2665 >= '0'
                  val x2667 = x2665 < '9'
                  val x2668 = x2666 && x2667
                  val x2683 = if (x2668) {
                    val x2669 = x2665 - '0'
                    val x2670 = x2669.toInt
                    x2670
                  } else {
                    val x2671 = x2665 >= 'A'
                    val x2672 = x2665 < 'F'
                    val x2673 = x2671 && x2672
                    val x2682 = if (x2673) {
                      val x2674 = x2665 - '7'
                      val x2675 = x2674.toInt
                      x2675
                    } else {
                      val x2676 = x2665 >= 'a'
                      val x2677 = x2665 < 'f'
                      val x2678 = x2676 && x2677
                      val x2681 = if (x2678) {
                        val x2679 = x2665 - 'W'
                        val x2680 = x2679.toInt
                        x2680
                      } else {
                        0
                      }
                      x2681
                    }
                    x2682
                  }
                  val x2684 = x2683 * 4096
                  val x2685 = x2653 + 3
                  val x2686 = x0(x2685)
                  val x2687 = x2686 >= '0'
                  val x2688 = x2686 < '9'
                  val x2689 = x2687 && x2688
                  val x2704 = if (x2689) {
                    val x2690 = x2686 - '0'
                    val x2691 = x2690.toInt
                    x2691
                  } else {
                    val x2692 = x2686 >= 'A'
                    val x2693 = x2686 < 'F'
                    val x2694 = x2692 && x2693
                    val x2703 = if (x2694) {
                      val x2695 = x2686 - '7'
                      val x2696 = x2695.toInt
                      x2696
                    } else {
                      val x2697 = x2686 >= 'a'
                      val x2698 = x2686 < 'f'
                      val x2699 = x2697 && x2698
                      val x2702 = if (x2699) {
                        val x2700 = x2686 - 'W'
                        val x2701 = x2700.toInt
                        x2701
                      } else {
                        0
                      }
                      x2702
                    }
                    x2703
                  }
                  val x2705 = x2704 * 256
                  val x2706 = x2684 + x2705
                  val x2707 = x2653 + 4
                  val x2708 = x0(x2707)
                  val x2709 = x2708 >= '0'
                  val x2710 = x2708 < '9'
                  val x2711 = x2709 && x2710
                  val x2726 = if (x2711) {
                    val x2712 = x2708 - '0'
                    val x2713 = x2712.toInt
                    x2713
                  } else {
                    val x2714 = x2708 >= 'A'
                    val x2715 = x2708 < 'F'
                    val x2716 = x2714 && x2715
                    val x2725 = if (x2716) {
                      val x2717 = x2708 - '7'
                      val x2718 = x2717.toInt
                      x2718
                    } else {
                      val x2719 = x2708 >= 'a'
                      val x2720 = x2708 < 'f'
                      val x2721 = x2719 && x2720
                      val x2724 = if (x2721) {
                        val x2722 = x2708 - 'W'
                        val x2723 = x2722.toInt
                        x2723
                      } else {
                        0
                      }
                      x2724
                    }
                    x2725
                  }
                  val x2727 = x2726 * 16
                  val x2728 = x2706 + x2727
                  val x2729 = x0(x2661)
                  val x2730 = x2729 >= '0'
                  val x2731 = x2729 < '9'
                  val x2732 = x2730 && x2731
                  val x2747 = if (x2732) {
                    val x2733 = x2729 - '0'
                    val x2734 = x2733.toInt
                    x2734
                  } else {
                    val x2735 = x2729 >= 'A'
                    val x2736 = x2729 < 'F'
                    val x2737 = x2735 && x2736
                    val x2746 = if (x2737) {
                      val x2738 = x2729 - '7'
                      val x2739 = x2738.toInt
                      x2739
                    } else {
                      val x2740 = x2729 >= 'a'
                      val x2741 = x2729 < 'f'
                      val x2742 = x2740 && x2741
                      val x2745 = if (x2742) {
                        val x2743 = x2729 - 'W'
                        val x2744 = x2743.toInt
                        x2744
                      } else {
                        0
                      }
                      x2745
                    }
                    x2746
                  }
                  val x2748 = x2728 + x2747
                  val x2750 = x2748.toChar
                  val x2751 = x2649(x2749) = x2750
                  val x2752 = x2644 += 4
                  ()
                } else {
                  val x2754 = x2659 == 'b'
                  val x2795 = if (x2754) {
                    val x2755 = x2647
                    val x2756 = x2649(x2755) = ''
                    x2756
                  } else {
                    val x2758 = x2659 == 'f'
                    val x2791 = if (x2758) {
                      val x2759 = x2647
                      val x2760 = x2649(x2759) = ''
                      x2760
                    } else {
                      val x2762 = x2659 == 't'
                      val x2787 = if (x2762) {
                        val x2763 = x2647
                        val x2764 = x2649(x2763) = '	'
                        x2764
                      } else {
                        val x2766 = x2659 == 'n'
                        val x2783 = if (x2766) {
                          val x2767 = x2647
                          val x2768 = x2649(x2767) = '\n'
                          x2768
                        } else {
                          val x2770 = x2659 == 'r'
                          val x2779 = if (x2770) {
                            val x2771 = x2647
                            val x2772 = x2649(x2771) = '
'
                            x2772
                          } else {
                            val x2774 = x2647
                            val x2775 = x2649(x2774) = x2659
                            x2775
                          }
                          x2779
                        }
                        x2783
                      }
                      x2787
                    }
                    x2791
                  }
                  x2795
                }
                val x2799 = x2644 += 2
                ()
              } else {
                val x2801 = x2647
                val x2802 = x2649(x2801) = x2654
                val x2803 = x2644 += 1
                ()
              }
              val x2806 = x2647 += 1
              ()
            }
            val x2809 = x2647
            val x2825 = while ({
              val x2813 = x2812
              val x2814 = x2813 < x2809
              x2814
            }) {
              val x2816 = x2811
              val x2817 = x2812
              val x2818 = 0 + x2817
              val x2819 = x2649(x2818)
              val x2820 = x2816 + x2819
              x2811 = x2820
              val x2822 = x2817 + 1
              x2812 = x2822
              ()
            }
            val x2826 = x2811
            x2832 = false
            x2831 = x2826
          }
          x2838 = x2832
          x2839 = x2637
          x2837 = x2831
        }
        var x2866: Int = 0
        var x2865: Boolean = false
        var x2864: java.lang.String = null
        if (x2838) {
          x2866 = x2839
          x2865 = x2838
          x2864 = x2837
        } else {
          val x2841 = x2839 >= x5
          var x2854: Boolean = false
          var x2855: Int = 0
          if (x2841) {
            x2854 = true
            x2855 = x2839
          } else {
            val x2843 = x0(x2839)
            val x2844 = x2843 == '"'
            var x2849: Boolean = false
            var x2850: Int = 0
            if (x2844) {
              val x2845 = x2839 + 1
              x2849 = false
              x2850 = x2845
            } else {
              x2849 = true
              x2850 = x2839
            }
            x2854 = x2849
            x2855 = x2850
          }
          var x2861: Int = 0
          var x2860: Boolean = false
          var x2859: java.lang.String = null
          if (x2854) {
            x2861 = x2505
            x2860 = true
            x2859 = null
          } else {
            x2861 = x2855
            x2860 = false
            x2859 = x2837
          }
          x2866 = x2861
          x2865 = x2860
          x2864 = x2859
        }
        var x2872: Anon6507737 = null
        var x2873: Boolean = false
        if (x2865) {
          x2872 = null
          x2873 = true
        } else {
          val x2869 = new Anon6507737(5, x2864)
          x2872 = x2869
          x2873 = false
        }
        val x2874 = new ParseResultAnon6507737(x2872, x2873, x2866)
        x2874
      } else {
        x2509
      }
      x2875: ParseResultAnon6507737
    }
    x2887 = { x2877: (Int) =>
      val x2882 = x2881(x2877)
      val x2883 = x2882.empty
      val x2885 = if (x2883) {
        val x2884 = x2876(x2877)
        x2884
      } else {
        x2882
      }
      x2885: ParseResultAnon6507737
    }
    val x2890 = x1(0)
    x2890
  }
}
/**
 * ***************************************
 * End of Generated Code
 * *****************************************
 */