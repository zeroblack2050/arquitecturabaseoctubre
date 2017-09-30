package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 23/09/2017.
 */

public interface IProductRepository {

    ArrayList<Product> getProductList();
    Product createProduct(Product product);

    DeleteResponse deleteProduct(String id);
}
