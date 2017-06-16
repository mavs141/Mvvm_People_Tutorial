package chenyijie.mvvm_people_tutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chenyijie on 2017/6/14.
 */

public class Picture implements Parcelable{
    public String large;
    public String medium;
    public String thumbnail;

    protected Picture(Parcel in) {
        large = in.readString();
        medium = in.readString();
        thumbnail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(large);
        dest.writeString(medium);
        dest.writeString(thumbnail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}

//public class Picture implements Serializable {
//    @SerializedName("large") public String large;
//    @SerializedName("medium") public String medium;
//    @SerializedName("thumbnail") public String thumbnail;
//}
