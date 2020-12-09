package com.evansamuel.pbptubes.ui.fitur.menu.UniTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddFoodFragmentTest {
     @Mock
    AddFoodFragment fragment;

    @Test
    public void shouldShowErrorMessageWhenNameIsEmpty() throws Exception {
        when(fragment.getName()).thenReturn("");
        System.out.println("menu name: " + fragment.getName());
        when(fragment.getPrice()).thenReturn("");
        System.out.println("Price: " + fragment.getPrice());
        when(fragment.getImage()).thenReturn("");
        System.out.println("Image URL: " + fragment.getImage());
        fragment.onAddClick();
        verify(fragment).showNameError("Name must be filled");


    }

    @Test
    public void shouldShowErrorMessageWhenPriceIsEmpty() throws Exception {
        when(fragment.getName()).thenReturn("Nasi Padang");
        System.out.println("menu name: " + fragment.getName());
        when(fragment.getPrice()).thenReturn("");
        System.out.println("price: " + fragment.getPrice());
        fragment.onAddClick();
        verify(fragment).showPriceError("Price must be filled");


    }

    @Test
    public void shouldShowErrorMessageWhenImageIsEmpty() throws Exception {
        when(fragment.getName()).thenReturn("Nasi Padang");
        System.out.println("menu name: " + fragment.getName());
        when(fragment.getPrice()).thenReturn("15000");
        System.out.println("price: " + fragment.getPrice());
        when(fragment.getImage()).thenReturn("");
        System.out.println("Image URL: " + fragment.getImage());
        fragment.onAddClick();
        verify(fragment).showImageError("Image URL must be filled");


    }

    @Test
    public void shouldShowSuccessMessageWhenAllFieldIsFilled() throws Exception {
        when(fragment.getName()).thenReturn("Nasi Padang");
        System.out.println("Menu name : " + fragment.getName());
        when(fragment.getPrice()).thenReturn("15000");
        System.out.println("Price : " + fragment.getPrice());
        when(fragment.getPrice()).thenReturn("https://awsimages.detik.net.id/community/media/visual/2019/04/24/de2758a6-ea38-4ae9-8c4b-f2b395a81a22_43.png?w=700&q=90");
        System.out.println("Image URL : " + fragment.getImage());
        when(fragment.getValid(fragment, fragment.getName(), fragment.getPrice(), fragment.getImage())).thenReturn(true);
        System.out.println("Hasil : " + fragment.getValid(fragment, fragment.getName(), fragment.getPrice(), fragment.getImage()));
        fragment.onAddClick();
    }



}
