package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by jasmany on 29/09/2017.
 */

public interface UpdateInterfaceProductView extends IBaseView{

    void showProductUpdateI(ArrayList<Product> productArrayList);

    void showResultUpdateNewProductI(boolean isCreated);

    void showAlertInternetI(int title, int message);
}
