package ir.artapps.gamebrowser.repo

import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.GetProfileResult

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

interface PodRepository {
     suspend fun getUserProfile(token: String): GetProfileResponseModel
}