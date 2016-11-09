package myAlgorithm.binaryTree;

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
//            mysearch.display(testTreeNode);

//            System.out.println(mysearch.search(9));

            mysearch.preOrderUnRecur(testTreeNode);
            mysearch.inOrderUnRecur(testTreeNode);
            mysearch.posOrderUnRecur1(testTreeNode);

            System.out.println("-------------------------");
            System.out.println("前序遍历序列化");
            String str = mysearch.serialByPre(testTreeNode);
            System.out.println(str);
            System.out.println("----------反序列化-------");
            TreeNode node = mysearch.reconByPreString(str);
            BinarySearchTree test = new BinarySearchTree();
            test.preOrderUnRecur(node);


            System.out.println();
            System.out.println("---------Morris中序遍历---------");
            mysearch.morrisIn(testTreeNode);

            System.out.println("---------判断二叉树是否为平衡二叉树---------");
            mysearch.isBalance(testTreeNode);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
