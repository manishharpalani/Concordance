import spock.lang.Specification

class ConcordanceTest extends Specification {
    Concordance concordance = new Concordance();

    def "test getConcordance"() {
        given:
        InputStream inStream = new FileInputStream("./resources/sample_text.in")
        PrintStream printStream = new PrintStream("./resources/ConcordanceTest.out")

        when:
            concordance.read(inStream);
        then:
            concordance.printReport(printStream);
    }
}
