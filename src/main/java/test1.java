public class test1 {
    public void main(){
        int a;
        a = 3;
        a =add(a,5);
        a = multi(a,5);
        System.out.println("result :" + a);
    }

   int add(int a , int b){return a+b;}
   int multi(int a,int b){return a*b;}
   int tf(int a){return a;}
}
