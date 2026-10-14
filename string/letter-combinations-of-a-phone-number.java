import java.util.*;

class Solution {
    private static final String[] KEYS = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder combination, String digits, int index) {
        if (index == digits.length()) {
            result.add(combination.toString());
            return;
        }
        String letters = KEYS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            combination.append(c);
            backtrack(result, combination, digits, index + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
