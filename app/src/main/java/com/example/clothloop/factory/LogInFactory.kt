import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clothloop.viewModel.logInViewModel

class LogInViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(logInViewModel::class.java)) {
            return logInViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}