package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {

        items.forEach { item ->
            when (item.name) {

                "Aged Brie" ->
                    if (item.sellIn < 0) {
                        item.quality += 2
                    } else {
                        item.quality += 1
                    }

                "Sulfuras, Hand of Ragnaros" -> {}

                "Backstage passes to a TAFKAL80ETC concert" ->
                    when {
                        item.sellIn <= 0 -> item.quality = 0
                        item.sellIn in 1..5 -> item.quality += 3
                        item.sellIn in 6..10 -> item.quality += 2
                        else -> item.quality += 1
                    }

                "Conjured" ->
                    if (item.sellIn < 0) {
                        item.quality -= 4
                    } else {
                        item.quality -= 2
                    }

                else ->
                    if (item.sellIn < 0) {
                        item.quality -= 2
                    } else {
                        item.quality -= 1
                    }
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.sellIn -= 1
                when {
                    item.quality < 0 -> item.quality = 0
                    item.quality > 50 -> item.quality = 50
                    else -> {}
                }
            }

        }
    }

}

