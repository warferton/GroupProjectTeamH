package com.teamh.teamhfinalproject.api.dao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class DAOModule {

    @Provides
     ProductsDAO provideProductsDAO(){
        return new ProductDataAccess();
    }
}

