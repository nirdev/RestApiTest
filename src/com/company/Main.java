package com.company;



import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.company.ChooseOptions.Choose;
import static com.company.ChooseOptions.y;


public class Main {

    public static void main(String[] args) {
        /**
         * Choosing the option between 3 pages
         */
        System.out.println("Enter the subject you like (1.posts 2.comments 3.albums");
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();


/**
 * Check if the number is legal == if he's integer and not String
 *
 */
            try {
                userNumber(number);

            } catch (NumberFormatException e) {
                System.out.println("please input decimal value");
                main(null);
            }
        Integer x = Integer.valueOf(number);
            String SecondStage=GettingStarted.GetStart(x);



        /**Check if the Integer between the range of 1-3
         * than using the mathoods "getUrl" and "Choose" that shows the information.
         *
         */
        if((x<4)&(x>0)){
            geturl(Choose(x),SecondStage);
        }
        else{
            System.out.println("please input number between 1-3");
            main(null);
        }




        }

















    /**
     * gettinng url String convert it to InputStreatm and than Creating a Json Object
     * @param myurl
     */
     private static void geturl(String myurl,String SecondStage1) {
        INetworkAdmin netAdmin = new NetworkAdmin();
        try {
           String json =  netAdmin.getHttpResponse("https://jsonplaceholder.typicode.com"+myurl);
            InputStream streamrdr = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8.name()));
            JsonReader rdr = Json.createReader(streamrdr);
            JsonArray objects=rdr.readArray();


            int JsonLengh=0;
            for(JsonObject ob : objects.getValuesAs(JsonObject.class)){
                JsonLengh=JsonLengh+1;
                System.out.println(ob.getJsonString(SecondStage1));
            }

            /**
             * Starting to Put all the Values in Json Array for the 3rd stage which is organizing the data's for the user
             *
             */
            JsonObject ob1[]= new JsonObject[JsonLengh] ;
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void userNumber(String number)  throws NumberFormatException {
        int num = Integer.parseInt(number);

    }

}



