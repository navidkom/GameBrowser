
package ir.artapps.gamebrowser.entities.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.artapps.gamebrowser.entities.Game;

public class FavoriteResponse {
    @SerializedName("type")
    @Expose
    public Integer type;

    @SerializedName("item")
    @Expose
    public ProductGame item;
}
