import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class wordleHelper {
    public static void main(String [] args){
        System.out.println("\n \n"+"         WORDLE HELPER :)\n5 letter words " + "(6 chances to get the correct word)");
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        Dictionary1 wWords = new Dictionary1();
        Dictionary2 allowable = new Dictionary2();
        //Runs game 5 times
        for(int p = 0; p< 5; p++) {
        ArrayList<String> wordList = new ArrayList<>(); // Stores the wordle words
        ArrayList<String> filterList1 = new ArrayList<>(); // Stores wordle words that aren't grey

        for(int i = 0; i< wWords.getSize(); i++){
            wordList.add(wWords.getWord(i));
        }

//            if(p != 0)System.out.println("\n \n"+"         WORDLE HELPER :)\n5 letter words " +
//                "(6 chances to get the correct word)");
//            else System.out.println("         WORDLE HELPER :)\n5 letter words " +
//                    "(6 chances to get the correct word)");

        //Getting a random word from the dictionary;
//        String word = wWords.getWord(rand.nextInt(wWords.getSize()));
//        word = word.toLowerCase();

        //Picking a word
        if(p != 0)System.out.println("\n\nChoose a 5 letter word");
        else System.out.println("Choose a 5 letter word");
        String word = sc.nextLine();
        word = word.toLowerCase();
        boolean inDic = false;
        while(!inDic){
            for(int i=0;i<wWords.getSize();i++){
                if(word.equals(wWords.getWord(i))) {
                    inDic = true;
                }
            }
            if(!inDic){
                System.out.println("Word is not in dictionary,try again!");
                word = sc.nextLine();
            }
        }

        //Keeps changing the word till its 5 letters long
       /* while(word.length() != 5){
            word = book.getWord(rand.nextInt(book.getSize()));
            word = word.toLowerCase();
        }*/


            //COMPARE LOOP
            ArrayList<Character> chr = new ArrayList<>();
            ArrayList<Integer> pos = new ArrayList<>();
            boolean won = false;
            for (int i = 1; i <= 6 && !won; i++) {
                if (i == 1) System.out.println("First tries 'adieu' (lots of vowels)");
                //  String [] ar1 = {"audio", "canoe", "adieu", "psych", "hymns"};

                System.out.println("Attempt " + i + ": ");

                String word1 = "";
                int r1 = rand.nextInt(2);
                int r2 = rand.nextInt(3);
                if (i == 1) {
                    word1 = "adieu";
                    System.out.println(word1);
                } else {
//               word1 = wordList.get(rand.nextInt(wordList.size()));
                    word1 = wordList.get(rand.nextInt(wordList.size()));
                    System.out.println(word1);
                }


                //If 5 letter inputted word isn't in the dictonary or if word is greater then 5 letters
                for (int s = 0; !word1.equalsIgnoreCase(allowable.getWord(s)); s++) {
                    if (s == allowable.getSize() - 1) {
                        if (word1.length() != 5) {
                            System.out.println("Not 5 letters, please type again :)");
                            word1 = sc.nextLine();
                            word1 = word1.toLowerCase();
                        } else {
                            System.out.println("Word doesn't make sense, type another: ");
                            word1 = sc.nextLine();
                            word1 = word1.toLowerCase();
                        }
                        s = 0;
                    }
                }


                // line 53-64: Used to get letters of words with more then one of the same letter in them (used later)
                int count = 0;
                char chr1 = ' ';
                char chr2 = ' ';
                for (int c1 = 0; c1 < word.length() - 1; c1++) {
                    for (int c2 = c1 + 1; c2 < word.length(); c2++) {
                        if (word.charAt(c1) == word.charAt(c2)) {
                            count++;
                            if (count == 1) chr1 = word.charAt(c1);
                            else if (count > 1) chr2 = word.charAt(c1);
                        }
                    }
                }

                int reached1 = 100;
                int reached2;


                //If the words are the same
                if (word1.equals(word)) {
                    System.out.println("Congratulations " + word1 + " is the correct word");
                    won = true; // Closes compare loop
                } else {
                    for (int y = 0; y < word.length(); y++) {
                        for (int x = 0; x < word1.length(); x++) {

                            //removes words with blank letters
                            if (word.charAt(y) != word1.charAt(x)) {
                                for (int r = 0; r < wordList.size(); r++) {
                                    char f = word1.charAt(x);
                                    //String letter = Character.toString(f);
                                    if ((wordList.get(r).charAt(y) == f)) wordList.remove(r--);
                                }
                            }

                            //If letters match
                            else if (word.charAt(y) == word1.charAt(x)) {
                                //If the letters match in the right position
                                if (y == x) {
                                    System.out.println("letter " + word1.charAt(x) + " at position " + (x + 1) + " is GREEN!");
                                    chr2 = word1.charAt(y);
                                    chr.add(word1.charAt(y));
                                    pos.add(y);
                                    // removes word without green chr in that position
                                    for (int r = 0; r < wordList.size(); r++) {
                                        char f = word1.charAt(x);
                                        //String letter = Character.toString(f);
                                        if ((wordList.get(r).charAt(y) != f)) wordList.remove(r--);
                                    }
                                }
                                // if theres two of the same letter
                                else if (y != x && (word.charAt(y) == chr1)) {
                                    System.out.println("letter " + word1.charAt(x) + " at position " + (x + 1) + " is YELLOW");
                                }
                                // if there isnt two of the same letter
                                else if (y != x) {
                                    if (chr2 != word1.charAt(y))
                                        System.out.println("letter " + word1.charAt(x) + " at position " + (x + 1) + " is YELLOW");
                                    // removes word with yellow chr in that position
                                    for (int r = 0; r < wordList.size(); r++) {
                                        char f = word1.charAt(x);
                                        String letter = Character.toString(f);
                                        if (!(wordList.get(r).contains(letter))) wordList.remove(r--);
                                    }
                                }
                            }

                        }
                    }

                }

                if (i == 5 && !won) {
                    System.out.println("THIS IS YOUR LAST ATTEMPT! Goodluck :)");
                }
            }

            if (!won) {
                System.out.println("Unlucky, you lost :(\nThe word was " + word);
            }
        }
    }
}









//Dictionary class (Used to pull a random 5 letter word)
class Dictionary1{

    private String input[];

    public Dictionary1(){
        input = load("C://wordlewords.txt");
    }

    public int getSize(){
        return input.length;
    }

    public String getWord(int n){
        return input[n].trim();
    }

    private String[] load(String file) {
        File aFile = new File(file);
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader( new FileReader(aFile) );
            String line = null;
            int i = 0;
            while (( line = input.readLine()) != null){
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        }catch (FileNotFoundException ex){
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }finally{
            try {
                if (input!= null) {
                    input.close();
                }
            }catch (IOException ex){
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(String s: array){
            s.trim();
        }
        return array;
    }
}







class Dictionary2 {

    private String input[];

    public Dictionary2() {
        input = load("C://allowable.txt");
    }

    public int getSize() {
        return input.length;
    }

    public String getWord(int n) {
        return input[n].trim();
    }

    private String[] load(String file) {
        File aFile = new File(file);
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(aFile));
            String line = null;
            int i = 0;
            while ((line = input.readLine()) != null) {
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find the file - are you sure the file is in this location: " + file);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for (String s : array) {
            s.trim();
        }
        return array;
    }
}
