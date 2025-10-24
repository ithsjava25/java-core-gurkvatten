package com.example;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Category {
    private static final Map<String, Category> CACHE = new ConcurrentHashMap<>();

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmedName = name.trim();
        if (trimmedName.isBlank()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }

        String normalizedName = trimmedName.substring(0, 1).toUpperCase(Locale.ROOT) +
                trimmedName.substring(1).toLowerCase(Locale.ROOT);

        return CACHE.computeIfAbsent(normalizedName, Category::new);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}