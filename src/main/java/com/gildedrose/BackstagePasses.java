package com.gildedrose;

public class BackstagePasses implements Sellable {

    private Item item;

    public BackstagePasses(int quality, int sellin) {
        this.item = new Item ("Backstage passes to a TAFKAL80ETC concert", quality, sellin);
    }

    @Override
    public void updateQuality() {
        if(item.sellIn >= 0 && item.quality < 50){
            if(item.sellIn <= 10 && item.sellIn > 5){
                item.quality+=2;
            } else if (item.sellIn <= 5 && item.sellIn >= 0){
                item.quality+=3;
            } else {
                item.quality++;
            }
        }
        if(item.sellIn<0) item.quality = 0;
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
