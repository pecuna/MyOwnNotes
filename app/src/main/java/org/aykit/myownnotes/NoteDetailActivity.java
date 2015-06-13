package org.aykit.myownnotes;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.MenuItem;
import android.widget.TextView;


/**
 * An activity representing a single Note detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link NoteListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link NoteDetailFragment}.
 */
public class NoteDetailActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null)
        {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(NoteDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(NoteDetailFragment.ARG_ITEM_ID));
            NoteDetailFragment fragment = new NoteDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.note_detail_container, fragment)
                    .commit();
        }

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if(action != null && type != null)
        {
            if ( action.equals(Intent.ACTION_SEND) )
            {
                if(type.equals("text/plain") )
                {
                    //a valid intent was received. proccess it.
                    proccessIntent(intent);
                }
            }
        }

        
    }

    private void proccessIntent(Intent intent)
    {
        TextView textView = (TextView) findViewById(R.id.note_text);
        textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, NoteListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
