package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.UpdateInterfaceProductView;

import retrofit.RetrofitError;

/**
 * Created by jasmany on 29/09/2017.
 */

public class UpdateProductPresenter extends BasePresenter<UpdateInterfaceProductView> {

    private ProductRepository productRepository;

    public UpdateProductPresenter(){
        productRepository = new ProductRepository();
    }

    public void UpdateProduct(String id, Product product) {
        if (getValidateInternet().isConnected()){
            createThreadCreateProduct(id, product);
        }else{
            getView().showAlertInternetI(R.string.error, R.string.validate_internet);
        }
    }

    private void createThreadCreateProduct(final String id, final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UpdateroductService(id, product);
                } catch (RepositoryError repositoryError) {
                    repositoryError.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void UpdateroductService(String id, Product product) throws RepositoryError {
        try{
            productRepository.updateProduct(id, product);
            getView().showResultUpdateNewProductI(true);
        }catch (RetrofitError retrofitError){
            getView().showResultUpdateNewProductI(false);
        }
    }
}
