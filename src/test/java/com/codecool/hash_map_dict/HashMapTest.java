package com.codecool.hash_map_dict;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private HashMap hashMap= new HashMap();


    @org.junit.jupiter.api.Test
    void add() {
        int elementNumbers = hashMap.getElementsNumber();
        hashMap.add("qwe", 2);
        assertEquals(elementNumbers + 1, hashMap.getElementsNumber());
    }

    @org.junit.jupiter.api.Test
    void getValue() {
        hashMap.add("asd", 1);
        assertEquals(1, hashMap.getValue("asd"));
    }

    @org.junit.jupiter.api.Test
    void clearAll() {
        hashMap.clearAll();
        assertEquals(0, hashMap.getElementsNumber());
    }

    @Test
    void clearAllThroughGetValue() {
        hashMap.add("íyx", 3);
        hashMap.clearAll();
        assertThrows( IllegalArgumentException.class, () ->{
            hashMap.getValue("íyx");
        });
    }

    @org.junit.jupiter.api.Test
    void remove() {
        hashMap.add("qaí", 4);
        hashMap.remove("qaí");
        assertThrows( IllegalArgumentException.class, () ->{
            hashMap.getValue("qaí");
        });
    }
}