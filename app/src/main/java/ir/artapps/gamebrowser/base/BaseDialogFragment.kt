package ir.artapps.gamebrowser.base

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ir.artapps.gamebrowser.R

/**
 * Created by navid
 */
open class BaseDialogFragment : DialogFragment() {
    override fun show(
        manager: FragmentManager,
        tag: String?
    ) {
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.AppTheme)

        dialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    //        activity?.window?.decorView?.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            activity?.window?.statusBarColor = Color.TRANSPARENT
//        }

    override fun onResume() {
        super.onResume()
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )

        }
    }
}