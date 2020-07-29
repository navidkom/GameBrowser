
package ir.artapps.gamebrowser.entities.pod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinancialLevelSrv implements Parcelable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("level")
    @Expose
    public String level;
    @SerializedName("levelName")
    @Expose
    public String levelName;
    @SerializedName("value")
    @Expose
    public Integer value;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.level);
        dest.writeString(this.levelName);
        dest.writeValue(this.value);
    }

    public FinancialLevelSrv() {
    }

    protected FinancialLevelSrv(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.level = in.readString();
        this.levelName = in.readString();
        this.value = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<FinancialLevelSrv> CREATOR = new Parcelable.Creator<FinancialLevelSrv>() {
        @Override
        public FinancialLevelSrv createFromParcel(Parcel source) {
            return new FinancialLevelSrv(source);
        }

        @Override
        public FinancialLevelSrv[] newArray(int size) {
            return new FinancialLevelSrv[size];
        }
    };
}
