package com.eckstein.paige.knittingcompanion.Utilities;

import com.eckstein.paige.knittingcompanion.Yarn.Yarn;
import com.eckstein.paige.knittingcompanion.Yarn.YarnList;

import java.util.HashMap;

/**
 * Get Yarn objects from Ravelry search and add to List
 * Credit to Sofivanhanen and her code, from which this was edited
 * From https://github.com/sofivanhanen/Yarnie
 */
public class FullYarnsResult {
    private HashMap<Integer, Yarn> yarns;

    public HashMap<Integer, Yarn> getYarns() {
        return yarns;
    }

    public YarnList getYarnAsList() {
        YarnList result = new YarnList();
        for (Yarn yarn : yarns.values()) {
            result.add(yarn);
        }
        return result;
    }
}
