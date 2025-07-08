package com.gildedrose

abstract class ItemWithRules(private var item: Item) {

    abstract fun update()

    fun checkAndAdjustQualityToWithinBounds() {
        when {
            item.quality < 0 -> item.quality = 0
            item.quality > 50 -> item.quality = 50
            else -> {}
        }
    }
}