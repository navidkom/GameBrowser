package ir.artapps.gamebrowser.base

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
    }

    override fun onResume() {
        super.onResume()
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setWindowAnimations(
            R.style.dialog_animation_fade
        )
    }
}