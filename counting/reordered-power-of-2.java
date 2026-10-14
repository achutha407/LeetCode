class Solution {
    // Method to determine if the digits of the input number can be reordered to form a power of two
    public boolean reorderedPowerOf2(int n) {
        // Convert the number into a string representation of its digit count
        String targetDigitCount = convertToDigitCount(n);
        // Iterate through powers of 2
        for (int i = 1; i <= 1e9; i <<= 1) { 
            // Compare the digit count of the current power of 2 with the input number's digit count
            if (targetDigitCount.equals(convertToDigitCount(i))) {
                // If both counts match, the digits can be reordered to form a power of two
                return true;
            }
        }
        // If no power of 2 matches, return false
        return false;
    }

    // Helper method to convert a number into a string representing the count of each digit
    private String convertToDigitCount(int n) {
        // Array to store the count of digits from 0 to 9
        char[] digitCount = new char[10]; 
        // Divide the number into digits and count them
        while (n > 0) {
            digitCount[n % 10]++;
            n /= 10;
        }
        // Return a new string constructed from the array of digit counts
        return new String(digitCount);
    }
}
