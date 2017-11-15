package com.boomhe.mymvp.module.home;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.boomhe.mymvp.R;
import com.boomhe.mymvp.module.base.BaseActivity;
import com.boomhe.mymvp.module.news.main.NewsMainFragment;
import com.boomhe.mymvp.utils.ToastHelper;

/**
 * @author
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SparseArray<String> mSparseTags = new SparseArray<>();
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Long mExitTime = 0L;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        initDrawerLayout(drawer, navigationView);

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        navigationView.setCheckedItem(R.id.nav_news);
        addFragment(R.id.fl_container, new NewsMainFragment(), "News");
    }

    @Override
    public void onBackPressed() {
        int entryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (entryCount == 1) {
            // 如果剩一个说明在主页，提示按两次退出app
            exit();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_videos) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 初始化 DrawerLayout
     *
     * @param drawerLayout
     * @param navView
     */
    private void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | attributes.flags);
            // 将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            // 将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
        navView.setNavigationItemSelectedListener(this);
    }

    private void exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastHelper.toastWarning(this, "再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
