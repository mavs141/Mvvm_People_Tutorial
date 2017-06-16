package chenyijie.mvvm_people_tutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * http://www.jianshu.com/p/a60b609ec7e7
 * 應該要改用Parcelable比較好
 *
 * http://www.jianshu.com/p/97503d7faaf3
 *
 *
 * Created by chenyijie on 2017/6/14.
 */

public class People implements Parcelable{
    // 記得加上serializedName讓json可以找到對應的POJO
    @SerializedName("gender") public String gender;
    @SerializedName("name") public Name name;
    @SerializedName("location") public Location location;
    @SerializedName("email") public String email;
    @SerializedName("login") public Login login;
    @SerializedName("phone") public String phone;
    @SerializedName("cell") public String cell;
    @SerializedName("picture") public Picture picture;

    public String fullName;
    public boolean hasEmail(){
        return (email != null && !email.isEmpty());
    }

    protected People(Parcel in) {
        gender = in.readString();
        name = in.readParcelable(Thread.currentThread().getContextClassLoader());
        location = in.readParcelable(Thread.currentThread().getContextClassLoader());
        email = in.readString();
        login = in.readParcelable(Thread.currentThread().getContextClassLoader());
        phone = in.readString();
        cell = in.readString();
        picture = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gender);
        dest.writeParcelable(name, 0);
        dest.writeParcelable(location, 0);
        dest.writeString(email);
        dest.writeParcelable(login, 0);
        dest.writeString(phone);
        dest.writeString(cell);
        dest.writeParcelable(picture, 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };
}

//public class People implements Serializable {
//    @SerializedName("gender") public String gender;
//    @SerializedName("name") public Name name;
//    @SerializedName("location") public Location location;
//    @SerializedName("email") public String email;
//    @SerializedName("login") public Login login;
//    @SerializedName("phone") public String phone;
//    @SerializedName("cell") public String cell;
//    @SerializedName("picture") public Picture picture;
//
//    public String fullName;
//    public boolean hasEmail(){
//        return (email != null && !email.isEmpty());
//    }
//}
