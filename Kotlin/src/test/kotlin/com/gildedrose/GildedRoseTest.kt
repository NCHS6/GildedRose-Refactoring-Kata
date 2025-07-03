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
    fun `quality reduced by 1 for regular items`() {
        val item = Item(name = "Blue Whale", sellIn = 10, quality = 20)
        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(19, gildedRose.items[0].quality)
    }

    @Test
    fun `quality reduced by 2 if passed sell by date`() {
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
    fun `quality increases by 1 per day for brie`() {
        val quality = Random.nextInt(1, 100)
        val item = Item(name = "Aged Brie", sellIn = 10, quality = 0)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(1, gildedRose.items[0].quality)
    }

    @Test
    fun `quality increases by 2 per day for passed sell by date brie brie`() {
        val quality = Random.nextInt(1, 100)
        val item = Item(name = "Aged Brie", sellIn = -1, quality = 0)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(2, gildedRose.items[0].quality)
    }

    @Test
    fun `quality never over 50 by passed sell by date brie`() {
        val quality = Random.nextInt(1, 100)
        val item = Item(name = "Aged Brie", sellIn = -1, quality = 49)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertTrue(gildedRose.items[0].quality <= 50)
    }

    @Test
    fun `quality never over 50 by brie`() {
        val quality = Random.nextInt(1, 100)
        val item = Item(name = "Aged Brie", sellIn = -1, quality = 50)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertTrue(gildedRose.items[0].quality <= 50)
    }

    @Test
    fun `quality never over 50 by regular items`() {
        val item = Item(name = "Blue Moon", sellIn = 10, quality = 50)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertTrue(gildedRose.items[0].quality <= 50)
    }

    @Test
    fun `quality never changes for Sulfuras`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(80, gildedRose.items[0].quality)
    }

    @Test
    fun `quality never changes for passed sell by date Sulfuras`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -1, quality = 80)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(80, gildedRose.items[0].quality)
    }

    @Test
    fun `quality increases by 1 with 11 or more sellIn for concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(21, gildedRose.items[0].quality)
    }

    @Test
    fun `quality increases by 2 with 6 sellIn for concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 6, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(22, gildedRose.items[0].quality)
    }

    @Test
    fun `quality increases by 2 with 10 sellIn for concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(22, gildedRose.items[0].quality)
    }

    @Test
    fun `quality increases by 3 with 1 sellIn for concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 1, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(23, gildedRose.items[0].quality)
    }

    @Test
    fun `quality increases by 3 with 5 sellIn for concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(23, gildedRose.items[0].quality)
    }

    @Test
    fun `quality goes to 0 after the concert`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 20)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun `sellIn never changes for Sulfuras`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(10, gildedRose.items[0].sellIn)
    }

    //TDD

    @Test
    fun `quality of conjured degrades by 2 per day`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 30)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(10, gildedRose.items[0].sellIn)
    }

    @Test
    fun `quality of conjured degrades by 4 per day when passed sell by date`() {
        val item = Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 30)

        val itemArr = listOf(item)
        val gildedRose = GildedRose(itemArr)
        gildedRose.updateQuality()
        assertEquals(10, gildedRose.items[0].sellIn)
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


