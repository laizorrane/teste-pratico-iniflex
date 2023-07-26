package com.projedata.testepraticoiniflex.service;

import com.projedata.testepraticoiniflex.dto.DetalheIdade;
import com.projedata.testepraticoiniflex.dto.DetalheSalarioMinimo;
import com.projedata.testepraticoiniflex.dto.FuncionarioFormatado;
import com.projedata.testepraticoiniflex.mock.FuncionarioMockService;
import com.projedata.testepraticoiniflex.mock.FuncionarioMockServiceImpl;
import com.projedata.testepraticoiniflex.model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FuncionarioServiceImplTest {

    @Mock
    private FuncionarioMockServiceImpl funcionarioMockService;

    @InjectMocks
    private FuncionarioServiceImpl funcionarioServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPreencherFuncionarios() {
        funcionarioServiceImpl.preencherFuncionarios();
        verify(funcionarioMockService, times(1)).startFuncionarios();
    }

    @Test
    public void testRemoverFuncionarioJoao() {
        var funcionarioNome = "João";
        funcionarioServiceImpl.removerFuncionarioJoao(funcionarioNome);
        verify(funcionarioMockService, times(1)).removerFuncionarioPorNome(funcionarioNome);
    }

    @Test
    public void testAtualizarSalario10PorCentoTodosFuncionarios() {
        var funcionarios = criarListaFuncionarios();
        when(funcionarioMockService.listarFuncionarios()).thenReturn(funcionarios);

        funcionarioServiceImpl.atualizarSalario10PorCentoTodosFuncionarios();

        funcionarios.forEach(funcionario -> {
            var salarioAtual = funcionario.getSalario();
            var aumento = salarioAtual.multiply(new BigDecimal("0.1"));
            var salarioEsperado = salarioAtual.add(aumento);
            assertEquals(salarioEsperado, funcionario.getSalario().add(aumento));
        });

        verify(funcionarioMockService, times(1)).listarFuncionarios();
        verifyNoMoreInteractions(funcionarioMockService);
    }

    @Test
    public void testFuncionariosAgrupadosPorFuncao() {
        var funcionariosPorFuncao = criarMapaFuncionariosPorFuncao();
        when(funcionarioMockService.getFuncionariosPorFuncao()).thenReturn(funcionariosPorFuncao);

        var result = funcionarioServiceImpl.funcionariosAgrupadosPorFuncao();

        assertEquals(funcionariosPorFuncao, result);
        verify(funcionarioMockService, times(1)).getFuncionariosPorFuncao();
    }

    @Test
    public void testListaFuncionarioFormatado() {
        var funcionarios = criarListaFuncionarios();
        when(funcionarioMockService.listarFuncionarios()).thenReturn(funcionarios);

        var result = funcionarioServiceImpl.listaFuncionarioFormatado();

        var funcionariosFormatados = criarListaFuncionariosFormatados();
        assertEquals(funcionariosFormatados, result);
        verify(funcionarioMockService, times(1)).listarFuncionarios();
    }

    @Test
    public void testAniversariantesDoMesOutubroEDezembro() {
        var funcionarios = criarListaFuncionarios();
        when(funcionarioMockService.listarFuncionarios()).thenReturn(funcionarios);

        var result = funcionarioServiceImpl.aniversariantesDoMesOutubroEDezembro();

        var aniversariantes = criarListaAniversariantes();
        assertEquals(aniversariantes, result);
        verify(funcionarioMockService, times(1)).listarFuncionarios();
    }

    @Test
    public void testFuncionarioMaiorIdade() {
        when(funcionarioMockService.funcionarioMaiorIdade()).thenReturn(criarFuncionarioMaiorIdade());

        var result = funcionarioServiceImpl.funcionarioMaiorIdade();

        var detalheIdadeEsperado = criarDetalheIdade();
        assertEquals(detalheIdadeEsperado, result);
        verify(funcionarioMockService, times(1)).funcionarioMaiorIdade();
    }

    @Test
    public void testListaPorOrdemAlfabetica() {
        var funcionarios = criarListaFuncionarios();
        when(funcionarioMockService.listaPorOrdemAlfabetica()).thenReturn(funcionarios);

        var result = funcionarioServiceImpl.listaPorOrdemAlfabetica();

        assertEquals(funcionarios, result);
        verify(funcionarioMockService, times(1)).listaPorOrdemAlfabetica();
    }

    @Test
    public void testSomaSalarioTodosFuncionarios() {
        var salarios = criarListaSalarios();
        when(funcionarioMockService.getSalarios()).thenReturn(salarios);

        var somaSalario = funcionarioServiceImpl.somaSalarioTodosFuncionarios();

        var somaSalarioEsperado = "48.563,31";
        assertEquals(somaSalarioEsperado, somaSalario);
        verify(funcionarioMockService, times(1)).getSalarios();
    }

    @Test
    public void testListaFuncionarioSalarioMinimo() {
        var funcionarios = criarListaFuncionarios();
        when(funcionarioMockService.listarFuncionarios()).thenReturn(funcionarios);

        var detalhesSalarioMinimo = funcionarioServiceImpl.listaFuncionarioSalarioMinimo();

        var detalhesSalarioMinimoEsperado = criarListaDetalhesSalarioMinimo();
        assertEquals(detalhesSalarioMinimoEsperado, detalhesSalarioMinimo);
        verify(funcionarioMockService, times(1)).listarFuncionarios();
    }

    private List<Funcionario> criarListaFuncionarios() {
        var funcionarios = new ArrayList<Funcionario>();
        funcionarios.add(new Funcionario("Maria", stringParaLocalDate("2000-10-18"), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", stringParaLocalDate("1990-05-12"), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", stringParaLocalDate("1961-05-02"), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", stringParaLocalDate("1988-10-14"), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", stringParaLocalDate("1995-01-05"), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", stringParaLocalDate("1999-11-19"), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", stringParaLocalDate("1993-03-31"), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", stringParaLocalDate("1994-07-08"), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", stringParaLocalDate("2003-05-24"), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", stringParaLocalDate("1996-09-02"), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }

    private Map<String, List<Funcionario>> criarMapaFuncionariosPorFuncao() {
        var funcionarios = criarListaFuncionarios();
        Map<String, List<Funcionario>> mapaFuncionariosPorFuncao = new HashMap<>();

        for (var funcionario : funcionarios) {
            var funcao = funcionario.getFuncao();
            if (!mapaFuncionariosPorFuncao.containsKey(funcao)) {
                mapaFuncionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            mapaFuncionariosPorFuncao.get(funcao).add(funcionario);
        }

        return mapaFuncionariosPorFuncao;
    }

    private List<FuncionarioFormatado> criarListaFuncionariosFormatados() {
        var funcionarios = criarListaFuncionarios();
        var funcionariosFormatados = new ArrayList<FuncionarioFormatado>();

        for (var funcionario : funcionarios) {
            var funcionarioFormatado = new FuncionarioFormatado(
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    funcionario.getFuncao(),
                    funcionarioServiceImpl.formatarValorNumerico(funcionario.getSalario()));
            funcionariosFormatados.add(funcionarioFormatado);
        }

        return funcionariosFormatados;
    }

    private List<Funcionario> criarListaAniversariantes() {
        var funcionarios = criarListaFuncionarios();
        var aniversariantes = new ArrayList<Funcionario>();

        for (var funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                aniversariantes.add(funcionario);
            }
        }

        return aniversariantes;
    }

    private Funcionario criarFuncionarioMaiorIdade() {
        var funcionarios = criarListaFuncionarios();
        var funcionarioMaiorIdade = funcionarios.get(0);

        for (var funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(funcionarioMaiorIdade.getDataNascimento())) {
                funcionarioMaiorIdade = funcionario;
            }
        }

        return funcionarioMaiorIdade;
    }

    private List<BigDecimal> criarListaSalarios() {
        var salarios = new ArrayList<BigDecimal>();
        salarios.add(new BigDecimal("2009.44"));
        salarios.add(new BigDecimal("2284.38"));
        salarios.add(new BigDecimal("9836.14"));
        salarios.add(new BigDecimal("19119.88"));
        salarios.add(new BigDecimal("2234.68"));
        salarios.add(new BigDecimal("1582.72"));
        salarios.add(new BigDecimal("4071.84"));
        salarios.add(new BigDecimal("3017.45"));
        salarios.add(new BigDecimal("1606.85"));
        salarios.add(new BigDecimal("2799.93"));
        return salarios;
    }

    private LocalDate stringParaLocalDate(String dateString) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

    private DetalheIdade criarDetalheIdade() {
        var funcionarios = criarListaFuncionarios();
        var funcionarioMaiorIdade = funcionarios.get(0);

        for (Funcionario funcionario : funcionarios) {
            if (funcionarioServiceImpl.calcularIdade(funcionario.getDataNascimento()) > funcionarioServiceImpl.calcularIdade(funcionarioMaiorIdade.getDataNascimento())) {
                funcionarioMaiorIdade = funcionario;
            }
        }

        int idadeMaior = funcionarioServiceImpl.calcularIdade(funcionarioMaiorIdade.getDataNascimento());
        return new DetalheIdade(funcionarioMaiorIdade.getNome(), idadeMaior);
    }

    private List<DetalheSalarioMinimo> criarListaDetalhesSalarioMinimo() {
        var funcionarios = criarListaFuncionarios();

        return funcionarios.stream()
                .map(funcionario -> {
                    BigDecimal salarioFuncionario = funcionario.getSalario();
                    BigDecimal qtdSalariosMinimos = salarioFuncionario.divide(new BigDecimal("1212.00"), MathContext.DECIMAL128);

                    return new DetalheSalarioMinimo(
                            funcionario.getNome(),
                            funcionario.getDataNascimento(),
                            funcionario.getFuncao(),
                            salarioFuncionario,
                            qtdSalariosMinimos
                    );
                })
                .collect(Collectors.toList());
    }

}
