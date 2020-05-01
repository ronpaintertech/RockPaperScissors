package tech.ronpainter.rockpaperscissors;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Future update to use phone's camera to detect hand making Rock, Paper, Scissors gesture
 */
public class PiecesCameraAIFragment extends Fragment {

    private OnPieceDetectedCallback mOnPieceClickCallback = null;

    interface OnPieceDetectedCallback extends PiecesFragmentCallbacks {

    }

    public PiecesCameraAIFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pieces_camera_ai, container, false);

// reuse this logic after piece detected
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Button btnPicked = (Button) v;
//                String btnPickedText = btnPicked.getText().toString();
//
//                if (btnPickedText.equals(getString(R.string.rock))) {
//                    mOnPieceClickCallback.onChoicePicked(PieceType.ROCK);
//                } else if (btnPickedText.equals(getString(R.string.paper))) {
//                    mOnPieceClickCallback.onChoicePicked(PieceType.PAPER);
//                } else if (btnPickedText.equals(getString(R.string.scissors))){
//                    mOnPieceClickCallback.onChoicePicked(PieceType.SCISSORS);
//                }
//
//            }
//        };

        return v;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Activities containing this fragment must implement it's callbacks.
        Activity activity = getActivity();
        if(! (activity instanceof OnPieceDetectedCallback)) {
            throw new ClassCastException(activity.getClass().getSimpleName() + "  must implement PiecesButtonFragment.OnPieceClickListener interface");
        }

        mOnPieceClickCallback = (OnPieceDetectedCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnPieceClickCallback = null;
    }

    public void piecesEnabled(boolean enable) {
        //TODO: enable / disable camera?  just taking picture during middle of "shoot"?
    }
}
