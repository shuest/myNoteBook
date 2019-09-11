public class Main {
    public static void main(String[] args) {
        ShareResource sr = new ShareResource();
        new Thread(()->{
            for(int i =0; i <10; i++)
                sr.printA();
        },"A").start();
        new Thread(()->{
            for(int i =0; i <10; i++)
                sr.printL();
        },"L").start();
        new Thread(()->{
            for(int i =0; i <10; i++)
                sr.printI();
        },"I").start();
    }
}
