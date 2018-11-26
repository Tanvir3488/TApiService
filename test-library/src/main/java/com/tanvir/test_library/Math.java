package com.tanvir.test_library;

public class Math {

    public static int Plus(int a , int b){

        return a+b;
    }

    public static int Minuse(int a , int b){

        return a-b;
    }

    public static int Multiple(int a , int b){

        return a*b;
    }

    public static int DIV(int a , int b){

        if (b==0){

            throw new  IllegalArgumentException("Can't divide by Zero");
        }
        return a/b;
    }
}
