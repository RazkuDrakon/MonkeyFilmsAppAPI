package home.data.network

import core_network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.MediaModel

class HomeService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doFilms():List<MediaModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(HomeClient::class.java).doFilmList()
            response.body()?.homeList ?: emptyList()
        }
    }
}