import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * We keeps citations for each word in the wordMap - word is the key, and
 * the value is a CSV of line number the word appears in.
 *  - each line is added with the
 */
class WordCitation {
    private Map<String, String> wordMap = new HashMap<>();
    int lineNum = 0;

    /**
     *
     * @param words - words in a line to be added to citations. This method
     *              keep the count internally. It should be called for each line
     *              iteratively from the first line (line 0) to the last line.
     */
    void add(List<String> words) {
        for(String word: words) {
            add(word.toLowerCase(), lineNum);
        }
        lineNum++;
    }

    void add(String word, int page) {
        wordMap.merge(word, Integer.toString(page), (a, b) -> String.format("%s,%s", a, b));
    }

    /**
     * Prints the count:csv_citations to printStream for each word.
     * Requirement - alphabetical list of the words present in a text with a count of how often
     * each word appears and citations of where each word appears in the text (e.g., page number).
     * Example:
     *******************
     * a                              {6:0,0,0,1,2,4}
     * alphabetical                   {1:0}
     * ...
     *******************
     * Note: Array.sort is performed on the wordMap keys to create a alphabetic ordered Concordance.
     * Array.sort has worst cast O(N*logN)performance.
     * https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort%28int%5b%5d%29
     * @param printStream the stream to print the citations to.
     */
    void printResults(PrintStream printStream) {
        String[] keys = wordMap.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        for (String key: keys) {
            printStream.println(getWordEntry(key));
        }
    }

    String getWordEntry(String word) {
        String pagesCsv= wordMap.getOrDefault(word, "");
        if (pagesCsv.isEmpty())
            return "";
        long count = pagesCsv.chars().filter(ch -> ch == ',').count() + 1;
        return String.format("%-30.30s {%d:%s}", word, count, pagesCsv);
    }






}
