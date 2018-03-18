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

package rus.myapplication.Model.Http;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rus.myapplication.Model.SearchResult;
import rus.myapplication.Mvp.MVP_GoogleBooks;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class BooksService implements MVP_GoogleBooks.BooksModel {

    @Override
    public Observable<SearchResult> searchBooks(String query) {

        RxJavaCallAdapterFactory rxAdapter =
                RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoogleBooksAPI.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        GoogleBooksAPI googleBooksService = retrofit.create(GoogleBooksAPI.class);
        Observable<SearchResult> obs = googleBooksService.searchBook(query);

        return obs.observeOn(AndroidSchedulers.mainThread());

    }
}
