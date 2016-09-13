package com.triode.androidtestapp.core.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.triode.androidtestapp.core.mvp.IPresenter;
import com.triode.androidtestapp.core.mvp.IViewState;
import com.triode.androidtestapp.core.mvp.ViewState;
import com.triode.androidtestapp.core.mvp.ViewStateView;
import com.triode.androidtestapp.core.utils.CoreLogger;

/**
 * Created by Triode on 5/6/2016.
 */
public abstract class ViewStateFragment<V extends ViewStateView, P extends IPresenter<V>,
        VS extends ViewState>
        extends MVPFragment<V, P> implements IViewState<VS> {


    /**
     * The tag against the view state will be saved
     */
    public static final String VIEW_STATE = "internalSavedViewState8954201239547";

    /**
     * The tag against the view state will be saved
     */
    public static final String FRAGMENT_STATE = "internalSavedFragmentState8954201239547";
    protected boolean mRetainState = true;

    /**
     * Construct the new instance
     * <p/>
     * return the instance of {@code ViewStateFragment}
     */
    public ViewStateFragment() {
        initArguments();
    }

    /**
     * function initialize the arguments if it is null
     */
    protected void initArguments(){
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
    }



    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p/>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if(mRetainState)
            setRetainInstance(mRetainState);
        return mView.getView();
    }

    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        saveState();
        super.onDestroyView();
    }


    /**
     * function called on when the view added kto the window
     * and the activity is created for the same
     */
    @Override
    protected void onViewComplete() {
        if(!restoreFromArguments())
            onBindView();
        mView.onViewComplete();
    }

    /**
     * function check for the saved view state in the argument and
     * restore the view state
     * @return true if restored otherwise false
     */
    private boolean restoreFromArguments(){
        final Bundle b = getArguments();
        if (b != null) {
            VS viewState = getSavedViewState();
            if (viewState != null) {
                CoreLogger.log(getClass().getName(), "Restoring View State");
                restoreViewState(viewState);
                return true;
            }
        }
        CoreLogger.log(getClass().getName(), "No View State Found");
        return false;
    }

    /**
     * function which will return the saved view state
     *
     * @return the view state of the enclosed view s
     */
    protected VS getSavedViewState(){
        return (VS)getArguments().getParcelable(VIEW_STATE);
    }





    /**
     * function save the view state to the arguments
     *
     */
    private void saveState(){
        VS viewState = saveViewState();
        if(viewState != null){
            getArguments().putParcelable(VIEW_STATE, viewState);
        }
    }

    /**
     * function save the sate of the view and return it
     *
     * @return the {@code ViewState} of the enclosing view of the
     * fragment
     */
    @Override
    public VS saveViewState() {
        return (VS)mView.saveViewState();
    }

    /**
     * function restore the state of the view
     *
     * @param viewState the view state saved
     */
    @Override
    public void restoreViewState(VS viewState) {
        mView.restoreViewState(viewState);
    }
}
