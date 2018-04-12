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





    private Sellable[] items;
    private GildedRose gildedRose;

    @Before
    public void init(){
        items =  new Sellable[] {   new CommonItem("Kakakia", 10, 10),
                                new CommonItem("Fatakia", 5, 10),
                                new AgedBrie( 10, 10),
                                new Sulfuras(),
                                new BackstagePasses( 20, 10),
                                new BackstagePasses( 20, 49)};
        gildedRose = new GildedRose(items);
    }



    @Test
    public void commonItems_qualityDegradesAsSellInDegrades(){

        gildedRose.updateQuality();

        Assert.assertTrue(items[COMMON_ITEM1].getQuality() == 9);
        Assert.assertTrue(items[COMMON_ITEM1].getSellIn() == 9);

        Assert.assertTrue(items[COMMON_ITEM2].getQuality() == 9);
        Assert.assertTrue(items[COMMON_ITEM2].getSellIn() == 4);

        gildedRose.updateQuality();

        Assert.assertTrue(items[COMMON_ITEM1].getQuality() == 8);
        Assert.assertTrue(items[COMMON_ITEM1].getSellIn() == 8);
        Assert.assertTrue(items[COMMON_ITEM2].getQuality() == 8);
        Assert.assertTrue(items[COMMON_ITEM2].getSellIn() == 3);

    }




    @Test
    public void commonItems_qualityDoesNotFallBellowZero(){
        for(int i = 0; i < 11; i++){
            gildedRose.updateQuality();
        }
        Assert.assertTrue(items[COMMON_ITEM1].getQuality() == 0);
        Assert.assertTrue(items[COMMON_ITEM1].getSellIn() == -1);

        Assert.assertTrue(items[COMMON_ITEM2].getQuality() == 0);
        Assert.assertTrue(items[COMMON_ITEM2].getSellIn() == -6);

    }


    @Test
    public void commonItems_qualityDegradesTwoTimesFasterAfterSellInExpired(){
        for(int i = 0; i < 5; i++){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[COMMON_ITEM2].getQuality() == 5);
        Assert.assertTrue(items[COMMON_ITEM2].getSellIn() == 0);

        gildedRose.updateQuality();

        Assert.assertTrue(items[COMMON_ITEM2].getQuality() == 3);
        Assert.assertTrue(items[COMMON_ITEM2].getSellIn() == -1);



    }

    @Test
    public void agedBrie_increasesItsQualityAsItAges(){

        Assert.assertTrue(items[AGED_BRIE1].getQuality() == 10);
        Assert.assertTrue(items[AGED_BRIE1].getSellIn() == 10);

        gildedRose.updateQuality();

        Assert.assertTrue(items[AGED_BRIE1].getQuality() == 11);
        Assert.assertTrue(items[AGED_BRIE1].getSellIn() == 9);

    }

    @Test
    public void agedBrie_qualityCannotGetMoreThan50(){


        for (int i=0; i<40; i++){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[AGED_BRIE1].getQuality() == 50);
        Assert.assertTrue(items[AGED_BRIE1].getSellIn() == -30);

        gildedRose.updateQuality();

        Assert.assertTrue(items[AGED_BRIE1].getQuality() == 50);
        Assert.assertTrue(items[AGED_BRIE1].getSellIn() == -31);

    }

    @Test
    public void sulfuras_isNotAffectedByTimeOrQualityDegradation(){
        gildedRose.updateQuality();
        Assert.assertTrue(items[SULFURAS].getQuality() == 80);
        Assert.assertTrue(items[SULFURAS].getSellIn() == 0);

        gildedRose.updateQuality();
        Assert.assertTrue(items[SULFURAS].getQuality() == 80);
        Assert.assertTrue(items[SULFURAS].getSellIn() == 0);
    }

    //	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
//    Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
//    Quality drops to 0 after the concert
    @Test
    public void backstage_increasesQuality10DaysOrMore(){
        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 11);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 19);
        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 12);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 18);

    }

    @Test
    public void backstage_increasesQuality10Days(){

        for(int i=0; i<10; i++){
            gildedRose.updateQuality();
        }
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 20);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 10);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 22);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 9);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 24);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 8);

    }

    @Test
    public void backstage_increasesQuality5Days(){

        for(int i=0; i<14; i++){
            gildedRose.updateQuality();
        }
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 28);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 6);

        gildedRose.updateQuality();

        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 30);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 5);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 33);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 4);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 36);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == 3);
    }

    @Test
    public void backstage_qualityDoesNotIncreaseMoreThan50(){
        for(int i = 0; i < 10; i++){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[BACKSTAGE_PASSES2].getQuality() == 50);
        Assert.assertTrue(items[BACKSTAGE_PASSES2].getSellIn() == 10);
    }

    @Test
    public void backstage_qualityDropsTo0AfterConcert(){
        while(items[BACKSTAGE_PASSES].getQuality() > 0){
            gildedRose.updateQuality();
        }

        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == -1);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 0);

        gildedRose.updateQuality();
        Assert.assertTrue(items[BACKSTAGE_PASSES].getSellIn() == -2);
        Assert.assertTrue(items[BACKSTAGE_PASSES].getQuality() == 0);
    }




}

