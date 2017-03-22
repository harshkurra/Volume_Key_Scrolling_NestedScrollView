package blog.hkurra.basic_nestedscrollview_volumescroll;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private NestedScrollView mNestedScrollView = null;
    private AppBarLayout mAppBarLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
                keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            event.startTracking();
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (mNestedScrollView == null) {
            return false;
        }
        boolean notLongPress = (event.getFlags() & KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0;

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP && notLongPress) {
            if (!mNestedScrollView.pageScroll(View.FOCUS_UP) && mAppBarLayout != null) {
                mAppBarLayout.setExpanded(true, true);
            }
            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && notLongPress) {
            if (mAppBarLayout != null &&
                    mAppBarLayout.getHeight() == mAppBarLayout.getBottom()) {
                mAppBarLayout.setExpanded(false, true);
            } else {
                return mNestedScrollView.pageScroll(View.FOCUS_DOWN);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if (mAppBarLayout != null) {
                mAppBarLayout.setExpanded(true, true);
            }
            if (mNestedScrollView != null) {
                mNestedScrollView.smoothScrollTo(0, 0);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
           //Do Nothing
            return true;
        }
        return false;
    }
}
