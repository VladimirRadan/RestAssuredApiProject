package config;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Config {


    @BeforeMethod
    public void setup(){
        System.out.println("Izvrsava se pre svake metode");
    }








}
