package com.gildedrose

class BackstagePass(private var pass: Item) : ItemWithRules(pass) {

    override fun update() {
        when {
            pass.sellIn <= 0 -> pass.quality = 0

            pass.sellIn in 1..5 -> pass.quality += 3

            pass.sellIn in 6..10 -> pass.quality += 2

            else -> pass.quality += 1
        }

        checkAndAdjustQualityToWithinBounds()

        pass.sellIn -= 1
    }

}