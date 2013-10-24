package com.poc.cardui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ImageCardRow implements CardRow {
	private ImageCard card;
	private LayoutInflater inflater;
	private int layoutId;

	public ImageCardRow(ImageCard card, LayoutInflater inflater, int layoutId) {
		super();
		this.card = card;
		this.inflater = inflater;
		this.layoutId = layoutId;
	}

	@Override
	public View getView(View convertView) {
		ViewHolder holder;
		View view;
		if (convertView == null) {
			view = inflater.inflate(layoutId, null);
			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.title);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.title.setText(card.getTitle());
		return view;
	}

	@Override
	public int getViewType() {
		return RowType.CARD_IMAGE.ordinal();
	}

	private class ViewHolder {
		private TextView title;
	}

	@Override
	public Card getCard() {
		return card;
	}

}
