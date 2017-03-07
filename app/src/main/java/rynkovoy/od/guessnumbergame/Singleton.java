package rynkovoy.od.guessnumbergame;

import android.content.SharedPreferences;

/**
 * Created by anton on 21.09.2016.
 */
public class Singleton {
    private SharedPreferences sp;
    void setSharedPreferences(SharedPreferences sp){
        this.sp = sp;
    }
    SharedPreferences getSharedPreferences(){
        return this.sp;
    }
    private static Singleton instance;
    Singleton(){

    }
    Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
}
