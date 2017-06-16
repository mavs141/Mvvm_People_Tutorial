package chenyijie.mvvm_people_tutorial.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by chenyijie on 2017/6/14.
 */

public interface GetPeopleService {
    @GET
    Observable<PeopleInfo> getPeople(@Url String url);
}
