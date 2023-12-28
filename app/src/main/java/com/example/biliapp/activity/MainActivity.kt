package com.example.biliapp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biliapp.DataSender
import com.example.biliapp.adapter.IntermediateAdapter
import com.example.biliapp.adapter.UpListAdapter
import com.example.biliapp.databinding.ActivityMainBinding
import com.example.biliapp.item.IntermediateItem
import com.example.biliapp.item.UpItem
import com.example.biliapp.item.VideoItem

class MainActivity : AppCompatActivity(), UpListAdapter.OnClickListener, UpListAdapter.OnLongClickListener {
    private var upData: ArrayList<UpItem> = DataSender().createUpItem()
    private var videoData: ArrayList<IntermediateItem> = DataSender().createVideoItem()
    private lateinit var pagerLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding
    private lateinit var upListAdapter: UpListAdapter
    private lateinit var intermediateAdapter: IntermediateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState!=null){
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU) {
                upData = savedInstanceState.getParcelableArrayList("up_data", UpItem::class.java) as ArrayList<UpItem>
                videoData = savedInstanceState.getParcelableArrayList("video_data", IntermediateItem::class.java) as ArrayList<IntermediateItem> }
            else{
                upData= savedInstanceState.getIntegerArrayList("up_img")!!
                    .zip(savedInstanceState.getStringArrayList("up_name")!!)
                    .zip(savedInstanceState.getIntegerArrayList("up_fans")!!)
                    { (upImg, upName), upFans -> UpItem(upImg, upName,upFans) } as ArrayList<UpItem>
                videoData.clear()
                for(i in savedInstanceState.getStringArrayList("video_owner")!!){
                    videoData.add(IntermediateItem(i,
                        ArrayList(savedInstanceState.getStringArrayList("video_name_$i")!!
                            .zip(savedInstanceState.getIntegerArrayList("video_img_$i")!!)
                            {videoName,videoImg -> VideoItem(videoName,videoImg)})))
                }
            }
        }

        binding.upList.layoutManager= LinearLayoutManager(this)
            .apply { orientation= LinearLayoutManager.HORIZONTAL}
        binding.upList.adapter= UpListAdapter(upData)
            .apply {clickListener=this@MainActivity
                longClickListener=this@MainActivity}
        binding.dynamicList.adapter= IntermediateAdapter(videoData)
        upListAdapter=binding.upList.adapter as UpListAdapter
        intermediateAdapter=binding.dynamicList.adapter as IntermediateAdapter

        pagerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data?.getBooleanExtra("is_unfollowed", false) == true) {
                Toast.makeText(this,"取关成功",Toast.LENGTH_SHORT).show()
                result.data!!.getStringExtra("up_name")!!.let {upName->
                    upData = ArrayList(upData.filter { it.name != upName})
                    videoData = ArrayList(videoData.filter { it.owner != upName})
                    upListAdapter.deleteUp(upName)
                    intermediateAdapter.deleteVideo(upName)
                }
            }
        }
    }

    override fun onLongClick(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            intent.putExtra("up_info",upData[position])
            intent.putExtra("video_info", ArrayList(videoData[position].content!!))}
        else{
            intent.putExtra("up_img", upData[position].img)
            intent.putExtra("up_name",upData[position].name)
            intent.putExtra("up_fans",upData[position].fans)
            intent.putStringArrayListExtra("video_name", ArrayList(videoData[position].content!!.map { it.name }))
            intent.putIntegerArrayListExtra("video_img", ArrayList(videoData[position].content!!.map { it.img }))}
        pagerLauncher.launch(intent)
    }

    override fun onClick(position: Int) {
        binding.dynamicList.setCurrentItem(position,true)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU) {
            outState.putParcelableArrayList("up_data",upData)
            outState.putParcelableArrayList("video_data",videoData)}
        else{
            outState.putIntegerArrayList("up_img", ArrayList(upData.map { it.img }))
            outState.putStringArrayList("up_name",ArrayList(upData.map { it.name }))
            outState.putIntegerArrayList("up_fans",ArrayList(upData.map { it.fans }))
            outState.putStringArrayList("video_owner", ArrayList(videoData.map { it.owner }))
            for(i in videoData){
                outState.putStringArrayList("video_name_${i.owner}", ArrayList(i.content!!.map { it.name }))
                outState.putIntegerArrayList("video_img_${i.owner}", ArrayList(i.content.map { it.img }))}}
    }
}