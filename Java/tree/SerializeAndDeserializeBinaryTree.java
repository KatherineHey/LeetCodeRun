package tree;

/**
 * 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/803931/
 * Java-Simple-pre-order-based-solution-faster-than-56
 * @author Katherine
 *
 */
public class SerializeAndDeserializeBinaryTree {
    int index = 0;
    String SEP = ",";
    String NULL = "#";
    
    // Encodes a tree to a single string.
    // Encodes with Preorder traversal
    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();
        PreorderEncode(root, sb, SEP, NULL);
        
        return sb.toString();
    }
    
    public void PreorderEncode(TreeNode root, StringBuilder sb, String SEP, String NULL) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        
        sb.append(root.val).append(SEP);
        
        PreorderEncode(root.left, sb, SEP, NULL);
        PreorderEncode(root.right, sb, SEP, NULL);
    }
    
    public TreeNode PreorderDecode(String[] nodes) {
        if (index >= nodes.length) return null;
        
        String data = nodes[index++];
        if (data.equals(NULL)) return null;
        
        TreeNode node = new TreeNode(Integer.parseInt(data));
        
        node.left = PreorderDecode(nodes);
        node.right = PreorderDecode(nodes);
        
        return node;
    }

    // Decodes your encoded data to tree.
    // Decodes with preorder traversal
    public TreeNode deserialize(String data) {
        if (data.endsWith(SEP)) data.substring(0, data.length());
        String[] nodes = data.split(",");
        
        TreeNode root = PreorderDecode(nodes);
        
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        
        n.right = new TreeNode(3);
        n.right.left = new TreeNode(4);
        n.right.right = new TreeNode(5);
        
        SerializeAndDeserializeBinaryTree s = new SerializeAndDeserializeBinaryTree();
        String encode = s.serialize(n);
        
        System.out.println(encode);
        s.deserialize(encode);
    }
}
