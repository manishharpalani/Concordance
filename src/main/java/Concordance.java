import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Build a concordance
 * A "concordance" is an alphabetical list of the words present in a text with a count of how often each
 * word appears and citations of where each word appears in the text (e.g., page number). Write a program
 * -- in the programming language of your choice -- that will generate a concordance of an arbitrary text
 * document written in English: the text can be read from stdin, and the program should output the
 * concordance to stdout or a file. For each word, it should print the count and the sorted list of citations, in
 * this case the zero-indexed sentence number in which that word occurs. You may assume that the input
 * contains only spaces, newlines, standard English letters, and standard English punctuation
 * marks.
 */
public class Concordance {
    LineParser parser = new LineParser();
    WordCitation citation = new WordCitation();

    void read(InputStream inStream) {
        Scanner scanner = new Scanner(inStream);
        while (scanner.hasNextLine()) {
            List<String> words = parser.getWords(scanner.nextLine());
            citation.add(words);
        }
    }

    void printReport(PrintStream printStream) {
        citation.printResults(printStream);
    }

    /**
     *
     * @param args - one required parameter for the output file name.
     *             Input is read from standard input
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing parameter - output file name required. Exiting.");
            exit(0);
        }
        Concordance concordance = new Concordance();
        System.out.println("======== Press Cmd+D on OSX when done with input ========");
        concordance.read(System.in);

        PrintStream printStream = null;
        try {
            printStream = new PrintStream(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        concordance.printReport(printStream);
        System.out.println(String.format("======== Concordance written to %s ========", args[0]));
    }

}
