package chenyijie.mvvm_people_tutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chenyijie on 2017/6/14.
 */

public class Location implements Parcelable{
    public String street;
    public String city;
    public String state;
    public String zip;

    protected Location(Parcel in) {
        street = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}

//public class Location implements Serializable{
//    @SerializedName("street") public String street;
//    @SerializedName("city") public String city;
//    @SerializedName("state") public String state;
//    @SerializedName("zip") public String zip;
//
//}
