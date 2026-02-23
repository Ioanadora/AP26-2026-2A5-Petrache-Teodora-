
package com.teo.lab1;

public class suma {

    public static int sum(int nr){

        while(nr>=10)
        {
            int sum1=0;
            int x=nr;
            while(x>0)
            {
                sum1=sum1+x%10;
                x=x/10;
            }
            //System.out.println(sum1);
            nr=sum1;
        }

        return nr;
    }
}
