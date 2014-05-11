package lms

import java.io.{ BufferedReader, FileReader, Serializable }
import scala.collection.mutable.ArrayBuffer

object LMSJsonParser extends JsonParse(
  "false".length, "false".toArray,
  "true".length, "true".toArray,
  "null".toArray
)

/**
 * ***************************************
 * Emitting Generated Code
 * *****************************************
 */
class JsonParse(px1820: Int, px1821: Array[Char], px1823: Int, px1824: Array[Char], px1942: Array[Char]) extends ((Array[Char]) => ParseResultAnon6507737) {
  def apply(x0: Array[Char]): ParseResultAnon6507737 = {
    /*var x6: scala.Function1[Int, ParseResultAnon6507737] = null
    var x3257: scala.Function1[Int, ParseResultAnon6507737] = null
    var x3234: scala.Function1[Int, ParseResultAnon6507737] = null
    var x1819: scala.Function1[Int, ParseResultAnon6507737] = null
    var x1407: scala.Function1[Int, ParseResultAnon6507737] = null
    var x1302: scala.Function1[Int, ParseResultListAnon6507737] = null
    var x1282: scala.Function1[Int, ParseResultListAnon6507737] = null
    var x1705: scala.Function1[Int, ParseResultListAnon6507737] = null
    var x1685: scala.Function1[Int, ParseResultListAnon6507737] = null
    x6 = { x7: (Int) =>
      var x9: Anon6507737 = null
      var x10: Boolean = true
      var x11: Int = x7
      val x3258 = x3257(x7)
      val x3259 = x3258.res
      x9 = x3259
      val x3261 = x3258.empty
      x10 = x3261
      val x3263 = x3258.next
      x11 = x3263
      val x3265 = x9
      val x3266 = x10
      val x3267 = x11
      val x3268 = new ParseResultAnon6507737(x3265, x3266, x3267)
      x3268: ParseResultAnon6507737
    }
    val x25 = x0.length
    val x181 = { x139: (Int) =>
      var x141: Char = ' '
      var x142: Boolean = true
      var x143: Int = x139
      val x145 = x139 >= x25
      val x175 = if (x145) {
        x141 = ' '
        x142 = true
        x143 = x139
        ()
      } else {
        val x150 = x0(x139)
        val x151 = x150 == '\\'
        val x173 = if (x151) {
          val x152 = x139 + 1
          val x154 = x152 >= x25
          val x167 = if (x154) {
            x141 = ' '
            x142 = true
            x143 = x152
            ()
          } else {
            val x160 = x0(x152)
            x141 = x160
            x142 = false
            val x161 = x152 + 1
            x143 = x161
            ()
          }
          x167
        } else {
          x141 = ' '
          x142 = true
          x143 = x139
          ()
        }
        x173
      }
      val x176 = x141
      val x177 = x142
      val x178 = x143
      val x179 = new ParseResultChar(x176, x177, x178)
      x179: ParseResultChar
    }
    val x218 = { x133: (Int) =>
      var x135: Char = ' '
      var x136: Boolean = true
      var x137: Int = x133
      val x182 = x181(x133)
      val x183 = x182.empty
      val x212 = if (x183) {
        val x184 = x133 >= x25
        val x204 = if (x184) {
          x135 = ' '
          x136 = true
          x137 = x133
          ()
        } else {
          val x189 = x0(x133)
          val x191 = x189 == '"'
          val x202 = if (x191) {
            x135 = ' '
            x136 = true
            x137 = x133
            ()
          } else {
            x135 = x189
            x136 = false
            val x196 = x133 + 1
            x137 = x196
            ()
          }
          x202
        }
        x204
      } else {
        val x206 = x182.res
        x135 = x206
        x136 = x183
        val x209 = x182.next
        x137 = x209
        ()
      }
      val x213 = x135
      val x214 = x136
      val x215 = x137
      val x216 = new ParseResultChar(x213, x214, x215)
      x216: ParseResultChar
    }
    val x89 = List()
    val x780 = { x738: (Int) =>
      var x740: Char = ' '
      var x741: Boolean = true
      var x742: Int = x738
      val x744 = x738 >= x25
      val x774 = if (x744) {
        x740 = ' '
        x741 = true
        x742 = x738
        ()
      } else {
        val x749 = x0(x738)
        val x750 = x749 == '\\'
        val x772 = if (x750) {
          val x751 = x738 + 1
          val x753 = x751 >= x25
          val x766 = if (x753) {
            x740 = ' '
            x741 = true
            x742 = x751
            ()
          } else {
            val x759 = x0(x751)
            x740 = x759
            x741 = false
            val x760 = x751 + 1
            x742 = x760
            ()
          }
          x766
        } else {
          x740 = ' '
          x741 = true
          x742 = x738
          ()
        }
        x772
      }
      val x775 = x740
      val x776 = x741
      val x777 = x742
      val x778 = new ParseResultChar(x775, x776, x777)
      x778: ParseResultChar
    }
    val x817 = { x732: (Int) =>
      var x734: Char = ' '
      var x735: Boolean = true
      var x736: Int = x732
      val x781 = x780(x732)
      val x782 = x781.empty
      val x811 = if (x782) {
        val x783 = x732 >= x25
        val x803 = if (x783) {
          x734 = ' '
          x735 = true
          x736 = x732
          ()
        } else {
          val x788 = x0(x732)
          val x790 = x788 == '"'
          val x801 = if (x790) {
            x734 = ' '
            x735 = true
            x736 = x732
            ()
          } else {
            x734 = x788
            x735 = false
            val x795 = x732 + 1
            x736 = x795
            ()
          }
          x801
        }
        x803
      } else {
        val x805 = x781.res
        x734 = x805
        x735 = x782
        val x808 = x781.next
        x736 = x808
        ()
      }
      val x812 = x734
      val x813 = x735
      val x814 = x736
      val x815 = new ParseResultChar(x812, x813, x814)
      x815: ParseResultChar
    }
    x1282 = { x96: (Int) =>
      var x98: scala.collection.immutable.List[Anon6507737] = null
      var x99: Boolean = true
      var x100: Int = x96
      val x102 = x96 >= x25
      val x1276 = if (x102) {
        x98 = null
        x99 = true
        x100 = x96
        ()
      } else {
        val x112 = x0(x96)
        val x113 = x112 == '"'
        val x1274 = if (x113) {
          var x118: java.lang.String = ""
          var x119: Boolean = false
          val x114 = x96 + 1
          var x120: Int = x114
          var x122: Int = -1
          var x123: Boolean = true
          var x124: Int = x114
          val x237 = while ({
            val x125 = x123
            val x126 = x122
            val x127 = x124
            val x128 = x126 != x127
            val x129 = x125 && x128
            x129
          }) {
            val x131 = x124
            x122 = x131
            val x219 = x218(x131)
            val x220 = x219.empty
            val x235 = if (x220) {
              x123 = false
              ()
            } else {
              val x223 = x118
              val x224 = x119
              val x225 = x120
              x118 = x223
              x119 = false
              val x228 = x219.next
              x120 = x228
              x124 = x228
              ()
            }
            x235
          }
          val x238 = x118
          val x239 = x119
          val x240 = x120
          val x245 = if (x239) {
            true
          } else {
            false
          }
          val x1268 = if (x245) {
            x98 = null
            x99 = true
            x100 = x96
            ()
          } else {
            val x244 = if (x239) {
              0
            } else {
              x114
            }
            var x257: Int = x244
            val x258 = x257
            var x259: Int = x258
            var x260: Int = 0
            val x261 = x260
            var x262: Int = x261
            val x246 = if (x239) {
              x114
            } else {
              x240
            }
            val x263 = x246 - x244
            val x264 = new Array[Char](x263)
            val x412 = while ({
              val x265 = x259
              val x266 = x265 < x246
              x266
            }) {
              val x268 = x259
              val x269 = x0(x268)
              val x270 = x269 == '\\'
              val x271 = x268 + 1
              val x272 = x271 < x246
              val x273 = x270 && x272
              val x409 = if (x273) {
                val x274 = x0(x271)
                val x275 = x274 == 'u'
                val x276 = x268 + 5
                val x277 = x276 < x246
                val x278 = x275 && x277
                val x402 = if (x278) {
                  val x364 = x262
                  val x279 = x268 + 2
                  val x280 = x0(x279)
                  val x281 = x280 >= '0'
                  val x282 = x280 < '9'
                  val x283 = x281 && x282
                  val x298 = if (x283) {
                    val x284 = x280 - '0'
                    val x285 = x284.toInt
                    x285
                  } else {
                    val x286 = x280 >= 'A'
                    val x287 = x280 < 'F'
                    val x288 = x286 && x287
                    val x297 = if (x288) {
                      val x289 = x280 - '7'
                      val x290 = x289.toInt
                      x290
                    } else {
                      val x291 = x280 >= 'a'
                      val x292 = x280 < 'f'
                      val x293 = x291 && x292
                      val x296 = if (x293) {
                        val x294 = x280 - 'W'
                        val x295 = x294.toInt
                        x295
                      } else {
                        0
                      }
                      x296
                    }
                    x297
                  }
                  val x299 = x298 * 4096
                  val x300 = x268 + 3
                  val x301 = x0(x300)
                  val x302 = x301 >= '0'
                  val x303 = x301 < '9'
                  val x304 = x302 && x303
                  val x319 = if (x304) {
                    val x305 = x301 - '0'
                    val x306 = x305.toInt
                    x306
                  } else {
                    val x307 = x301 >= 'A'
                    val x308 = x301 < 'F'
                    val x309 = x307 && x308
                    val x318 = if (x309) {
                      val x310 = x301 - '7'
                      val x311 = x310.toInt
                      x311
                    } else {
                      val x312 = x301 >= 'a'
                      val x313 = x301 < 'f'
                      val x314 = x312 && x313
                      val x317 = if (x314) {
                        val x315 = x301 - 'W'
                        val x316 = x315.toInt
                        x316
                      } else {
                        0
                      }
                      x317
                    }
                    x318
                  }
                  val x320 = x319 * 256
                  val x321 = x299 + x320
                  val x322 = x268 + 4
                  val x323 = x0(x322)
                  val x324 = x323 >= '0'
                  val x325 = x323 < '9'
                  val x326 = x324 && x325
                  val x341 = if (x326) {
                    val x327 = x323 - '0'
                    val x328 = x327.toInt
                    x328
                  } else {
                    val x329 = x323 >= 'A'
                    val x330 = x323 < 'F'
                    val x331 = x329 && x330
                    val x340 = if (x331) {
                      val x332 = x323 - '7'
                      val x333 = x332.toInt
                      x333
                    } else {
                      val x334 = x323 >= 'a'
                      val x335 = x323 < 'f'
                      val x336 = x334 && x335
                      val x339 = if (x336) {
                        val x337 = x323 - 'W'
                        val x338 = x337.toInt
                        x338
                      } else {
                        0
                      }
                      x339
                    }
                    x340
                  }
                  val x342 = x341 * 16
                  val x343 = x321 + x342
                  val x344 = x0(x276)
                  val x345 = x344 >= '0'
                  val x346 = x344 < '9'
                  val x347 = x345 && x346
                  val x362 = if (x347) {
                    val x348 = x344 - '0'
                    val x349 = x348.toInt
                    x349
                  } else {
                    val x350 = x344 >= 'A'
                    val x351 = x344 < 'F'
                    val x352 = x350 && x351
                    val x361 = if (x352) {
                      val x353 = x344 - '7'
                      val x354 = x353.toInt
                      x354
                    } else {
                      val x355 = x344 >= 'a'
                      val x356 = x344 < 'f'
                      val x357 = x355 && x356
                      val x360 = if (x357) {
                        val x358 = x344 - 'W'
                        val x359 = x358.toInt
                        x359
                      } else {
                        0
                      }
                      x360
                    }
                    x361
                  }
                  val x363 = x343 + x362
                  val x365 = x363.toChar
                  val x366 = x264(x364) = x365
                  val x367 = x259 += 4
                  ()
                } else {
                  val x369 = x274 == 'b'
                  val x400 = if (x369) {
                    val x370 = x262
                    val x371 = x264(x370) = ''
                    x371
                  } else {
                    val x373 = x274 == 'f'
                    val x398 = if (x373) {
                      val x374 = x262
                      val x375 = x264(x374) = ''
                      x375
                    } else {
                      val x377 = x274 == 't'
                      val x396 = if (x377) {
                        val x378 = x262
                        val x379 = x264(x378) = '	'
                        x379
                      } else {
                        val x381 = x274 == 'n'
                        val x394 = if (x381) {
                          val x382 = x262
                          val x383 = x264(x382) = '\n'
                          x383
                        } else {
                          val x385 = x274 == 'r'
                          val x392 = if (x385) {
                            val x386 = x262
                            val x387 = x264(x386) = '
'
                            x387
                          } else {
                            val x389 = x262
                            val x390 = x264(x389) = x274
                            x390
                          }
                          x392
                        }
                        x394
                      }
                      x396
                    }
                    x398
                  }
                  x400
                }
                val x403 = x259 += 2
                ()
              } else {
                val x405 = x262
                val x406 = x264(x405) = x269
                val x407 = x259 += 1
                ()
              }
              val x410 = x262 += 1
              ()
            }
            val x413 = x262
            val x414 = new Anon1680061013(x264, 0, x413)
            val x415 = new String(x414.input, x414.start, x414.length)
            val x417 = x246 >= x25
            val x1266 = if (x417) {
              x98 = null
              x99 = true
              x100 = x96
              ()
            } else {
              val x423 = x0(x246)
              val x424 = x423 == '"'
              val x1264 = if (x424) {
                var x429: java.lang.String = ""
                var x430: Boolean = false
                val x425 = x246 + 1
                var x431: Int = x425
                var x433: Int = -1
                var x434: Boolean = true
                var x435: Int = x425
                val x470 = while ({
                  val x436 = x434
                  val x437 = x433
                  val x438 = x435
                  val x439 = x437 != x438
                  val x440 = x436 && x439
                  x440
                }) {
                  val x442 = x435
                  x433 = x442
                  val x444 = x442 >= x25
                  val x468 = if (x444) {
                    x434 = false
                    ()
                  } else {
                    val x448 = x0(x442)
                    val x449 = x448 == ' '
                    val x450 = x448 == '\n'
                    val x451 = x449 || x450
                    val x466 = if (x451) {
                      val x454 = x429
                      val x455 = x430
                      val x456 = x431
                      x429 = x454
                      x430 = false
                      val x452 = x442 + 1
                      x431 = x452
                      x435 = x452
                      ()
                    } else {
                      x434 = false
                      ()
                    }
                    x466
                  }
                  x468
                }
                val x471 = x429
                val x472 = x430
                val x473 = x431
                val x1258 = if (x472) {
                  x98 = null
                  x99 = true
                  x100 = x96
                  ()
                } else {
                  val x479 = x473 >= x25
                  val x1256 = if (x479) {
                    x98 = null
                    x99 = true
                    x100 = x96
                    ()
                  } else {
                    val x487 = x0(x473)
                    val x488 = x487 == ':'
                    val x1254 = if (x488) {
                      var x492: java.lang.String = ""
                      var x493: Boolean = false
                      val x489 = x473 + 1
                      var x494: Int = x489
                      var x496: Int = -1
                      var x497: Boolean = true
                      var x498: Int = x489
                      val x533 = while ({
                        val x499 = x497
                        val x500 = x496
                        val x501 = x498
                        val x502 = x500 != x501
                        val x503 = x499 && x502
                        x503
                      }) {
                        val x505 = x498
                        x496 = x505
                        val x507 = x505 >= x25
                        val x531 = if (x507) {
                          x497 = false
                          ()
                        } else {
                          val x511 = x0(x505)
                          val x512 = x511 == ' '
                          val x513 = x511 == '\n'
                          val x514 = x512 || x513
                          val x529 = if (x514) {
                            val x517 = x492
                            val x518 = x493
                            val x519 = x494
                            x492 = x517
                            x493 = false
                            val x515 = x505 + 1
                            x494 = x515
                            x498 = x515
                            ()
                          } else {
                            x497 = false
                            ()
                          }
                          x529
                        }
                        x531
                      }
                      val x534 = x492
                      val x535 = x493
                      val x536 = x494
                      val x541 = if (x535) {
                        true
                      } else {
                        false
                      }
                      val x546 = if (x541) {
                        true
                      } else {
                        false
                      }
                      val x551 = if (x546) {
                        true
                      } else {
                        false
                      }
                      val x1248 = if (x551) {
                        x98 = null
                        x99 = true
                        x100 = x96
                        ()
                      } else {
                        val x552 = if (x546) {
                          x96
                        } else {
                          val x547 = if (x541) {
                            x425
                          } else {
                            val x542 = if (x535) {
                              x473
                            } else {
                              x536
                            }
                            x542
                          }
                          x547
                        }
                        val x558 = x6(x552)
                        val x559 = x558.empty
                        val x565 = if (x559) {
                          true
                        } else {
                          false
                        }
                        val x572 = if (x565) {
                          true
                        } else {
                          false
                        }
                        val x1246 = if (x572) {
                          x98 = null
                          x99 = true
                          x100 = x96
                          ()
                        } else {
                          var x580: scala.collection.immutable.List[Anon6507737] = x89
                          var x581: Boolean = false
                          val x566 = if (x559) {
                            x96
                          } else {
                            val x562 = x558.next
                            x562
                          }
                          val x573 = if (x565) {
                            x566
                          } else {
                            x566
                          }
                          var x582: Int = x573
                          var x584: Int = -1
                          var x585: Boolean = true
                          var x586: Int = x573
                          val x1213 = while ({
                            val x587 = x585
                            val x588 = x584
                            val x589 = x586
                            val x590 = x588 != x589
                            val x591 = x587 && x590
                            x591
                          }) {
                            val x593 = x586
                            x584 = x593
                            var x596: java.lang.String = ""
                            var x597: Boolean = false
                            var x598: Int = x593
                            var x600: Int = -1
                            var x601: Boolean = true
                            var x602: Int = x593
                            val x637 = while ({
                              val x603 = x601
                              val x604 = x600
                              val x605 = x602
                              val x606 = x604 != x605
                              val x607 = x603 && x606
                              x607
                            }) {
                              val x609 = x602
                              x600 = x609
                              val x611 = x609 >= x25
                              val x635 = if (x611) {
                                x601 = false
                                ()
                              } else {
                                val x615 = x0(x609)
                                val x616 = x615 == ' '
                                val x617 = x615 == '\n'
                                val x618 = x616 || x617
                                val x633 = if (x618) {
                                  val x621 = x596
                                  val x622 = x597
                                  val x623 = x598
                                  x596 = x621
                                  x597 = false
                                  val x619 = x609 + 1
                                  x598 = x619
                                  x602 = x619
                                  ()
                                } else {
                                  x601 = false
                                  ()
                                }
                                x633
                              }
                              x635
                            }
                            val x638 = x596
                            val x639 = x597
                            val x640 = x598
                            val x1211 = if (x639) {
                              x585 = false
                              ()
                            } else {
                              val x647 = x640 >= x25
                              val x1209 = if (x647) {
                                x585 = false
                                ()
                              } else {
                                val x651 = x0(x640)
                                val x652 = x651 == ','
                                val x1207 = if (x652) {
                                  var x656: java.lang.String = ""
                                  var x657: Boolean = false
                                  val x653 = x640 + 1
                                  var x658: Int = x653
                                  var x660: Int = -1
                                  var x661: Boolean = true
                                  var x662: Int = x653
                                  val x697 = while ({
                                    val x663 = x661
                                    val x664 = x660
                                    val x665 = x662
                                    val x666 = x664 != x665
                                    val x667 = x663 && x666
                                    x667
                                  }) {
                                    val x669 = x662
                                    x660 = x669
                                    val x671 = x669 >= x25
                                    val x695 = if (x671) {
                                      x661 = false
                                      ()
                                    } else {
                                      val x675 = x0(x669)
                                      val x676 = x675 == ' '
                                      val x677 = x675 == '\n'
                                      val x678 = x676 || x677
                                      val x693 = if (x678) {
                                        val x681 = x656
                                        val x682 = x657
                                        val x683 = x658
                                        x656 = x681
                                        x657 = false
                                        val x679 = x669 + 1
                                        x658 = x679
                                        x662 = x679
                                        ()
                                      } else {
                                        x661 = false
                                        ()
                                      }
                                      x693
                                    }
                                    x695
                                  }
                                  val x698 = x656
                                  val x699 = x657
                                  val x700 = x658
                                  val x1203 = if (x699) {
                                    x585 = false
                                    ()
                                  } else {
                                    val x704 = x700 >= x25
                                    val x1201 = if (x704) {
                                      x585 = false
                                      ()
                                    } else {
                                      val x711 = x0(x700)
                                      val x712 = x711 == '"'
                                      val x1199 = if (x712) {
                                        var x717: java.lang.String = ""
                                        var x718: Boolean = false
                                        val x713 = x700 + 1
                                        var x719: Int = x713
                                        var x721: Int = -1
                                        var x722: Boolean = true
                                        var x723: Int = x713
                                        val x836 = while ({
                                          val x724 = x722
                                          val x725 = x721
                                          val x726 = x723
                                          val x727 = x725 != x726
                                          val x728 = x724 && x727
                                          x728
                                        }) {
                                          val x730 = x723
                                          x721 = x730
                                          val x818 = x817(x730)
                                          val x819 = x818.empty
                                          val x834 = if (x819) {
                                            x722 = false
                                            ()
                                          } else {
                                            val x822 = x717
                                            val x823 = x718
                                            val x824 = x719
                                            x717 = x822
                                            x718 = false
                                            val x827 = x818.next
                                            x719 = x827
                                            x723 = x827
                                            ()
                                          }
                                          x834
                                        }
                                        val x837 = x717
                                        val x838 = x718
                                        val x839 = x719
                                        val x844 = if (x838) {
                                          true
                                        } else {
                                          false
                                        }
                                        val x1195 = if (x844) {
                                          x585 = false
                                          ()
                                        } else {
                                          val x843 = if (x838) {
                                            0
                                          } else {
                                            x713
                                          }
                                          var x854: Int = x843
                                          val x855 = x854
                                          var x856: Int = x855
                                          var x857: Int = 0
                                          val x858 = x857
                                          var x859: Int = x858
                                          val x845 = if (x838) {
                                            x713
                                          } else {
                                            x839
                                          }
                                          val x860 = x845 - x843
                                          val x861 = new Array[Char](x860)
                                          val x1009 = while ({
                                            val x862 = x856
                                            val x863 = x862 < x845
                                            x863
                                          }) {
                                            val x865 = x856
                                            val x866 = x0(x865)
                                            val x867 = x866 == '\\'
                                            val x868 = x865 + 1
                                            val x869 = x868 < x845
                                            val x870 = x867 && x869
                                            val x1006 = if (x870) {
                                              val x871 = x0(x868)
                                              val x872 = x871 == 'u'
                                              val x873 = x865 + 5
                                              val x874 = x873 < x845
                                              val x875 = x872 && x874
                                              val x999 = if (x875) {
                                                val x961 = x859
                                                val x876 = x865 + 2
                                                val x877 = x0(x876)
                                                val x878 = x877 >= '0'
                                                val x879 = x877 < '9'
                                                val x880 = x878 && x879
                                                val x895 = if (x880) {
                                                  val x881 = x877 - '0'
                                                  val x882 = x881.toInt
                                                  x882
                                                } else {
                                                  val x883 = x877 >= 'A'
                                                  val x884 = x877 < 'F'
                                                  val x885 = x883 && x884
                                                  val x894 = if (x885) {
                                                    val x886 = x877 - '7'
                                                    val x887 = x886.toInt
                                                    x887
                                                  } else {
                                                    val x888 = x877 >= 'a'
                                                    val x889 = x877 < 'f'
                                                    val x890 = x888 && x889
                                                    val x893 = if (x890) {
                                                      val x891 = x877 - 'W'
                                                      val x892 = x891.toInt
                                                      x892
                                                    } else {
                                                      0
                                                    }
                                                    x893
                                                  }
                                                  x894
                                                }
                                                val x896 = x895 * 4096
                                                val x897 = x865 + 3
                                                val x898 = x0(x897)
                                                val x899 = x898 >= '0'
                                                val x900 = x898 < '9'
                                                val x901 = x899 && x900
                                                val x916 = if (x901) {
                                                  val x902 = x898 - '0'
                                                  val x903 = x902.toInt
                                                  x903
                                                } else {
                                                  val x904 = x898 >= 'A'
                                                  val x905 = x898 < 'F'
                                                  val x906 = x904 && x905
                                                  val x915 = if (x906) {
                                                    val x907 = x898 - '7'
                                                    val x908 = x907.toInt
                                                    x908
                                                  } else {
                                                    val x909 = x898 >= 'a'
                                                    val x910 = x898 < 'f'
                                                    val x911 = x909 && x910
                                                    val x914 = if (x911) {
                                                      val x912 = x898 - 'W'
                                                      val x913 = x912.toInt
                                                      x913
                                                    } else {
                                                      0
                                                    }
                                                    x914
                                                  }
                                                  x915
                                                }
                                                val x917 = x916 * 256
                                                val x918 = x896 + x917
                                                val x919 = x865 + 4
                                                val x920 = x0(x919)
                                                val x921 = x920 >= '0'
                                                val x922 = x920 < '9'
                                                val x923 = x921 && x922
                                                val x938 = if (x923) {
                                                  val x924 = x920 - '0'
                                                  val x925 = x924.toInt
                                                  x925
                                                } else {
                                                  val x926 = x920 >= 'A'
                                                  val x927 = x920 < 'F'
                                                  val x928 = x926 && x927
                                                  val x937 = if (x928) {
                                                    val x929 = x920 - '7'
                                                    val x930 = x929.toInt
                                                    x930
                                                  } else {
                                                    val x931 = x920 >= 'a'
                                                    val x932 = x920 < 'f'
                                                    val x933 = x931 && x932
                                                    val x936 = if (x933) {
                                                      val x934 = x920 - 'W'
                                                      val x935 = x934.toInt
                                                      x935
                                                    } else {
                                                      0
                                                    }
                                                    x936
                                                  }
                                                  x937
                                                }
                                                val x939 = x938 * 16
                                                val x940 = x918 + x939
                                                val x941 = x0(x873)
                                                val x942 = x941 >= '0'
                                                val x943 = x941 < '9'
                                                val x944 = x942 && x943
                                                val x959 = if (x944) {
                                                  val x945 = x941 - '0'
                                                  val x946 = x945.toInt
                                                  x946
                                                } else {
                                                  val x947 = x941 >= 'A'
                                                  val x948 = x941 < 'F'
                                                  val x949 = x947 && x948
                                                  val x958 = if (x949) {
                                                    val x950 = x941 - '7'
                                                    val x951 = x950.toInt
                                                    x951
                                                  } else {
                                                    val x952 = x941 >= 'a'
                                                    val x953 = x941 < 'f'
                                                    val x954 = x952 && x953
                                                    val x957 = if (x954) {
                                                      val x955 = x941 - 'W'
                                                      val x956 = x955.toInt
                                                      x956
                                                    } else {
                                                      0
                                                    }
                                                    x957
                                                  }
                                                  x958
                                                }
                                                val x960 = x940 + x959
                                                val x962 = x960.toChar
                                                val x963 = x861(x961) = x962
                                                val x964 = x856 += 4
                                                ()
                                              } else {
                                                val x966 = x871 == 'b'
                                                val x997 = if (x966) {
                                                  val x967 = x859
                                                  val x968 = x861(x967) = ''
                                                  x968
                                                } else {
                                                  val x970 = x871 == 'f'
                                                  val x995 = if (x970) {
                                                    val x971 = x859
                                                    val x972 = x861(x971) = ''
                                                    x972
                                                  } else {
                                                    val x974 = x871 == 't'
                                                    val x993 = if (x974) {
                                                      val x975 = x859
                                                      val x976 = x861(x975) = '	'
                                                      x976
                                                    } else {
                                                      val x978 = x871 == 'n'
                                                      val x991 = if (x978) {
                                                        val x979 = x859
                                                        val x980 = x861(x979) = '\n'
                                                        x980
                                                      } else {
                                                        val x982 = x871 == 'r'
                                                        val x989 = if (x982) {
                                                          val x983 = x859
                                                          val x984 = x861(x983) = '
'
                                                          x984
                                                        } else {
                                                          val x986 = x859
                                                          val x987 = x861(x986) = x871
                                                          x987
                                                        }
                                                        x989
                                                      }
                                                      x991
                                                    }
                                                    x993
                                                  }
                                                  x995
                                                }
                                                x997
                                              }
                                              val x1000 = x856 += 2
                                              ()
                                            } else {
                                              val x1002 = x859
                                              val x1003 = x861(x1002) = x866
                                              val x1004 = x856 += 1
                                              ()
                                            }
                                            val x1007 = x859 += 1
                                            ()
                                          }
                                          val x1010 = x859
                                          val x1011 = new Anon1680061013(x861, 0, x1010)
                                          val x1012 = new String(x1011.input, x1011.start, x1011.length)
                                          val x1014 = x845 >= x25
                                          val x1193 = if (x1014) {
                                            x585 = false
                                            ()
                                          } else {
                                            val x1018 = x0(x845)
                                            val x1019 = x1018 == '"'
                                            val x1191 = if (x1019) {
                                              var x1024: java.lang.String = ""
                                              var x1025: Boolean = false
                                              val x1020 = x845 + 1
                                              var x1026: Int = x1020
                                              var x1028: Int = -1
                                              var x1029: Boolean = true
                                              var x1030: Int = x1020
                                              val x1065 = while ({
                                                val x1031 = x1029
                                                val x1032 = x1028
                                                val x1033 = x1030
                                                val x1034 = x1032 != x1033
                                                val x1035 = x1031 && x1034
                                                x1035
                                              }) {
                                                val x1037 = x1030
                                                x1028 = x1037
                                                val x1039 = x1037 >= x25
                                                val x1063 = if (x1039) {
                                                  x1029 = false
                                                  ()
                                                } else {
                                                  val x1043 = x0(x1037)
                                                  val x1044 = x1043 == ' '
                                                  val x1045 = x1043 == '\n'
                                                  val x1046 = x1044 || x1045
                                                  val x1061 = if (x1046) {
                                                    val x1049 = x1024
                                                    val x1050 = x1025
                                                    val x1051 = x1026
                                                    x1024 = x1049
                                                    x1025 = false
                                                    val x1047 = x1037 + 1
                                                    x1026 = x1047
                                                    x1030 = x1047
                                                    ()
                                                  } else {
                                                    x1029 = false
                                                    ()
                                                  }
                                                  x1061
                                                }
                                                x1063
                                              }
                                              val x1066 = x1024
                                              val x1067 = x1025
                                              val x1068 = x1026
                                              val x1187 = if (x1067) {
                                                x585 = false
                                                ()
                                              } else {
                                                val x1072 = x1068 >= x25
                                                val x1185 = if (x1072) {
                                                  x585 = false
                                                  ()
                                                } else {
                                                  val x1078 = x0(x1068)
                                                  val x1079 = x1078 == ':'
                                                  val x1183 = if (x1079) {
                                                    var x1083: java.lang.String = ""
                                                    var x1084: Boolean = false
                                                    val x1080 = x1068 + 1
                                                    var x1085: Int = x1080
                                                    var x1087: Int = -1
                                                    var x1088: Boolean = true
                                                    var x1089: Int = x1080
                                                    val x1124 = while ({
                                                      val x1090 = x1088
                                                      val x1091 = x1087
                                                      val x1092 = x1089
                                                      val x1093 = x1091 != x1092
                                                      val x1094 = x1090 && x1093
                                                      x1094
                                                    }) {
                                                      val x1096 = x1089
                                                      x1087 = x1096
                                                      val x1098 = x1096 >= x25
                                                      val x1122 = if (x1098) {
                                                        x1088 = false
                                                        ()
                                                      } else {
                                                        val x1102 = x0(x1096)
                                                        val x1103 = x1102 == ' '
                                                        val x1104 = x1102 == '\n'
                                                        val x1105 = x1103 || x1104
                                                        val x1120 = if (x1105) {
                                                          val x1108 = x1083
                                                          val x1109 = x1084
                                                          val x1110 = x1085
                                                          x1083 = x1108
                                                          x1084 = false
                                                          val x1106 = x1096 + 1
                                                          x1085 = x1106
                                                          x1089 = x1106
                                                          ()
                                                        } else {
                                                          x1088 = false
                                                          ()
                                                        }
                                                        x1120
                                                      }
                                                      x1122
                                                    }
                                                    val x1125 = x1083
                                                    val x1126 = x1084
                                                    val x1127 = x1085
                                                    val x1132 = if (x1126) {
                                                      true
                                                    } else {
                                                      false
                                                    }
                                                    val x1137 = if (x1132) {
                                                      true
                                                    } else {
                                                      false
                                                    }
                                                    val x1142 = if (x1137) {
                                                      true
                                                    } else {
                                                      false
                                                    }
                                                    val x1179 = if (x1142) {
                                                      x585 = false
                                                      ()
                                                    } else {
                                                      val x1143 = if (x1137) {
                                                        x700
                                                      } else {
                                                        val x1138 = if (x1132) {
                                                          x1020
                                                        } else {
                                                          val x1133 = if (x1126) {
                                                            x1068
                                                          } else {
                                                            x1127
                                                          }
                                                          x1133
                                                        }
                                                        x1138
                                                      }
                                                      val x1147 = x6(x1143)
                                                      val x1148 = x1147.empty
                                                      val x1154 = if (x1148) {
                                                        true
                                                      } else {
                                                        false
                                                      }
                                                      val x1161 = if (x1154) {
                                                        true
                                                      } else {
                                                        false
                                                      }
                                                      val x1177 = if (x1161) {
                                                        x585 = false
                                                        ()
                                                      } else {
                                                        val x1166 = x580
                                                        val x1167 = x581
                                                        val x1168 = x582
                                                        val x1160 = if (x1154) {
                                                          null
                                                        } else {
                                                          val x1153 = if (x1148) {
                                                            null
                                                          } else {
                                                            val x1141 = if (x1137) {
                                                              null
                                                            } else {
                                                              x1012
                                                            }
                                                            val x1149 = x1147.res
                                                            val x1150 = new Tuple2StringAnon6507737(x1141, x1149)
                                                            x1150
                                                          }
                                                          val x1158 = new Anon6507737(7, x1153)
                                                          x1158
                                                        }
                                                        val x1170 = x1160 :: x1166
                                                        x580 = x1170
                                                        x581 = false
                                                        val x1155 = if (x1148) {
                                                          x700
                                                        } else {
                                                          val x1151 = x1147.next
                                                          x1151
                                                        }
                                                        val x1162 = if (x1154) {
                                                          x1155
                                                        } else {
                                                          x1155
                                                        }
                                                        x582 = x1162
                                                        x586 = x1162
                                                        ()
                                                      }
                                                      x1177
                                                    }
                                                    x1179
                                                  } else {
                                                    x585 = false
                                                    ()
                                                  }
                                                  x1183
                                                }
                                                x1185
                                              }
                                              x1187
                                            } else {
                                              x585 = false
                                              ()
                                            }
                                            x1191
                                          }
                                          x1193
                                        }
                                        x1195
                                      } else {
                                        x585 = false
                                        ()
                                      }
                                      x1199
                                    }
                                    x1201
                                  }
                                  x1203
                                } else {
                                  x585 = false
                                  ()
                                }
                                x1207
                              }
                              x1209
                            }
                            x1211
                          }
                          val x1214 = x580
                          val x1215 = x581
                          val x1216 = x582
                          val x1222 = if (x1215) {
                            true
                          } else {
                            false
                          }
                          val x1228 = if (x1222) {
                            true
                          } else {
                            false
                          }
                          val x1237 = if (x1228) {
                            val x1229 = if (x1222) {
                              x96
                            } else {
                              val x1223 = if (x1215) {
                                x1216
                              } else {
                                x1216
                              }
                              x1223
                            }
                            val x1231 = new ParseResultListAnon6507737(null, true, x1229)
                            x1231
                          } else {
                            val x1227 = if (x1222) {
                              null
                            } else {
                              val x571 = if (x565) {
                                null
                              } else {
                                val x564 = if (x559) {
                                  null
                                } else {
                                  val x550 = if (x546) {
                                    null
                                  } else {
                                    x415
                                  }
                                  val x560 = x558.res
                                  val x561 = new Tuple2StringAnon6507737(x550, x560)
                                  x561
                                }
                                val x569 = new Anon6507737(7, x564)
                                x569
                              }
                              val x1221 = if (x1215) {
                                null
                              } else {
                                val x1219 = x1214.reverse
                                x1219
                              }
                              val x1225 = new Tuple2Anon6507737ListAnon6507737(x571, x1221)
                              x1225
                            }
                            val x1232 = x1227._1
                            val x1233 = x1227._2
                            val x1234 = x1232 :: x1233
                            val x1229 = if (x1222) {
                              x96
                            } else {
                              val x1223 = if (x1215) {
                                x1216
                              } else {
                                x1216
                              }
                              x1223
                            }
                            val x1235 = new ParseResultListAnon6507737(x1234, false, x1229)
                            x1235
                          }
                          val x1238 = if (x1228) {
                            val x1229 = if (x1222) {
                              x96
                            } else {
                              val x1223 = if (x1215) {
                                x1216
                              } else {
                                x1216
                              }
                              x1223
                            }
                            val x1231 = new ParseResultListAnon6507737(null, true, x1229)
                            x1231
                          } else {
                            val x1227 = if (x1222) {
                              null
                            } else {
                              val x571 = if (x565) {
                                null
                              } else {
                                val x564 = if (x559) {
                                  null
                                } else {
                                  val x550 = if (x546) {
                                    null
                                  } else {
                                    x415
                                  }
                                  val x560 = x558.res
                                  val x561 = new Tuple2StringAnon6507737(x550, x560)
                                  x561
                                }
                                val x569 = new Anon6507737(7, x564)
                                x569
                              }
                              val x1221 = if (x1215) {
                                null
                              } else {
                                val x1219 = x1214.reverse
                                x1219
                              }
                              val x1225 = new Tuple2Anon6507737ListAnon6507737(x571, x1221)
                              x1225
                            }
                            val x1232 = x1227._1
                            val x1233 = x1227._2
                            val x1234 = x1232 :: x1233
                            val x1229 = if (x1222) {
                              x96
                            } else {
                              val x1223 = if (x1215) {
                                x1216
                              } else {
                                x1216
                              }
                              x1223
                            }
                            val x1235 = new ParseResultListAnon6507737(x1234, false, x1229)
                            x1235
                          }
                          val x1239 = x1238.res
                          x98 = x1239
                          val x1241 = x1238.empty
                          x99 = x1241
                          val x1243 = x1238.next
                          x100 = x1243
                          ()
                        }
                        x1246
                      }
                      x1248
                    } else {
                      x98 = null
                      x99 = true
                      x100 = x96
                      ()
                    }
                    x1254
                  }
                  x1256
                }
                x1258
              } else {
                x98 = null
                x99 = true
                x100 = x96
                ()
              }
              x1264
            }
            x1266
          }
          x1268
        } else {
          x98 = null
          x99 = true
          x100 = x96
          ()
        }
        x1274
      }
      val x1277 = x98
      val x1278 = x99
      val x1279 = x100
      val x1280 = new ParseResultListAnon6507737(x1277, x1278, x1279)
      x1280: ParseResultListAnon6507737
    }
    x1302 = { x90: (Int) =>
      var x92: scala.collection.immutable.List[Anon6507737] = null
      var x93: Boolean = true
      var x94: Int = x90
      val x1283 = x1282(x90)
      val x1284 = x1283.empty
      val x1296 = if (x1284) {
        x92 = x89
        x93 = false
        x94 = x90
        ()
      } else {
        val x1290 = x1283.res
        x92 = x1290
        x93 = x1284
        val x1293 = x1283.next
        x94 = x1293
        ()
      }
      val x1297 = x92
      val x1298 = x93
      val x1299 = x94
      val x1300 = new ParseResultListAnon6507737(x1297, x1298, x1299)
      x1300: ParseResultListAnon6507737
    }
    x1407 = { x19: (Int) =>
      var x21: Anon6507737 = null
      var x22: Boolean = true
      var x23: Int = x19
      val x26 = x19 >= x25
      val x1401 = if (x26) {
        x21 = null
        x22 = true
        x23 = x19
        ()
      } else {
        val x34 = x0(x19)
        val x35 = x34 == '{'
        val x1399 = if (x35) {
          var x39: java.lang.String = ""
          var x40: Boolean = false
          val x36 = x19 + 1
          var x41: Int = x36
          var x43: Int = -1
          var x44: Boolean = true
          var x45: Int = x36
          val x80 = while ({
            val x46 = x44
            val x47 = x43
            val x48 = x45
            val x49 = x47 != x48
            val x50 = x46 && x49
            x50
          }) {
            val x52 = x45
            x43 = x52
            val x54 = x52 >= x25
            val x78 = if (x54) {
              x44 = false
              ()
            } else {
              val x58 = x0(x52)
              val x59 = x58 == ' '
              val x60 = x58 == '\n'
              val x61 = x59 || x60
              val x76 = if (x61) {
                val x64 = x39
                val x65 = x40
                val x66 = x41
                x39 = x64
                x40 = false
                val x62 = x52 + 1
                x41 = x62
                x45 = x62
                ()
              } else {
                x44 = false
                ()
              }
              x76
            }
            x78
          }
          val x81 = x39
          val x82 = x40
          val x83 = x41
          val x1393 = if (x82) {
            x21 = null
            x22 = true
            x23 = x19
            ()
          } else {
            val x1303 = x1302(x83)
            val x1304 = x1303.empty
            val x1391 = if (x1304) {
              x21 = null
              x22 = true
              val x1305 = x1303.next
              x23 = x1305
              ()
            } else {
              var x1312: java.lang.String = ""
              var x1313: Boolean = false
              val x1305 = x1303.next
              var x1314: Int = x1305
              var x1316: Int = -1
              var x1317: Boolean = true
              var x1318: Int = x1305
              val x1353 = while ({
                val x1319 = x1317
                val x1320 = x1316
                val x1321 = x1318
                val x1322 = x1320 != x1321
                val x1323 = x1319 && x1322
                x1323
              }) {
                val x1325 = x1318
                x1316 = x1325
                val x1327 = x1325 >= x25
                val x1351 = if (x1327) {
                  x1317 = false
                  ()
                } else {
                  val x1331 = x0(x1325)
                  val x1332 = x1331 == ' '
                  val x1333 = x1331 == '\n'
                  val x1334 = x1332 || x1333
                  val x1349 = if (x1334) {
                    val x1337 = x1312
                    val x1338 = x1313
                    val x1339 = x1314
                    x1312 = x1337
                    x1313 = false
                    val x1335 = x1325 + 1
                    x1314 = x1335
                    x1318 = x1335
                    ()
                  } else {
                    x1317 = false
                    ()
                  }
                  x1349
                }
                x1351
              }
              val x1354 = x1312
              val x1355 = x1313
              val x1356 = x1314
              val x1389 = if (x1355) {
                x21 = null
                x22 = true
                x23 = x19
                ()
              } else {
                val x1363 = x1356 >= x25
                val x1387 = if (x1363) {
                  x21 = null
                  x22 = true
                  x23 = x19
                  ()
                } else {
                  val x1369 = x0(x1356)
                  val x1370 = x1369 == '}'
                  val x1385 = if (x1370) {
                    val x1373 = x1303.res
                    val x1375 = new Anon6507737(8, x1373)
                    x21 = x1375
                    x22 = false
                    val x1371 = x1356 + 1
                    x23 = x1371
                    ()
                  } else {
                    x21 = null
                    x22 = true
                    x23 = x19
                    ()
                  }
                  x1385
                }
                x1387
              }
              x1389
            }
            x1391
          }
          x1393
        } else {
          x21 = null
          x22 = true
          x23 = x19
          ()
        }
        x1399
      }
      val x1402 = x21
      val x1403 = x22
      val x1404 = x23
      val x1405 = new ParseResultAnon6507737(x1402, x1403, x1404)
      x1405: ParseResultAnon6507737
    }
    x1685 = { x1479: (Int) =>
      var x1481: scala.collection.immutable.List[Anon6507737] = null
      var x1482: Boolean = true
      var x1483: Int = x1479
      val x1485 = x6(x1479)
      val x1486 = x1485.empty
      val x1679 = if (x1486) {
        x1481 = null
        x1482 = true
        x1483 = x1479
        ()
      } else {
        var x1494: scala.collection.immutable.List[Anon6507737] = x89
        var x1495: Boolean = false
        val x1492 = x1485.next
        var x1496: Int = x1492
        var x1498: Int = -1
        var x1499: Boolean = true
        var x1500: Int = x1492
        val x1647 = while ({
          val x1501 = x1499
          val x1502 = x1498
          val x1503 = x1500
          val x1504 = x1502 != x1503
          val x1505 = x1501 && x1504
          x1505
        }) {
          val x1507 = x1500
          x1498 = x1507
          var x1510: java.lang.String = ""
          var x1511: Boolean = false
          var x1512: Int = x1507
          var x1514: Int = -1
          var x1515: Boolean = true
          var x1516: Int = x1507
          val x1551 = while ({
            val x1517 = x1515
            val x1518 = x1514
            val x1519 = x1516
            val x1520 = x1518 != x1519
            val x1521 = x1517 && x1520
            x1521
          }) {
            val x1523 = x1516
            x1514 = x1523
            val x1525 = x1523 >= x25
            val x1549 = if (x1525) {
              x1515 = false
              ()
            } else {
              val x1529 = x0(x1523)
              val x1530 = x1529 == ' '
              val x1531 = x1529 == '\n'
              val x1532 = x1530 || x1531
              val x1547 = if (x1532) {
                val x1535 = x1510
                val x1536 = x1511
                val x1537 = x1512
                x1510 = x1535
                x1511 = false
                val x1533 = x1523 + 1
                x1512 = x1533
                x1516 = x1533
                ()
              } else {
                x1515 = false
                ()
              }
              x1547
            }
            x1549
          }
          val x1552 = x1510
          val x1553 = x1511
          val x1554 = x1512
          val x1645 = if (x1553) {
            x1499 = false
            ()
          } else {
            val x1561 = x1554 >= x25
            val x1643 = if (x1561) {
              x1499 = false
              ()
            } else {
              val x1565 = x0(x1554)
              val x1566 = x1565 == ','
              val x1641 = if (x1566) {
                var x1570: java.lang.String = ""
                var x1571: Boolean = false
                val x1567 = x1554 + 1
                var x1572: Int = x1567
                var x1574: Int = -1
                var x1575: Boolean = true
                var x1576: Int = x1567
                val x1611 = while ({
                  val x1577 = x1575
                  val x1578 = x1574
                  val x1579 = x1576
                  val x1580 = x1578 != x1579
                  val x1581 = x1577 && x1580
                  x1581
                }) {
                  val x1583 = x1576
                  x1574 = x1583
                  val x1585 = x1583 >= x25
                  val x1609 = if (x1585) {
                    x1575 = false
                    ()
                  } else {
                    val x1589 = x0(x1583)
                    val x1590 = x1589 == ' '
                    val x1591 = x1589 == '\n'
                    val x1592 = x1590 || x1591
                    val x1607 = if (x1592) {
                      val x1595 = x1570
                      val x1596 = x1571
                      val x1597 = x1572
                      x1570 = x1595
                      x1571 = false
                      val x1593 = x1583 + 1
                      x1572 = x1593
                      x1576 = x1593
                      ()
                    } else {
                      x1575 = false
                      ()
                    }
                    x1607
                  }
                  x1609
                }
                val x1612 = x1570
                val x1613 = x1571
                val x1614 = x1572
                val x1637 = if (x1613) {
                  x1499 = false
                  ()
                } else {
                  val x1618 = x6(x1614)
                  val x1619 = x1618.empty
                  val x1635 = if (x1619) {
                    x1499 = false
                    ()
                  } else {
                    val x1622 = x1494
                    val x1623 = x1495
                    val x1624 = x1496
                    val x1626 = x1618.res
                    val x1627 = x1626 :: x1622
                    x1494 = x1627
                    x1495 = false
                    val x1628 = x1618.next
                    x1496 = x1628
                    x1500 = x1628
                    ()
                  }
                  x1635
                }
                x1637
              } else {
                x1499 = false
                ()
              }
              x1641
            }
            x1643
          }
          x1645
        }
        val x1648 = x1494
        val x1649 = x1495
        val x1650 = x1496
        val x1656 = if (x1649) {
          true
        } else {
          false
        }
        val x1663 = if (x1656) {
          true
        } else {
          false
        }
        val x1671 = if (x1663) {
          null
        } else {
          val x1662 = if (x1656) {
            null
          } else {
            val x1655 = if (x1649) {
              null
            } else {
              val x1653 = x1648.reverse
              x1653
            }
            val x1659 = x1485.res
            val x1660 = new Tuple2Anon6507737ListAnon6507737(x1659, x1655)
            x1660
          }
          val x1667 = x1662._1
          val x1668 = x1662._2
          val x1669 = x1667 :: x1668
          x1669
        }
        x1481 = x1671
        val x1672 = if (x1663) {
          true
        } else {
          false
        }
        x1482 = x1672
        val x1664 = if (x1656) {
          x1479
        } else {
          val x1657 = if (x1649) {
            x1650
          } else {
            x1650
          }
          x1657
        }
        val x1673 = if (x1663) {
          x1664
        } else {
          x1664
        }
        x1483 = x1673
        ()
      }
      val x1680 = x1481
      val x1681 = x1482
      val x1682 = x1483
      val x1683 = new ParseResultListAnon6507737(x1680, x1681, x1682)
      x1683: ParseResultListAnon6507737
    }
    x1705 = { x1473: (Int) =>
      var x1475: scala.collection.immutable.List[Anon6507737] = null
      var x1476: Boolean = true
      var x1477: Int = x1473
      val x1686 = x1685(x1473)
      val x1687 = x1686.empty
      val x1699 = if (x1687) {
        x1475 = x89
        x1476 = false
        x1477 = x1473
        ()
      } else {
        val x1693 = x1686.res
        x1475 = x1693
        x1476 = x1687
        val x1696 = x1686.next
        x1477 = x1696
        ()
      }
      val x1700 = x1475
      val x1701 = x1476
      val x1702 = x1477
      val x1703 = new ParseResultListAnon6507737(x1700, x1701, x1702)
      x1703: ParseResultListAnon6507737
    }
    x1819 = { x13: (Int) =>
      var x15: Anon6507737 = null
      var x16: Boolean = true
      var x17: Int = x13
      val x1408 = x1407(x13)
      val x1409 = x1408.empty
      val x1813 = if (x1409) {
        val x1410 = x13 >= x25
        val x1805 = if (x1410) {
          x15 = null
          x16 = true
          x17 = x13
          ()
        } else {
          val x1418 = x0(x13)
          val x1419 = x1418 == '['
          val x1803 = if (x1419) {
            var x1423: java.lang.String = ""
            var x1424: Boolean = false
            val x1420 = x13 + 1
            var x1425: Int = x1420
            var x1427: Int = -1
            var x1428: Boolean = true
            var x1429: Int = x1420
            val x1464 = while ({
              val x1430 = x1428
              val x1431 = x1427
              val x1432 = x1429
              val x1433 = x1431 != x1432
              val x1434 = x1430 && x1433
              x1434
            }) {
              val x1436 = x1429
              x1427 = x1436
              val x1438 = x1436 >= x25
              val x1462 = if (x1438) {
                x1428 = false
                ()
              } else {
                val x1442 = x0(x1436)
                val x1443 = x1442 == ' '
                val x1444 = x1442 == '\n'
                val x1445 = x1443 || x1444
                val x1460 = if (x1445) {
                  val x1448 = x1423
                  val x1449 = x1424
                  val x1450 = x1425
                  x1423 = x1448
                  x1424 = false
                  val x1446 = x1436 + 1
                  x1425 = x1446
                  x1429 = x1446
                  ()
                } else {
                  x1428 = false
                  ()
                }
                x1460
              }
              x1462
            }
            val x1465 = x1423
            val x1466 = x1424
            val x1467 = x1425
            val x1797 = if (x1466) {
              x15 = null
              x16 = true
              x17 = x13
              ()
            } else {
              val x1706 = x1705(x1467)
              val x1707 = x1706.empty
              val x1795 = if (x1707) {
                x15 = null
                x16 = true
                val x1708 = x1706.next
                x17 = x1708
                ()
              } else {
                var x1715: java.lang.String = ""
                var x1716: Boolean = false
                val x1708 = x1706.next
                var x1717: Int = x1708
                var x1719: Int = -1
                var x1720: Boolean = true
                var x1721: Int = x1708
                val x1756 = while ({
                  val x1722 = x1720
                  val x1723 = x1719
                  val x1724 = x1721
                  val x1725 = x1723 != x1724
                  val x1726 = x1722 && x1725
                  x1726
                }) {
                  val x1728 = x1721
                  x1719 = x1728
                  val x1730 = x1728 >= x25
                  val x1754 = if (x1730) {
                    x1720 = false
                    ()
                  } else {
                    val x1734 = x0(x1728)
                    val x1735 = x1734 == ' '
                    val x1736 = x1734 == '\n'
                    val x1737 = x1735 || x1736
                    val x1752 = if (x1737) {
                      val x1740 = x1715
                      val x1741 = x1716
                      val x1742 = x1717
                      x1715 = x1740
                      x1716 = false
                      val x1738 = x1728 + 1
                      x1717 = x1738
                      x1721 = x1738
                      ()
                    } else {
                      x1720 = false
                      ()
                    }
                    x1752
                  }
                  x1754
                }
                val x1757 = x1715
                val x1758 = x1716
                val x1759 = x1717
                val x1793 = if (x1758) {
                  x15 = null
                  x16 = true
                  x17 = x13
                  ()
                } else {
                  val x1766 = x1759 >= x25
                  val x1791 = if (x1766) {
                    x15 = null
                    x16 = true
                    x17 = x13
                    ()
                  } else {
                    val x1772 = x0(x1759)
                    val x1773 = x1772 == ']'
                    val x1789 = if (x1773) {
                      val x1776 = x1706.res
                      val x1778 = x1776.asInstanceOf[scala.collection.immutable.List[Anon6507737]]
                      val x1779 = new Anon6507737(6, x1778)
                      x15 = x1779
                      x16 = false
                      val x1774 = x1759 + 1
                      x17 = x1774
                      ()
                    } else {
                      x15 = null
                      x16 = true
                      x17 = x13
                      ()
                    }
                    x1789
                  }
                  x1791
                }
                x1793
              }
              x1795
            }
            x1797
          } else {
            x15 = null
            x16 = true
            x17 = x13
            ()
          }
          x1803
        }
        x1805
      } else {
        val x1807 = x1408.res
        x15 = x1807
        x16 = x1409
        val x1810 = x1408.next
        x17 = x1810
        ()
      }
      val x1814 = x15
      val x1815 = x16
      val x1816 = x17
      val x1817 = new ParseResultAnon6507737(x1814, x1815, x1816)
      x1817: ParseResultAnon6507737
    }
    x3234 = { x3216: (Int) =>
      var x3218: Anon6507737 = null
      var x3219: Boolean = true
      var x3220: Int = x3216
      val x3222 = x1819(x3216)
      val x3223 = x3222.res
      x3218 = x3223
      val x3225 = x3222.empty
      x3219 = x3225
      val x3227 = x3222.next
      x3220 = x3227
      val x3229 = x3218
      val x3230 = x3219
      val x3231 = x3220
      val x3232 = new ParseResultAnon6507737(x3229, x3230, x3231)
      x3232: ParseResultAnon6507737
    }
    val x1820 = px1820 // static data: 5
    val x1821 = px1821 // static data: Array(f,a,l,s,e)
    val x1822 = new Anon6507737(1, null)
    val x1884 = { x1832: (Int) =>
      var x1834: Anon6507737 = null
      var x1835: Boolean = true
      var x1836: Int = x1832
      val x1838 = x1832 + x1820
      val x1839 = x1838 > x25
      val x1878 = if (x1839) {
        x1834 = null
        x1835 = true
        x1836 = x1832
        ()
      } else {
        var x1845: Int = 0
        var x1846: Boolean = true
        val x1864 = while ({
          val x1847 = x1846
          val x1848 = x1845
          val x1849 = x1848 < x1820
          val x1850 = x1847 && x1849
          x1850
        }) {
          val x1852 = x1845
          val x1853 = x1832 + x1852
          val x1854 = x0(x1853)
          val x1855 = x1821(x1852)
          val x1857 = x1854 == x1855
          val x1860 = if (x1857) {
            ()
          } else {
            x1846 = false
            ()
          }
          val x1861 = x1852 + 1
          x1845 = x1861
          ()
        }
        val x1865 = x1846
        val x1876 = if (x1865) {
          x1834 = x1822
          x1835 = false
          x1836 = x1838
          ()
        } else {
          x1834 = null
          x1835 = true
          x1836 = x1832
          ()
        }
        x1876
      }
      val x1879 = x1834
      val x1880 = x1835
      val x1881 = x1836
      val x1882 = new ParseResultAnon6507737(x1879, x1880, x1881)
      x1882: ParseResultAnon6507737
    }
    val x1823 = px1823 // static data: 4
    val x1824 = px1824 // static data: Array(t,r,u,e)
    val x1825 = new Anon6507737(2, null)
    val x1941 = { x1826: (Int) =>
      var x1828: Anon6507737 = null
      var x1829: Boolean = true
      var x1830: Int = x1826
      val x1885 = x1884(x1826)
      val x1886 = x1885.empty
      val x1935 = if (x1886) {
        val x1887 = x1826 + x1823
        val x1888 = x1887 > x25
        val x1927 = if (x1888) {
          x1828 = null
          x1829 = true
          x1830 = x1826
          ()
        } else {
          var x1894: Int = 0
          var x1895: Boolean = true
          val x1913 = while ({
            val x1896 = x1895
            val x1897 = x1894
            val x1898 = x1897 < x1823
            val x1899 = x1896 && x1898
            x1899
          }) {
            val x1901 = x1894
            val x1902 = x1826 + x1901
            val x1903 = x0(x1902)
            val x1904 = x1824(x1901)
            val x1906 = x1903 == x1904
            val x1909 = if (x1906) {
              ()
            } else {
              x1895 = false
              ()
            }
            val x1910 = x1901 + 1
            x1894 = x1910
            ()
          }
          val x1914 = x1895
          val x1925 = if (x1914) {
            x1828 = x1825
            x1829 = false
            x1830 = x1887
            ()
          } else {
            x1828 = null
            x1829 = true
            x1830 = x1826
            ()
          }
          x1925
        }
        x1927
      } else {
        val x1929 = x1885.res
        x1828 = x1929
        x1829 = x1886
        val x1932 = x1885.next
        x1830 = x1932
        ()
      }
      val x1936 = x1828
      val x1937 = x1829
      val x1938 = x1830
      val x1939 = new ParseResultAnon6507737(x1936, x1937, x1938)
      x1939: ParseResultAnon6507737
    }
    val x1968 = { x1950: (Int) =>
      var x1952: Anon6507737 = null
      var x1953: Boolean = true
      var x1954: Int = x1950
      val x1956 = x1941(x1950)
      val x1957 = x1956.res
      x1952 = x1957
      val x1959 = x1956.empty
      x1953 = x1959
      val x1961 = x1956.next
      x1954 = x1961
      val x1963 = x1952
      val x1964 = x1953
      val x1965 = x1954
      val x1966 = new ParseResultAnon6507737(x1963, x1964, x1965)
      x1966: ParseResultAnon6507737
    }
    val x1942 = px1942 // static data: Array(n,u,l,l)
    val x1943 = new Anon6507737(0, null)
    val x2025 = { x1944: (Int) =>
      var x1946: Anon6507737 = null
      var x1947: Boolean = true
      var x1948: Int = x1944
      val x1969 = x1968(x1944)
      val x1970 = x1969.empty
      val x2019 = if (x1970) {
        val x1971 = x1944 + x1823
        val x1972 = x1971 > x25
        val x2011 = if (x1972) {
          x1946 = null
          x1947 = true
          x1948 = x1944
          ()
        } else {
          var x1978: Int = 0
          var x1979: Boolean = true
          val x1997 = while ({
            val x1980 = x1979
            val x1981 = x1978
            val x1982 = x1981 < x1823
            val x1983 = x1980 && x1982
            x1983
          }) {
            val x1985 = x1978
            val x1986 = x1944 + x1985
            val x1987 = x0(x1986)
            val x1988 = x1942(x1985)
            val x1990 = x1987 == x1988
            val x1993 = if (x1990) {
              ()
            } else {
              x1979 = false
              ()
            }
            val x1994 = x1985 + 1
            x1978 = x1994
            ()
          }
          val x1998 = x1979
          val x2009 = if (x1998) {
            x1946 = x1943
            x1947 = false
            x1948 = x1971
            ()
          } else {
            x1946 = null
            x1947 = true
            x1948 = x1944
            ()
          }
          x2009
        }
        x2011
      } else {
        val x2013 = x1969.res
        x1946 = x2013
        x1947 = x1970
        val x2016 = x1969.next
        x1948 = x2016
        ()
      }
      val x2020 = x1946
      val x2021 = x1947
      val x2022 = x1948
      val x2023 = new ParseResultAnon6507737(x2020, x2021, x2022)
      x2023: ParseResultAnon6507737
    }
    val x2050 = { x2032: (Int) =>
      var x2034: Anon6507737 = null
      var x2035: Boolean = true
      var x2036: Int = x2032
      val x2038 = x2025(x2032)
      val x2039 = x2038.res
      x2034 = x2039
      val x2041 = x2038.empty
      x2035 = x2041
      val x2043 = x2038.next
      x2036 = x2043
      val x2045 = x2034
      val x2046 = x2035
      val x2047 = x2036
      val x2048 = new ParseResultAnon6507737(x2045, x2046, x2047)
      x2048: ParseResultAnon6507737
    }
    val x2055 = null.asInstanceOf[Char]
    val x2056 = new OptionChar(x2055, false)
    val x2558 = { x2026: (Int) =>
      var x2028: Anon6507737 = null
      var x2029: Boolean = true
      var x2030: Int = x2026
      val x2051 = x2050(x2026)
      val x2052 = x2051.empty
      val x2552 = if (x2052) {
        val x2053 = x2026 >= x25
        val x2544 = if (x2053) {
          x2028 = null
          x2029 = true
          x2030 = x2026
          ()
        } else {
          val x2067 = x0(x2026)
          val x2068 = x2067 == '-'
          val x2542 = if (x2068) {
            val x2069 = x2026 + 1
            val x2073 = x2069 >= x25
            val x2312 = if (x2073) {
              x2028 = null
              x2029 = true
              x2030 = x2026
              ()
            } else {
              val x2081 = x0(x2069)
              val x2082 = x2081 >= '0'
              val x2083 = x2081 <= '9'
              val x2084 = x2082 && x2083
              val x2310 = if (x2084) {
                var x2088: java.lang.String = ""
                var x2089: Boolean = false
                val x2085 = x2069 + 1
                var x2090: Int = x2085
                var x2092: Int = -1
                var x2093: Boolean = true
                var x2094: Int = x2085
                val x2130 = while ({
                  val x2095 = x2093
                  val x2096 = x2092
                  val x2097 = x2094
                  val x2098 = x2096 != x2097
                  val x2099 = x2095 && x2098
                  x2099
                }) {
                  val x2101 = x2094
                  x2092 = x2101
                  val x2103 = x2101 >= x25
                  val x2128 = if (x2103) {
                    x2093 = false
                    ()
                  } else {
                    val x2107 = x0(x2101)
                    val x2108 = x2107 >= '0'
                    val x2109 = x2107 <= '9'
                    val x2110 = x2108 && x2109
                    val x2126 = if (x2110) {
                      val x2113 = x2088
                      val x2114 = x2089
                      val x2115 = x2090
                      val x2117 = x2113 + x2107
                      x2088 = x2117
                      x2089 = false
                      val x2111 = x2101 + 1
                      x2090 = x2111
                      x2094 = x2111
                      ()
                    } else {
                      x2093 = false
                      ()
                    }
                    x2126
                  }
                  x2128
                }
                val x2131 = x2088
                val x2132 = x2089
                val x2133 = x2090
                val x2138 = if (x2132) {
                  true
                } else {
                  false
                }
                val x2147 = if (x2138) {
                  true
                } else {
                  false
                }
                val x2153 = if (x2147) {
                  true
                } else {
                  false
                }
                val x2165 = if (x2153) {
                  true
                } else {
                  false
                }
                val x2304 = if (x2165) {
                  x2028 = null
                  x2029 = true
                  x2030 = x2026
                  ()
                } else {
                  val x2154 = if (x2147) {
                    x2026
                  } else {
                    val x2139 = if (x2132) {
                      x2069
                    } else {
                      x2133
                    }
                    val x2148 = if (x2138) {
                      x2139
                    } else {
                      x2139
                    }
                    x2148
                  }
                  val x2166 = if (x2153) {
                    x2154
                  } else {
                    x2154
                  }
                  val x2172 = x2166 >= x25
                  val x2302 = if (x2172) {
                    x2028 = null
                    x2029 = true
                    x2030 = x2026
                    ()
                  } else {
                    val x2179 = x0(x2166)
                    val x2180 = x2179 == '.'
                    val x2300 = if (x2180) {
                      val x2181 = x2166 + 1
                      val x2183 = x2181 >= x25
                      val x2294 = if (x2183) {
                        x2028 = null
                        x2029 = true
                        x2030 = x2026
                        ()
                      } else {
                        val x2191 = x0(x2181)
                        val x2192 = x2191 >= '0'
                        val x2193 = x2191 <= '9'
                        val x2194 = x2192 && x2193
                        val x2292 = if (x2194) {
                          var x2198: java.lang.String = ""
                          var x2199: Boolean = false
                          val x2195 = x2181 + 1
                          var x2200: Int = x2195
                          var x2202: Int = -1
                          var x2203: Boolean = true
                          var x2204: Int = x2195
                          val x2240 = while ({
                            val x2205 = x2203
                            val x2206 = x2202
                            val x2207 = x2204
                            val x2208 = x2206 != x2207
                            val x2209 = x2205 && x2208
                            x2209
                          }) {
                            val x2211 = x2204
                            x2202 = x2211
                            val x2213 = x2211 >= x25
                            val x2238 = if (x2213) {
                              x2203 = false
                              ()
                            } else {
                              val x2217 = x0(x2211)
                              val x2218 = x2217 >= '0'
                              val x2219 = x2217 <= '9'
                              val x2220 = x2218 && x2219
                              val x2236 = if (x2220) {
                                val x2223 = x2198
                                val x2224 = x2199
                                val x2225 = x2200
                                val x2227 = x2223 + x2217
                                x2198 = x2227
                                x2199 = false
                                val x2221 = x2211 + 1
                                x2200 = x2221
                                x2204 = x2221
                                ()
                              } else {
                                x2203 = false
                                ()
                              }
                              x2236
                            }
                            x2238
                          }
                          val x2241 = x2198
                          val x2242 = x2199
                          val x2243 = x2200
                          val x2248 = if (x2242) {
                            true
                          } else {
                            false
                          }
                          val x2257 = if (x2248) {
                            true
                          } else {
                            false
                          }
                          val x2263 = if (x2257) {
                            true
                          } else {
                            false
                          }
                          val x2274 = if (x2263) {
                            true
                          } else {
                            false
                          }
                          val x2280 = if (x2274) {
                            null
                          } else {
                            val x2273 = if (x2263) {
                              0.0
                            } else {
                              val x2262 = if (x2257) {
                                null
                              } else {
                                val x2164 = if (x2153) {
                                  null
                                } else {
                                  val x2152 = if (x2147) {
                                    null
                                  } else {
                                    val x2071 = new OptionChar(x2067, true)
                                    val x2146 = if (x2138) {
                                      null
                                    } else {
                                      val x2137 = if (x2132) {
                                        null
                                      } else {
                                        val x2135 = new Tuple2CharString(x2081, x2131)
                                        x2135
                                      }
                                      val x2142 = x2137._1
                                      val x2143 = x2137._2
                                      val x2144 = x2142 + x2143
                                      x2144
                                    }
                                    val x2150 = new Tuple2OptionCharString(x2071, x2146)
                                    x2150
                                  }
                                  val x2157 = x2152._1
                                  val x2159 = x2157.defined
                                  val x2162 = if (x2159) {
                                    val x2158 = x2152._2
                                    val x2160 = x2157.value
                                    val x2161 = x2160 + x2158
                                    x2161
                                  } else {
                                    val x2158 = x2152._2
                                    x2158
                                  }
                                  x2162
                                }
                                val x2256 = if (x2248) {
                                  null
                                } else {
                                  val x2247 = if (x2242) {
                                    null
                                  } else {
                                    val x2245 = new Tuple2CharString(x2191, x2241)
                                    x2245
                                  }
                                  val x2252 = x2247._1
                                  val x2253 = x2247._2
                                  val x2254 = x2252 + x2253
                                  x2254
                                }
                                val x2260 = new Tuple2StringString(x2164, x2256)
                                x2260
                              }
                              val x2268 = x2262._2
                              val x2267 = x2262._1
                              val x2269 = x2267 + "."
                              val x2270 = x2269 + x2268
                              val x2271 = x2270.toDouble
                              x2271
                            }
                            val x2278 = new Anon6507737(4, x2273)
                            x2278
                          }
                          x2028 = x2280
                          val x2281 = if (x2274) {
                            true
                          } else {
                            false
                          }
                          x2029 = x2281
                          val x2264 = if (x2257) {
                            x2026
                          } else {
                            val x2249 = if (x2242) {
                              x2181
                            } else {
                              x2243
                            }
                            val x2258 = if (x2248) {
                              x2249
                            } else {
                              x2249
                            }
                            x2258
                          }
                          val x2275 = if (x2263) {
                            x2264
                          } else {
                            x2264
                          }
                          val x2282 = if (x2274) {
                            x2275
                          } else {
                            x2275
                          }
                          x2030 = x2282
                          ()
                        } else {
                          x2028 = null
                          x2029 = true
                          x2030 = x2026
                          ()
                        }
                        x2292
                      }
                      x2294
                    } else {
                      x2028 = null
                      x2029 = true
                      x2030 = x2026
                      ()
                    }
                    x2300
                  }
                  x2302
                }
                x2304
              } else {
                x2028 = null
                x2029 = true
                x2030 = x2026
                ()
              }
              x2310
            }
            x2312
          } else {
            val x2314 = x2067 >= '0'
            val x2315 = x2067 <= '9'
            val x2316 = x2314 && x2315
            val x2540 = if (x2316) {
              var x2318: java.lang.String = ""
              var x2319: Boolean = false
              val x2069 = x2026 + 1
              var x2320: Int = x2069
              var x2322: Int = -1
              var x2323: Boolean = true
              var x2324: Int = x2069
              val x2360 = while ({
                val x2325 = x2323
                val x2326 = x2322
                val x2327 = x2324
                val x2328 = x2326 != x2327
                val x2329 = x2325 && x2328
                x2329
              }) {
                val x2331 = x2324
                x2322 = x2331
                val x2333 = x2331 >= x25
                val x2358 = if (x2333) {
                  x2323 = false
                  ()
                } else {
                  val x2337 = x0(x2331)
                  val x2338 = x2337 >= '0'
                  val x2339 = x2337 <= '9'
                  val x2340 = x2338 && x2339
                  val x2356 = if (x2340) {
                    val x2343 = x2318
                    val x2344 = x2319
                    val x2345 = x2320
                    val x2347 = x2343 + x2337
                    x2318 = x2347
                    x2319 = false
                    val x2341 = x2331 + 1
                    x2320 = x2341
                    x2324 = x2341
                    ()
                  } else {
                    x2323 = false
                    ()
                  }
                  x2356
                }
                x2358
              }
              val x2361 = x2318
              val x2362 = x2319
              val x2363 = x2320
              val x2368 = if (x2362) {
                true
              } else {
                false
              }
              val x2377 = if (x2368) {
                true
              } else {
                false
              }
              val x2383 = if (x2377) {
                true
              } else {
                false
              }
              val x2395 = if (x2383) {
                true
              } else {
                false
              }
              val x2534 = if (x2395) {
                x2028 = null
                x2029 = true
                x2030 = x2026
                ()
              } else {
                val x2384 = if (x2377) {
                  x2026
                } else {
                  val x2369 = if (x2362) {
                    x2026
                  } else {
                    x2363
                  }
                  val x2378 = if (x2368) {
                    x2369
                  } else {
                    x2369
                  }
                  x2378
                }
                val x2396 = if (x2383) {
                  x2384
                } else {
                  x2384
                }
                val x2402 = x2396 >= x25
                val x2532 = if (x2402) {
                  x2028 = null
                  x2029 = true
                  x2030 = x2026
                  ()
                } else {
                  val x2409 = x0(x2396)
                  val x2410 = x2409 == '.'
                  val x2530 = if (x2410) {
                    val x2411 = x2396 + 1
                    val x2413 = x2411 >= x25
                    val x2524 = if (x2413) {
                      x2028 = null
                      x2029 = true
                      x2030 = x2026
                      ()
                    } else {
                      val x2421 = x0(x2411)
                      val x2422 = x2421 >= '0'
                      val x2423 = x2421 <= '9'
                      val x2424 = x2422 && x2423
                      val x2522 = if (x2424) {
                        var x2428: java.lang.String = ""
                        var x2429: Boolean = false
                        val x2425 = x2411 + 1
                        var x2430: Int = x2425
                        var x2432: Int = -1
                        var x2433: Boolean = true
                        var x2434: Int = x2425
                        val x2470 = while ({
                          val x2435 = x2433
                          val x2436 = x2432
                          val x2437 = x2434
                          val x2438 = x2436 != x2437
                          val x2439 = x2435 && x2438
                          x2439
                        }) {
                          val x2441 = x2434
                          x2432 = x2441
                          val x2443 = x2441 >= x25
                          val x2468 = if (x2443) {
                            x2433 = false
                            ()
                          } else {
                            val x2447 = x0(x2441)
                            val x2448 = x2447 >= '0'
                            val x2449 = x2447 <= '9'
                            val x2450 = x2448 && x2449
                            val x2466 = if (x2450) {
                              val x2453 = x2428
                              val x2454 = x2429
                              val x2455 = x2430
                              val x2457 = x2453 + x2447
                              x2428 = x2457
                              x2429 = false
                              val x2451 = x2441 + 1
                              x2430 = x2451
                              x2434 = x2451
                              ()
                            } else {
                              x2433 = false
                              ()
                            }
                            x2466
                          }
                          x2468
                        }
                        val x2471 = x2428
                        val x2472 = x2429
                        val x2473 = x2430
                        val x2478 = if (x2472) {
                          true
                        } else {
                          false
                        }
                        val x2487 = if (x2478) {
                          true
                        } else {
                          false
                        }
                        val x2493 = if (x2487) {
                          true
                        } else {
                          false
                        }
                        val x2504 = if (x2493) {
                          true
                        } else {
                          false
                        }
                        val x2510 = if (x2504) {
                          null
                        } else {
                          val x2503 = if (x2493) {
                            0.0
                          } else {
                            val x2492 = if (x2487) {
                              null
                            } else {
                              val x2394 = if (x2383) {
                                null
                              } else {
                                val x2382 = if (x2377) {
                                  null
                                } else {
                                  val x2376 = if (x2368) {
                                    null
                                  } else {
                                    val x2367 = if (x2362) {
                                      null
                                    } else {
                                      val x2365 = new Tuple2CharString(x2067, x2361)
                                      x2365
                                    }
                                    val x2372 = x2367._1
                                    val x2373 = x2367._2
                                    val x2374 = x2372 + x2373
                                    x2374
                                  }
                                  val x2380 = new Tuple2OptionCharString(x2056, x2376)
                                  x2380
                                }
                                val x2387 = x2382._1
                                val x2389 = x2387.defined
                                val x2392 = if (x2389) {
                                  val x2388 = x2382._2
                                  val x2390 = x2387.value
                                  val x2391 = x2390 + x2388
                                  x2391
                                } else {
                                  val x2388 = x2382._2
                                  x2388
                                }
                                x2392
                              }
                              val x2486 = if (x2478) {
                                null
                              } else {
                                val x2477 = if (x2472) {
                                  null
                                } else {
                                  val x2475 = new Tuple2CharString(x2421, x2471)
                                  x2475
                                }
                                val x2482 = x2477._1
                                val x2483 = x2477._2
                                val x2484 = x2482 + x2483
                                x2484
                              }
                              val x2490 = new Tuple2StringString(x2394, x2486)
                              x2490
                            }
                            val x2498 = x2492._2
                            val x2497 = x2492._1
                            val x2499 = x2497 + "."
                            val x2500 = x2499 + x2498
                            val x2501 = x2500.toDouble
                            x2501
                          }
                          val x2508 = new Anon6507737(4, x2503)
                          x2508
                        }
                        x2028 = x2510
                        val x2511 = if (x2504) {
                          true
                        } else {
                          false
                        }
                        x2029 = x2511
                        val x2494 = if (x2487) {
                          x2026
                        } else {
                          val x2479 = if (x2472) {
                            x2411
                          } else {
                            x2473
                          }
                          val x2488 = if (x2478) {
                            x2479
                          } else {
                            x2479
                          }
                          x2488
                        }
                        val x2505 = if (x2493) {
                          x2494
                        } else {
                          x2494
                        }
                        val x2512 = if (x2504) {
                          x2505
                        } else {
                          x2505
                        }
                        x2030 = x2512
                        ()
                      } else {
                        x2028 = null
                        x2029 = true
                        x2030 = x2026
                        ()
                      }
                      x2522
                    }
                    x2524
                  } else {
                    x2028 = null
                    x2029 = true
                    x2030 = x2026
                    ()
                  }
                  x2530
                }
                x2532
              }
              x2534
            } else {
              x2028 = null
              x2029 = true
              x2030 = x2026
              ()
            }
            x2540
          }
          x2542
        }
        x2544
      } else {
        val x2546 = x2051.res
        x2028 = x2546
        x2029 = x2052
        val x2549 = x2051.next
        x2030 = x2549
        ()
      }
      val x2553 = x2028
      val x2554 = x2029
      val x2555 = x2030
      val x2556 = new ParseResultAnon6507737(x2553, x2554, x2555)
      x2556: ParseResultAnon6507737
    }
    val x2583 = { x2565: (Int) =>
      var x2567: Anon6507737 = null
      var x2568: Boolean = true
      var x2569: Int = x2565
      val x2571 = x2558(x2565)
      val x2572 = x2571.res
      x2567 = x2572
      val x2574 = x2571.empty
      x2568 = x2574
      val x2576 = x2571.next
      x2569 = x2576
      val x2578 = x2567
      val x2579 = x2568
      val x2580 = x2569
      val x2581 = new ParseResultAnon6507737(x2578, x2579, x2580)
      x2581: ParseResultAnon6507737
    }
    val x2821 = { x2559: (Int) =>
      var x2561: Anon6507737 = null
      var x2562: Boolean = true
      var x2563: Int = x2559
      val x2584 = x2583(x2559)
      val x2585 = x2584.empty
      val x2815 = if (x2585) {
        val x2586 = x2559 >= x25
        val x2807 = if (x2586) {
          x2561 = null
          x2562 = true
          x2563 = x2559
          ()
        } else {
          val x2595 = x0(x2559)
          val x2596 = x2595 == '-'
          val x2805 = if (x2596) {
            val x2597 = x2559 + 1
            val x2601 = x2597 >= x25
            val x2707 = if (x2601) {
              x2561 = null
              x2562 = true
              x2563 = x2559
              ()
            } else {
              val x2608 = x0(x2597)
              val x2609 = x2608 >= '0'
              val x2610 = x2608 <= '9'
              val x2611 = x2609 && x2610
              val x2705 = if (x2611) {
                val x2614 = x2608 - '0'
                val x2615 = x2614.toInt
                var x2617: Int = x2615
                var x2618: Boolean = false
                val x2612 = x2597 + 1
                var x2619: Int = x2612
                var x2621: Int = -1
                var x2622: Boolean = true
                var x2623: Int = x2612
                val x2664 = while ({
                  val x2624 = x2622
                  val x2625 = x2621
                  val x2626 = x2623
                  val x2627 = x2625 != x2626
                  val x2628 = x2624 && x2627
                  x2628
                }) {
                  val x2630 = x2623
                  x2621 = x2630
                  val x2632 = x2630 >= x25
                  val x2662 = if (x2632) {
                    x2622 = false
                    ()
                  } else {
                    val x2637 = x0(x2630)
                    val x2638 = x2637 >= '0'
                    val x2639 = x2637 <= '9'
                    val x2640 = x2638 && x2639
                    val x2660 = if (x2640) {
                      val x2646 = x2617
                      val x2647 = x2618
                      val x2648 = x2619
                      val x2643 = x2637 - '0'
                      val x2644 = x2643.toInt
                      val x2650 = x2646 * 10
                      val x2651 = x2650 + x2644
                      x2617 = x2651
                      x2618 = false
                      val x2641 = x2630 + 1
                      x2619 = x2641
                      x2623 = x2641
                      ()
                    } else {
                      x2622 = false
                      ()
                    }
                    x2660
                  }
                  x2662
                }
                val x2665 = x2617
                val x2666 = x2618
                val x2667 = x2619
                val x2670 = if (x2666) {
                  true
                } else {
                  x2666
                }
                val x2676 = if (x2670) {
                  true
                } else {
                  false
                }
                val x2687 = if (x2676) {
                  true
                } else {
                  false
                }
                val x2693 = if (x2687) {
                  null
                } else {
                  val x2686 = if (x2676) {
                    0
                  } else {
                    val x2675 = if (x2670) {
                      null
                    } else {
                      val x2599 = new OptionChar(x2595, true)
                      val x2669 = if (x2666) {
                        0
                      } else {
                        x2665
                      }
                      val x2673 = new Tuple2OptionCharInt(x2599, x2669)
                      x2673
                    }
                    val x2680 = x2675._1
                    val x2682 = x2680.defined
                    val x2684 = if (x2682) {
                      val x2681 = x2675._2
                      val x2683 = -1 * x2681
                      x2683
                    } else {
                      val x2681 = x2675._2
                      x2681
                    }
                    x2684
                  }
                  val x2691 = new Anon6507737(3, x2686)
                  x2691
                }
                x2561 = x2693
                val x2694 = if (x2687) {
                  true
                } else {
                  false
                }
                x2562 = x2694
                val x2677 = if (x2670) {
                  x2559
                } else {
                  val x2671 = if (x2666) {
                    x2597
                  } else {
                    x2667
                  }
                  x2671
                }
                val x2688 = if (x2676) {
                  x2677
                } else {
                  x2677
                }
                val x2695 = if (x2687) {
                  x2688
                } else {
                  x2688
                }
                x2563 = x2695
                ()
              } else {
                x2561 = null
                x2562 = true
                x2563 = x2559
                ()
              }
              x2705
            }
            x2707
          } else {
            val x2709 = x2595 >= '0'
            val x2710 = x2595 <= '9'
            val x2711 = x2709 && x2710
            val x2803 = if (x2711) {
              val x2712 = x2595 - '0'
              val x2713 = x2712.toInt
              var x2715: Int = x2713
              var x2716: Boolean = false
              val x2597 = x2559 + 1
              var x2717: Int = x2597
              var x2719: Int = -1
              var x2720: Boolean = true
              var x2721: Int = x2597
              val x2762 = while ({
                val x2722 = x2720
                val x2723 = x2719
                val x2724 = x2721
                val x2725 = x2723 != x2724
                val x2726 = x2722 && x2725
                x2726
              }) {
                val x2728 = x2721
                x2719 = x2728
                val x2730 = x2728 >= x25
                val x2760 = if (x2730) {
                  x2720 = false
                  ()
                } else {
                  val x2735 = x0(x2728)
                  val x2736 = x2735 >= '0'
                  val x2737 = x2735 <= '9'
                  val x2738 = x2736 && x2737
                  val x2758 = if (x2738) {
                    val x2744 = x2715
                    val x2745 = x2716
                    val x2746 = x2717
                    val x2741 = x2735 - '0'
                    val x2742 = x2741.toInt
                    val x2748 = x2744 * 10
                    val x2749 = x2748 + x2742
                    x2715 = x2749
                    x2716 = false
                    val x2739 = x2728 + 1
                    x2717 = x2739
                    x2721 = x2739
                    ()
                  } else {
                    x2720 = false
                    ()
                  }
                  x2758
                }
                x2760
              }
              val x2763 = x2715
              val x2764 = x2716
              val x2765 = x2717
              val x2768 = if (x2764) {
                true
              } else {
                x2764
              }
              val x2774 = if (x2768) {
                true
              } else {
                false
              }
              val x2785 = if (x2774) {
                true
              } else {
                false
              }
              val x2791 = if (x2785) {
                null
              } else {
                val x2784 = if (x2774) {
                  0
                } else {
                  val x2773 = if (x2768) {
                    null
                  } else {
                    val x2767 = if (x2764) {
                      0
                    } else {
                      x2763
                    }
                    val x2771 = new Tuple2OptionCharInt(x2056, x2767)
                    x2771
                  }
                  val x2778 = x2773._1
                  val x2780 = x2778.defined
                  val x2782 = if (x2780) {
                    val x2779 = x2773._2
                    val x2781 = -1 * x2779
                    x2781
                  } else {
                    val x2779 = x2773._2
                    x2779
                  }
                  x2782
                }
                val x2789 = new Anon6507737(3, x2784)
                x2789
              }
              x2561 = x2791
              val x2792 = if (x2785) {
                true
              } else {
                false
              }
              x2562 = x2792
              val x2775 = if (x2768) {
                x2559
              } else {
                val x2769 = if (x2764) {
                  x2559
                } else {
                  x2765
                }
                x2769
              }
              val x2786 = if (x2774) {
                x2775
              } else {
                x2775
              }
              val x2793 = if (x2785) {
                x2786
              } else {
                x2786
              }
              x2563 = x2793
              ()
            } else {
              x2561 = null
              x2562 = true
              x2563 = x2559
              ()
            }
            x2803
          }
          x2805
        }
        x2807
      } else {
        val x2809 = x2584.res
        x2561 = x2809
        x2562 = x2585
        val x2812 = x2584.next
        x2563 = x2812
        ()
      }
      val x2816 = x2561
      val x2817 = x2562
      val x2818 = x2563
      val x2819 = new ParseResultAnon6507737(x2816, x2817, x2818)
      x2819: ParseResultAnon6507737
    }
    val x2846 = { x2828: (Int) =>
      var x2830: Anon6507737 = null
      var x2831: Boolean = true
      var x2832: Int = x2828
      val x2834 = x2821(x2828)
      val x2835 = x2834.res
      x2830 = x2835
      val x2837 = x2834.empty
      x2831 = x2837
      val x2839 = x2834.next
      x2832 = x2839
      val x2841 = x2830
      val x2842 = x2831
      val x2843 = x2832
      val x2844 = new ParseResultAnon6507737(x2841, x2842, x2843)
      x2844: ParseResultAnon6507737
    }
    val x2925 = { x2883: (Int) =>
      var x2885: Char = ' '
      var x2886: Boolean = true
      var x2887: Int = x2883
      val x2889 = x2883 >= x25
      val x2919 = if (x2889) {
        x2885 = ' '
        x2886 = true
        x2887 = x2883
        ()
      } else {
        val x2894 = x0(x2883)
        val x2895 = x2894 == '\\'
        val x2917 = if (x2895) {
          val x2896 = x2883 + 1
          val x2898 = x2896 >= x25
          val x2911 = if (x2898) {
            x2885 = ' '
            x2886 = true
            x2887 = x2896
            ()
          } else {
            val x2904 = x0(x2896)
            x2885 = x2904
            x2886 = false
            val x2905 = x2896 + 1
            x2887 = x2905
            ()
          }
          x2911
        } else {
          x2885 = ' '
          x2886 = true
          x2887 = x2883
          ()
        }
        x2917
      }
      val x2920 = x2885
      val x2921 = x2886
      val x2922 = x2887
      val x2923 = new ParseResultChar(x2920, x2921, x2922)
      x2923: ParseResultChar
    }
    val x2962 = { x2877: (Int) =>
      var x2879: Char = ' '
      var x2880: Boolean = true
      var x2881: Int = x2877
      val x2926 = x2925(x2877)
      val x2927 = x2926.empty
      val x2956 = if (x2927) {
        val x2928 = x2877 >= x25
        val x2948 = if (x2928) {
          x2879 = ' '
          x2880 = true
          x2881 = x2877
          ()
        } else {
          val x2933 = x0(x2877)
          val x2935 = x2933 == '"'
          val x2946 = if (x2935) {
            x2879 = ' '
            x2880 = true
            x2881 = x2877
            ()
          } else {
            x2879 = x2933
            x2880 = false
            val x2940 = x2877 + 1
            x2881 = x2940
            ()
          }
          x2946
        }
        x2948
      } else {
        val x2950 = x2926.res
        x2879 = x2950
        x2880 = x2927
        val x2953 = x2926.next
        x2881 = x2953
        ()
      }
      val x2957 = x2879
      val x2958 = x2880
      val x2959 = x2881
      val x2960 = new ParseResultChar(x2957, x2958, x2959)
      x2960: ParseResultChar
    }
    val x3209 = { x2822: (Int) =>
      var x2824: Anon6507737 = null
      var x2825: Boolean = true
      var x2826: Int = x2822
      val x2847 = x2846(x2822)
      val x2848 = x2847.empty
      val x3203 = if (x2848) {
        val x2849 = x2822 >= x25
        val x3195 = if (x2849) {
          x2824 = null
          x2825 = true
          x2826 = x2822
          ()
        } else {
          val x2856 = x0(x2822)
          val x2857 = x2856 == '"'
          val x3193 = if (x2857) {
            var x2862: java.lang.String = ""
            var x2863: Boolean = false
            val x2858 = x2822 + 1
            var x2864: Int = x2858
            var x2866: Int = -1
            var x2867: Boolean = true
            var x2868: Int = x2858
            val x2981 = while ({
              val x2869 = x2867
              val x2870 = x2866
              val x2871 = x2868
              val x2872 = x2870 != x2871
              val x2873 = x2869 && x2872
              x2873
            }) {
              val x2875 = x2868
              x2866 = x2875
              val x2963 = x2962(x2875)
              val x2964 = x2963.empty
              val x2979 = if (x2964) {
                x2867 = false
                ()
              } else {
                val x2967 = x2862
                val x2968 = x2863
                val x2969 = x2864
                x2862 = x2967
                x2863 = false
                val x2972 = x2963.next
                x2864 = x2972
                x2868 = x2972
                ()
              }
              x2979
            }
            val x2982 = x2862
            val x2983 = x2863
            val x2984 = x2864
            val x2989 = if (x2983) {
              true
            } else {
              false
            }
            val x3187 = if (x2989) {
              x2824 = null
              x2825 = true
              x2826 = x2858
              ()
            } else {
              val x2988 = if (x2983) {
                0
              } else {
                x2858
              }
              var x3002: Int = x2988
              val x3003 = x3002
              var x3004: Int = x3003
              var x3005: Int = 0
              val x3006 = x3005
              var x3007: Int = x3006
              val x2990 = if (x2983) {
                x2858
              } else {
                x2984
              }
              val x3008 = x2990 - x2988
              val x3009 = new Array[Char](x3008)
              val x3157 = while ({
                val x3010 = x3004
                val x3011 = x3010 < x2990
                x3011
              }) {
                val x3013 = x3004
                val x3014 = x0(x3013)
                val x3015 = x3014 == '\\'
                val x3016 = x3013 + 1
                val x3017 = x3016 < x2990
                val x3018 = x3015 && x3017
                val x3154 = if (x3018) {
                  val x3019 = x0(x3016)
                  val x3020 = x3019 == 'u'
                  val x3021 = x3013 + 5
                  val x3022 = x3021 < x2990
                  val x3023 = x3020 && x3022
                  val x3147 = if (x3023) {
                    val x3109 = x3007
                    val x3024 = x3013 + 2
                    val x3025 = x0(x3024)
                    val x3026 = x3025 >= '0'
                    val x3027 = x3025 < '9'
                    val x3028 = x3026 && x3027
                    val x3043 = if (x3028) {
                      val x3029 = x3025 - '0'
                      val x3030 = x3029.toInt
                      x3030
                    } else {
                      val x3031 = x3025 >= 'A'
                      val x3032 = x3025 < 'F'
                      val x3033 = x3031 && x3032
                      val x3042 = if (x3033) {
                        val x3034 = x3025 - '7'
                        val x3035 = x3034.toInt
                        x3035
                      } else {
                        val x3036 = x3025 >= 'a'
                        val x3037 = x3025 < 'f'
                        val x3038 = x3036 && x3037
                        val x3041 = if (x3038) {
                          val x3039 = x3025 - 'W'
                          val x3040 = x3039.toInt
                          x3040
                        } else {
                          0
                        }
                        x3041
                      }
                      x3042
                    }
                    val x3044 = x3043 * 4096
                    val x3045 = x3013 + 3
                    val x3046 = x0(x3045)
                    val x3047 = x3046 >= '0'
                    val x3048 = x3046 < '9'
                    val x3049 = x3047 && x3048
                    val x3064 = if (x3049) {
                      val x3050 = x3046 - '0'
                      val x3051 = x3050.toInt
                      x3051
                    } else {
                      val x3052 = x3046 >= 'A'
                      val x3053 = x3046 < 'F'
                      val x3054 = x3052 && x3053
                      val x3063 = if (x3054) {
                        val x3055 = x3046 - '7'
                        val x3056 = x3055.toInt
                        x3056
                      } else {
                        val x3057 = x3046 >= 'a'
                        val x3058 = x3046 < 'f'
                        val x3059 = x3057 && x3058
                        val x3062 = if (x3059) {
                          val x3060 = x3046 - 'W'
                          val x3061 = x3060.toInt
                          x3061
                        } else {
                          0
                        }
                        x3062
                      }
                      x3063
                    }
                    val x3065 = x3064 * 256
                    val x3066 = x3044 + x3065
                    val x3067 = x3013 + 4
                    val x3068 = x0(x3067)
                    val x3069 = x3068 >= '0'
                    val x3070 = x3068 < '9'
                    val x3071 = x3069 && x3070
                    val x3086 = if (x3071) {
                      val x3072 = x3068 - '0'
                      val x3073 = x3072.toInt
                      x3073
                    } else {
                      val x3074 = x3068 >= 'A'
                      val x3075 = x3068 < 'F'
                      val x3076 = x3074 && x3075
                      val x3085 = if (x3076) {
                        val x3077 = x3068 - '7'
                        val x3078 = x3077.toInt
                        x3078
                      } else {
                        val x3079 = x3068 >= 'a'
                        val x3080 = x3068 < 'f'
                        val x3081 = x3079 && x3080
                        val x3084 = if (x3081) {
                          val x3082 = x3068 - 'W'
                          val x3083 = x3082.toInt
                          x3083
                        } else {
                          0
                        }
                        x3084
                      }
                      x3085
                    }
                    val x3087 = x3086 * 16
                    val x3088 = x3066 + x3087
                    val x3089 = x0(x3021)
                    val x3090 = x3089 >= '0'
                    val x3091 = x3089 < '9'
                    val x3092 = x3090 && x3091
                    val x3107 = if (x3092) {
                      val x3093 = x3089 - '0'
                      val x3094 = x3093.toInt
                      x3094
                    } else {
                      val x3095 = x3089 >= 'A'
                      val x3096 = x3089 < 'F'
                      val x3097 = x3095 && x3096
                      val x3106 = if (x3097) {
                        val x3098 = x3089 - '7'
                        val x3099 = x3098.toInt
                        x3099
                      } else {
                        val x3100 = x3089 >= 'a'
                        val x3101 = x3089 < 'f'
                        val x3102 = x3100 && x3101
                        val x3105 = if (x3102) {
                          val x3103 = x3089 - 'W'
                          val x3104 = x3103.toInt
                          x3104
                        } else {
                          0
                        }
                        x3105
                      }
                      x3106
                    }
                    val x3108 = x3088 + x3107
                    val x3110 = x3108.toChar
                    val x3111 = x3009(x3109) = x3110
                    val x3112 = x3004 += 4
                    ()
                  } else {
                    val x3114 = x3019 == 'b'
                    val x3145 = if (x3114) {
                      val x3115 = x3007
                      val x3116 = x3009(x3115) = ''
                      x3116
                    } else {
                      val x3118 = x3019 == 'f'
                      val x3143 = if (x3118) {
                        val x3119 = x3007
                        val x3120 = x3009(x3119) = ''
                        x3120
                      } else {
                        val x3122 = x3019 == 't'
                        val x3141 = if (x3122) {
                          val x3123 = x3007
                          val x3124 = x3009(x3123) = '	'
                          x3124
                        } else {
                          val x3126 = x3019 == 'n'
                          val x3139 = if (x3126) {
                            val x3127 = x3007
                            val x3128 = x3009(x3127) = '\n'
                            x3128
                          } else {
                            val x3130 = x3019 == 'r'
                            val x3137 = if (x3130) {
                              val x3131 = x3007
                              val x3132 = x3009(x3131) = '
'
                              x3132
                            } else {
                              val x3134 = x3007
                              val x3135 = x3009(x3134) = x3019
                              x3135
                            }
                            x3137
                          }
                          x3139
                        }
                        x3141
                      }
                      x3143
                    }
                    x3145
                  }
                  val x3148 = x3004 += 2
                  ()
                } else {
                  val x3150 = x3007
                  val x3151 = x3009(x3150) = x3014
                  val x3152 = x3004 += 1
                  ()
                }
                val x3155 = x3007 += 1
                ()
              }
              val x3158 = x3007
              val x3159 = new Anon1680061013(x3009, 0, x3158)
              val x3160 = new String(x3159.input, x3159.start, x3159.length)
              val x3162 = x2990 >= x25
              val x3185 = if (x3162) {
                x2824 = null
                x2825 = true
                x2826 = x2822
                ()
              } else {
                val x3168 = x0(x2990)
                val x3169 = x3168 == '"'
                val x3183 = if (x3169) {
                  val x3173 = new Anon6507737(5, x3160)
                  x2824 = x3173
                  x2825 = false
                  val x3170 = x2990 + 1
                  x2826 = x3170
                  ()
                } else {
                  x2824 = null
                  x2825 = true
                  x2826 = x2822
                  ()
                }
                x3183
              }
              x3185
            }
            x3187
          } else {
            x2824 = null
            x2825 = true
            x2826 = x2822
            ()
          }
          x3193
        }
        x3195
      } else {
        val x3197 = x2847.res
        x2824 = x3197
        x2825 = x2848
        val x3200 = x2847.next
        x2826 = x3200
        ()
      }
      val x3204 = x2824
      val x3205 = x2825
      val x3206 = x2826
      val x3207 = new ParseResultAnon6507737(x3204, x3205, x3206)
      x3207: ParseResultAnon6507737
    }
    x3257 = { x3210: (Int) =>
      var x3212: Anon6507737 = null
      var x3213: Boolean = true
      var x3214: Int = x3210
      val x3235 = x3234(x3210)
      val x3236 = x3235.empty
      val x3251 = if (x3236) {
        val x3237 = x3209(x3210)
        val x3238 = x3237.res
        x3212 = x3238
        val x3240 = x3237.empty
        x3213 = x3240
        val x3242 = x3237.next
        x3214 = x3242
        ()
      } else {
        val x3245 = x3235.res
        x3212 = x3245
        x3213 = x3236
        val x3248 = x3235.next
        x3214 = x3248
        ()
      }
      val x3252 = x3212
      val x3253 = x3213
      val x3254 = x3214
      val x3255 = new ParseResultAnon6507737(x3252, x3253, x3254)
      x3255: ParseResultAnon6507737
    }
    var x2: Anon6507737 = null
    var x3: Boolean = true
    var x4: Int = -1
    val x3270 = x6(0)
    val x3271 = x3270.res
    x2 = x3271
    val x3273 = x3270.empty
    x3 = x3273
    val x3275 = x3270.next
    x4 = x3275
    val x3277 = x2
    val x3278 = x3
    val x3279 = x4
    val x3280 = new ParseResultAnon6507737(x3277, x3278, x3279)
    x3280*/
	null
  }
}
/**
 * ***************************************
 * End of Generated Code
 * *****************************************
 */