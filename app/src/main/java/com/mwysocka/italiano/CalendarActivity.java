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

public class CalendarActivity extends AppCompatActivity {

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

        words.add(new SingleWord("poniedziałek", "lunedì", R.raw.test));
        words.add(new SingleWord("wtorek", "martedì", R.raw.test));
        words.add(new SingleWord("środa", "mercoledì", R.raw.test));
        words.add(new SingleWord("czwartek", "giovedì", R.raw.test));
        words.add(new SingleWord("piątek", "venerdì", R.raw.test));
        words.add(new SingleWord("sobota", "sabato", R.raw.test));
        words.add(new SingleWord("niedziela", "domenica", R.raw.test));
        words.add(new SingleWord("styczeń", "gennaio", R.raw.test));
        words.add(new SingleWord("luty", "febbraio", R.raw.test));
        words.add(new SingleWord("marzec", "marzo", R.raw.test));
        words.add(new SingleWord("kwiecień", "aprile", R.raw.test));
        words.add(new SingleWord("maj", "maggio", R.raw.test));
        words.add(new SingleWord("czerwiec", "giugno", R.raw.test));
        words.add(new SingleWord("lipiec", "luglio", R.raw.test));
        words.add(new SingleWord("sierpień", "agosto", R.raw.test));
        words.add(new SingleWord("wrzesień", "settembre", R.raw.test));
        words.add(new SingleWord("październik", "ottobre", R.raw.test));
        words.add(new SingleWord("listopad", "novembre", R.raw.test));
        words.add(new SingleWord("grudzień", "dicembre", R.raw.test));
        words.add(new SingleWord("rano", "mattina", R.raw.test));
        words.add(new SingleWord("dzień", "giorno", R.raw.test));
        words.add(new SingleWord("wieczór", "sera", R.raw.test));
        words.add(new SingleWord("noc", "notte", R.raw.test));
        words.add(new SingleWord("godzina", "ora", R.raw.test));
        words.add(new SingleWord("doba", "giorno", R.raw.test));
        words.add(new SingleWord("tydzień", "settimana", R.raw.test));
        words.add(new SingleWord("miesiąc", "mese", R.raw.test));
        words.add(new SingleWord("rok", "anno", R.raw.test));
        words.add(new SingleWord("dzisiaj", "oggi", R.raw.test));
        words.add(new SingleWord("jutro", "domani", R.raw.test));
        words.add(new SingleWord("wczoraj", "ieri", R.raw.test));
        words.add(new SingleWord("wiosna", "primavera", R.raw.test));
        words.add(new SingleWord("lato", "estate", R.raw.test));
        words.add(new SingleWord("jesień", "autunno", R.raw.test));
        words.add(new SingleWord("zima", "inverno", R.raw.test));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_calendar,
                                R.color.category_calendar_white, R.color.category_calendar_red);
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
                    mMediaPlayer = MediaPlayer.create(CalendarActivity.this, word.getAudioResourceId());
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
