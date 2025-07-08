package com.gildedrose

class AgedBrie(private val cheese: Item) : ItemWithRules(cheese) {

    override fun update() {
        if (cheese.sellIn < 0) {
            cheese.quality += 2
        } else {
            cheese.quality += 1
        }

        checkAndAdjustQualityToWithinBounds()

        cheese.sellIn -= 1
    }



}