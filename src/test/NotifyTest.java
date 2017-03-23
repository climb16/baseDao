package test;
public class NotifyTest {
    private Boolean flag =  true ;
    private int i=0;


    class WaitThread extends Thread {
        public WaitThread(String name) {
            super(name);
        }

        public void run() {
            while(i<100){
                synchronized (flag) {


                    try {
                        i++;
                        System.out.println(getName() +"-------->"+i );
                        flag.wait();
                        //sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    flag.notify();

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        NotifyTest test = new NotifyTest();

        WaitThread waitThread01 = test.new WaitThread("waiter01");
        WaitThread waitThread02 = test.new WaitThread("waiter02");

        waitThread01.start();
        waitThread02.start();

    }

}  
