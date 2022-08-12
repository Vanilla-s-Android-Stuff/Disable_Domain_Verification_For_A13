package github.vergedx.disable_domain_verification_for_a13;

import android.content.Intent;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

// https://github.com/Henry-ZHR/KillDomainVerification/blob/master/app/src/main/java/moe/henry_zhr/kill_domain_verification/MainHook.java
public class MainHook implements IXposedHookLoadPackage { // TODO: Refactor by using Kotlin.

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals("android")) return;

        // https://developer.android.com/reference/android/content/pm/PackageManager.ResolveInfoFlags
        XposedHelpers.findAndHookMethod("com.android.server.pm.verify.domain.DomainVerificationUtils",
                lpparam.classLoader, "isDomainVerificationIntent", Intent.class, long.class,
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return false;
                    }
                }
        );
    }
}
