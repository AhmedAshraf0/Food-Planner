package com.example.foodplanner.meal_screen;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.foodplanner.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealFragment extends Fragment {
    private RecyclerView ingredentsRec;
    private IngredientsAdapter ingredientsAdapter;
    private List<String> measuresTest , ingredentsTest;
    private ImageView imageView;
    private VideoView videoView;
    public MealFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        measuresTest = new ArrayList<>();
        ingredentsTest = new ArrayList<>();
        ingredentsTest.add("Vegetable Oil");
        ingredentsTest.add("Onion");
        ingredentsTest.add("Garlic");
        ingredentsTest.add("Ginger");
        ingredentsTest.add("Coriander");
        measuresTest.add("1 tbls");
        measuresTest.add("1 finely chopped");
        measuresTest.add("2 cloves chopped");
        measuresTest.add("1 part");
        measuresTest.add("1 Packet");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        ingredentsRec = view.findViewById(R.id.ingredients_rec);
        imageView = view.findViewById(R.id.headerImage);
//        videoView = view.findViewById(R.id.video);
//
//        MediaController mediaController = new MediaController(getContext());
//        mediaController.setAnchorView(videoView);
//        Uri uri = Uri.parse("rtsp://v6.cache4.c.youtube.com/CigLENy73wIaHwmh5W2TKCuN2RMYDSANFEgGUgx1c2VyX3VwbG9hZHMM/0/0/0/video.3gp");
//        videoView.setVideoURI(uri);
//        videoView.setMediaController(mediaController);
//        videoView.requestFocus();
//        videoView.start();
        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.video);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                //i should parse url to get id after v=
                String videoId = "tspdJ6hxqnc";
                youTubePlayer.cueVideo(videoId,0);
            }
        });
        imageView.setImageResource(R.mipmap.image3);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ingredentsRec.setLayoutManager(linearLayoutManager);
        ingredientsAdapter = new IngredientsAdapter(measuresTest,ingredentsTest);
        ingredentsRec.setAdapter(ingredientsAdapter);
    }
}