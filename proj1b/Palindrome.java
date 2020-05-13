public class Palindrome {

	public Deque<Character> wordToDeque(String word){
		Deque<Character> result = new LinkedListDeque<>();
		for (int i = 0; i <word.length() ; i++) {
			result.addLast(word.charAt(i));
		}
		return result;
	}

	public boolean isPalindrome(String word) {
		Deque<Character> palindrome = wordToDeque(word);
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != palindrome.removeLast()){
				return false;
			}
		}
		return true;
	}


	public boolean isPalindrome(String word, CharacterComparator cc){
		Deque<Character> offByOne = wordToDeque(word);
		for (int i = 0; i < word.length()/2; i++) {
			if (!cc.equalChars(word.charAt(i),offByOne.removeLast())){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		OffByOne ob = new OffByOne();
		String word = "flake";
		Palindrome p = new Palindrome();
		System.out.println(p.isPalindrome(word, ob));
	}



}
