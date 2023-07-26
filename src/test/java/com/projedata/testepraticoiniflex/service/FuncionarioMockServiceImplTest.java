package com.projedata.testepraticoiniflex.service;

import com.projedata.testepraticoiniflex.mock.FuncionarioMock;
import com.projedata.testepraticoiniflex.mock.FuncionarioMockServiceImpl;
import com.projedata.testepraticoiniflex.model.Funcionario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class FuncionarioMockServiceImplTest {

    @Mock
    private FuncionarioMockServiceImpl funcionarioService;

    @Mock
    private FuncionarioMock funcionarioMock;

    @Before
    public void setUp() {
        funcionarioService = new FuncionarioMockServiceImpl();
        funcionarioService.startFuncionarios();
    }

    @Test
    public void testRemoverFuncionarioPorNome() {
        var nome = "João";
        funcionarioService.removerFuncionarioPorNome(nome);
        var funcionarios = funcionarioService.listarFuncionarios();
        for (Funcionario funcionario : funcionarios) {
            assertNotEquals(nome, funcionario.getNome());
        }
    }

    /*@Test
    public void testUpdateFuncionario() {
        var nome = "Caio";
        var funcionario = new Funcionario(nome, funcionarioService.stringParaLocalDate("15/07/1975"), new BigDecimal(5000.0), "Gerente");
        funcionarioService.updateFuncionario(funcionario);
        var funcionarios = funcionarioService.listarFuncionarios();
        for (var funcionario1 : funcionarios) {
            if (funcionario1.getNome().equals(nome)) {
                assertEquals(funcionario1.getNome(), funcionario.getNome());
                assertEquals(funcionario1.getDataNascimento(), funcionario.getDataNascimento());
                assertEquals(funcionario1.getSalario(), funcionario.getSalario());
                assertEquals(funcionario1.getFuncao(), funcionario.getFuncao());
            }
        }

    }*/

    @Test
    public void testListarFuncionarios() {
        var funcionarios = funcionarioService.listarFuncionarios();
        assertNotNull(funcionarios);
    }

    @Test
    public void testAgruparFuncionariosPorFuncao() {
        funcionarioService.agruparFuncionariosPorFuncao();
        var funcionariosPorFuncao = funcionarioService.getFuncionariosPorFuncao();
        assertNotNull(funcionariosPorFuncao);
    }

    /*@Test
    public void testFuncionarioMaiorIdade() {
        var funcionario = funcionarioService.funcionarioMaiorIdade();
        assertNotNull(funcionario);
    }*/

    @Test
    public void testListaPorOrdemAlfabetica() {
        var funcionarios = funcionarioService.listaPorOrdemAlfabetica();
        assertNotNull(funcionarios);
    }

    @Test
    public void testGetSalarios() {
        var salarios = funcionarioService.getSalarios();
        assertNotNull(salarios);
    }

    @Test
    public void testStringParaLocalDate() {
        var data = "15/07/1975";
        var localDate = funcionarioService.stringParaLocalDate(data);
        assertNotNull(localDate);
    }

    @Test(expected = DateTimeParseException.class)
    public void testStringParaLocalDateInvalida() {
        var data = "15-07-1975";
        var localDate = funcionarioService.stringParaLocalDate(data);
        assertNotNull(localDate);
    }


}