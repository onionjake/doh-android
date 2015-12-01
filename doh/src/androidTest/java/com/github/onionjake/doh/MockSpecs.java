package com.github.onionjake.doh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jake on 11/28/15.
 */
public class MockSpecs implements Specs {
    @Override
    public Spec getDefault() {
        Spec s = new Spec();
        s.domain = "defaults";
        s.exclude = " ";

        CharacterGroup lower = new CharacterGroup();
        CharacterGroup upper = new CharacterGroup();

        CharacterGroup numbers = new CharacterGroup();

        CharacterGroup special = new CharacterGroup();

        lower.characters = "abcdefghijklmnopqrstuvwxyz";
        upper.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        numbers.characters = "0123456789";
        special.characters = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        s.use = new ArrayList<CharacterGroup>();

        s.use.add(lower);
        s.use.add(upper);
        s.use.add(numbers);
        s.use.add(special);

        s.maxLength = 20;

        return s;
    }

    @Override
    public Map<String, Spec> getSpecs() {
        return new HashMap<String,Spec>();
    }
}
