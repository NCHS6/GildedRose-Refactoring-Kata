package com.gildedrose

class AgedBrie(private val cheese: Item) : RegularRules(cheese) {

    override fun updateQuality() {
        if (cheese.sellIn < 0) {
            cheese.quality += 2
        } else {
            cheese.quality += 1
        }
        super.qualityCheck()
    }



}