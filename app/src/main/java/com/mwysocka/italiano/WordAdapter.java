package com.mwysocka.italiano;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marta on 16.12.2017.
 */

public class WordAdapter extends ArrayAdapter<SingleWord> {

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;
    private int mWhiteColorResourceId;
    private int mRedColorResourceId;

    public WordAdapter(@NonNull Context context, ArrayList<SingleWord> words,
                       int colorResourceId, int whiteColorResourceId, int redColorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
        mWhiteColorResourceId = whiteColorResourceId;
        mRedColorResourceId = redColorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link Word} object located at this position in the list
        SingleWord currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID italian_text_view
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.italian_text_view);
        // Get the italian translation from the current Word object and
        // set this text on the name TextView
        nameTextView.setText(currentWord.getItalianTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the current Word object and
        // set this text on the number TextView
        numberTextView.setText(currentWord.getDefaultTranslation());

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Find the TextView in the list_item.xml layout with the ID white_line
        TextView whiteLine = (TextView) listItemView.findViewById(R.id.white_line);
        // Find the color that the resource ID maps to
        int whiteColor = ContextCompat.getColor(getContext(), mWhiteColorResourceId);
        // Set the background color of the white line View
        whiteLine.setBackgroundColor(whiteColor);

        // Find the TextView in the list_item.xml layout with the ID red_line
        TextView redLine = (TextView) listItemView.findViewById(R.id.red_line);
        // Find the color that the resource ID maps to
        int redColor = ContextCompat.getColor(getContext(), mRedColorResourceId);
        // Set the background color of the white line View
        whiteLine.setBackgroundColor(whiteColor);

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
