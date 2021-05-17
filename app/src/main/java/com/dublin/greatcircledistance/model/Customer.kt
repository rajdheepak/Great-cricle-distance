package com.dublin.greatcircledistance.model

import com.google.gson.annotations.SerializedName

data class Customer(

	@SerializedName("user_id")
	val userId: Int,

	@SerializedName("latitude")
	val latitude: Double,

	@SerializedName("name")
	val name: String,

	@SerializedName("longitude")
	val longitude: Double
)
