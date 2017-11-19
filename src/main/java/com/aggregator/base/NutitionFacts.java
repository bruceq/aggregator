package com.aggregator.base;

/**
 * Created by 鑫 on 2017/6/23.
 */
public class NutitionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;

    public static class Builder {
        private final int serverSize;
        private final int servings;

        private int calories = 0;
        private int fat = 0;

        public Builder(int serverSize, int servings) {
            this.serverSize = serverSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public NutitionFacts build() {
            return new NutitionFacts(this);
        }

    }

    private NutitionFacts(Builder builder) {
        servingSize = builder.serverSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
    }

    public static void main(String[] args) {
        NutitionFacts cocaCola = new Builder(240, 8).calories(100).fat(35).build();
        NutitionFacts Sprite = new Builder(140, 10).calories(200).fat(55).build();
        System.out.println(cocaCola.calories);
        System.out.println(Sprite.calories);
    }

}


