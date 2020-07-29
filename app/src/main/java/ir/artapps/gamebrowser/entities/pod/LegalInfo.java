
package ir.artapps.gamebrowser.entities.pod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LegalInfo implements Parcelable {

    @SerializedName("fatherName")
    @Expose
    public String fatherName;
    @SerializedName("identificationNumber")
    @Expose
    public String identificationNumber;
    @SerializedName("birthState")
    @Expose
    public String birthState;
    @SerializedName("deathStatus")
    @Expose
    public String deathStatus;
    @SerializedName("birthDate")
    @Expose
    public String birthDate;
    @SerializedName("deathDate")
    @Expose
    public String deathDate;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("identifierSerial")
    @Expose
    public String identifierSerial;
    @SerializedName("identifierSeri")
    @Expose
    public String identifierSeri;
    @SerializedName("postalCode")
    @Expose
    public Integer postalCode;
    @SerializedName("postalCodeDescription")
    @Expose
    public String postalCodeDescription;
    @SerializedName("bookNo")
    @Expose
    public Integer bookNo;
    @SerializedName("bookRow")
    @Expose
    public Integer bookRow;
    @SerializedName("officeCode")
    @Expose
    public Integer officeCode;
    @SerializedName("officeName")
    @Expose
    public String officeName;
    @SerializedName("nationalCardSerial")
    @Expose
    public String nationalCardSerial;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fatherName);
        dest.writeString(this.identificationNumber);
        dest.writeString(this.birthState);
        dest.writeString(this.deathStatus);
        dest.writeString(this.birthDate);
        dest.writeString(this.deathDate);
        dest.writeString(this.gender);
        dest.writeString(this.identifierSerial);
        dest.writeString(this.identifierSeri);
        dest.writeValue(this.postalCode);
        dest.writeString(this.postalCodeDescription);
        dest.writeValue(this.bookNo);
        dest.writeValue(this.bookRow);
        dest.writeValue(this.officeCode);
        dest.writeString(this.officeName);
        dest.writeString(this.nationalCardSerial);
    }

    public LegalInfo() {
    }

    protected LegalInfo(Parcel in) {
        this.fatherName = in.readString();
        this.identificationNumber = in.readString();
        this.birthState = in.readString();
        this.deathStatus = in.readString();
        this.birthDate = in.readString();
        this.deathDate = in.readString();
        this.gender = in.readString();
        this.identifierSerial = in.readString();
        this.identifierSeri = in.readString();
        this.postalCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.postalCodeDescription = in.readString();
        this.bookNo = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bookRow = (Integer) in.readValue(Integer.class.getClassLoader());
        this.officeCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.officeName = in.readString();
        this.nationalCardSerial = in.readString();
    }

    public static final Parcelable.Creator<LegalInfo> CREATOR = new Parcelable.Creator<LegalInfo>() {
        @Override
        public LegalInfo createFromParcel(Parcel source) {
            return new LegalInfo(source);
        }

        @Override
        public LegalInfo[] newArray(int size) {
            return new LegalInfo[size];
        }
    };
}
