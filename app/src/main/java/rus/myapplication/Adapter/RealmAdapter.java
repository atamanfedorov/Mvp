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


package rus.myapplication.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import rus.myapplication.Model.Realm.RealmBook;
import rus.myapplication.R;
import rus.myapplication.databinding.GridItemBinding;

/**
 * Created by XXX on 11.03.2018.
 */

public class RealmAdapter extends RealmRecyclerViewAdapter<RealmBook,RealmAdapter.ViewHolder> {


    public RealmAdapter(OrderedRealmCollection<RealmBook> books) {
        super(books, true);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        GridItemBinding gridItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.grid_item,
                parent,
                false
        );


        return new ViewHolder(gridItemBinding);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final RealmBook book = getItem(position);

        holder.gridItemBinding.setBook(book);
        holder.gridItemBinding.executePendingBindings();

        holder.gridItemBinding.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        book.deleteFromRealm();
                    }
                });
                // realm.close();
            }
        });



    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        GridItemBinding gridItemBinding;

        public ViewHolder(GridItemBinding gridItemBinding) {
            super(gridItemBinding.getRoot());
            this.gridItemBinding = gridItemBinding;
        }

    }
}

