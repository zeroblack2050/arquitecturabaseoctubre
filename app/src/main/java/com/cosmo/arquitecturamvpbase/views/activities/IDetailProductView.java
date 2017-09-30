package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by Superadmin1 on 23/09/2017.
 */

public interface IDetailProductView extends IBaseView{

    void showAlertDialog(int validate_internet);

    void showToast(int correct);

    void showAlertDialogError(int incorrect);
}
