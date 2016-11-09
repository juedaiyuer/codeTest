package myAlgorithm.array;

/**
 * Created by juedaiyuer on 16-11-9.
 */
public class testmyArray {
    public static void main(String[] args) {
        int[] arr = {1,2,1,1,1};
        myArray testmyArray = new myArray();
        int len = testmyArray.getMaxLength(arr,3);
        System.out.println(len);
    }
}
