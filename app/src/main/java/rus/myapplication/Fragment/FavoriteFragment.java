/*
 * Copyright 2017 Ruslan_<<RUS_M>>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rus.myapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import io.realm.RealmResults;
import rus.myapplication.Adapter.RealmAdapter;
import rus.myapplication.Model.Realm.RealmBook;
import rus.myapplication.R;

/**
 * Created by XXX on 10.03.2018.
 */

public class FavoriteFragment extends Fragment {

  //  private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   realm = Realm.getDefaultInstance();
    }

    public static FavoriteFragment newInstance() {

        Bundle args = new Bundle();
        
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        RecyclerView recyclerView = root.findViewById(R.id.rvItems);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        RealmResults<RealmBook> realmBooks = Realm.getDefaultInstance().where(RealmBook.class).findAll();
        recyclerView.setAdapter(new RealmAdapter(realmBooks));

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Realm.getDefaultInstance().close();
    }
}

