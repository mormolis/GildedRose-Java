package com.gildedrose;

public class CommonItem implements Sellable{

    private Item item;

    public CommonItem(String name, int quality, int sellIn) {
        this.item = new Item(name, quality, sellIn);
    }

    @Override
    public void updateQuality() {
        if (item.quality > 0) {
            item.quality--;
        }
        if (item.quality > 0 && item.sellIn <= 0){
            item.quality--;
        }
    }

    @Override
    public void updateSellIn() {
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
