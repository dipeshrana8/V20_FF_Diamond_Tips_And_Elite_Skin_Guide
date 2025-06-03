package com.fansquad.ffdiatips.skinrewards.spalsh

import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.fansquad.ffdiatips.skinrewards.R
import com.fansquad.ffdiatips.skinrewards.myAds.AdsPreference
import com.fansquad.ffdiatips.skinrewards.myAds.MyController
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MyApplication : MyController() {

    private var editor: SharedPreferences.Editor? = null

    companion object {
        lateinit var sharedPreferences: SharedPreferences
    }

    private val databaseName = "ad_demo"

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sharedPreferences = getSharedPreferences(databaseName, MODE_PRIVATE)
        editor = sharedPreferences.edit()

        setupFirebaseRemoteConfig()
    }

    private fun setupFirebaseRemoteConfig() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(100)
            .build()

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                try {
                    val jsonString = remoteConfig.getString("comgetdailydiamondslivefreedaily")

                    Log.d("RemoteConfig", "Fetched JSON: $jsonString")

                    val jsonObject = JSONObject(jsonString)

                    AdsPreference.setNative_show(
                        jsonObject.optBoolean(
                            AdsPreference.Native_OnOff, false
                        )
                    )
                    AdsPreference.setIninterstialWeb(
                        jsonObject.optBoolean(
                            "interstial_onoff",
                            false
                        )
                    )

                    val bannerImageArray = extractStringList(jsonObject, "daily_BannerImage")
                    AdsPreference.setStringListPref(bannerImageArray)

                    val multipleLinkArray = extractStringList(jsonObject, "multiple_link")
                    if (multipleLinkArray.isNotEmpty()) {
                        val selectedLink = multipleLinkArray.random()
                        AdsPreference.setRedirectLink(selectedLink)
                        AdsPreference.setnative_link(selectedLink)
                        AdsPreference.setappopen_redirect(selectedLink)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun extractStringList(jsonObject: JSONObject, key: String): ArrayList<String> {
        val result = ArrayList<String>()
        try {
            val jsonArray = JSONArray(jsonObject.getString(key))
            for (i in 0 until jsonArray.length()) {
                result.add(jsonArray.getString(i))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }
}
