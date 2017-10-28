package com.company;

import java.util.Scanner;
import static com.company.ChooseOptions.Chooseoption2;
import static com.company.ChooseOptions.Chooseoption3;
import static com.company.ChooseOptions.Chooseoption4;
import static com.company.Scanner.ShowScanner;

public class GettingStarted {
    static String option2;

    /**
     * this methood checking after the first choice and Returning the String of the 2nd choice example (Show me the Body of each topic)
     *
     * @param x
     * @return
     */
    public static String GetStart (int x){
        if (x == 1) {

            System.out.println("Choose the subject you want to see: 1.Id 2.Title 3.Body");
            int b=ShowScanner();
            option2 =Chooseoption2(b);

        }
        else if (x == 2) {

            System.out.println("Choose the subject you want to see: 1.Id 2.name 3.Body");
            int b=ShowScanner();
            option2 =Chooseoption3(b);

        }
        else if (x==3){
            System.out.println("Choose the subject you want to see: 1.Id 2.Title");
            int b=ShowScanner();
            option2=Chooseoption4(b);
        }



return option2;
    }

}
