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

package rus.myapplication.Presenter;

import org.greenrobot.eventbus.EventBus;

import rus.myapplication.Model.Book;
import rus.myapplication.Model.Dao.RealmDao;
import rus.myapplication.Model.Http.BooksService;
import rus.myapplication.Model.SearchResult;
import rus.myapplication.Mvp.MVP_GoogleBooks;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by XXX on 12.03.2018.
 */

public class BooksPresenter implements MVP_GoogleBooks.BooksPresenter {

    private MVP_GoogleBooks.BooksView view;
    private MVP_GoogleBooks.BooksModel booksModel;
    private MVP_GoogleBooks.BooksModel.DAO dao;

    public BooksPresenter(MVP_GoogleBooks.BooksView view) {
        this.view = view;
        this.booksModel = new BooksService();
        this.dao = new RealmDao();
    }

    @Override
    public void searchBooks(String query) {
        Observable<SearchResult>searchResultCall = booksModel.searchBooks(query);
        searchResultCall.subscribe(new Action1<SearchResult>() {
            @Override
            public void call(SearchResult searchResult) {
                EventBus.getDefault().postSticky(searchResult);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                view.showError();
            }
        });
    }


    @Override
    public void updateBooks(Book book, boolean isChecked) {
        if (isChecked) {
           insert(book);
           view.showNotifyBookAdded();
        }
        else {
           delete(book);
           view.showNotifyBookRemoved();
        }
    }

    private  void insert(Book book) {
        dao.openRealm();
        dao.insert(book);
        dao.closeRealm();
    }

    private void delete(Book book) {
        dao.openRealm();
        dao.delete(book);
        dao.closeRealm();
    }

}
