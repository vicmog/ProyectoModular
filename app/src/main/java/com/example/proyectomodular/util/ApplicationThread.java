package com.example.proyectomodular.util;

import android.app.Application;
import android.provider.ContactsContract;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationThread extends Application {

    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService threadExecutorPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
}
