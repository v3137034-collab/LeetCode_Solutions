class Solution:
    def missingNumber(self, nums):
        n = len(nums)
        # Sum of numbers from 0 to n
        expected_sum = n * (n + 1) // 2
        # Subtract actual sum of nums
        actual_sum = sum(nums)
        return expected_sum - actual_sum