package com.example.liuk.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuk.Activity.MainActivity;
import com.example.liuk.myapplication.R;

/**
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MemoAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoAddFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MemoAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemoEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemoAddFragment newInstance(String param1, String param2) {
        MemoAddFragment fragment = new MemoAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private int memoId = -1;
    TextView textView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        MainActivity activity = (MainActivity)getActivity();
        textView = (TextView)activity.findViewById(R.id.menu_add);


        //memoId = getI

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo_edit, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(textView != null){
            textView.setTextColor(Color.parseColor("#FF6600"));
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(textView != null){
            textView.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }




}