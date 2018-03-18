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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rus.myapplication.Adapter.GoogleBookAdapter;
import rus.myapplication.Model.Book;
import rus.myapplication.Model.SearchResult;
import rus.myapplication.Mvp.MVP_GoogleBooks;
import rus.myapplication.Presenter.BooksPresenter;
import rus.myapplication.R;

public class BooksFragment extends Fragment implements MVP_GoogleBooks.BooksView {

    private static MVP_GoogleBooks.BooksPresenter mPresenter;
    private RecyclerView mRecyclerView;

    public static BooksFragment newInstance() {

        Bundle args = new Bundle();

        BooksFragment fragment = new BooksFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = root.findViewById(R.id.rvItems);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setItemViewCacheSize(20);

        return root;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(SearchResult searchResult){

        GoogleBookAdapter adapter = new GoogleBookAdapter(searchResult.getListBooks()) {
            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.getListItemBinding().cbFavor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Book book = getItem(position);
                        mPresenter.updateBooks(book,b);
                    }
                });
            }
        };

        mRecyclerView.setAdapter(adapter);


    }


    public MVP_GoogleBooks.BooksPresenter getPresenter() {

        if (mPresenter == null) {
            mPresenter = new BooksPresenter(this);
        }

        return mPresenter;
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), R.string.msg_error_search_books, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNotifyBookAdded() {
        Toast.makeText(getActivity(), R.string.msg_book_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNotifyBookRemoved() {
        Toast.makeText(getActivity(), R.string.msg_book_removed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

}

