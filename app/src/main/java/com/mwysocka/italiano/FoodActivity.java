package com.mwysocka.italiano;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create an ArrayList of words
        final ArrayList<SingleWord> words = new ArrayList<SingleWord>();

        words.add(new SingleWord("Jesteś głodny?", "Hai fame?", R.raw.test));
        words.add(new SingleWord("Smacznego", "Buon appetito!", R.raw.test));
        words.add(new SingleWord("Chcesz herbaty?", "Vuoi il tè?", R.raw.test));
        words.add(new SingleWord("chleb", "pane", R.raw.test));
        words.add(new SingleWord("herbata", "té", R.raw.test));
        words.add(new SingleWord("jaja", "uova", R.raw.test));
        words.add(new SingleWord("kawa", "caffé", R.raw.test));
        words.add(new SingleWord("kiełbasa", "salsiccia", R.raw.test));
        words.add(new SingleWord("masło", "burro", R.raw.test));
        words.add(new SingleWord("mięso", "carne", R.raw.test));
        words.add(new SingleWord("owoce", "frutta", R.raw.test));
        words.add(new SingleWord("ryby", "pesce", R.raw.test));
        words.add(new SingleWord("ryż", "riso", R.raw.test));
        words.add(new SingleWord("ser", "formaggio", R.raw.test));
        words.add(new SingleWord("sok", "succo", R.raw.test));
        words.add(new SingleWord("warzywa", "verdura", R.raw.test));
        words.add(new SingleWord("wędlina", "affettati", R.raw.test));
        words.add(new SingleWord("woda mineralna", "acqua minerale", R.raw.test));
        words.add(new SingleWord("ziemniaki", "patate", R.raw.test));
        words.add(new SingleWord("zupa", "zuppa", R.raw.test));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_food,
                                R.color.category_food_white, R.color.category_food_red);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SingleWord word = words.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(FoodActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
