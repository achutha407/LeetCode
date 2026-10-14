class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
          
            if (currentChar == '(') {
                depth++;
                if (depth > 1) {
                    result.append(currentChar);
                }
            } else {
                depth--;
                if (depth > 0) {
                    result.append(currentChar);
                }
            }
        }
        return result.toString();
    }
}