package com.mwysocka.italiano;

/**
 * Created by Marta on 16.12.2017.
 */

public class SingleWord {
    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Italian translation for the word */
    private String mItalianTranslation;

    /** Audio resource for the word */
    private int mAudioResourceId;

    /** Two parameters constructor
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param italianTranslation is the word in the Italian language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public SingleWord(String defaultTranslation, String italianTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mItalianTranslation = italianTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Italian translation of the word.
     */
    public String getItalianTranslation() {
        return mItalianTranslation;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId(){
        return mAudioResourceId;
    }

}
