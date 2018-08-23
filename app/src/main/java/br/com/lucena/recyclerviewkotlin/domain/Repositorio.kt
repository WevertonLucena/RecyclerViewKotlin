package br.com.lucena.recyclerviewkotlin.domain

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class Repositorio protected constructor(`in`: Parcel) : Parcelable {

    @SerializedName("id")
    internal var id: Int = 0

    @SerializedName("name")
    var name: String

    @SerializedName("description")
    var description: String

    @SerializedName("stargazers_count")
    var stars: Int = 0

    @SerializedName("forks")
    var forks: Int = 0

    @SerializedName("owner")
    var autor: Autor? = null

    init {
        id = `in`.readInt()
        name = `in`.readString()
        description = `in`.readString()
        stars = `in`.readInt()
        forks = `in`.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(stars)
        parcel.writeInt(forks)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Repositorio> = object : Parcelable.Creator<Repositorio> {
            override fun createFromParcel(`in`: Parcel): Repositorio {
                return Repositorio(`in`)
            }

            override fun newArray(size: Int): Array<Repositorio?> {
                return arrayOfNulls(size)
            }
        }
    }
}
