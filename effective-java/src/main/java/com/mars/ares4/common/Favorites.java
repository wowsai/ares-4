package com.mars.ares4.common;

import com.google.common.collect.Maps;

import java.util.Map;

public class Favorites {
    private final Map<Class<?>, Object> favorites = Maps.newHashMap();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
