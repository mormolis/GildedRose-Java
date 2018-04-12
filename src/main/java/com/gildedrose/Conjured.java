package com.gildedrose;

public class Conjured implements Sellable{

    private CommonItem item;

    public Conjured(int sellIn, int quality) {
        this.item = new CommonItem("Conjured Mana Cake", sellIn, quality);
    }

    @Override
    public void updateQuality() {
        item.updateQuality();
        item.updateQuality();
    }

    @Override
    public void updateSellIn() {
        item.updateSellIn();
    }

    @Override
    public int getQuality() {
        return item.getQuality();
    }

    @Override
    public int getSellIn() {
        return item.getSellIn();
    }
}
