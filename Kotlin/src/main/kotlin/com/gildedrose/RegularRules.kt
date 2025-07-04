package com.gildedrose

open class RegularRules(private var item : Item) {

    open fun updateQuality() {
        if (item.sellIn < 0) {
            item.quality -= 2
        } else {
            item.quality -= 1
        }
        qualityCheck()
    }

    fun qualityCheck() {
        when {
            item.quality < 0 -> item.quality = 0
            item.quality > 50 -> item.quality = 50
            else -> {}
        }
    }


    fun updateSellIn() {
        item.sellIn -= 1
    }

}