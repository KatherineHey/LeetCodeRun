package array;

import java.util.ArrayList;
import java.util.List;

class OrderedStream {
    String[] stream;
    int ptr;
    public OrderedStream(int n) {
        stream = new String[n+1]; // index 0 to n (1 indexed)
        ptr = 1;
    }
    
    public List<String> insert(int id, String value) {
        stream[id] = value;
        
        // if ptr points to an empty spot, ptr stay the same position, return empty array
        // else return as far filled up as position, from ptr to idx
        List<String> arr = new ArrayList<String>();

        if (ptr >= stream.length || stream[ptr]==null) return arr;
        
        int idx = ptr;
        while (idx < id) {
            if (stream[idx] != null) idx++;
            else break;
        }

        while (ptr <= idx) {
            arr.add(stream[ptr++]);
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        OrderedStream os= new OrderedStream(5);
        os.insert(3, "ccccc"); // Inserts (3, "ccccc"), returns [].
        os.insert(1, "aaaaa"); // Inserts (1, "aaaaa"), returns ["aaaaa"].
        os.insert(2, "bbbbb"); // Inserts (2, "bbbbb"), returns ["bbbbb", "ccccc"].
        os.insert(5, "eeeee"); // Inserts (5, "eeeee"), returns [].
        os.insert(4, "ddddd"); // Inserts (4, "ddddd"), returns ["ddddd", "eeeee"].
    }
}
