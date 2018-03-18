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

package rus.myapplication.Util;

import java.util.Arrays;

import io.realm.RealmList;
import rus.myapplication.Model.Book;
import rus.myapplication.Model.ImageLinks;
import rus.myapplication.Model.Realm.RealmBook;
import rus.myapplication.Model.VolumeInfo;

/**
 * Created by XXX on 10.03.2018.
 */

public class RealmUtil {

    public static RealmBook convertBookToRealm(Book book) {

        RealmBook realmBook = new RealmBook();
        if (book != null) {
            realmBook.setId(book.getId());
            VolumeInfo volumeInfo = book.getVolumeInfo();

            if (volumeInfo != null) {
                realmBook.setTitle(volumeInfo.getTitle());

                String[] authors = volumeInfo.getAuthors();

                if (authors != null) {
                    RealmList<String> realmStrings = new RealmList<>();
                    realmStrings.addAll(Arrays.asList(authors));
                    realmBook.setAuthors(realmStrings);
                }

                realmBook.setInfoLink(volumeInfo.getInfoLink());

                ImageLinks imageLinks = volumeInfo.getImageLinks();
                if (imageLinks != null) {
                    realmBook.setSmallThumbnail(imageLinks.getSmallThumbnail());
                    realmBook.setThumbnail(imageLinks.getThumbnail());
                }
            }
        }

        return realmBook;
    }


    public static String toString(RealmBook book) {
        return Arrays.toString(book.getAuthors().toArray());
    }

}
