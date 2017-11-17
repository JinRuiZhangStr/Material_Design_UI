package com.example.material_design_ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.material_design_ui.adapter.FruitRcyAdapter;
import com.example.material_design_ui.bean.Fruit;
import com.example.material_design_ui.netutils.HttpMethod;
import com.example.material_design_ui.netutils.NativaHttpCallback;
import com.example.material_design_ui.utils.NativeNetHelper;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView nav_view;
    private FloatingActionButton fab;
    private String url="https://jinruizhangstr.github.io/horizontalscrollview.json";

    private static final String TAG = "MainActivity";
    private RecyclerView mRcy;
    private SwipeRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        initNavigation();
        initFloatingActionButton();
        initData();
    }

    private void initData() {
        mRcy = findViewById(R.id.recycler_view);
        srl = findViewById(R.id.swipe_refresh);

        //设置下拉刷新进度条的颜色
        srl.setColorSchemeResources(R.color.colorPrimary);

        //模拟下拉刷新网络数据
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                srl.setRefreshing(false);
                                Toast.makeText(MainActivity.this, "updata success !", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });

        //recyclerview 加载数据 自定义HttpURLConnection  get请求方式
        NativeNetHelper.get(this, HttpMethod.GET, url, null, new NativaHttpCallback<Fruit>() {


            @Override
            public void Success(Fruit bean) {
                FruitRcyAdapter adapter = new FruitRcyAdapter(MainActivity.this,bean);
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
                mRcy.setLayoutManager(layoutManager);
                mRcy.setAdapter(adapter);


            }

            @Override
            public void Faield(Throwable ex) {
                Log.e(TAG, "Faield: 失败" );
            }

            @Override
            public void onShowProgress() {

            }

            @Override
            public void onHideProgress() {

            }

            @Override
            public Fruit onCreateBean(String requestResult) {
                Log.e(TAG, "onCreateBean: "+requestResult );
                Gson gson = new Gson();
                Fruit fruit = gson.fromJson(requestResult, Fruit.class);
                return fruit;
            }

            @Override
            public String setHeaderInfo() {
                return null;
            }
        });

    }

    private void initFloatingActionButton() {

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "this is floatingactionbutton !", Toast.LENGTH_SHORT).show();

                Snackbar.make(view, "click toast", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "this is floatactionbutton and snackbar !", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

    }

    private void initNavigation() {
        nav_view = findViewById(R.id.nav_view);

        //默认选中nav_call菜单
//        nav_view.setCheckedItem(R.id.nav_call);

        //drawerlayout 里边下边NavigationView的菜单点击方式
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }


    /**
     * toolbar 菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * toolbar菜单的点击方式
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "云上传", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "回收站", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "设置..", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                break;
            default:
        }
        return true;
    }
}
