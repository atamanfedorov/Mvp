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

package rus.myapplication.Model.Dao;

import io.realm.Realm;
import rus.myapplication.Model.Book;
import rus.myapplication.Model.Realm.RealmBook;
import rus.myapplication.Mvp.MVP_GoogleBooks;
import rus.myapplication.Util.RealmUtil;

/**
 * Created by XXX on 10.03.2018.
 */

public class RealmDao implements MVP_GoogleBooks.BooksModel.DAO {

    private Realm mRealm;

    public RealmDao() {
        this(Realm.getDefaultInstance());
    }

    public RealmDao(Realm realm) {
        mRealm = realm;
    }

    @Override
    public void insert(final Book book) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmBook realmBook = RealmUtil.convertBookToRealm(book);
                realm.insertOrUpdate(realmBook);
            }
        });
    }


    @Override
    public void delete(final Book book) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmBook realmBook = loadBy(book.getId());
                realmBook.deleteFromRealm();
            }
        });
    }

    private RealmBook loadBy(String id) {
        return mRealm.where(RealmBook.class).equalTo("id", id).findFirst();
    }


    /*
    @Override
    public RealmResults<RealmBook> getCollection() {
        return mRealm
                .where(RealmBook.class)
                .findAll();
    }
    */

    @Override
    public void openRealm() {
        if (mRealm == null) {
            mRealm = Realm.getDefaultInstance();
        }
    }

    @Override
    public void closeRealm() {
        this.mRealm.close();
        this.mRealm = null;
    }

}



