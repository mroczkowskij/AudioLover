package com.jj.audiolover.view

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeImageTransform
import android.transition.Explode
import android.widget.ImageView
import com.jj.audiolover.R
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by JJ on 28.01.2018.
 */
@Singleton
class ScreenManager @Inject constructor() {
    val TAG = "FRAGMENT"

    //TODO awfull, hidious hack, need to fix dagger with activity scope component and inject activity in here
    lateinit var activity: WeakReference<AppCompatActivity>

    fun showFragment(fragment : Fragment){
        activity.get()?.let {
            val supportFragmentManager = activity.get()?.supportFragmentManager
            val fragmentTransaction = supportFragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.content, fragment)
            fragmentTransaction.addToBackStack(fragment.tag)
            fragmentTransaction.commit()
        }
    }

    fun showInitialFragment(fragment : Fragment){
        activity.get()?.let {
            val supportFragmentManager = activity.get()?.supportFragmentManager
            val fragmentTransaction = supportFragmentManager!!.beginTransaction()
            fragmentTransaction.add(R.id.content, fragment, TAG)
            fragmentTransaction.disallowAddToBackStack()
            fragmentTransaction.commit()
        }
    }

    fun showFragmentTransition( fromShared: ImageView, to: Fragment) {

        activity.get()?.let {
            val fragmentManager = activity.get()?.supportFragmentManager
            val fragmentFrom = fragmentManager?.findFragmentByTag(TAG)


            // Setup exit transition on first fragment
            fragmentFrom?.setSharedElementReturnTransition(DetailsTransition())
            fragmentFrom?.setExitTransition(Explode())

            // Setup enter transition on second fragment
            to.setSharedElementEnterTransition(DetailsTransition())
            to.setEnterTransition(Explode())

            activity.get()
                    ?.supportFragmentManager
                    ?.beginTransaction()?.replace(R.id.content, to)?.addToBackStack("transaction")?.addSharedElement(fromShared, "album")?.commit()
        }
    }
}