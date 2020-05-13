/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        OffByN obn = new OffByN(2);
        OffByOne obo = new OffByOne();
        int minLength = 4;
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word,obn)) {
                System.out.println(word);
            }
        }
    }
}
//Uncomment this class once you've written isPalindrome.