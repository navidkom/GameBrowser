package ir.artapps.gamebrowser.util

import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.*

object DistanceUtil {
    fun distance(
        lat1: Double, lat2: Double, lon1: Double,
        lon2: Double
    ): Double {
        val radius = 6371 // Radius of the earth
        val latDistance = Math.toRadians(lat2 - lat1)
        val lonDistance = Math.toRadians(lon2 - lon1)
        val a =
            (sin(latDistance / 2) * sin(latDistance / 2)
                    + (cos(Math.toRadians(lat1)) * cos(
                Math.toRadians(
                    lat2
                )
            )
                    * sin(lonDistance / 2) * sin(lonDistance / 2)))
        val c =
            2 * atan2(sqrt(a), sqrt(1 - a))
        var distance = radius * c * 1000 // convert to meters
        distance = distance.pow(2.0)
        return sqrt(distance)
    }

    fun distanceToString(distance: Double): String {
        var calDis = distance
        var unit = "m"
        if (distance > 1000) {
            calDis = distance / 1000
            unit = "km"
        }
        val nf: NumberFormat = DecimalFormat("###.#")
        return String.format("%s %s", nf.format(calDis), unit)
    }
}