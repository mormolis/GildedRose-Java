package com.gildedrose;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {



//  - All items have a SellIn value which denotes the number of days we have to sell the item
//	- All items have a Quality value which denotes how valuable the item is
//	- At the end of each day our system lowers both values for every item
//
//    Pretty simple, right? Well this is where it gets interesting:
//
//  - Once the sell by date has passed, Quality degrades twice as fast
//	- The Quality of an item is never negative
//	- "Aged Brie" actually increases in Quality the older it gets
//	- The Quality of an item is never more than 50
//            - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality

//	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
//    Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
//    Quality drops to 0 after the concert
//
//    We have recently signed a supplier of conjured items. This requires an update to our system:
//
//            - "Conjured" items degrade in Quality twice as fast as normal items

    private final int COMMON_ITEM1 = 0;
    private final int COMMON_ITEM2 = 1;
    private final int AGED_BRIE1 = 2;
    private final int SULFURAS = 3;
    private final int BACKSTAGE_PASSES = 4;
    private final int BACKSTAGE_PASSES2 = 5;





    private Item[] items;
    private GildedRose gildedRose;

    @Before
    public void init(){
        items =  new Item[] {   new Item("Kakakia", 10, 10),
                                new Item("Fatakia", 5, 10),
                                new Item("Aged Brie", 10, 10),
                                new Item("Sulfuras, Hand of Ragnaros", 10, 10),
                                new Item("Backstage passes to a TAFKAL80ETC concert", 20, 10),
                                new Item("Backstage passes to a TAFKAL80ETC concert", 20, 49)};
        gildedRose = new GildedRose(items);
    }



    @Test
    public void commonItems_qualityDegradesAsSellInDegrades(){

        gildedRose.updateQuality();

        Assert.assertTrue(items[COMMON_ITEM1].quality == 9);
        Assert.assertTrue(items[COMMON_ITEM1].sellIn == 9);

        Assert.assertTrue(items[COMMON_ITEM2].quality == 9);
        Assert.assertTrue(items[COMMON_ITEM2].sellIn == 4);

        gildedRose.updateQuality();

        Assert.assertTrue(items[COMMON_ITEM1].quality == 8);
        Assert.assertTrue(items[COMMON_ITEM1].sellIn == 8);
        Assert.assertTrue(items[COMMON_ITEM2].quality == 8);
        Assert.assertTrue(items[COMMON_ITEM2].sellIn == 3);

    }




    @Test
    public void commonItems_qualityDoesNotFallBellowZero(){
        for(int i = 0; i < 11; i++){
            gildedRose.updateQuality();
        }
        Assert.assertTrue(items[COMMON_ITEM1].quality == 0);
        Assert.assertTrue(items[COMMON_ITEM1].sellIn == -1);

        Assert.assertTrue(items[COMMON_ITEM2].quality == 0);
        Assert.assertTrue(items[COMMON_ITEM2].sellIn == -6);

    }


    @Test
    public void commonItems_qualityDegradesTwoTimesFasterAfterSellInExpired(){
        for(int i = 0; i < 5; i++){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[COMMON_ITEM2].quality == 5);
        Assert.assertTrue(items[COMMON_ITEM2].sellIn == 0);

        gildedRose.updateQuality();

        Assert.assertTrue(items[COMMON_ITEM2].quality == 3);
        Assert.assertTrue(items[COMMON_ITEM2].sellIn == -1);



    }

    @Test
    public void agedBrie_increasesItsQualityAsItAges(){

        Assert.assertTrue(items[AGED_BRIE1].quality == 10);
        Assert.assertTrue(items[AGED_BRIE1].sellIn == 10);

        gildedRose.updateQuality();

        Assert.assertTrue(items[AGED_BRIE1].quality == 11);
        Assert.assertTrue(items[AGED_BRIE1].sellIn == 9);

    }

    @Test
    public void agedBrie_qualityCannotGetMoreThan50(){


        for (int i=0; i<40; i++){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[AGED_BRIE1].quality == 50);
        Assert.assertTrue(items[AGED_BRIE1].sellIn == -30);

        gildedRose.updateQuality();

        Assert.assertTrue(items[AGED_BRIE1].quality == 50);
        Assert.assertTrue(items[AGED_BRIE1].sellIn == -31);

    }

    @Test
    public void sulfuras_isNotAffectedByTimeOrQualityDegradation(){
        gildedRose.updateQuality();
        Assert.assertTrue(items[SULFURAS].quality == 10);
        Assert.assertTrue(items[SULFURAS].sellIn == 10);

        gildedRose.updateQuality();
        Assert.assertTrue(items[SULFURAS].quality == 10);
        Assert.assertTrue(items[SULFURAS].sellIn == 10);
    }

    //	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
//    Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
//    Quality drops to 0 after the concert
    @Test
    public void backstage_increasesQuality10DaysOrMore(){
        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 11);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 19);
        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 12);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 18);

    }

    @Test
    public void backstage_increasesQuality10Days(){

        for(int i=0; i<10; i++){
            gildedRose.updateQuality();
        }
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 20);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 10);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 22);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 9);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 24);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 8);

    }

    @Test
    public void backstage_increasesQuality5Days(){

        for(int i=0; i<14; i++){
            gildedRose.updateQuality();
        }
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 28);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 6);

        gildedRose.updateQuality();

        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 30);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 5);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 33);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 4);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 36);
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 3);
    }

    @Test
    public void backstage_qualityDoesNotIncreaseMoreThan50(){
        for(int i = 0; i < 10; i++){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[BACKSTAGE_PASSES2].quality == 50);
        Assert.assertTrue(items[BACKSTAGE_PASSES2].sellIn == 10);
    }

    @Test
    public void backstage_qualityDropsTo0AfterConcert(){
        while(items[BACKSTAGE_PASSES].sellIn > 0){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == 0);
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 45);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == -1);
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 0);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].sellIn == -2);
        Assert.assertTrue(items[BACKSTAGE_PASSES].quality == 0);
    }




}

