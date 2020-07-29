
package ir.artapps.gamebrowser.entities.pod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LegalInfo {

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

}
