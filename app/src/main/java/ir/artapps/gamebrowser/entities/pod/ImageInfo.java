
package ir.artapps.gamebrowser.entities.pod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageInfo implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.hashCode);
        dest.writeString(this.description);
        dest.writeValue(this.actualWidth);
        dest.writeValue(this.actualHeight);
        dest.writeValue(this.width);
        dest.writeValue(this.height);
    }

    public ImageInfo() {
    }

    protected ImageInfo(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.hashCode = in.readString();
        this.description = in.readString();
        this.actualWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.actualHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ImageInfo> CREATOR = new Parcelable.Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel source) {
            return new ImageInfo(source);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };
}
