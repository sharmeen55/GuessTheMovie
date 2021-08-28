import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//1. Select Random movie
//2. Show guessed word and don't show others
//3. If guessed is equal to 10 he lose
//4. If guessed all characters he win

public class Game{
    static int[] agp = new int[300];
    public static String print(String one){
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < one.length(); i++){
            if(agp[one.charAt(i)] == 1) {
                ans.append(one.charAt(i));
            }
            else ans.append("_");
        }
        return ans.toString();
    }
    static ArrayList<Character>wrg = new ArrayList<Character>();
    public static boolean check(char a, String one){
        for(int i = 0; i < one.length(); i++){
            if(one.charAt(i) == a){
                return true;
            }
        }
        wrg.add(a);
        return false;
    }
    public static boolean iswon(String one){
        for(int i = 0; i < one.length(); i++){
            if(agp[one.charAt(i)] == 0) return false;
        }
        return true;
    }
    public static void printWrongs(){
        for(char a:wrg){
            System.out.print(a + " ");
        }
        System.out.println("");
    }
    public static void main(String[] arg) throws FileNotFoundException {
        File mov = new File("movies.txt");
        Scanner sharmin = new Scanner(mov);
        ArrayList<String>movie = new ArrayList<String>();
        String temp;
        while(sharmin.hasNextLine()){
            temp = sharmin.nextLine();
            movie.add(temp);
        }
        int idx = (int)(Math.random()*(movie.size()));
        String choosen = movie.get(idx);
        for(int i = 0; i < 300; i++) agp[i] = 0;
        agp[' '] = 1;
        int wrong = 0;
        Scanner usr = new Scanner(System.in);
        while(wrong < 10){
            System.out.println("You are guessing:" + print(choosen));
            System.out.print("Guess a letter:");
            char now = usr.next().charAt(0);
            if(!check(now, choosen)){
                wrong++;
            }else{
                agp[now] = 1;
            }
            if(iswon(choosen)){
                System.out.println("You Win!");
                System.out.println("You have guessed '" + print(choosen)+"' correctly.");
                System.exit(0);
            }
            System.out.print(("You Have guessed (" + wrong + ") wrong letters: "));
            printWrongs();
        }
        System.out.println("You Lose!");
    }
}