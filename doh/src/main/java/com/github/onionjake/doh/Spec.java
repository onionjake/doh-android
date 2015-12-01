package com.github.onionjake.doh;

import java.util.ArrayList;

/**
 * Created by jake on 11/28/15.
 */
public class Spec {
    public String domain;   // What domain e.g. 'google.com' does this spec apply to.
    public String exclude;  // string of characters to exclude
    public ArrayList<CharacterGroup> use;      // string of potential characters the password can include
    public String require;  // the class and number of characters required in the password
    public int maxLength;   // The longest the password can be
}
