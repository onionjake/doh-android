package com.github.onionjake.doh.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.github.onionjake.doh.Doh;
import com.github.onionjake.doh.DohDefaultSpecs;
import com.github.onionjake.doh.DohMethod;
import com.github.onionjake.doh.Options;

/**
 * Created by dan on 12/16/15.
 */

public class ComputeAsyncTaskLoader extends AsyncTaskLoader<String> {
    public static final String OPTIONS = "options";
    private Options mOptions;

    public ComputeAsyncTaskLoader(Context context, Bundle args) {
        super(context);
        if (args != null) {
            mOptions = args.getParcelable(OPTIONS);
        }
    }

    @Override
    public String loadInBackground() {
        return Doh.Compute(mOptions, new DohMethod(), new DohDefaultSpecs());
    }
}
