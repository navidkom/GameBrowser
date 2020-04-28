package ir.artapps.gamebrowser.ui.detail

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.customview.VenueDetailCustomView
import kotlinx.android.synthetic.main.fragment_venue_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by navid
 */
class VenueDetailFragment : BaseDialogFragment() {
    private val MAP_ZOOM_LEVEL = 14.0f
    private var venue: Game? = null
    private val venueDetailViewModel: VenueDetailViewModel by viewModel()

}