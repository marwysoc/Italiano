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

public class FamilyActivity extends AppCompatActivity {

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

        words.add(new SingleWord("rodzice", "i genitori", R.raw.test));
        words.add(new SingleWord("matka", "la madre", R.raw.test));
        words.add(new SingleWord("ojciec", "il padre", R.raw.test));
        words.add(new SingleWord("babcia", "la nonna", R.raw.test));
        words.add(new SingleWord("dziadek", "il nonno", R.raw.test));
        words.add(new SingleWord("córka", "la figlia ", R.raw.test));
        words.add(new SingleWord("syn", "il figlio", R.raw.test));
        words.add(new SingleWord("ciotka", "la zia", R.raw.test));
        words.add(new SingleWord("wujek", "lo zio", R.raw.test));
        words.add(new SingleWord("brat", "il fratello", R.raw.test));
        words.add(new SingleWord("siostra", "la sorella", R.raw.test));
        words.add(new SingleWord("teść", "il suocero", R.raw.test));
        words.add(new SingleWord("teściowa", "la suocera", R.raw.test));
        words.add(new SingleWord("zięć", "il genero", R.raw.test));
        words.add(new SingleWord("synowa", "la nuora", R.raw.test));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family,
                                R.color.category_family_white, R.color.category_family_red);
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
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
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
