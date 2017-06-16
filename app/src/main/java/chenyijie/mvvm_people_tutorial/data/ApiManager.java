package chenyijie.mvvm_people_tutorial.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenyijie on 2017/6/14.
 */

public class ApiManager {
    private final static String BASE_URL = "http://api.randomuser.me/";
    public final static String RANDOM_USER_URL = "http://api.randomuser.me/?results=10&nat=en";
    public final static String PROJECT_URL = "https://github.com/erikcaffrey/People-MVVM";
    private static ApiManager instance ;

    public static ApiManager getInstance(){
        if(instance == null) instance = new ApiManager();
        return instance;
    }

    public GetPeopleService getPeople(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GetPeopleService.class);
    }
}
