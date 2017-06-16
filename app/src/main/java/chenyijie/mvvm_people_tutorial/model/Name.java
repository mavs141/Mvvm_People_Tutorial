package chenyijie.mvvm_people_tutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chenyijie on 2017/6/14.
 */

public class Name implements Parcelable{
    public String title;
    public String firstName;
    public String lastName;

    protected Name(Parcel in) {
        title = in.readString();
        firstName = in.readString();
        lastName = in.readString();
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
    }
}

//public class Name implements Serializable {
//    @SerializedName("title") public String title;
//    @SerializedName("first") public String firstName;
//    @SerializedName("last") public String lastName;
//}
