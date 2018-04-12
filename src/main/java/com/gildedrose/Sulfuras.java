package com.gildedrose;

public class Sulfuras implements Sellable{

    private Item item;

    public Sulfuras() {
        this.item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
    }

    @Override
    public void updateQuality() {

    }

    @Override
    public void updateSellIn() {

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
