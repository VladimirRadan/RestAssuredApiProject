package tests;

import logic.LogicClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestClass {

    LogicClass logic = new LogicClass();

    @Test
    public void test1(){
         assertThat(logic.isResponseEmpty()).isTrue();
    }

    @Test
    public void test2(){
        assertThat(logic.isResponseEmpty2()).isTrue();
    }



}
