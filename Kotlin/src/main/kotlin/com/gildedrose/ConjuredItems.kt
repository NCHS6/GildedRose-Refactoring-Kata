package com.gildedrose

class ConjuredItems(private var item:Item) : RegularRules(item) {

    override fun updateQuality()  {

        if (item.sellIn < 0) {
            item.quality -= 2*2
        } else {
            item.quality -= 1 * 2
        }
        super.qualityCheck()
    }
}