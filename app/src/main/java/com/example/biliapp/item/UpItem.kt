package com.example.biliapp.item

import android.os.Parcel
import android.os.Parcelable

class UpItem(val img:Int, val name: String?,val fans:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(img)
        parcel.writeString(name)
        parcel.writeInt(fans)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpItem> {
        override fun createFromParcel(parcel: Parcel): UpItem {
            return UpItem(parcel)
        }

        override fun newArray(size: Int): Array<UpItem?> {
            return arrayOfNulls(size)
        }
    }
}
