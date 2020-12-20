package string;

public class ModifyString {
    public String modifyString(String s) {
        char[] sch = s.toCharArray();
        
        for (int i = 0 ; i < sch.length; i++) {
            if (sch[i] == '?') {
                char pre = 'a';
                char cur = 'b';
                char suf = 'z';
                if (i > 0) {
                    pre = sch[i-1];
                }
                if (i+1 < sch.length) {
                    suf = sch[i+1];
                }
                if (cur == pre || cur == suf) {
                    boolean add = true;
                    int diff = 1;
                    while (cur == pre || cur == suf) {
                        if (add)
                            cur = (char) (cur+diff);
                        else cur = (char) (cur-diff);
                        if (cur < 'a' || cur > 'z') {
                            add = !add;
                            diff++;
                        }
                    }
                }
                
                sch[i] = cur;
            }
        }
        
        return new String(sch);
    }
    
    public static void main(String[] args) {
        ModifyString ms = new ModifyString();
        //System.out.println(ms.modifyString("?zs"));
        //System.out.println(ms.modifyString("ubv?w"));
        //System.out.println(ms.modifyString("v????gm??a?czgn?ba?dya?????b?t????j??ag??qm?????t?imx?f??gc??a????orb??e?uvae?ak?????a?e??f??qg"));
        System.out.println(ms.modifyString("?l?j??s??c???"));
        
    }
}
