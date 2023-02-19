package com.example.foodplanner.meal_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.network.models.MealModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealActivity extends AppCompatActivity {

    private static final String TAG = "MealActivity";
    private RecyclerView ingredentsRec;
    private IngredientsAdapter ingredientsAdapter;
    private List<String> listMeasures, listIngredients;
    private TextView mealMainTitle, mealInstructions;
    private ImageView headerImage;
    MealModel meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        String strIngredient = "strIngredient0", strMeasure = "strMeasure0";
        listMeasures = new ArrayList<>();
        listIngredients = new ArrayList<>();

        //receiving data from api
        meal = (MealModel) getIntent().getExtras().get("meal");

        //preparing ingredients & measures lists
        if (!meal.getStrIngredient1().equals("")) {
            listIngredients.add(meal.getStrIngredient1());
            if (!meal.getStrIngredient2().equals("")) {
                listIngredients.add(meal.getStrIngredient2());
                if (!meal.getStrIngredient3().equals("")) {
                    listIngredients.add(meal.getStrIngredient3());
                    if (!meal.getStrIngredient4().equals("")) {
                        listIngredients.add(meal.getStrIngredient4());
                        if (!meal.getStrIngredient5().equals("")) {
                            listIngredients.add(meal.getStrIngredient5());
                            if (!meal.getStrIngredient6().equals("")) {
                                listIngredients.add(meal.getStrIngredient6());
                                if (!meal.getStrIngredient7().equals("")) {
                                    listIngredients.add(meal.getStrIngredient7());
                                    if (!meal.getStrIngredient8().equals("")) {
                                        listIngredients.add(meal.getStrIngredient8());
                                        if (!meal.getStrIngredient9().equals("")) {
                                            listIngredients.add(meal.getStrIngredient9());
                                            if (!meal.getStrIngredient10().equals("")) {
                                                listIngredients.add(meal.getStrIngredient10());
                                                if (!meal.getStrIngredient11().equals("")) {
                                                    listIngredients.add(meal.getStrIngredient11());
                                                    if (!meal.getStrIngredient12().equals("")) {
                                                        listIngredients.add(meal.getStrIngredient12());
                                                        if (!meal.getStrIngredient13().equals("")) {
                                                            listIngredients.add(meal.getStrIngredient13());
                                                            if (!meal.getStrIngredient14().equals("")) {
                                                                listIngredients.add(meal.getStrIngredient14());
                                                                if (!meal.getStrIngredient15().equals("")) {
                                                                    listIngredients.add(meal.getStrIngredient15());
                                                                    if (!meal.getStrIngredient16().equals("")) {
                                                                        listIngredients.add(meal.getStrIngredient16());
                                                                        if (!meal.getStrIngredient17().equals("")) {
                                                                            listIngredients.add(meal.getStrIngredient17());
                                                                            if (!meal.getStrIngredient18().equals("")) {
                                                                                listIngredients.add(meal.getStrIngredient18());
                                                                                if (!meal.getStrIngredient19().equals("")) {
                                                                                    listIngredients.add(meal.getStrIngredient19());
                                                                                    if (!meal.getStrIngredient20().equals(""))
                                                                                        listIngredients.add(meal.getStrIngredient20());
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!meal.getStrMeasure1().equals("")) {
            if (meal.getStrMeasure1().equals(" "))
                listMeasures.add("-");
            else
                listMeasures.add(meal.getStrMeasure1());
            if (!meal.getStrMeasure2().equals("")) {
                if (meal.getStrMeasure2().equals(" "))
                    listMeasures.add("-");
                else
                    listMeasures.add(meal.getStrMeasure2());
                if (!meal.getStrMeasure3().equals("")) {
                    if (meal.getStrMeasure3().equals(" "))
                        listMeasures.add("-");
                    else
                        listMeasures.add(meal.getStrMeasure3());
                    if (!meal.getStrMeasure4().equals("")) {
                        if (meal.getStrMeasure4().equals(" "))
                            listMeasures.add("-");
                        else
                            listMeasures.add(meal.getStrMeasure4());
                        if (!meal.getStrMeasure5().equals("")) {
                            if (meal.getStrMeasure5().equals(" "))
                                listMeasures.add("-");
                            else
                                listMeasures.add(meal.getStrMeasure5());
                            if (!meal.getStrMeasure6().equals("")) {
                                if (meal.getStrMeasure6().equals(" "))
                                    listMeasures.add("-");
                                else
                                    listMeasures.add(meal.getStrMeasure6());
                                if (!meal.getStrMeasure7().equals("")) {
                                    if (meal.getStrMeasure7().equals(" "))
                                        listMeasures.add("-");
                                    else
                                        listMeasures.add(meal.getStrMeasure7());
                                    if (!meal.getStrMeasure8().equals("")) {
                                        if (meal.getStrMeasure8().equals(" "))
                                            listMeasures.add("-");
                                        else
                                            listMeasures.add(meal.getStrMeasure8());
                                        if (!meal.getStrMeasure9().equals("")) {
                                            if (meal.getStrMeasure9().equals(" "))
                                                listMeasures.add("-");
                                            else
                                                listMeasures.add(meal.getStrMeasure9());
                                            if (!meal.getStrMeasure10().equals("")) {
                                                if (meal.getStrMeasure10().equals(" "))
                                                    listMeasures.add("-");
                                                else
                                                    listMeasures.add(meal.getStrMeasure10());
                                                if (!meal.getStrMeasure11().equals("")) {
                                                    if (meal.getStrMeasure11().equals(" "))
                                                        listMeasures.add("-");
                                                    else
                                                        listMeasures.add(meal.getStrMeasure11());
                                                    if (!meal.getStrMeasure12().equals("")) {
                                                        if (meal.getStrMeasure12().equals(" "))
                                                            listMeasures.add("-");
                                                        else
                                                            listMeasures.add(meal.getStrMeasure12());
                                                        if (!meal.getStrMeasure13().equals("")) {
                                                            if (meal.getStrMeasure13().equals(" "))
                                                                listMeasures.add("-");
                                                            else
                                                                listMeasures.add(meal.getStrMeasure13());
                                                            if (!meal.getStrMeasure14().equals("")) {
                                                                if (meal.getStrMeasure14().equals(" "))
                                                                    listMeasures.add("-");
                                                                else
                                                                    listMeasures.add(meal.getStrMeasure14());
                                                                if (!meal.getStrMeasure15().equals("")) {
                                                                    if (meal.getStrMeasure15().equals(" "))
                                                                        listMeasures.add("-");
                                                                    else
                                                                        listMeasures.add(meal.getStrMeasure15());
                                                                    if (!meal.getStrMeasure16().equals("")) {
                                                                        if (meal.getStrMeasure16().equals(" "))
                                                                            listMeasures.add("-");
                                                                        else
                                                                            listMeasures.add(meal.getStrMeasure16());
                                                                        if (!meal.getStrMeasure17().equals("")) {
                                                                            if (meal.getStrMeasure17().equals(" "))
                                                                                listMeasures.add("-");
                                                                            else
                                                                                listMeasures.add(meal.getStrMeasure17());
                                                                            if (!meal.getStrMeasure18().equals("")) {
                                                                                if (meal.getStrMeasure18().equals(" "))
                                                                                    listMeasures.add("-");
                                                                                else
                                                                                    listMeasures.add(meal.getStrMeasure18());
                                                                                if (!meal.getStrMeasure19().equals("")) {
                                                                                    if (meal.getStrMeasure19().equals(" "))
                                                                                        listMeasures.add("-");
                                                                                    else
                                                                                        listMeasures.add(meal.getStrMeasure19());
                                                                                    if (!meal.getStrMeasure20().equals("")){
                                                                                        if (meal.getStrMeasure20().equals(" "))
                                                                                            listMeasures.add("-");
                                                                                        else
                                                                                            listMeasures.add(meal.getStrMeasure20());
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        ingredentsRec = findViewById(R.id.ingredients_rec);
        ingredentsRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ingredientsAdapter = new IngredientsAdapter();
        ingredentsRec.setAdapter(ingredientsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mealMainTitle = findViewById(R.id.mealMainTitle);
        mealMainTitle.setText(meal.getStrMeal());

        headerImage = findViewById(R.id.headerImage);
        Glide.with(this).load(meal.getStrMealThumb()).into(headerImage);

        mealInstructions = findViewById(R.id.mealInstructions);
        mealInstructions.setText(meal.getStrInstructions());

        YouTubePlayerView youTubePlayerView = findViewById(R.id.video);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                //i should parse url to get id after v=
                String videoId = meal.getStrYoutube().substring(meal.getStrYoutube().indexOf("=") + 1);
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        ingredientsAdapter.setLists(listMeasures, listIngredients);
        ingredientsAdapter.notifyDataSetChanged();


        Log.i(TAG, "onStart: " + mealMainTitle.getText());
    }
}