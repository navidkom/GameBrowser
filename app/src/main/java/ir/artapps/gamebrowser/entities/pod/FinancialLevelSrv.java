
package ir.artapps.gamebrowser.entities.pod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinancialLevelSrv {

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

}
