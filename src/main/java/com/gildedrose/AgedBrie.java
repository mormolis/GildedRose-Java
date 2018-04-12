package com.gildedrose;

public class AgedBrie implements Sellable {

    private Item item;

    public AgedBrie(int sellIn, int quality){
        item = new Item("Aged Brie", sellIn, quality );
    }

    @Override
    public void updateQuality() {
        if(item.quality < 50) item.quality++;

    }

    @Override
    public void updateSellIn(){
        item.sellIn--;
    }

    @Override
    public int getQuality() {
        return item.quality;
    }

    @Override
    public int getSellIn() {
        return item.sellIn;
    }

}
