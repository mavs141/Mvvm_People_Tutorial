package chenyijie.mvvm_people_tutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chenyijie on 2017/6/14.
 */

public class Login implements Parcelable{
    @SerializedName("userName") public String userName;

    protected Login(Parcel in) {
        userName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}

//public class Login implements Serializable {
//    @SerializedName("userName") public String userName;
//}
