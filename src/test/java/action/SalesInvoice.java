package action;

public class SalesInvoice extends Invoice {
    public static String formatId (String oldId) {
        return oldId + "SalesInvoic";
    }
    public static void main(String[] d){
        Invoice i = new Invoice();

        Invoice invoice = new SalesInvoice();String s="1234";
        System.out.println(invoice.formatId("1234"));
        System.out.println("1234"=="1234");
        System.out.println(s=="1234");
    }
    }