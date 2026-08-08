class Solution {
    public String removeStars(String s) {
        StringBuilder result = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
