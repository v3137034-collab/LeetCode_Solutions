class Solution {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;
        
        while (i >= 0) {
            // Step 1: Skip spaces
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            
            // If we reached the end of the string, break out
            if (i < 0) break;
            
            // Step 2: Mark the end of the current word
            int end = i;
            
            // Step 3: Find the start of the current word
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            
            // Step 4: Append the word to the builder
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(s.substring(i + 1, end + 1));
        }
        
        return result.toString();
    }
}