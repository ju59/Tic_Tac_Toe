package com.julienb.gamecenter.Morpion;


import android.util.Log;

/**
 * Created by Administrateur on 08/09/2017.
 */

public class Engine {


    String status = "defaut";
    private static final String TAG = "Morpion Engine";
    private int tab[][]=  { {0,0,0},{0,0,0},{0,0,0} };
    private int count = 0;

    public boolean Engine(int x,int y, int joueur){

        if (tab[x][y]==0) {
            tab[x][y] = joueur;



            if (tab[0][0] == tab[0][1] && tab[0][1] == tab[0][2] && tab[0][2] != 0){
                Log.d(TAG, "IF "+tab[0][0]+" == "+tab[0][1]+" == "+tab[0][2]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            if (tab[1][0] == tab[1][1] && tab[1][1] == tab[1][2] && tab[1][2] != 0){
                Log.d(TAG, "IF "+tab[1][0]+" == "+tab[1][1]+" == "+tab[1][2]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            if (tab[2][0] == tab[2][1] && tab[2][1] == tab[2][2] && tab[2][2] != 0){
                Log.d(TAG, "IF "+tab[2][0]+" == "+tab[2][1]+" == "+tab[2][2]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            if (tab[0][0] == tab[1][0] && tab[1][0] == tab[2][0] && tab[2][0] != 0){
                Log.d(TAG, "IF "+tab[0][0]+" == "+tab[1][0]+" == "+tab[2][0]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            if (tab[0][1] == tab[1][1] && tab[1][1] == tab[2][1] && tab[2][1] != 0){
                Log.d(TAG, "IF "+tab[0][1]+" == "+tab[1][1]+" == "+tab[2][1]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            if (tab[0][2] == tab[1][2] && tab[1][2] == tab[2][2] && tab[2][2] != 0){
                Log.d(TAG, "IF "+tab[0][2]+" == "+tab[1][2]+" == "+tab[2][2]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            if (tab[0][0] == tab[1][1] && tab[1][1] == tab[2][2] && tab[2][2] != 0){
                Log.d(TAG, "IF "+tab[0][0]+" == "+tab[1][1]+" == "+tab[2][2]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            if (tab[0][2] == tab[1][1] && tab[1][1] == tab[2][0] && tab[2][0] != 0){
                Log.d(TAG, "IF "+tab[0][2]+" == "+tab[1][1]+" == "+tab[2][0]);
                status = "gagne";
                Log.d(TAG,"STAUT = "+status);}

            Log.d(TAG, ""+tab[0][0]+""+tab[0][1]+""+tab[0][2]);
            Log.d(TAG, ""+tab[1][0]+""+tab[1][1]+""+tab[1][2]);
            Log.d(TAG, ""+tab[2][0]+""+tab[2][1]+""+tab[2][2]);
            return  true;
        }
        else {
            if (tab[0][0]!=0&&tab[0][1]!=0&&tab[0][2]!=0&&tab[1][0]!=0&&tab[1][1]!=0&&tab[1][2]!=0&&tab[2][0]!=0&&tab[2][1]!=0&&tab[2][2]!=0){
                Log.d(TAG,"egalité");
                status = "egalite";}
            return false;

        }

    }
    public String getStatus() {
        return status;
    }
}
