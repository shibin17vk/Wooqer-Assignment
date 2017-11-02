package com.webapp.wooqerassignment.widget;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import com.webapp.wooqerassignment.constants.AppConstants;

import java.util.concurrent.ConcurrentHashMap;

/**
 * TypefaceCache
 *
 * @author shibin
 * @version 1.0
 * @since 4 Jan 2017
 */

public class TypefaceCache {

    private static final ConcurrentHashMap<String, Typeface> CACHE = new ConcurrentHashMap<String, Typeface>();

    public static Typeface get(final AssetManager manager, final String name) {

        synchronized (CACHE) {

            if (!CACHE.containsKey(name)) {
                try {
                    final Typeface t = Typeface.createFromAsset(manager, name);
                    CACHE.put(name, t);
                } catch (Exception ex) {
                    Log.e(AppConstants.APP_TAG, "Custom font <" + name + " > can not be loaded from the assets");
                    ex.printStackTrace();
                }
            }
            return CACHE.get(name);
        }
    }
}
