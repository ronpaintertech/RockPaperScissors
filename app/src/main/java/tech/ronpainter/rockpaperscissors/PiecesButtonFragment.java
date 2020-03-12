package tech.ronpainter.rockpaperscissors;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PiecesButtonFragment extends Fragment {

    private Button btnRock;
    private Button btnPaper;
    private Button btnScissors;

    private OnPieceClickCallback mOnPieceClickCallback = null;

    interface OnPieceClickCallback extends PiecesFragmentCallbacks {

    }

    public PiecesButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pieces_button, container, false);

        btnRock = v.findViewById(R.id.btnRock);
        btnPaper = v.findViewById(R.id.btnPaper);
        btnScissors = v.findViewById(R.id.btnScissors);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnPicked = (Button) v;
                String btnPickedText = btnPicked.getText().toString();

                if (btnPickedText.equals(getString(R.string.rock))) {
                    mOnPieceClickCallback.onChoicePicked(PieceType.ROCK);
                } else if (btnPickedText.equals(getString(R.string.paper))) {
                    mOnPieceClickCallback.onChoicePicked(PieceType.PAPER);
                } else if (btnPickedText.equals(getString(R.string.scissors))){
                    mOnPieceClickCallback.onChoicePicked(PieceType.SCISSORS);
                }

            }
        };

        btnRock.setOnClickListener(listener);
        btnPaper.setOnClickListener(listener);
        btnScissors.setOnClickListener(listener);

        return v;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Activities containing this fragment must implement it's callbacks.
        Activity activity = getActivity();
        if(! (activity instanceof OnPieceClickCallback)) {
            throw new ClassCastException(activity.getClass().getSimpleName() + "  must implement PiecesButtonFragment.OnPieceClickListener interface");
        }

        mOnPieceClickCallback = (OnPieceClickCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnPieceClickCallback = null;
    }

    public void piecesEnabled(boolean enable) {
        btnRock.setEnabled(enable);
        btnPaper.setEnabled(enable);
        btnScissors.setEnabled(enable);
    }
}
