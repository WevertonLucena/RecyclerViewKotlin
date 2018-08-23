package br.com.lucena.recyclerviewkotlin.domain

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName


class Autor protected constructor(`in`: Parcel) : Parcelable {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("login")
    var login: String

    @SerializedName("avatar_url")
    var avatar: String

    override fun toString(): String {
        return "[ $id, $login,$avatar]"
    }

    init {
        id = `in`.readInt()
        login = `in`.readString()
        avatar = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(login)
        parcel.writeString(avatar)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Autor> = object : Parcelable.Creator<Autor> {
            override fun createFromParcel(`in`: Parcel): Autor {
                return Autor(`in`)
            }

            override fun newArray(size: Int): Array<Autor?> {
                return arrayOfNulls(size)
            }
        }
    }
}