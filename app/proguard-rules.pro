# Material Components (libs.material)
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# AndroidX Core KTX (libs.core.ktx)
-dontwarn androidx.core.**

# ssp (scaled size pixels) and sdp (scaled density pixels) libraries
# These are just utility libs, usually no rules needed, but safe to keep their classes:
-keep class com.intuit.ssp.** { *; }
-keep class com.intuit.sdp.** { *; }

# Firebase Remote Config (libs.firebase.config)
-keep class com.google.firebase.remoteconfig.** { *; }
-dontwarn com.google.firebase.remoteconfig.**

# AndroidX Browser (libs.browser)
-keep class androidx.browser.** { *; }
-dontwarn androidx.browser.**

# Glide (libs.glide)
# Glide requires specific rules to work with ProGuard:
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** { *; }
-keep class com.bumptech.glide.** { *; }
-dontwarn com.bumptech.glide.**

# Gson (libs.gson)
-keep class com.google.gson.stream.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# OneSignal SDK
-keep class com.onesignal.** { *; }
-dontwarn com.onesignal.**

# AndroidX AppCompat (libs.appcompat)
-keep class androidx.appcompat.** { *; }
-dontwarn androidx.appcompat.**

# AndroidX Activity (libs.activity)
-keep class androidx.activity.** { *; }
-dontwarn androidx.activity.**

# ConstraintLayout (libs.constraintlayout)
-keep class androidx.constraintlayout.** { *; }
-dontwarn androidx.constraintlayout.**

# Lottie (com.airbnb.android:lottie:6.6.2)
-keep class com.airbnb.lottie.** { *; }
-dontwarn com.airbnb.lottie.**


# Keep all public classes that extend Activity
-keep public class * extends android.app.Activity

# Keep all public methods in classes that implement Parcelable
-keep public class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Keep specific class and its members

# Keep getters and setters for data models used with libraries like Gson


# Many libraries (like Retrofit, Gson, OkHttp) publish their own ProGuard rules.
# Check their documentation!


-keep class com.xrayscan.malebodyscanner.camera.simulatorapp.core.** { *; }
-keep class com.xrayscan.malebodyscanner.camera.simulatorapp.entry.** { *; }
