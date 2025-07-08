package com.gildedrose

class RegularRules(private var item : Item): ItemWithRules(item) {

    override fun update() {

        if (item.sellIn < 0) {
            item.quality -= 2
        } else {
            item.quality -= 1
        }

        checkAndAdjustQualityToWithinBounds()

        item.sellIn -= 1
    }
}