/*
思路：排序后，用前后双指针移动的方式来比较和target的大小
      也可以用二分搜索
*/

class Solution {
    public:
    vector<int> twoSum(vector<int>& nums, int target) {
        int head,tail;
        vector<int> ori;
        ori.insert(ori.begin(),nums.begin(),nums.end());
        sort(nums.begin(),nums.end());
        head = 0;
        tail = nums.size()-1;
        while (head < tail){
            int res = nums[head]+nums[tail];
            if (res == target) break;
            else if (res < target) head++;
            else tail--;
        }
        vector<int> result;
        for (int i = 0; i < ori.size();i++){
            if (ori[i] == nums[head] || ori[i]== nums[tail])
                result.push_back(i);
        }
        return result;
    }
};