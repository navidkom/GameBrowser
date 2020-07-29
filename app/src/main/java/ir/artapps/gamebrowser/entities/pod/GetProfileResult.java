
package ir.artapps.gamebrowser.entities.pod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfileResult {

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

}
