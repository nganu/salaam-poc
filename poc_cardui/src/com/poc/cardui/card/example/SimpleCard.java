package com.poc.cardui.card.example;

import com.poc.cardui.RowType;
import com.poc.cardui.card.model.Card;

public class SimpleCard implements Card {

	private String title;
	private String description;

	public SimpleCard(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getCardType() {
		return RowType.CARD_SIMPLE.ordinal();
	}

}
