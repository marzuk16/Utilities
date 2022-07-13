import java.util.Scanner;
public class Executor{
    public static void main(String[] args) {
        System.out.println("Executor class started.....................");
        Scanner input = new Scanner(System.in);
        TakaToWord takaToWord = new TakaToWord();
        String taka = input.next();
        String result = takaToWord.convert(taka);
        System.out.println(result);
        System.out.println("Executor class Ended.....................");    
    }
}