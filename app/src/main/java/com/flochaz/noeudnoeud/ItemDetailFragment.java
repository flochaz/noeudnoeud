package com.flochaz.noeudnoeud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.flochaz.noeudnoeud.database.DatabaseHandler;
import com.flochaz.noeudnoeud.database.Item;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The item content this fragment is presenting.
     */
    private Item mItem;

    /**
     * The UI elements showing the details of the Item
     */
    private TextView textName;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Should use the contentprovider here ideally
            mItem = DatabaseHandler.getInstance(getActivity()).getItem(getArguments().getLong(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        if (mItem != null) {
            textName = ((TextView) rootView.findViewById(R.id.textName));
            textName.setText(mItem.name);

        }

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        updatePersonFromUI();
    }

    private void updatePersonFromUI() {
        if (mItem != null) {
            mItem.name = textName.getText().toString();

            DatabaseHandler.getInstance(getActivity()).putItem(mItem);
        }
    }
}
