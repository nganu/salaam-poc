package com.poc.cardui.card.example;

import com.poc.cardui.card.model.Card;

import android.view.View;

public interface CardRow {
	public View getView(View convertView);
	public int getViewType();
	public Card getCard();
}
