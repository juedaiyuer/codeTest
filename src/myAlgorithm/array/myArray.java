package myAlgorithm.array;

/**
 * Created by juedaiyuer on 16-11-9.
 */
public class myArray {

    /*
    * Task:未排序正数数组中累加和为给定值的最长子树组长度
    *
    * 时间复杂度O（n）,额外空间复杂度O（1）
    *
    * 步骤
    * 1. arr[left...right]长度大于len,更新len
    *
    * sum==k
    *
    * 2. arr[left...i(i>right)],累加和一定大于k,所以另left+1,考察left之后位置开始的子数组，同时sum -= arr[left]
    *    sum 此时表示arr[left+1...right]的累加和
    *
    * sum<k 说明arr[left...right] 还要加上right后面的值，其和才可能达到k
    *
    * 3. right+1,即sum+=arr[right]
    *
    * sum>k 说明所有从left位置开始，在right之后的位置结束的子数组，即arr[left...i(i>right)],累加和一定大于k
    *
    * 4. left+1 sum-=arr[left];sum此时代表着arr[left+1...right]的累加和
    *
    * */
    public int getMaxLength(int[] arr,int k){
        if (arr == null || arr.length == 0 || k <= 0){
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;

        while (right < arr.length){
            if (sum == k) {
                len = Math.max(len,right-left+1);    // 步骤1
                sum -= arr[left++];                  // 步骤2
            }else if (sum < k){
                right++ ;
                if (right == arr.length){
                    break;
                }
                sum += arr[right];
            }else {                                  // sum>k
                sum -=arr[left++];
            }
        }
        return len;
    }
}
