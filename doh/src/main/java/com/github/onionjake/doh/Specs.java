package com.github.onionjake.doh;

import java.util.Map;

/**
 * Created by jake on 11/28/15.
 */
public interface Specs {
    Spec getDefault();
    Map<String,Spec> getSpecs();
}
