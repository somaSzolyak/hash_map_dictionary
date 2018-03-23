package com.codecool.hash_map_dict;


import java.util.LinkedList;

public class HashMap {
    private int bucketsize = 16;
    private LinkedList<KeyValue>[] elements = new LinkedList[bucketsize];
    private int elementsNumber = 0;

    public int getBucketsize() {
        return bucketsize;
    }

    public LinkedList<KeyValue>[] getElements() {
        return elements;
    }

    public int getElementsNumber() {
        return elementsNumber;
    }

    public void add(String key, int value) {

        // find out which position of the primitive array to use:
        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];


        // If the key already exists throw an error.
        if (list != null) {
            for (KeyValue obj : list) {
                if (obj.key.equals(key)) {
                    throw new IllegalArgumentException("This key is already taken");
                }
            }
        } else {
            list = new LinkedList<KeyValue>();
        }

        // Make a new instance of the KeyValue class, fill it with the key, value parameters, then add it to the list.
        KeyValue keyValue = new KeyValue(key, value);
        list.add(keyValue);
        elements[position] = list;
        elementsNumber++;

        resizeIfNeeded();
    }

    public int getValue(String key) {
        KeyValue result = null;
        // 1. Calculate the hash of the key. This defines which element to get from the "elements" array
        int pos = getHash(key);
        LinkedList<KeyValue> list = elements[pos];
        if (list == null) {
            throw new IllegalArgumentException("No such key");
        }
        // 2. Find in the List in this position the KeyValue element that has this key, then return its value.
        //    If none of the items in the list has this key throw error.
        for (KeyValue kv : list) {
            if (kv.key.equals(key)) {
                result = kv;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException("No such key");
        } else {
            return result.value;
        }
    }

    private int getHash(String key) {
        // This function converts somehow the key to an integer between 0 and bucketSize
        // In C# GetHashCode(), in Java hashCode() is a function of Object, so all non-primitive types
        // can easily be converted to an integer.
        return key.hashCode() % bucketsize;
    }

    private void resizeIfNeeded() {
    // If it holds more elements than bucketSize * 2, destroy and recreate it
    // with the double size of the elements array.
    // if it holds less elements than bucketSize / 2, destroy and recreate it
    // with half size of the elements array.
        if (elementsNumber > bucketsize*2) {
            bucketsize *= 2;
            LinkedList<KeyValue>[] newElements = new LinkedList[bucketsize];
            for (int i=0 ; i < elements.length; i++) {
                if (elements[i] == null) continue;
                newElements[i] = elements[i];
            }
            elements = newElements;
        }

        if (elementsNumber < bucketsize/2 & elementsNumber > 16) {
            bucketsize /= 2;
            LinkedList<KeyValue>[] newElements = new LinkedList[bucketsize];
            for (int i=0 ; i < elements.length; i++) {
                if (elements[i] == null) continue;
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    public void clearAll() {
        bucketsize = 16;
        elements = new LinkedList[bucketsize];
        elementsNumber = 0;
    }

    public boolean remove(String key) {
        int pos = getHash(key);
        LinkedList<KeyValue> list = elements[pos];
        for (KeyValue keyValue: list) {
            if (keyValue.key.equals(key)) {
                list.remove(keyValue);
                return true;
            }
        }
        throw new IllegalArgumentException("didn't find the key");
    }

// + other functions, like clearAll(), delete(),..

}

