package com.example.androiddatabinding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.androiddatabinding.databinding.ActivityMainBinding;
import com.example.androiddatabinding.models.Product;
import com.example.androiddatabinding.util.Products;

import static android.os.Build.VERSION_CODES.P;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    //data binding
    ActivityMainBinding mainBinding;

    //vars
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Products product = new Products();
        mProduct = product.PRODUCTS[0];

        //setting the product details
        mainBinding.setProduct(mProduct);

        //setting the quantity
        mainBinding.setQuantity(1);

        //QuantityDialog
        mainBinding.setIMainActivity((IMainActivity)this);
        mainBinding.setImageUrl("https://www.lightingcompany.co.uk/images/upton-red-glaze-table-lamp-with-white-faux-linen-shade-p23947-24711_image.jpg");
    }

    @Override
    public void inflateQuantityDialog() {
        ChooseQuantityDialog dialog = new ChooseQuantityDialog();
        dialog.show(getSupportFragmentManager(), getString(R.string.dialog_choose_quantity));
    }

    /**
     * Setting the quantity using callback from fragment to MainActivity
     * @param quantity
     */
    @Override
    public void setQuantity(int quantity) {
        mainBinding.setQuantity(quantity);
    }
}
