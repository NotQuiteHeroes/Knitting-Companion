package com.eckstein.paige.knittingcompanion.Utilities;

import com.eckstein.paige.knittingcompanion.Yarn.Yarn;

public class YarnSearchResult {

    // The getYarns query (/yarns/search.json) returns this object

    private Yarn[] yarns;
    private Object paginator;
    // TODO: Create the paginator class

    public Yarn[] getYarns() {
        return yarns;
    }

    // The query for full version patterns requires ids in space delimited form
    public String getIdsAsString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < yarns.length; i++) {
            string.append(yarns[i].getId());
            if (i + 1 < yarns.length) string.append(" ");
        }
        return string.toString();
    }

    public Object getPaginator() {
        return paginator;
    }
}


