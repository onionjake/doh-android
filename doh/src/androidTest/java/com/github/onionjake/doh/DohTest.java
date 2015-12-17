package com.github.onionjake.doh;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.Test;

/**
 * Created by jake on 11/28/15.
 */
public class DohTest extends AndroidTestCase {
    public void testDohMethod() {
        DohMethod d = new DohMethod();
        Options o = new Options();
        o.setDomain("google.com");
        o.setPassword("test".getBytes());
        o.setSalt("");
        o.setSequence("");
        o.setLength(20);

        assertEquals("vYptHs2mUWBp9+2wYzM2", d.Compute(o));
    }

    public void testDoh() {
        Options o = new Options();
        o.setDomain("google.com");
        o.setPassword("test".getBytes());
        o.setSalt("");
        o.setSequence("");
        o.setLength(20);
        DohMethod d = new DohMethod();
        MockSpecs ms = new MockSpecs();

        Log.d("DOH",Doh.Compute(o,d,ms));
    }
}
