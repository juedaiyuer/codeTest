package myAlgorithm;

import java.util.Stack;

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
    * 1. 先序遍历，非递归方法
    * 2. 栈实现功能
    * 3. 右侧push
    * 4. TreeNode head 可以修改为root
    * */
    public void preOrderUnRecur(TreeNode head){
        System.out.println("非递归先序遍历二叉树");
        if (head != null){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.print(head.value+",");

                if (head.right != null){
                    stack.push(head.right);
                }

                if (head.left != null){
                    stack.push(head.left);
                }

            }
        }
        System.out.println();
    }

    /*
    * 1. 中序遍历，非递归；左中右
    * 2. 栈实现
    *
    * */
    public void inOrderUnRecur(TreeNode head){
        System.out.println("中序遍历非递归二叉树方法");
        if (head != null){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while (!stack.isEmpty() || head != null){
                if (head != null){
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.print(head.value + ",");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /*
    * 1. 后序遍历，非递归，比较麻烦
    * 思想如下：
    * 1. 申请一个栈，记为s1,然后将头节点head压入s1
    * 2. 从s1中弹出的节点记为cur,然后依次将cur的左孩子和右孩子压入s1中
    * 3. 在整个过程中，每一个从s1中弹出的节点都放进s2中
    * 4. 不断重复步骤2和步骤3,直到s1为空
    * 5. 从s2中依次弹出节点并打印，打印的顺序就是后序遍历的顺序
    * */
    public void posOrderUnRecur1(TreeNode head){
        System.out.println("后序遍历非递归二叉树");
        if(head != null){
            Stack<TreeNode> s1 = new Stack<TreeNode>();
            Stack<TreeNode> s2 = new Stack<TreeNode>();
            s1.push(head);
            while(!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);

                if (head.left != null){
                    s1.push(head.left);
                }

                if(head.right != null){
                    s1.push(head.right);
                }

            }

            while (!s2.isEmpty()){
                System.out.print(s2.pop().value+",");
            }

        }
        System.out.println();
    }


        /*
        * 删除节点
        * */
/*    public TreeNode delete(int key){
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


    }*/
}
