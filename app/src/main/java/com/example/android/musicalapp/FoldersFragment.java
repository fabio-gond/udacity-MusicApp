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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoldersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FoldersFragment extends Fragment {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Song> songsCopy = new ArrayList<>();
    private static final String TAG = FoldersFragment.class.getSimpleName();
    private SongAdapter songAdapter;
    private ListView listView;

    private OnFragmentInteractionListener mListener;

    public FoldersFragment() {
        // Required empty public constructor
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folders, container, false);

        songs.add(new Song("Back in black", "Back in black" , "ACDC"));
        songs.add(new Song("Back to black", "Back to black" , "Amy Winehouse"));
        songs.add(new Song("Rock the Casbah", "Combat Rock" , "The Clash"));
        songs.add(new Song("Paint it black", "" , "Rolling Stones"));
        songs.add(new Song("Hey Jude", "The Beatles" , "The Beatles"));

        // Copy for the filter
        songsCopy.addAll(songs);

        songAdapter = new SongAdapter(getActivity(), songs);
        listView = view.findViewById(R.id.folders_list);
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
        void onFragmentInteraction(Uri uri);
    }
}
