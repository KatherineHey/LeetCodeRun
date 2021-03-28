package hash;

import java.util.regex.Pattern;

import utility.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

class DuplicateFile {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res= new ArrayList<List<String>>();
        int n = paths.length;
        if (n == 0) return res;
        
        HashMap<String, List<String>> files = new HashMap<String, List<String>>();
        for (String p: paths) {
            String[] ps = p.split(" ");
            String folderDir = ps[0];
            
            for (int i = 1; i < ps.length; i++) {
                //System.out.println(ps[i]);
                Pattern pattern = Pattern.compile("\\((.*?)\\)");
                Matcher matcher = pattern.matcher(ps[i]);
                String content = "";
                if (matcher.find()) {
                    content = matcher.group(0);
                }
                String fileName = "/" + ps[i].split("\\(")[0];
                files.computeIfAbsent(content, k->new ArrayList<String>()).add(folderDir+fileName);
            }
        }
        
        
        Iterator it = files.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            List<String> pathsForContent = (ArrayList)pair.getValue();
            if (pathsForContent.size() > 1) {
                res.add(pathsForContent);
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        DuplicateFile s = new DuplicateFile();
        IO.printArrayList(s.findDuplicate(paths));
    }
}