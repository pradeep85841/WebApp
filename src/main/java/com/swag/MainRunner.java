package com.swag;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class MainRunner {

    public static void main(String[] args) {

        TestNG testNG = new TestNG();


        testNG.setTestClasses(new Class[] { LogIn.class });


        TestListenerAdapter tla = new TestListenerAdapter();
        testNG.addListener(tla);


        testNG.run();
    }

}
