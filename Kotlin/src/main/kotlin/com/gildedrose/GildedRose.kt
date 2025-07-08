package com.gildedrose

fun createItemWithRules(item: Item): ItemWithRules = when {
    item.name == "Aged Brie" -> AgedBrie(item)
    item.name == "Sulfuras, Hand of Ragnaros" -> Sulfuras(item)
    item.name.startsWith("Backstage passes") -> BackstagePass(item)
    item.name.startsWith("Conjured") -> ConjuredItems(item)
    else -> RegularRules(item)
}

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        items.map { createItemWithRules(it) }
            .forEach {
                it.update()
            }
    }
}

