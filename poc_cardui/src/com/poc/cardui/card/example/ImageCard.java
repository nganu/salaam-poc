package com.poc.cardui.card.example;

import com.poc.cardui.RowType;
import com.poc.cardui.card.model.Card;

public class ImageCard implements Card {
	private String title;

	public ImageCard(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int getCardType() {
		return RowType.CARD_IMAGE.ordinal();
	}

}
