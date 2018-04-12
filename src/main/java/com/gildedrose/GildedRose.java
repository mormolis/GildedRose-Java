package com.gildedrose;


class GildedRose {
    Sellable[] items;

    public GildedRose(Sellable[] items) {
        this.items = items;

    }



    public void updateQuality() {
        for (Sellable item : items){
            item.updateQuality();
            item.updateSellIn();
        }
    }
}
