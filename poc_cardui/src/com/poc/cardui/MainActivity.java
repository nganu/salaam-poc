package com.poc.cardui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.haarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.haarman.listviewanimations.itemmanipulation.SwipeDismissAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.poc.cardui.card.example.CardRow;
import com.poc.cardui.card.example.ImageCard;
import com.poc.cardui.card.example.SimpleCard;
import com.poc.cardui.card.example.SimpleCardRow;
import com.poc.cardui.card.model.Card;
import com.poc.cardui.card.view.ImageCardRow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnDismissCallback {

	CardAdapter adapter;
	SwingBottomInAnimationAdapter mAdapter;
	Random rnd = new Random(System.currentTimeMillis());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < 3; i++) {
			cards.add(createRandomCard());
		}
		ListView list = (ListView) findViewById(R.id.card_list);

		View footer = LayoutInflater.from(this).inflate(
				R.layout.activity_add_more, null);
		Button btnLoad = (Button) footer.findViewById(R.id.btn_load);
		btnLoad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < 3; i++) {
					adapter.add(createRandomCard());
				}
				mAdapter.notifyDataSetChanged();

			}
		});
		list.addFooterView(footer);
		adapter = new CardAdapter(this, cards);
		mAdapter = new SwingBottomInAnimationAdapter(
				new SwipeDismissAdapter(adapter, this));
		mAdapter.setAbsListView(list);
		list.setAdapter(mAdapter);
	}

	private Card createRandomCard() {
		int type = rnd.nextInt(2);
		switch (type) {
		case 0:
			return new SimpleCard(
					"Title " + rnd.nextInt(50),
					"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, "
							+ "sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.");

		default:
			return new ImageCard("Title " + rnd.nextInt(50));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private class CardAdapter extends BaseAdapter {
		List<CardRow> rows;
		Context context;

		public CardAdapter(Context context, List<Card> cards) {
			rows = new ArrayList<CardRow>();
			this.context = context;
			for (Card card : cards) {
				add(card);
			}
		}

		@Override
		public int getCount() {
			return rows.size();
		}

		@Override
		public Object getItem(int position) {
			CardRow row = rows.get(position);
			if (row == null)
				return null;
			else
				return row.getCard();
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			CardRow row = rows.get(position);
			if (row == null)
				return null;
			else {
				return row.getView(convertView);
			}
		}

		@Override
		public int getViewTypeCount() {
			return RowType.values().length;
		}

		@Override
		public int getItemViewType(int position) {
			CardRow row = rows.get(position);
			if (row == null)
				return -1;
			else
				return row.getViewType();
		}

		public void remove(int position) {
			rows.remove(position);
		}

		public void add(Card card) {
			CardRow row;
			RowType type = RowType.values()[card.getCardType()];
			switch (type) {
			case CARD_SIMPLE:
				row = new SimpleCardRow((SimpleCard) card,
						LayoutInflater.from(context),
						R.layout.activity_card_simple);
				break;

			default:
				row = new ImageCardRow((ImageCard) card,
						LayoutInflater.from(context),
						R.layout.activity_card_image);
				break;
			}
			rows.add(row);
		}

	}

	@Override
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			adapter.remove(position);
			mAdapter.notifyDataSetChanged();
		}
		Toast.makeText(this, "Card removed", Toast.LENGTH_SHORT).show();
	}

}
