package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import utility.IO;

public class KthElement {
    public List<String> topKFrequent(String[] words, int k) {
     // Hashmap to keep track of the words occurance and frequency
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        ArrayList<Integer> allFrequencies = new ArrayList<Integer>();

        
        map.forEach((key,v) -> allFrequencies.add(v));
        IO.printArrayList(allFrequencies);
        // Add all the frequencies into allFrequencies arraylist
        int totalAmountOfWords = map.keySet().size();
        
        // Find the k th largest number in the allFrequencies arraylist
        //System.out.println("total: "+totalAmountOfWords);
        int newK = totalAmountOfWords - k;
        //System.out.println("newk: "+ newK);
        int newkth = findKth(allFrequencies, newK);
        //System.out.println("new kth element:"+newkth);
        int newKthElement = newK > 0 ? newkth : 0 ;
        
        TreeMap<Integer, TreeSet<String>> reverseSortedMap = new TreeMap<Integer, TreeSet<String>>(Collections.reverseOrder());
        map.forEach((key,v) -> {if (v >= newKthElement ) {
            if(!reverseSortedMap.containsKey(v)) reverseSortedMap.put(v, new TreeSet<String>());
            reverseSortedMap.get(v).add(key);}});
        
        List<String> result = new ArrayList<String>();
        reverseSortedMap.forEach((key,v) -> {result.addAll(v);});
        
        return result.size() > k? result.subList(0, k): result;
    }
    
    // asc order kth element index
    // k is 0 index based
    public int findKth (ArrayList<Integer> arr, int k) {
        int start = 0;
        int end = arr.size()-1;
        int pivotIndex = 0;
        
        while (start < end) {
            pivotIndex = partition(arr, start, end);
            if (pivotIndex == k) return arr.get(k);
            else if (pivotIndex < k) {
                start = pivotIndex+1;
            } else {
                end = pivotIndex-1;
            }
        }
        
        //System.out.println(start +" - "+ arr.get(k));
        return arr.get(k);
    }
    
    // reorder based on pivot
    // all the numbers before return index are smaller than pivot
    public int partition(ArrayList<Integer> arr, int l, int r) {
        int pivot = arr.get(r);
        int pivotIndex = l;
        for (int i = l; i < r; i++) {
            if (arr.get(i) <= pivot) {
                swap(arr, i, pivotIndex);
                pivotIndex++;
            }
        }
        
        swap(arr, pivotIndex, r);
        return pivotIndex;
    }
    
    public void swap(ArrayList<Integer> arr, int start, int end) {
        Integer tmp = arr.get(start);
        arr.set(start, arr.get(end));
        arr.set(end, tmp);
    }
    
    public static void main(String[] args) {
        KthElement ke = new KthElement();
        String[] words = {"cdrp","hoimzd","jipfc","gpfnqbgjsv","hwbsxlj","gmwjmff","dpcrz","pskkp","ktlxdvtsav","qfbtcrow","lxnmbrwwo","ktlxdvtsav","xwb","zsw","syhxswrw","gmwjmff","gpfnqbgjsv","fpctwemnc","gpfnqbgjsv","jipfc","jwoxsoe","zsw","zsw","zsw","zsw","rdylascbpp","pskkp","prfppxkszm","xdbalmj","ktlxdvtsav","jwoxsoe","rkcsnr","qfbtcrow","prfppxkszm","hoimzd","rdylascbpp","prfppxkszm","gmwjmff","grijaljzl","fpctwemnc","dlmzeasvbq","aklxzn","xajwdva","rdylascbpp","xwb","cdrp","rkcsnr","prfppxkszm","xwb","ecynvgovgi","nqf","xdbalmj","dlmzeasvbq","lxnmbrwwo","prfppxkszm","gpfnqbgjsv","pskkp","szzddifa","jwoxsoe","aklxzn","ecynvgovgi","syhxswrw","aklxzn","ktlxdvtsav","jwoxsoe","nqf","szzddifa","hoimzd","rdylascbpp","gur","gpfnqbgjsv","gpfnqbgjsv","prfppxkszm","ktlxdvtsav","ktlxdvtsav","syhxswrw","pskkp","zpxm","jwoxsoe","rkcsnr","rdylascbpp","rdylascbpp","rdylascbpp","ecynvgovgi","syhxswrw","uyrijhy","pskkp","xvl","qfbtcrow","jwoxsoe","lxnmbrwwo","gpfnqbgjsv","hoimzd","syhxswrw","nqf","gmwjmff","ecynvgovgi","szzddifa","zsw","uyrijhy","gpfnqbgjsv","ktlxdvtsav","zsw","syhxswrw","prfppxkszm","pskkp","skuylkwikh","szzddifa","hwbsxlj","zsw","rdylascbpp","pdpnafvvm","gur","xdbalmj","rkcsnr","pskkp","gpfnqbgjsv","syhxswrw","ktlxdvtsav","rdylascbpp","pskkp","xvl","gpfnqbgjsv","hoimzd","prfppxkszm","syhxswrw","dlmzeasvbq","xdbalmj","ecynvgovgi","uyrijhy","ktlxdvtsav","gur","lxnmbrwwo","gur","uyrijhy","syhxswrw","szzddifa","aklxzn","syhxswrw","rdylascbpp","zsw","pskkp","qfbtcrow","xdbalmj","hoimzd","ecynvgovgi","pskkp","ktlxdvtsav","aklxzn","zsw"};
        //String[] words = {"a"};
        int k = 16;
        IO.printArrayList(ke.topKFrequent(words, k));
    }
}
