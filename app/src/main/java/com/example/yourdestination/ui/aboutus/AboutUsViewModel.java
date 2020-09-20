package com.example.yourdestination.ui.aboutus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutUsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is high efficiency android application. " +
                "This is used to find the details of the tourist place which the user wants to visit." +
                "Your Destination architecture of mobile tourist guide system for" +
                " android mobile phones that is able to provide tourism information to the mobile users conveniently." +
                " It can realize to query information for various places and gives multi output and hence " +
                "it has more practical significance.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}