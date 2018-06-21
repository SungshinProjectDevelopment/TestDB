package org.androidtown.testdb;

/**
 * Created by hyon1001 on 2018-06-21.
 */

public class MPostItem {
    String postname;
    String restaurantname;
    String rating;
    String hits;
    String writerid;
    String postbody;

    public MPostItem(String postname, String restaurantname, String rating, String hits, String writerid, String postbody) {
        this.postname = postname;
        this.restaurantname = restaurantname;
        this.rating = rating;
        this.hits = hits;
        this.writerid = writerid;
        this.postbody = postbody;
    }
}
