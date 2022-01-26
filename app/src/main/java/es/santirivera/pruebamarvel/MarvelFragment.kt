package es.santirivera.pruebamarvel

import android.app.Dialog
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.santirivera.domain.exception.EmptyListException
import retrofit2.HttpException
import java.net.UnknownHostException


abstract class MarvelFragment : Fragment() {

    private var lastErrorMessageTimeStamp = 0L
    private val minTimeBetweenErrorMessages = 5000L

    private var loading = false

    private lateinit var dialog: Dialog

    /**
     * Returns true if you should retry the call
     */
    fun handleErrors(exception: Exception): Boolean {
        var retry = false
        val message = when (exception) {
            is UnknownHostException -> {
                retry = true
                getString(R.string.unknown_host)
            }

            is EmptyListException -> {
                retry = true
                getString(R.string.empty_list)
            }
            is HttpException -> {
                retry = false
                when (exception.code()) {
                    401 -> getString(R.string.api_key_error)
                    409 -> getString(R.string.api_params_error)
                    else -> exception.message()
                }
            }
            else -> exception.message
        }
        val timestamp = System.currentTimeMillis()
        val difference = timestamp - lastErrorMessageTimeStamp
        if (difference > minTimeBetweenErrorMessages) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            lastErrorMessageTimeStamp = timestamp
        }
        return retry
    }

    fun startLoad() {
        if (!loading) {
            dialog = createDialog()
            dialog.show()
            loading = true
        }
    }

    fun endLoad() {
        if (loading) {
            dialog.hide()
            loading = false
        }
    }

    private fun createDialog(): Dialog {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_dialog)
        return dialog
    }

}