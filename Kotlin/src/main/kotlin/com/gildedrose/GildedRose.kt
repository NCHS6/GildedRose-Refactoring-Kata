package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {

        items.forEach { item ->
            when {

                item.name == "Aged Brie" -> {
                    val agedBrie = AgedBrie(item)
                    agedBrie.updateQuality()
                    agedBrie.updateSellIn()
                }

                item.name == "Sulfuras, Hand of Ragnaros" -> {}

                item.name == "Backstage passes to a TAFKAL80ETC concert" -> {
                    val backstagePass = BackstagePass(item)
                    backstagePass.updateQuality()
                    backstagePass.updateSellIn()
                }


                item.name.startsWith("Conjured" ) -> {
                    val conjuredItems = ConjuredItems(item)
                    conjuredItems.updateQuality()
                    conjuredItems.updateSellIn()
                }

                else -> {
                    val regularRules = RegularRules(item)
                    regularRules.updateQuality()
                    regularRules.updateSellIn()
                }
            }
        }
    }
}

