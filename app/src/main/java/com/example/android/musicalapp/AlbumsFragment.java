package com.example.android.musicalapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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
 * {@link AlbumsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlbumsFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumsFragment extends Fragment {
    private ArrayList<Song> songs = new ArrayList<>();
    private static final String TAG = AlbumsFragment.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    public AlbumsFragment() {
        // Required empty public constructor
    }

    /*public static AlbumsFragment newInstance(String param1, String param2) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        songs.add(new Song("Know your rights", "The Clash" , "Combat Rock"));
        songs.add(new Song("Car Jamming", "The Clash" , "Combat Rock"));
        songs.add(new Song("Should I stay or should I go", "The Clash" , "Combat Rock"));
        songs.add(new Song("Rock the Casbah", "The Clash" , "Combat Rock"));
        songs.add(new Song("Red Angel Dragnet", "The Clash" , "Combat Rock"));
        songs.add(new Song("Straight to Hell", "The Clash" , "Combat Rock"));
        songs.add(new Song("Overpowered by Funk", "The Clash" , "Combat Rock"));
        songs.add(new Song("Atom Tan", "The Clash" , "Combat Rock"));

        SongAdapter songAdapter = new SongAdapter(getActivity(), songs);
        ListView listView = view.findViewById(R.id.albums_list);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
