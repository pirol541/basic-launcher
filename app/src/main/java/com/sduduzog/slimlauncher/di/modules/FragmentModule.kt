package com.sduduzog.slimlauncher.di.modules

import com.sduduzog.slimlauncher.ui.main.HomeFragment
import com.sduduzog.slimlauncher.ui.options.AddAppFragment
import com.sduduzog.slimlauncher.ui.options.CustomiseAppsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesCustomiseAppsFragment() : CustomiseAppsFragment

    @ContributesAndroidInjector
    abstract fun contributesAddAppFragment() : AddAppFragment
}
