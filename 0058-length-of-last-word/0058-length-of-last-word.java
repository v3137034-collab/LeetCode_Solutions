class Solution {
    public int lengthOfLastWord(String s) { // 1. Must return 'int', parameter must be 's'
        int length = 0; // 2. Fixed spelling here
        int i = s.length() - 1; // 3. Fixed spelling of length()

        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Count characters of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++; // 4. Fixed spelling here
            i--;
        }

        return length; // 5. Fixed spelling here
    }
}