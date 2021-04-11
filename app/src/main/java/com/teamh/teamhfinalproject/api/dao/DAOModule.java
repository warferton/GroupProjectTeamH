package com.teamh.teamhfinalproject.api.dao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;


@Module
@InstallIn(ActivityComponent.class)
public class DAOModule {

    @Provides
    ProductsDAO provideProductsDAO(){
        return new ProductDataAccess();
    }
}

