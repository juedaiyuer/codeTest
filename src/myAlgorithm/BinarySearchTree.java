package myAlgorithm;

/**
 * Created by juedaiyuer on 16-9-28.
 */
public class BinarySearchTree {
    public static TreeNode root;

    public BinarySearchTree(){
        this.root = null;
    }

    /*
    * 查找
    * */

    public TreeNode search(int key){
        TreeNode current = root;
        while(current != null && key != current.value){
            if(key<current.value)
                current = current.left;
            else
                current = current.right;

            if (current == null){
                return null;
            }
        }

        return current;
    }

    /*
    * 插入
    * */

    public TreeNode insert(int key) {
        //新增节点
        TreeNode newNode = new TreeNode(key);
        //当前节点
        TreeNode current = root;
        //上个节点
        TreeNode parrent = null;
        //如果根节点为空
        if (current == null) {
            root = newNode;
            return newNode;
        }

        while (true) {
            parrent = current;
            if (key < current.value) {
                current = current.left;
                if (current == null) {
                    parrent.left = newNode;
                    return newNode;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parrent.right = newNode;
                    return newNode;
                }
            }
        }
    }

    // 最大值，最小值
    public int max(){
        TreeNode node = root;
        TreeNode parrent = null;
        while (node != null){
            parrent = node;
            node = node.right;
        }
        return parrent.value;
    }

    public int min(){
        TreeNode node = root;
        TreeNode parrent = null;
        while (node != null){
            parrent = node;
            node = node.left;
        }
        return parrent.value;
    }

    public void display(TreeNode node) {
        if (node != null) {
            display(node.left);
            System.out.println(node.value + ",");
            display(node.right);
        }
    }



        /*
        * 删除节点
        * */
    public TreeNode delete(int key){
        TreeNode parent = root;
        TreeNode current = root;
        boolean isLeftChild = false;
        // 找到删除节点及是否在左子树
        // 且删除的节点在二叉数中存在
        while (current.value != key){
            parent = current;
            // 判断节点在哪一个方向的子树，左子树还是右子树
            if(current.value > key){
                isLeftChild = true;
                current = current.left;
            }else {
                isLeftChild = false;
                current = current.right;
            }
            //
            if(current == null){
                return current;
            }
        }

        // 如果删除节点左节点为空，右节点也为空
        if(current.left == null && current.right == null){
            if(current == root){
                root = null;
            }

            //在左子树
            if(isLeftChild == true){
                parent.left = null;
            }else {
                parent.right = null;
            }
        }

        // 如果删除节点只有一个子节点
        // 只有右节点的情况
        else if (current.left == null){
            // 删除节点在根节点
            if(current == root){
                root = current.right;
            }else if (isLeftChild){
                // 删除节点的最右侧，替换删除节点
                parent.left = current.right;
            }else {
                parent.right = current.right;
            }
        }

        // 如果删除节点左右子节点都不为空


    }
}
