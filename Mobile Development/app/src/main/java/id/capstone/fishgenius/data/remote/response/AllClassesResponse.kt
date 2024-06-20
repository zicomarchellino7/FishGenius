package id.capstone.fishgenius.data.remote.response

import com.google.gson.annotations.SerializedName
import id.capstone.fishgenius.data.local.database.Classes

data class AllClassesResponse(

	@field:SerializedName("data")
	val data: ClassData,

	@field:SerializedName("status")
	val status: String
)

data class ClassData(

	@field:SerializedName("class")
	val jsonMemberClass: List<Classes>
)