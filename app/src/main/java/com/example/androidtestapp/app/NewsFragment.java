package com.example.androidtestapp.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class NewsFragment extends Fragment {

    private static final String LOG_TAG = "NEWS_FRAGMENT";
    private ArrayAdapter<String> newsListAdapter;


    private FragmentDisplayer mFragmentDisplayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Log.i(LOG_TAG, "Registering receiver");
//        IntentFilter statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
//        mFragmentDisplayer = new FragmentDisplayer();
//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mFragmentDisplayer, statusIntentFilter);
//
//        newsListView = (ListView) findViewById(R.id.newsList);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_news, container, false);
//        return view;
//    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        newsListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new String[] {});
//        setListAdapter(newsListAdapter);
//
//        Log.i(LOG_TAG, "Registering receiver");
//        IntentFilter statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
//        mFragmentDisplayer = new FragmentDisplayer();
//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mFragmentDisplayer, statusIntentFilter);
    }






    private class FragmentDisplayer extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(Constants.STATUS)) {
                Log.i(LOG_TAG, "***************** UPDATED STATUS in fragment:" + intent.getStringExtra(Constants.STATUS));

//                String newsString = intent.getStringExtra(Constants.STATUS);
//                JSONArray jsonNewsList = new JSONArray();
//                try {
//                    jsonNewsList = new JSONArray(newsString);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                ArrayList<String> newsTitlesList = new ArrayList<>();
//                for (JSONObject jsonNews : jsonNewsList) {
//                    try {
//                        newsTitlesList.add(jsonNews.getString("title"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                newsListAdapter.clear();
//                newsListAdapter.addAll();

            }
        }
    }

}
