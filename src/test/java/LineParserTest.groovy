import spock.lang.Specification

class LineParserTest extends Specification {
    LineParser processor = new LineParser();
    def setup() {
    }

    def cleanup() {
    }

    def "test emptyString"() {
        expect:
        processor.getWords("").isEmpty()
    }

    def "test single word"() {
        expect:
        processor.getWords("word") == Arrays.asList("word");
    }

    def "test multiple words"() {
        expect:
        processor.getWords("we have four words") == Arrays.asList("we", "have", "four", "words");
    }

    def "test mix case words"() {
        expect:
        processor.getWords("We HAVE foUr words") == Arrays.asList("we", "have", "four", "words");
    }

    def "test period"() {
        expect:
        processor.getWords(".").isEmpty();
    }

    def "test double quotes word"() {
        expect:
        processor.getWords("\"concordance\"") == Arrays.asList("concordance");
    }

    def "surround with dash & space"() {
        expect:
        processor.getWords("-- concordance --") == Arrays.asList("concordance");
    }

    def "test word with apostrophe"() {
        expect:
        processor.getWords("don't") == Arrays.asList("don't");
    }

    def "test dashed words"() {
        expect:
        processor.getWords("\"man-ish-- -- -harp--alani--") == Arrays.asList("man-ish", "harp--alani");
    }

    def "test punctuation"() {
        expect:
        processor.getWords("!\"#\\\$%&'()*+,-./:;<=>?@[\\]^_`{|}~concordance!\"#\\\$%&'()*+,-./:;<=>?@[\\]^_`{|}~") == Arrays.asList("concordance");
    }

    def "test getWords"() {
        expect:
        processor.getWords("").isEmpty()
        processor.getWords('A "concordance" (e.g., page number). Write') == Arrays.asList("a", "concordance", "e.g", "page", "number", "write");
        processor.getWords("-- of your choice -- English: ") == Arrays.asList("of", "your", "choice", "english");
        processor.getWords("this zero-indexed newlines, standard English letters don't") == Arrays.asList("this", "zero-indexed", "newlines", "standard", "english", "letters", "don't");

    }

}
