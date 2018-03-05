package exemplotesteintegracao

import exemplotesteintegracao.command.SummaryCommand
import grails.gorm.transactions.Transactional
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import javax.sql.DataSource

@Transactional
class LivroService {

    DataSource dataSource

    List<Livro> livroSummary(SummaryCommand command) {
        Sql sql = new Sql(dataSource)
        Livro livro = new Livro(id: 100L, autor: "Teste autor", titulo: "Testst titulo", descricao: "Teste desc ", preco: 4.5, imagem: "test img")
        livro.dataAtualizacao = new Date()
        livro.dataCriacao = new Date()
        livro.id = 100L
        livro.save(flush: true)
        List<GroovyRowResult> livrosRows = sql.rows("SELECT * from livro", command.limit, command.offset)
//        return livrosRows.findResult {
//            return new Livro(titulo: it.titulo, id: it.id)
//        }
        return Livro.findAll()
    }
}
