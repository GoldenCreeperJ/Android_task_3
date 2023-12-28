package com.example.biliapp

import com.example.biliapp.item.IntermediateItem
import com.example.biliapp.item.UpItem
import com.example.biliapp.item.VideoItem

class DataSender {
    fun createUpItem() = arrayListOf(
        UpItem(R.drawable.up1, "小明",1000),
        UpItem(R.drawable.up2, "小王",2000),
        UpItem(R.drawable.up3, "小红",3000),
        UpItem(R.drawable.up4, "小张",0)
    )

    fun createVideoItem() = arrayListOf(
        IntermediateItem("小明",arrayListOf(
            VideoItem("我是小明", R.drawable.video1),
            VideoItem("小明是我", R.drawable.video5),)),
        IntermediateItem("小王",arrayListOf(
            VideoItem("我是小王", R.drawable.video2),
            VideoItem("小王是我", R.drawable.video6),)),
        IntermediateItem("小红",arrayListOf(
            VideoItem("我是小红", R.drawable.video3),
            VideoItem("小红是我", R.drawable.video7),)),
        IntermediateItem("小张",arrayListOf(
            VideoItem("我是小张", R.drawable.video4),
            VideoItem("小张是我", R.drawable.video8)))
    )
}