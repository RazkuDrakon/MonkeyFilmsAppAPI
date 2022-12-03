package home.domain

import home.data.HomeRepository
import model.MediaModel

class HomeUseCase {
    private val repository = HomeRepository()
    suspend operator fun invoke():List<MediaModel>{
        return repository.doFilms()
    }
}