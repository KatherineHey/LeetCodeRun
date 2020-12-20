package array;
/**
 * 5523. Crawler Log Folder
 * @author Katherine
 *
 */
public class CrawlerLogFolder {
    public int minOperations(String[] logs) {
        int depth = 0;
        
        for (String path: logs) {
            switch(path)
            {
                case "../":
                    if (depth > 0)
                        depth--;
                    break;
                case "./":
                    break;
                default:
                    depth++;
            }
        }

        return depth;
    }
}
