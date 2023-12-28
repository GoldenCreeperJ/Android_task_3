package com.example.biliapp.item

import android.os.Parcel
import android.os.Parcelable

class IntermediateItem(val owner:String?, val content: ArrayList<VideoItem>?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(VideoItem)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(owner)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IntermediateItem> {
        override fun createFromParcel(parcel: Parcel): IntermediateItem {
            return IntermediateItem(parcel)
        }

        override fun newArray(size: Int): Array<IntermediateItem?> {
            return arrayOfNulls(size)
        }
    }
}