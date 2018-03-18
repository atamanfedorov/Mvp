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
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import rus.myapplication.Model.Book;
import rus.myapplication.R;
import rus.myapplication.databinding.ListItemBinding;


/**
 * Created by XXX on 11.03.2018.
 */

public abstract class GoogleBookAdapter extends RecyclerView.Adapter<GoogleBookAdapter.ViewHolder> {

    private List<Book> mBooks;

    public GoogleBookAdapter(List<Book> books) {
        this.mBooks = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListItemBinding listItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item,
                parent,
                false);

        return new ViewHolder(listItemBinding);

    }

    @Override
    @CallSuper
    public void onBindViewHolder(ViewHolder holder, int position) {

        Book book = getItem(position);
        holder.listItemBinding.setBook(book);
        holder.listItemBinding.executePendingBindings();

    }


    public Book getItem(int position)
    {
        return mBooks.get(position);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding listItemBinding;

        public ViewHolder(ListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

        public ListItemBinding getListItemBinding() {
            return listItemBinding;
        }
    }


}
