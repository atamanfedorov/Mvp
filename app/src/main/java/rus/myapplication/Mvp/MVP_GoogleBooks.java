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

package rus.myapplication.Mvp;

import rus.myapplication.Model.Book;
import rus.myapplication.Model.SearchResult;
import rx.Observable;

/**
 * Created by XXX on 12.03.2018.
 */

public interface MVP_GoogleBooks {

    interface BooksModel {

        Observable<SearchResult> searchBooks(String query);

        interface DAO {
            void insert(Book book);
            void delete(Book book);
            void openRealm();
            void closeRealm();
        }

    }

    interface BooksPresenter {
        void searchBooks(String query);
        void updateBooks(Book book, boolean isChecked);
    }

    interface BooksView {
        void showError();
        void showNotifyBookAdded();
        void showNotifyBookRemoved();
    }


}
