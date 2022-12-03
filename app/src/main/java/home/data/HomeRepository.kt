package home.data

import home.data.network.HomeService
import model.MediaModel

class HomeRepository {
    private val api = HomeService()

    suspend fun doFilms():List<MediaModel>{
        return api.doFilms()
    }
}