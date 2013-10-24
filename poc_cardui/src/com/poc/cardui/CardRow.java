package com.poc.cardui;

import android.view.View;

public interface CardRow {
	public View getView(View convertView);
	public int getViewType();
	public Card getCard();
}
