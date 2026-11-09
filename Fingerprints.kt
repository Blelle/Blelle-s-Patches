/*
 * Copyright 2026 Morphe.
 * Patches for TimeTree (works.jubilee.timetree)
 */

package app.morphe.patches.timetree.ad

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprint to locate PremiumManager.e()Z
 * Identifies the method responsible for checking whether premium mode is active.
 * Anchor string: "is_premium_enabled"
 */
internal object PremiumManagerIsEnabledFingerprint : Fingerprint(
    returnType = "Z",
    parameters = listOf(),
    filters = listOf(
        string("is_premium_enabled")
    )
)

/**
 * Fingerprint to locate PremiumManager.f(Z)V
 * Identifies the method that updates premium status from background sync workers.
 */
internal object PremiumManagerSetEnabledFingerprint : Fingerprint(
    classFingerprint = PremiumManagerIsEnabledFingerprint,
    returnType = "V",
    parameters = listOf("Z"),
    filters = listOf(
        string("is_premium_enabled")
    )
)

/**
 * Fingerprint to locate 3rd-party ad SDK initialization in TimeTree (vz.a).
 * Silencing this method prevents Google AdMob, AppLovin, Pangle, FAN, and Amazon
 * SDKs from running background network requests and ad telemetry.
 */
internal object AdSdkInitializerFingerprint : Fingerprint(
    returnType = "Ljava/lang/Object;",
    parameters = listOf("Z", "Lhmh;", "Lra4;"),
    filters = listOf(
        string("openwrap")
    )
)

/**
 * Fingerprint for SplashAdsActivity.onCreate()
 * Allows intercepting the cold-start splash ad and calling finish() immediately.
 */
internal object SplashAdsActivityFingerprint : Fingerprint(
    definingClass = "Lworks/jubilee/timetree/app/ui/SplashAdsActivity;",
    name = "onCreate",
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;")
)
