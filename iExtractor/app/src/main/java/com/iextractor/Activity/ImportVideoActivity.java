package com.iextractor.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iextractor.Adapter.ImageAdapter;
import com.iextractor.Model.Image_From_Video;
import com.iextractor.R;

import net.alhazmy13.mediapicker.Video.VideoPicker;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class ImportVideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private ImageView returnButton;
    private RecyclerView recyclerView;
    private ArrayList<Image_From_Video> imageFromVideoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_video);
        initUI();
        setEventClick();
    }

    private void setEventClick() {
        returnButton.setOnClickListener(v -> startActivity(new Intent(ImportVideoActivity.this, MainActivity.class)));
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recycler_view);
        videoView = findViewById(R.id.video_view);
        returnButton = findViewById(R.id.imageView);
        new VideoPicker.Builder(ImportVideoActivity.this)
                .mode(VideoPicker.Mode.CAMERA_AND_GALLERY)
                .directory(VideoPicker.Directory.DEFAULT)
                .extension(VideoPicker.Extension.MP4)
                .enableDebuggingMode(true)
                .build();
    }

    private void setRecyclerView(String uri, int timeInMillisec) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ImportVideoActivity.this, LinearLayoutManager.HORIZONTAL, false));
        FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
        mmr.setDataSource(URLDecoder.decode(uri));
        for (int i = 0; i < timeInMillisec / 250; i++) {
            Bitmap b = mmr.getFrameAtTime(i * 250000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
            imageFromVideoArrayList.add(new Image_From_Video(b));
        }
        ImageAdapter imageAdapter = new ImageAdapter(ImportVideoActivity.this, imageFromVideoArrayList);
        recyclerView.setAdapter(imageAdapter);
//        imageAdapter.setOnItemClickListener(position -> {
//            Bitmap bitmap = imageFromVideoArrayList.get(position).getBitmap();
//            imageView.setImageBitmap(bitmap);
////            imageFromVideoArrayList.remove(position);
////            imageAdapter.notifyDataSetChanged();
//        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH);
            Log.d("BOSS: ", mPaths.get(0));
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, Uri.parse(mPaths.get(0)));
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            int timeInMillisec = Integer.parseInt(time);
            Log.d("BOSSSSS: ", time);
            videoView.setVideoURI(Uri.parse(mPaths.get(0)));
            videoView.start();
            setRecyclerView(mPaths.get(0), timeInMillisec);
        }
    }
}
