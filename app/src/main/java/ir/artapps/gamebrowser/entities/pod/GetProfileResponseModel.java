package ir.artapps.gamebrowser.entities.pod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Navid Komijani
 * on 28,July,2020
 */
public class GetProfileResponseModel implements Parcelable {
    @SerializedName("hasError")
    @Expose
    public Boolean hasError;
    @SerializedName("referenceNumber")
    @Expose
    public String referenceNumber;
    @SerializedName("errorCode")
    @Expose
    public Integer errorCode;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("ott")
    @Expose
    public String ott;
    @SerializedName("result")
    @Expose
    public UserProfile result;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.hasError);
        dest.writeString(this.referenceNumber);
        dest.writeValue(this.errorCode);
        dest.writeValue(this.count);
        dest.writeString(this.ott);
        dest.writeParcelable(this.result, flags);
    }

    public GetProfileResponseModel() {
    }

    protected GetProfileResponseModel(Parcel in) {
        this.hasError = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.referenceNumber = in.readString();
        this.errorCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ott = in.readString();
        this.result = in.readParcelable(UserProfile.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetProfileResponseModel> CREATOR = new Parcelable.Creator<GetProfileResponseModel>() {
        @Override
        public GetProfileResponseModel createFromParcel(Parcel source) {
            return new GetProfileResponseModel(source);
        }

        @Override
        public GetProfileResponseModel[] newArray(int size) {
            return new GetProfileResponseModel[size];
        }
    };
}
