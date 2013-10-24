package com.poc.cardui.card.example;

import com.poc.cardui.R;
import com.poc.cardui.RowType;
import com.poc.cardui.R.id;
import com.poc.cardui.card.model.Card;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class SimpleCardRow implements CardRow {
	private SimpleCard card;
	private LayoutInflater inflater;
	private int layoutId;

	public SimpleCardRow(SimpleCard card, LayoutInflater inflater, int layoutId) {
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
			holder.desc = (TextView) view.findViewById(R.id.description);
			view.setTag(holder);
		}
		else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.title.setText(card.getTitle());
		holder.desc.setText(card.getDescription());
		return view;
	}

	@Override
	public int getViewType() {
		return RowType.CARD_SIMPLE.ordinal();
	}

	private class ViewHolder {
		private TextView title;
		private TextView desc;
	}

	@Override
	public Card getCard() {
		return card;
	}

}
