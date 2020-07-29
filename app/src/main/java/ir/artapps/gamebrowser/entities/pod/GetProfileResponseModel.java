package ir.artapps.gamebrowser.entities.pod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Navid Komijani
 * on 28,July,2020
 */
public class GetProfileResponseModel {
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
    public GetProfileResult result;
}
