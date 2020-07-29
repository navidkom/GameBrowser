
package ir.artapps.gamebrowser.entities.pod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressSrv {

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

}
