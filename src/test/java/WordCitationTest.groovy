class WordCitationTest extends spock.lang.Specification {
    WordCitation concordance = new WordCitation();

    def setup() {
        concordance.add(Arrays.asList("aaa", "aaa", "bbb"));
        concordance.add(Arrays.asList("bbb"));
        concordance.add(Arrays.asList());
        concordance.add(Arrays.asList());
        concordance.add(Arrays.asList("ccc"));
    }

    def cleanup() {
    }

    def "test add"() {
        expect:
        concordance.getWordEntry(word) == entryString

        where:
        word    || entryString
        "aaa"   || "aaa                            {2:0,0}"
        "bbb"   || "bbb                            {2:0,1}"
        "ccc"   || "ccc                            {1:4}"
        "abc"   || ""
    }

    def "print results"() {
        given:
        def outStream = new ByteArrayOutputStream()
        def stream = new PrintStream(outStream);
        concordance.printResults(stream);

        expect:
        outStream.toString() == '''\
aaa                            {2:0,0}
bbb                            {2:0,1}
ccc                            {1:4}
'''
    }
}
