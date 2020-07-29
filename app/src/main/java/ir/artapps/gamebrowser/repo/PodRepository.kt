package ir.artapps.gamebrowser.repo

import ir.artapps.gamebrowser.entities.pod.UserProfile

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

interface PodRepository {
     suspend fun getUserProfile(token: String) : UserProfile?
}