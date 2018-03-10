import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parse a line into words. Word start with a alphaNum character and is delimited by whitespace character
 * Any punctuation before and after the word is stripped.
 */
public class LineParser {
    final String DELIMITER_REGEX = "(\\p{Punct}*\\s+\\p{Punct}*)+";
    final String BEGIN_PUNCTUATION_REGEX = "^\\p{Punct}+";
    final String END_PUNCTUATION_REGEX = "\\p{Punct}+$";

    List<String> getWords(String line) {
        List<String> words = new ArrayList<String>( Arrays.asList(line.toLowerCase().split(DELIMITER_REGEX)) );
        ensureWord(words, 0);
        ensureWord(words, words.size()-1);
        return words;
    }

    private void ensureWord(List<String> words, int index) {
        if (index < 0 || index >= words.size())
            return;

        String word = words.get(index);
        // remove word if empty
        if (word.isEmpty()) {
            words.remove(word);
            return;
        }

        // strip any punctuation characters at the start and end of the word
        String newWord = word.replaceAll(BEGIN_PUNCTUATION_REGEX, "").replaceAll(END_PUNCTUATION_REGEX, "");
        if (word.equals(newWord))
            return;

        if (!newWord.isEmpty())
            words.add(index, newWord);
        words.remove(word);
    }

}
