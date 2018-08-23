package br.com.lucena.recyclerviewkotlin.domain

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.Date

class Pull protected constructor(`in`: Parcel) : Parcelable {

    internal var id: Int = 0

    var title: String

    @SerializedName("created_at")
    var date: Date? = null

    var body: String

    @SerializedName("user")
    var user: Autor

    @SerializedName("html_url")
    var url: String

    init {
        id = `in`.readInt()
        title = `in`.readString()
        body = `in`.readString()
        user = `in`.readParcelable(Autor::class.java.classLoader)
        url = `in`.readString()
    }

    override fun toString(): String {
        return user.login + " - " + user.avatar
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeParcelable(user, i)
        parcel.writeString(url)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Pull> = object : Parcelable.Creator<Pull> {
            override fun createFromParcel(`in`: Parcel): Pull {
                return Pull(`in`)
            }

            override fun newArray(size: Int): Array<Pull?> {
                return arrayOfNulls(size)
            }
        }
    }
}