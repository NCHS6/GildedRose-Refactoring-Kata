package com.gildedrose

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.BeforeTest

internal class GildedRoseTest {

    @Test
    fun `regular items - quality reduced by 1`() {
        val item = Item(name = "Blue Whale", sellIn = 10, quality = 20)
        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(19, gildedRose.items[0].quality)
    }

    @Test
    fun `regular items - quality reduced by 2 if passed sell by date`() {
        val quality = Random.nextInt(1, 50)
        val item = Item(name = "Blue Whale", sellIn = -2, quality = quality)
        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(quality-2, gildedRose.items[0].quality)
    }

    @Test
    fun `quality never becomes negative`() {
        val item = Item(name = "Blue Whale", sellIn = -1, quality = 0)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertTrue(gildedRose.items[0].quality >= 0)
    }

    @Test
    fun `brie - quality increases by 1 per day`() {
        val item = Item(name = "Aged Brie", sellIn = 10, quality = 0)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(1, gildedRose.items[0].quality)
    }

    @Test
    fun `brie - quality increases by 2 per day for passed sell by date`() {
        val item = Item(name = "Aged Brie", sellIn = -1, quality = 0)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(2, gildedRose.items[0].quality)
    }

    @Test
    fun `brie - quality never over 50 by passed sell by date`() {
        val item = Item(name = "Aged Brie", sellIn = -1, quality = 49)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertTrue(gildedRose.items[0].quality <= 50)
    }

    @Test
    fun `brie - quality never over 50`() {
        val item = Item(name = "Aged Brie", sellIn = -1, quality = 50)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertTrue(gildedRose.items[0].quality <= 50)
    }

    @Test
    fun `regular items - quality never over 50`() {
        val item = Item(name = "Blue Moon", sellIn = 10, quality = 50)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertTrue(gildedRose.items[0].quality <= 50)
    }

    @Test
    fun `Sulfuras - quality never changes`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(80, gildedRose.items[0].quality)
    }

    @Test
    fun `Sulfuras - quality never changes for passed sell by date`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -1, quality = 80)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(80, gildedRose.items[0].quality)
    }

    @Test
    fun `Sulfuras - sellIn never changes`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(10, gildedRose.items[0].sellIn)
    }

    @Test
    fun `Backstage pass - quality increases by 1 well before concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(21, gildedRose.items[0].quality)
    }

    @Test
    fun `Backstage pass - quality increases by 2 soon before concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 6, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(22, gildedRose.items[0].quality)
    }

    @Test
    fun `Backstage pass - quality increases by 2 with 10 days before concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(22, gildedRose.items[0].quality)
    }

    @Test
    fun `Backstage pass - quality increases by 3 the day before concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 1, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(23, gildedRose.items[0].quality)
    }

    @Test
    fun `Backstage pass - quality increases by 3 just before concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(23, gildedRose.items[0].quality)
    }

    @Test
    fun `Backstage pass - quality goes to 0 after the concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(0, gildedRose.items[0].quality)
    }

    //TDD

    @Test
    fun `Conjured Items - quality degrades by 2 per day`() {
        val item = Item(name = "Conjured Mana Cake", sellIn = 10, quality = 30)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(28, gildedRose.items[0].quality)
    }

    @Test
    fun `Conjured Items - quality degrades by 4 per day when passed sell by date`() {
        val item = Item(name = "Conjured Mana Cake", sellIn = -1, quality = 30)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(26, gildedRose.items[0].quality)
    }














//    @Test
//    fun `regular items reduction in quality with random inputs`() {
//        val item = mockk<Item>()
//        every { item.name } returns "Blue Whale"
//        every { item.sellIn } returns Random.nextInt(100)
//        every { item.sellIn = any() } just Runs
//        val quality = Random.nextInt(1, 100)
//        every { item.quality } returns quality
//        every { item.quality = any() } answers { item.quality - 1 }
//
//        val itemArr = listOf(item)
//        val gildedRose = GildedRose(itemArr)
//        gildedRose.updateQuality()
//        assertEquals(quality, gildedRose.items[0].quality)
//
//        verify { gildedRose.items[0].quality }
//    }


}


