package com.cosmo.arquitecturamvpbase;

import com.cosmo.arquitecturamvpbase.helper.IValidateInternet;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.presenter.DetailProductPresenter;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.views.activities.IDetailProductView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Superadmin1 on 23/09/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Mock
    IValidateInternet validateInternet;

    @Mock
    IProductRepository productRepository;

    @Mock
    IDetailProductView detailProductView;

    DetailProductPresenter detailProductPresenter;

    @Before
    public void setUp() throws Exception{
        detailProductPresenter = Mockito.spy(new DetailProductPresenter(productRepository));
        detailProductPresenter.inject(detailProductView, validateInternet);

    }
    @Test
    public void methodDeleteProductWithConnectionShouldCallMethodCreateThreadDeleteProduct(){
        String id = "sfjklsf98s0fad0f89";
        when(validateInternet.isConnected()).thenReturn(true);
        detailProductPresenter.deleteProduct(id);
        verify(detailProductPresenter).createThreadDeleteProduct(id);
    }

    @Test
    public void methodDeleteProductWithoutConnectionShouldShowAlertDialgo(){
        String id = "sfjklsf98s0fad0f89";
        when(validateInternet.isConnected()).thenReturn(false);
        detailProductPresenter.deleteProduct(id);
        verify(detailProductView).showAlertDialog(R.string.validate_internet);
        verify(detailProductPresenter, never()).createThreadDeleteProduct(id);
    }

    @Test
    public void methodDeletProductShouldCallMethodDeleteProductInRepositoryTrue(){
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setStatus(true);
        String id = "sfjklsf98s0fad0f89";
        when(productRepository.deleteProduct(id)).thenReturn(deleteResponse);
        detailProductPresenter.deleteProductRepository(id);
        Assert.assertTrue(deleteResponse.isStatus());
        verify(detailProductView).showToast(R.string.correct);
        verify(detailProductView, never()).showAlertDialogError(R.string.error);


    }

    @Test
    public void methodDeletProductShouldCallMethodDeleteProductInRepositoryFalse(){
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setStatus(false);
        String id = "sfjklsf98s0fad0f89";
        when(productRepository.deleteProduct(id)).thenReturn(deleteResponse);
        detailProductPresenter.deleteProductRepository(id);
        Assert.assertFalse(deleteResponse.isStatus());
        verify(detailProductView).showAlertDialogError(R.string.error);
        verify(detailProductView, never()).showToast(R.string.correct);

    }

}
