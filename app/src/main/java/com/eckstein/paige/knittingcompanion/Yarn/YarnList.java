package com.eckstein.paige.knittingcompanion.Yarn;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class YarnList implements List<Yarn> {
    private Yarn[] yarns;
    private int firstFreeSpot;

    public YarnList() {
        yarns = new Yarn[20];
        firstFreeSpot = 0;
    }

    /**
     * Returns contained Yarns as an array
     *
     * @return The array of objects, null Yarns removed
     */
    // Can't return the 'Yarns' array as it contains empty spaces
    public Yarn[] returnAsArray() {
        Yarn[] result = new Yarn[firstFreeSpot];
        for (int i = 0; i < firstFreeSpot; i++) {
            result[i] = yarns[i];
        }
        return result;
    }

    /**
     * Creates a YarnList object out of an array
     *
     * @param yarns Array of Yarns
     * @return A YarnList object
     */
    public static YarnList YarnListFromArray(Yarn[] yarns) {
        YarnList list = new YarnList();
        for (Yarn yarn : yarns) {
            list.add(yarn);
        }
        return list;
    }


    /**
     * Get size of the list
     *
     * @return Number of contained Yarns as int
     */
    @Override
    public int size() {
        return firstFreeSpot;
    }

    /**
     * Get if empty
     *
     * @return True if empty, false if at least one Yarn contained
     */
    @Override
    public boolean isEmpty() {
        return firstFreeSpot == 0;
    }

    /**
     * Check if list contains a specific Yarn object
     *
     * @param o Yarn we're looking for
     * @return true if contained, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        if (!o.getClass().equals(Yarn.class)) {
            return false;
        } else {
            for (Yarn yarn : yarns) {
                if (yarn == null) return false;
                if (yarn.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get an iterator of the contained Yarn objects
     *
     * @return An iterator of Yarns
     */
    @NonNull
    @Override
    public Iterator<Yarn> iterator() {
        return new Iterator<Yarn>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (yarns.length <= currentIndex) return false;
                else return yarns[currentIndex] != null;
            }

            @Override
            public Yarn next() {
                Yarn toReturn = yarns[currentIndex];
                currentIndex++;
                return toReturn;
            }
        };
    }

    /**
     * Add a Yarn
     *
     * @param yarn Yarn to add
     * @return Always true
     */
    @Override
    public boolean add(Yarn yarn) {
        if (firstFreeSpot == yarns.length) {
            expandList();
        }
        yarns[firstFreeSpot] = yarn;
        firstFreeSpot++;
        return true;
    }

    /**
     * For when array is full
     */
    private void expandList() {
        Yarn[] newArray = new Yarn[yarns.length * 2];
        for (int i = 0; i < firstFreeSpot; i++) {
            newArray[i] = yarns[i];
        }
        yarns = newArray;
    }

    /**
     * Check if this YarnList contains everything in the given collection
     *
     * @param collection Items to check
     * @return True if all items are contained, false otherwise
     */
    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) return false;
        }
        return true;
    }

    /**
     * Checks if contains same objects as the defined YarnList
     *
     * @param o Object we check
     * @return True if o is a YarnList containing all the same Yarns, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass() != YarnList.class) return false;
        else {
            if (containsAll((YarnList) o)) {
                return ((YarnList) o).containsAll(YarnList.this);
            }
            return false;
        }
    }

    /**
     * Get Yarn at index
     *
     * @param i Index to check
     * @return Yarn object specified, or possibly null if index not filled
     */
    @Override
    public Yarn get(int i) {
        return yarns[i];
    }

    @NonNull
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends Yarn> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends Yarn> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Yarn set(int i, Yarn Yarn) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void add(int i, Yarn Yarn) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Yarn remove(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ListIterator<Yarn> listIterator() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public ListIterator<Yarn> listIterator(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public List<Yarn> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
