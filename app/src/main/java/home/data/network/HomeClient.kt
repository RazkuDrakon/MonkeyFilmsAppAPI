package home.data.network

import home.data.network.response.HomeResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeClient {
    @GET("/v3/ba19d40a-9750-4413-bd70-9c6e703cc9e9")
    suspend fun doFilmList(): Response<HomeResponse>
}