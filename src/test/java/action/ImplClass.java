package action;

import java.math.BigDecimal;

 interface InterfaceA {
    default String f(){
        return"this is InterfaceA";
    }
    static String getName(){
        return "InterfaceA";
    }
}


public class ImplClass implements InterfaceA {
    public static void main(String[] args) {

        System.out.println(new ImplClass().f());
        System.out.println(InterfaceA.getName());
    }
}
