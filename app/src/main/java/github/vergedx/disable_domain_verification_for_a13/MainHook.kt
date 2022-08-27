package github.vergedx.disable_domain_verification_for_a13

import android.content.Intent
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam?) {
        if (lpparam?.packageName != "android") return

        XposedHelpers.findAndHookMethod(
            // https://developer.android.com/reference/android/content/pm/PackageManager.ResolveInfoFlags
            "com.android.server.pm.verify.domain.DomainVerificationUtils", lpparam.classLoader,
            "isDomainVerificationIntent", Intent::class.java, Long::class.javaPrimitiveType,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return false
                }
            }
        )
    }
}
