import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Set up Retrofit
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

// ViewModel
class ItemViewModel : ViewModel() {
    val items = liveData(Dispatchers.IO) {
        val response = RetrofitInstance.apiService.getItems()
        emit(response.filter { !it.name.isNullOrBlank() }
            .sortedWith(compareBy({ it.listId }, { it.name })))
    }
}
