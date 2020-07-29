package ir.artapps.gamebrowser.entities.pod

/**
 *   Created by Navid Komijani
 *   on 28,July,2020
 */
data class TokenResponseModel (
    val state : String,
    val code: String,
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val scope: String,
    val refresh_token: String,
    val id_token: String,
    val device_uid: String
)