package myAlgorithm;

/**
 * Created by juedaiyuer on 16-9-29.
 */
public class test {
    public static void main(String[] args) {
        try{

            TreeNode testTreeNode = null;
            BinarySearchTree mysearch = new BinarySearchTree() ;
            testTreeNode = mysearch.insert(12);
            mysearch.insert(5);
            mysearch.insert(18);
            mysearch.insert(15);
            mysearch.insert(17);
            mysearch.insert(19);
            mysearch.insert(2);
            mysearch.insert(9);

//            System.out.println(mysearch.max());
//            System.out.println(mysearch.min());
//
            mysearch.display(testTreeNode);

//            System.out.println(mysearch.search(9));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
