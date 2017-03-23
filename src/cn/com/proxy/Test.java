package cn.com.proxy;

/**
 * Created by xiao on 2016/6/15.
 */
public class Test {

    public static void main(String[] args) {
        //Test.add();

        Test.proxycglib();

        Test.proxy();

    }


    private static void add(){
        Long a = System.currentTimeMillis();
        for (int i = 0; i< 100000; i++){
            UService u = new UService();
            u.add();
        }
        long b = System.currentTimeMillis();
        System.out.println(b-a);
    }

    private static void proxy(){
        Long a = System.currentTimeMillis();
        for (int i = 0; i< 100; i++){
            BookProxy bookProxy = new BookProxy();
            Service u = (Service) bookProxy.bind(UService.class);
            u.add();
        }
        long b = System.currentTimeMillis();
        System.out.println(b-a);
    }

    private static void proxycglib(){
        Long a = System.currentTimeMillis();
        for (int i = 0; i< 100; i++){
            ProxyCglib proxyCglib = new ProxyCglib();
            UService u = (UService) proxyCglib.getInstance(UService.class);
            u.add();
        }
        long b = System.currentTimeMillis();
        System.out.println(b-a);
    }
}
