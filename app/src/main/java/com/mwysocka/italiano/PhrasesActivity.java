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

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new SingleWord("Cześć!", "Ciao!", R.raw.test));
        words.add(new SingleWord("Dzień dobry", "Buon giorno", R.raw.test));
        words.add(new SingleWord("Dobry wieczór", "Buona sera", R.raw.test));
        words.add(new SingleWord("Do widzenia", "Arrivederci ", R.raw.test));
        words.add(new SingleWord("Dobranoc", "Buona notte", R.raw.test));
        words.add(new SingleWord("Ja się masz?", "Come stai?", R.raw.test));
        words.add(new SingleWord("Dziękuję, dobrze", "Bene, grazie", R.raw.test));
        words.add(new SingleWord("Nie najlepiej", "Non molto bene", R.raw.test));
        words.add(new SingleWord("Przepraszam", "Scusa", R.raw.test));
        words.add(new SingleWord("Proszę (podając coś)", "Prego", R.raw.test));
        words.add(new SingleWord("Dziękuję", "Grazie", R.raw.test));
        words.add(new SingleWord("Nie ma za co", "Di niente", R.raw.test));
        words.add(new SingleWord("Nie rozumiem", "Non capisco", R.raw.test));
        words.add(new SingleWord("Jest Polakiem/Polką", "Sono polacco/polacca", R.raw.test));
        words.add(new SingleWord("Gdzie mieszkasz?", "Dove abiti?", R.raw.test));
        words.add(new SingleWord("Mieszkam w ...", "Vivo a ...", R.raw.test));
        words.add(new SingleWord("Jak masz na imię?", "Come si chiama?", R.raw.test));
        words.add(new SingleWord("Mam na imię ...", "Mi chiamo ...", R.raw.test));
        words.add(new SingleWord("To mój adres", "Ecco il mio indirizzo", R.raw.test));
        words.add(new SingleWord("Która jest godzina?", "Chce ore sono?", R.raw.test));
        words.add(new SingleWord("Szczęśliwej podróży", "Buon viaggio", R.raw.test));
        words.add(new SingleWord("Jak minęła podróż?", "Come è stato il tuo viaggio?", R.raw.test));
        words.add(new SingleWord("Ile to kosztuje?", "Quanto costa?", R.raw.test));
        words.add(new SingleWord("Ile płacę?", "Quanto pago?", R.raw.test));
        words.add(new SingleWord("Gratulacje!", "Complimenti!", R.raw.test));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases,
                                R.color.category_phrases_white, R.color.category_phrases_red);
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
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
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
