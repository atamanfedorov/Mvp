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

import retrofit2.http.GET;
import retrofit2.http.Query;
import rus.myapplication.Model.SearchResult;
import rx.Observable;


public interface GoogleBooksAPI {

    String URL_BASE = "https://www.googleapis.com/books/v1/volumes/";

    @GET("./")
    Observable<SearchResult> searchBook(@Query("q") String q);

}










