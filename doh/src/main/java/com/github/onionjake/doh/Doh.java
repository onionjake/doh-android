package com.github.onionjake.doh;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by jake on 11/28/15.
 */
public class Doh {
    public static String Compute(Options o, Method m, Specs s) {
        Spec spec = s.getDefault();
        Map<String, Spec> specs = s.getSpecs();
        if(specs.containsKey(o.domain)) {
            spec = specs.get(o.domain);
        }

        o.length = spec.maxLength;

        String password = m.Compute(o);

        final String b64_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        // try to use the same amount of characters from each element
        int even_split = ((int) Math.floor(b64_chars.length() * 1.0 / spec.use.size()));
        String use = "";

        int i = 0;
        for (CharacterGroup c:spec.use) {
            int slice = even_split;
            if (slice > c.characters.length()) {
                slice = c.characters.length();
            }

            if (i+1 == spec.use.size()) {
                slice = c.characters.length();
            }
            use += c.characters.substring(0,slice);
            i++;
        }

        use = use.replaceAll("[" + spec.exclude + "]", "");

        // TODO: Define properly in DOH spec the behaviour of this character translation.
        // This implementation will truncate characters if search chars is longer than replace chars
        return StringUtils.replaceChars(password, b64_chars, use);


    }

}
