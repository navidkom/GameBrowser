package ir.artapps.gamebrowser.entities.pod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Navid Komijani
 * on 28,July,2020
 */
public class GetMetaResponseModel {
    @SerializedName("client_metadata")
    @Expose
    public String clientMetadata = "";
}


