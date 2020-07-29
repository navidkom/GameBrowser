
package ir.artapps.gamebrowser.entities.pod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageInfo {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("hashCode")
    @Expose
    public String hashCode;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("actualWidth")
    @Expose
    public Integer actualWidth;
    @SerializedName("actualHeight")
    @Expose
    public Integer actualHeight;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;

}
