package com.example.appmovie.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("id")
    val id :Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("overview")
    val overview:String,
    @SerializedName("vote_average")
    val voteAverage:Double,
    @SerializedName("adult")
    val adult:Boolean,
    @SerializedName("backdrop_path")
    val imagen:String,
    @SerializedName("release_date")
    val fecha:String
): Serializable