package com.example.biliapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biliapp.adapter.VideoListAdapter
import com.example.biliapp.databinding.ActivityDetailBinding
import com.example.biliapp.item.UpItem
import com.example.biliapp.item.VideoItem

class DetailActivity : AppCompatActivity() {
    private var isUnfollowed=false
    private lateinit var binding:ActivityDetailBinding
    private lateinit var videoInfo:ArrayList<VideoItem>
    private lateinit var upInfo:UpItem


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isUnfollowed=false

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            videoInfo= intent.getParcelableExtra("video_info", ArrayList<VideoItem>()::class.java)!!
            upInfo=intent.getParcelableExtra("up_info", UpItem::class.java)!! }
        else{
            upInfo= UpItem(intent.getIntExtra("up_img",0),
                           intent.getStringExtra("up_name"),
                           intent.getIntExtra("up_fans",0))
            videoInfo= intent.getStringArrayListExtra("video_name")!!
                .zip(intent.getIntegerArrayListExtra("video_img")!!)
                {videoName,videoImg -> VideoItem(videoName,videoImg)} as ArrayList<VideoItem>
        }
        upInfo.apply {
            binding.upName.text = name
            binding.upImg.setImageResource(img)
            binding.upFans.text = "粉丝：$fans 人"
        }
        binding.videoList.layoutManager= LinearLayoutManager(this)
        binding.videoList.adapter= VideoListAdapter(videoInfo)
        binding.unfollowButton.setOnClickListener{
            if (isUnfollowed){
                Toast.makeText(this,"关注成功", Toast.LENGTH_SHORT).show()
                binding.unfollowButton.text="已关注" }
            else{
                Toast.makeText(this,"取关成功",Toast.LENGTH_SHORT).show()
                binding.unfollowButton.text="关注" }
            isUnfollowed= !isUnfollowed
        }
        binding.backButton.setOnClickListener { finish() }
    }

    override fun finish() {
        val intent = Intent()
        intent.putExtra("is_unfollowed",isUnfollowed)
        intent.putExtra("up_name",binding.upName.text)
        setResult(RESULT_OK,intent)
        super.finish()
    }
}