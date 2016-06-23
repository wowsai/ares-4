package com.mars.ares4.common;

import org.junit.Test;

public class FavoritesTest {
    @Test
    public void testFavorites() {
        Favorites favorites = new Favorites();
        favorites.putFavorite(Integer.class, 100);
        favorites.putFavorite(String.class, "Java");
        favorites.putFavorite(Boolean.class, true);
        boolean boolFavorite = favorites.getFavorite(Boolean.class);
        System.out.println(boolFavorite);
        System.out.println(favorites.getFavorite(String.class));
        System.out.println(favorites.getFavorite(Integer.class));
    }

    @Test
    public void testMin() {
        System.out.println(min(100, 10, 23, 2343, 2, 1));
    }

    private static int min(int first, int... rest) {
        int min = first;
        for(int num : rest) {
            if (num < min) min = num;
        }
        return min;
    }
}
