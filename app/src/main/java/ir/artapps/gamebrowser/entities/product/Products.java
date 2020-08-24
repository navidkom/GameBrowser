
package ir.artapps.gamebrowser.entities.product;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.artapps.gamebrowser.entities.Game;
import ir.artapps.gamebrowser.entities.Rate;


public class Products {
    @SerializedName("products")
    @Expose
    public List<ProductGame> products;
}