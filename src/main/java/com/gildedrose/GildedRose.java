package com.gildedrose;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

class GildedRose {
    Item[] items;

    private final String AGED_BRIE = "Aged Brie";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final String BACKSTAGE_TICKETS = "Backstage passes to a TAFKAL80ETC concert";

    private final List<String> specialItems = new LinkedList<>();


    public GildedRose(Item[] items) {
        this.items = items;
        populateSpecialItems();

    }

    private void populateSpecialItems(){
        specialItems.add(AGED_BRIE);
        specialItems.add(SULFURAS);
        specialItems.add(BACKSTAGE_TICKETS);

    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            if(items[i].name.equals(AGED_BRIE)){
                if(items[i].quality < 50) items[i].quality++;
                items[i].sellIn--;
                continue;
            }

            if(items[i].name.equals(BACKSTAGE_TICKETS)){
                if(items[i].quality < 50){
                    if(items[i].sellIn <= 10 && items[i].sellIn > 5){
                        items[i].quality+=2;
                    } else if (items[i].sellIn <= 5 && items[i].sellIn > 0){
                        items[i].quality+=3;
                    } else if (items[i].sellIn <= 0){
                        items[i].quality = 0;
                    } else {
                        items[i].quality++;
                    }

                }
                items[i].sellIn--;
                continue;
            }

            if(items[i].name.equals(SULFURAS)){
                continue;
            }

            if(!specialItems.contains(items[i].name)){
                items[i].sellIn--;
                if (items[i].quality > 0) {
                    items[i].quality--;
                }
                if (items[i].quality > 0 && items[i].sellIn < 0){
                    items[i].quality--;
                }
                continue;
            }




        }
    }
}