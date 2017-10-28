package com.company;

public class ChooseOptions {
    static String y;
    static String y2;

    public static String Choose(int x){

            if(x==1){
                y="/posts";

            }
            else if(x==2){
                y="/comments";
            }
            else{
                y="/albums";
            }



return y;

    }
    public static String Chooseoption2(int x){
        if (x==1){
            y2="id";
        }
        else if(x==2){
            y2="title";
        }
        else if (x==3){
            y2="body";
        }
        else {
            System.out.println("Please Enter number between 1-3");
        }
        //fix nir review 2
return y2;
    }
    public static String Chooseoption3(int x){
        if (x==1){
            y2="id";
        }
        else if(x==2){
            y2="name";
        }
        else if(x==3) {
            y2="body";
        }
        else{
            System.out.println("Please Enter Number between 1-3");
        }
        return y2;
    }
    public static String Chooseoption4(int x){
        if (x==1){
            y2="id";
        }
        else if(x==2){
            y2="title";
        }
        else {
            System.out.println("Please Enter number between 1-2");
        }

        return y2;
    }

}
