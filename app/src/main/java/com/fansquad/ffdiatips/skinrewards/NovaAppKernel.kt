package com.fansquad.ffdiatips.skinrewards

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.fansquad.ffdiatips.skinrewards.zstream.AdPulsePrefs
import com.fansquad.ffdiatips.skinrewards.zstream.ZControlUnit
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class NovaAppKernel : ZControlUnit() {

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


                    val jsonObject = JSONObject(jsonString)

                    AdPulsePrefs.setNative_show(
                        jsonObject.optBoolean(
                            AdPulsePrefs.KEY_NATIVE_TOGGLE, false
                        )
                    )
                    AdPulsePrefs.setIninterstialWeb(
                        jsonObject.optBoolean(
                            "interstial_onoff",
                            false
                        )
                    )

                    val bannerImageArray = extractStringList(jsonObject, "daily_BannerImage")
                    AdPulsePrefs.setKeyStringList(bannerImageArray)

                    val multipleLinkArray = extractStringList(jsonObject, "multiple_link")
                    if (multipleLinkArray.isNotEmpty()) {
                        val selectedLink = multipleLinkArray.random()
                        AdPulsePrefs.setRedirectLink(selectedLink)
                        AdPulsePrefs.setnative_link(selectedLink)
                        AdPulsePrefs.storeInterstitialRoute(selectedLink)
                    }
                    val privacyPolicyUrl = jsonObject.optString("privacy_policy_link", "")
                    AdPulsePrefs.setPrivacyPolicyUrl(privacyPolicyUrl)

                    val oneSignalAppId = jsonObject.optString("onesignal_id", "")
                    AdPulsePrefs.setOneSignalAppId(oneSignalAppId)

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
