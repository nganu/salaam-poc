package com.poc.cardui;

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
