package com.eckstein.paige.knittingcompanion.Utilities;

import com.eckstein.paige.knittingcompanion.Yarn.Yarn;
import com.eckstein.paige.knittingcompanion.Yarn.YarnList;

import java.util.HashMap;

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
