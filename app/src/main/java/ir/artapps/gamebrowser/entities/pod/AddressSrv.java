
package ir.artapps.gamebrowser.entities.pod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressSrv implements Parcelable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("faxNumber")
    @Expose
    public String faxNumber;
    @SerializedName("postalcode")
    @Expose
    public String postalcode;
    @SerializedName("latitude")
    @Expose
    public Integer latitude;
    @SerializedName("longitude")
    @Expose
    public Integer longitude;
    @SerializedName("simpleAddress")
    @Expose
    public String simpleAddress;
    @SerializedName("default")
    @Expose
    public Boolean _default;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.country);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.faxNumber);
        dest.writeString(this.postalcode);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
        dest.writeString(this.simpleAddress);
        dest.writeValue(this._default);
    }

    public AddressSrv() {
    }

    protected AddressSrv(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.address = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.country = in.readString();
        this.phoneNumber = in.readString();
        this.faxNumber = in.readString();
        this.postalcode = in.readString();
        this.latitude = (Integer) in.readValue(Integer.class.getClassLoader());
        this.longitude = (Integer) in.readValue(Integer.class.getClassLoader());
        this.simpleAddress = in.readString();
        this._default = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AddressSrv> CREATOR = new Parcelable.Creator<AddressSrv>() {
        @Override
        public AddressSrv createFromParcel(Parcel source) {
            return new AddressSrv(source);
        }

        @Override
        public AddressSrv[] newArray(int size) {
            return new AddressSrv[size];
        }
    };
}
