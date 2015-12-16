package com.github.onionjake.doh.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.onionjake.doh.Doh;
import com.github.onionjake.doh.DohDefaultSpecs;
import com.github.onionjake.doh.DohMethod;
import com.github.onionjake.doh.Options;
import com.github.onionjake.doh.Spec;
import com.github.onionjake.doh.Specs;
import com.github.onionjake.doh.app.databinding.ActivityMainBinding;
import com.github.onionjake.doh.app.databinding.ContentMainBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private final static int COMPUTE_LOADER_ID = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        binding.contentMain.buttonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putParcelable(ComputeAsyncTaskLoader.OPTIONS, getOptions(binding));
                getSupportLoaderManager().restartLoader(COMPUTE_LOADER_ID, args, new LoaderManager.LoaderCallbacks<String>() {

                    @Override
                    public Loader<String> onCreateLoader(int id, Bundle args) {
                        return new ComputeAsyncTaskLoader(getApplicationContext(), args);
                    }

                    @Override
                    public void onLoadFinished(Loader<String> loader, String password) {
                        binding.contentMain.mainDescription.setText(password);
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("pw", password);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getApplicationContext(), R.string.copied_to_clipboard,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLoaderReset(Loader<String> loader) {

                    }
                }).forceLoad();
            }
        });
    }

    private Options getOptions(ActivityMainBinding binding) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        md.update(binding.contentMain.mainEditTextPassword.getText().toString().getBytes());
        byte[] digest = md.digest();

        return new Options(digest,
                binding.contentMain.mainEditTextDomain.getText().toString(),
                binding.contentMain.mainEditTextSequence.getText().toString(),
                binding.contentMain.mainEditTextSalt.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
