package logic;

import java.util.ArrayList;
import java.util.List;

public class LogicClass {



    public boolean isResponseEmpty(){
        List list = new ArrayList();
        list.add(50);
        //list.remove(0);
        if (!list.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isResponseEmpty2(){
        List list = new ArrayList();
        list.add(50);
        list.remove(0);
        if (!list.isEmpty()){
            return true;
        }
        return false;
    }




}
