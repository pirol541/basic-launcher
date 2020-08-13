package com.sduduzog.slimlauncher.ui.main

import android.content.*
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sduduzog.slimlauncher.R
import com.sduduzog.slimlauncher.adapters.HomeAdapter
import com.sduduzog.slimlauncher.models.HomeApp
import com.sduduzog.slimlauncher.models.MainViewModel
import com.sduduzog.slimlauncher.utils.BaseFragment
import com.sduduzog.slimlauncher.utils.OnLaunchAppListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), SharedPreferences.OnSharedPreferenceChangeListener, OnLaunchAppListener {
    private lateinit var settings : SharedPreferences

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var receiver: BroadcastReceiver
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter1 = HomeAdapter(this)
        home_fragment_list.adapter = adapter1

        settings = this.context?.getSharedPreferences(getString(R.string.prefs_settings), AppCompatActivity.MODE_PRIVATE)!!
        settings.registerOnSharedPreferenceChangeListener(this)

        activity?.let {
            viewModel = ViewModelProvider(it, viewModelFactory).get(MainViewModel::class.java)
        } ?: throw Error("Activity null, something here is fucked up")

        viewModel.apps.observe(viewLifecycleOwner, Observer { list ->
            list?.let { apps ->
                adapter1.setItems(apps.filter {
                    it.sortingIndex < 6
                })
            }
        })

        home_fragment.setOnLongClickListener {
            home_fragment_options.visibility = View.VISIBLE
            true
        }

        home_fragment_options.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_customiseAppsFragment))
    }

    override fun onStart() {
        super.onStart()
    }

    override fun getFragmentView(): ViewGroup = home_fragment

    override fun onResume() {
        super.onResume()
        home_fragment_options.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onLaunch(app: HomeApp, view: View) {
        try {
            val intent = Intent()
            val name = ComponentName(app.packageName, app.activityName)
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
            intent.component = name
            intent.resolveActivity(activity!!.packageManager)?.let {
                launchActivity(view, intent)
            }
        } catch (e: Exception) {
            // Do no shit yet
        }
    }

    override fun onBack(): Boolean {
        home_fragment_options.visibility = View.GONE
        return true
    }

    override fun onHome() {
        home_fragment_options.visibility = View.GONE
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences, p1: String): Unit { }
}
