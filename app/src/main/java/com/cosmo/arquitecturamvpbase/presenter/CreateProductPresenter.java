package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.IBaseView;
import com.cosmo.arquitecturamvpbase.views.activities.ICreateProductView;

import retrofit.RetrofitError;

/**
 * Created by jersonsuaza on 9/19/17.
 */

public class CreateProductPresenter extends BasePresenter<ICreateProductView> {

    private ProductRepository productRepository;

    public CreateProductPresenter(){
        productRepository = new ProductRepository();
    }

    public void createNewProduct(Product product) {
        if (getValidateInternet().isConnected()){
            createThreadCreateProduct(product);
        }else{
            //TODO: mostrar alert
        }
    }

    private void createThreadCreateProduct(final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createNewProductService(product);

            }
        });
        thread.start();
    }

    private void createNewProductService(Product product){
        try{
            Product productCreated = productRepository.createProduct(product);
            getView().showResultCreateNewProduct(true);
        }catch (RetrofitError retrofitError){
            getView().showResultCreateNewProduct(false);
        }
    }

}
