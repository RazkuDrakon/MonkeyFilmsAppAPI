package home.data.network.response

import com.google.gson.annotations.SerializedName
import model.MediaModel

data class HomeResponse (@SerializedName("success") val homeList:List<MediaModel>)