
package ir.artapps.gamebrowser.entities.pod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile implements Parcelable {

    @SerializedName("version")
    @Expose
    public Integer version;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("email_unverified")
    @Expose
    public String emailUnverified;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("nationalCode")
    @Expose
    public String nationalCode;
    @SerializedName("nationalCode_verified")
    @Expose
    public String nationalCodeVerified;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("addressSrv")
    @Expose
    public AddressSrv addressSrv;
    @SerializedName("nickName")
    @Expose
    public String nickName;
    @SerializedName("birthDate")
    @Expose
    public Integer birthDate;
    @SerializedName("score")
    @Expose
    public Integer score;
    @SerializedName("followingCount")
    @Expose
    public Integer followingCount;
    @SerializedName("imageInfo")
    @Expose
    public ImageInfo imageInfo;
    @SerializedName("profileImage")
    @Expose
    public String profileImage;
    @SerializedName("joinDate")
    @Expose
    public Integer joinDate;
    @SerializedName("cellphoneNumber")
    @Expose
    public String cellphoneNumber;
    @SerializedName("cellphoneNumber_unverified")
    @Expose
    public String cellphoneNumberUnverified;
    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("sheba")
    @Expose
    public String sheba;
    @SerializedName("guest")
    @Expose
    public Boolean guest;
    @SerializedName("chatSendEnable")
    @Expose
    public Boolean chatSendEnable;
    @SerializedName("chatReceiveEnable")
    @Expose
    public Boolean chatReceiveEnable;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("ssoId")
    @Expose
    public String ssoId;
    @SerializedName("ssoIssuerCode")
    @Expose
    public Integer ssoIssuerCode;
    @SerializedName("client_metadata")
    @Expose
    public String clientMetadata;
    @SerializedName("legalInfo")
    @Expose
    public LegalInfo legalInfo;
    @SerializedName("financialLevelSrv")
    @Expose
    public FinancialLevelSrv financialLevelSrv;
    @SerializedName("readOnlyFields")
    @Expose
    public String readOnlyFields;
    @SerializedName("follower")
    @Expose
    public Boolean follower;

    @SerializedName("kidzyName")
    @Expose
    public String kidzyName;

    @SerializedName("age")
    @Expose
    public Integer age ;

    @SerializedName("sex")
    @Expose
    public String sex;

    @SerializedName("avatar")
    @Expose
    public Integer avatar ;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.version);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.emailUnverified);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.nationalCode);
        dest.writeString(this.nationalCodeVerified);
        dest.writeString(this.gender);
        dest.writeParcelable(this.addressSrv, flags);
        dest.writeString(this.nickName);
        dest.writeValue(this.birthDate);
        dest.writeValue(this.score);
        dest.writeValue(this.followingCount);
        dest.writeParcelable(this.imageInfo, flags);
        dest.writeString(this.profileImage);
        dest.writeValue(this.joinDate);
        dest.writeString(this.cellphoneNumber);
        dest.writeString(this.cellphoneNumberUnverified);
        dest.writeValue(this.userId);
        dest.writeString(this.sheba);
        dest.writeValue(this.guest);
        dest.writeValue(this.chatSendEnable);
        dest.writeValue(this.chatReceiveEnable);
        dest.writeString(this.username);
        dest.writeString(this.ssoId);
        dest.writeValue(this.ssoIssuerCode);
        dest.writeString(this.clientMetadata);
        dest.writeParcelable(this.legalInfo, flags);
        dest.writeParcelable(this.financialLevelSrv, flags);
        dest.writeString(this.readOnlyFields);
        dest.writeValue(this.follower);
        dest.writeValue(this.kidzyName);
        dest.writeValue(this.age);
        dest.writeValue(this.sex);
        dest.writeValue(this.avatar);
    }

    public UserProfile() {
    }

    protected UserProfile(Parcel in) {
        this.version = (Integer) in.readValue(Integer.class.getClassLoader());
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.emailUnverified = in.readString();
        this.phoneNumber = in.readString();
        this.nationalCode = in.readString();
        this.nationalCodeVerified = in.readString();
        this.gender = in.readString();
        this.addressSrv = in.readParcelable(AddressSrv.class.getClassLoader());
        this.nickName = in.readString();
        this.birthDate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.score = (Integer) in.readValue(Integer.class.getClassLoader());
        this.followingCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imageInfo = in.readParcelable(ImageInfo.class.getClassLoader());
        this.profileImage = in.readString();
        this.joinDate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cellphoneNumber = in.readString();
        this.cellphoneNumberUnverified = in.readString();
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sheba = in.readString();
        this.guest = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.chatSendEnable = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.chatReceiveEnable = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.username = in.readString();
        this.ssoId = in.readString();
        this.ssoIssuerCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.clientMetadata = in.readString();
        this.legalInfo = in.readParcelable(LegalInfo.class.getClassLoader());
        this.financialLevelSrv = in.readParcelable(FinancialLevelSrv.class.getClassLoader());
        this.readOnlyFields = in.readString();
        this.follower = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.kidzyName = (String) in.readValue(String.class.getClassLoader());
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sex = (String) in.readValue(String.class.getClassLoader());
        this.avatar = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserProfile> CREATOR = new Parcelable.Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
}
