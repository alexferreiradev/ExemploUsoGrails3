package exemplotesteintegracao

import exemplotesteintegracao.command.SummaryCommand
import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import grails.testing.services.ServiceUnitTest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class LivroServiceSpec extends Specification implements ServiceUnitTest<LivroService> {

    @Autowired
    LivroService service

    void "carrega livros com gorm"() {
        setup:
        /* Este teste precisa da base de dados preenchida */
        SummaryCommand command = new SummaryCommand()
        command.limit = 10
        command.offset = 0

        when:
        List<Livro> livros = service.livroSummary(command)

        then:
        !livros.isEmpty()
        livros.get(0).autor == "PAMUK, ORHAN"
    }

}
