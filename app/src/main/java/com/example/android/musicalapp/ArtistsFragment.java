package com.example.android.musicalapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ArtistsFragment extends Fragment {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Song> songsCopy = new ArrayList<>();
    private static final String TAG = ArtistsFragment.class.getSimpleName();
    private SongAdapter songAdapter;
    private ListView listView;

    private ArtistsFragment.OnFragmentInteractionListener mListener;

    public ArtistsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    public void filterSongs(String text){
        songAdapter.clear();
        for(int i=0 ; i<songsCopy.size() ; i++){
            Song song = songsCopy.get(i);
            if (song.getTitle().toLowerCase().contains(text.toLowerCase()))
                songAdapter.add(song);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artists, container, false);

        songs.add(new Song("Space Oddity", "Space Oddity" , "David Bowie"));
        songs.add(new Song("Heroes", "Heroes" , "David Bowie"));
        songs.add(new Song("Rebel Rebel", "Diamond Dogs" , "David Bowie"));
        songs.add(new Song("Golden Years", "Station to Station" , "David Bowie"));
        songs.add(new Song("Starman", "The rise and fall of Ziggy" , "David Bowie"));
        songs.add(new Song("Life on Mars?", "Hunky Dory" , "David Bowie"));
        songs.add(new Song("Under Pressure", "Hot Space" , "David Bowie"));

        // Copy for the filter
        songsCopy.addAll(songs);

        songAdapter = new SongAdapter(getActivity(), songs);
        listView = view.findViewById(R.id.artists_list);
        listView.setAdapter(songAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Create a new intent
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("title", songs.get(i).getTitle());
                intent.putExtra("artist", songs.get(i).getArtist());
                intent.putExtra("album", songs.get(i).getAlbum());

                // Start the new activity
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
