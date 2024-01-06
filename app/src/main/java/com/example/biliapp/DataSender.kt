package com.example.biliapp

import com.example.biliapp.item.IntermediateItem
import com.example.biliapp.item.UpItem
import com.example.biliapp.item.VideoItem

class DataSender {
    fun createUpItem() = arrayListOf(
        UpItem(R.drawable.up1, "小明0",1000),
        UpItem(R.drawable.up2, "小王0",2000),
        UpItem(R.drawable.up3, "小红0",3000),
        UpItem(R.drawable.up4, "小张0",0),
        UpItem(R.drawable.up1, "小明1",1000),
        UpItem(R.drawable.up2, "小王1",2000),
        UpItem(R.drawable.up3, "小红1",3000),
        UpItem(R.drawable.up4, "小张1",0),
        UpItem(R.drawable.up1, "小明2",1000),
        UpItem(R.drawable.up2, "小王2",2000),
        UpItem(R.drawable.up3, "小红2",3000),
        UpItem(R.drawable.up4, "小张2",0)
    )

    fun createVideoItem() = arrayListOf(
        IntermediateItem("小明0",arrayListOf(
            VideoItem("我是小明0", R.drawable.video1),
            VideoItem("小明0是我", R.drawable.video5),)),
        IntermediateItem("小王0",arrayListOf(
            VideoItem("我是小王0", R.drawable.video2),
            VideoItem("小王0是我", R.drawable.video6),)),
        IntermediateItem("小红0",arrayListOf(
            VideoItem("我是小红0", R.drawable.video3),
            VideoItem("小红0是我", R.drawable.video7),)),
        IntermediateItem("小张0",arrayListOf(
            VideoItem("我是小张0", R.drawable.video4),
            VideoItem("小张0是我", R.drawable.video8))),
        IntermediateItem("小明1",arrayListOf(
            VideoItem("我是小明1", R.drawable.video1),
            VideoItem("小明1是我", R.drawable.video5),)),
        IntermediateItem("小王1",arrayListOf(
            VideoItem("我是小王1", R.drawable.video2),
            VideoItem("小王1是我", R.drawable.video6),)),
        IntermediateItem("小红1",arrayListOf(
            VideoItem("我是小红1", R.drawable.video3),
            VideoItem("小红1是我", R.drawable.video7),)),
        IntermediateItem("小张1",arrayListOf(
            VideoItem("我是小张1", R.drawable.video4),
            VideoItem("小张1是我", R.drawable.video8))),
        IntermediateItem("小明2",arrayListOf(
            VideoItem("我是小明2", R.drawable.video1),
            VideoItem("小明2是我", R.drawable.video5),)),
        IntermediateItem("小王2",arrayListOf(
            VideoItem("我是小王2", R.drawable.video2),
            VideoItem("小王2是我", R.drawable.video6),)),
        IntermediateItem("小红2",arrayListOf(
            VideoItem("我是小红2", R.drawable.video3),
            VideoItem("小红2是我", R.drawable.video7),)),
        IntermediateItem("小张2",arrayListOf(
            VideoItem("我是小张2", R.drawable.video4),
            VideoItem("小张2是我", R.drawable.video8)))
    )
}