package com.github.zigen.slidingpanelview;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.zigen.slidingpanel.SlidingUpPanelLayout;
import com.github.zigen.slidingpanel.SlidingUpPanelLayout.PanelSlideListener;
import java.util.Arrays;
import java.util.List;
import com.github.zigen.slidingpanel.SlidingUpPanelLayout.PanelState;

public class MainActivity extends AppCompatActivity {
	
	private static final String TAG ="MainActivity";
	
	SlidingUpPanelLayout mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
            }
        });

        List<String> your_array_list = Arrays.asList(
                "This",
                "Is",
                "An",
                "Example",
                "ListView",
                "That",
                "You",
                "Can",
                "Scroll",
                ".",
                "It",
                "Shows",
                "How",
                "Any",
                "Scrollable",
                "View",
                "Can",
                "Be",
                "Included",
                "As",
                "A",
                "Child",
                "Of",
                "SlidingUpPanelLayout"
        );

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);
		
		mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
		mLayout.addPanelSlideListener(new PanelSlideListener() {
			@Override
			public void onPanelSlide(View arg0, float slideOffset) {
				Log.i(TAG, "onPanelSlide, offset " + slideOffset);
			}

			@Override
			public void onPanelStateChanged(View arg0, PanelState arg1, PanelState newState) {
				Log.i(TAG, "onPanelStateChanged " + newState);
			}

		});
	}

	@Override
	public void onBackPressed() {
		if (mLayout != null
				&& (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED)) {
			mLayout.setPanelState(PanelState.COLLAPSED);
		} else {
			super.onBackPressed();
		}
	}
}