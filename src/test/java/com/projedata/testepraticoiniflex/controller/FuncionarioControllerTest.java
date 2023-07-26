package com.projedata.testepraticoiniflex.controller;

import com.projedata.testepraticoiniflex.dto.DetalheIdade;
import com.projedata.testepraticoiniflex.dto.DetalheSalarioMinimo;
import com.projedata.testepraticoiniflex.dto.FuncionarioFormatado;
import com.projedata.testepraticoiniflex.model.Funcionario;
import com.projedata.testepraticoiniflex.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FuncionarioControllerTest {

    @Mock
    private FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPreencherFuncionarios() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Funcionários preenchidos com sucesso.");

        doNothing().when(funcionarioService).preencherFuncionarios();

        ResponseEntity<String> actualResponse = funcionarioController.preencherFuncionarios();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).preencherFuncionarios();
    }

    @Test
    public void testRemoverFuncionario() {
        String funcionarioNome = "João";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Funcionário removido com sucesso.");

        doNothing().when(funcionarioService).removerFuncionarioJoao(funcionarioNome);

        ResponseEntity<String> actualResponse = funcionarioController.removerFuncionario(funcionarioNome);

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).removerFuncionarioJoao(funcionarioNome);
    }

    @Test
    public void testListaFuncionarioFormatado() {
        List<FuncionarioFormatado> funcionariosFormatados = new ArrayList<>();
        ResponseEntity<List<FuncionarioFormatado>> expectedResponse = ResponseEntity.ok(funcionariosFormatados);

        when(funcionarioService.listaFuncionarioFormatado()).thenReturn(funcionariosFormatados);

        ResponseEntity<List<FuncionarioFormatado>> actualResponse = funcionarioController.listaFuncionarioFormatado();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).listaFuncionarioFormatado();
    }

    @Test
    public void testAtualizarSalario10PorCentoTodosFuncionarios() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Salários atualizados com sucesso.");

        doNothing().when(funcionarioService).atualizarSalario10PorCentoTodosFuncionarios();

        ResponseEntity<String> actualResponse = funcionarioController.atualizarSalario10PorCentoTodosFuncionarios();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).atualizarSalario10PorCentoTodosFuncionarios();
    }

    @Test
    public void testAgruparFuncionariosPorFuncao() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Funcionários agrupados com sucesso.");

        doNothing().when(funcionarioService).agruparFuncionariosPorFuncao();

        ResponseEntity<String> actualResponse = funcionarioController.agruparFuncionariosPorFuncao();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).agruparFuncionariosPorFuncao();
    }

    @Test
    public void testFuncionariosAgrupadosPorFuncao() {
        Map<String, List<Funcionario>> funcionariosAgrupados = new HashMap<>();
        ResponseEntity<Map<String, List<Funcionario>>> expectedResponse = ResponseEntity.ok(funcionariosAgrupados);

        when(funcionarioService.funcionariosAgrupadosPorFuncao()).thenReturn(funcionariosAgrupados);

        ResponseEntity<Map<String, List<Funcionario>>> actualResponse = funcionarioController.funcionariosAgrupadosPorFuncao();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).funcionariosAgrupadosPorFuncao();
    }

    @Test
    public void testAniversariantesDoMesOutubroEDezembro() {
        List<Funcionario> funcionariosAniversariantes = new ArrayList<>();
        ResponseEntity<List<Funcionario>> expectedResponse = ResponseEntity.ok(funcionariosAniversariantes);

        when(funcionarioService.aniversariantesDoMesOutubroEDezembro()).thenReturn(funcionariosAniversariantes);

        ResponseEntity<List<Funcionario>> actualResponse = funcionarioController.aniversariantesDoMesOutubroEDezembro();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).aniversariantesDoMesOutubroEDezembro();
    }

    @Test
    public void testFuncionarioMaiorIdade() {
        DetalheIdade detalheIdade = new DetalheIdade("João", 30);
        ResponseEntity<DetalheIdade> expectedResponse = ResponseEntity.ok(detalheIdade);

        when(funcionarioService.funcionarioMaiorIdade()).thenReturn(detalheIdade);

        ResponseEntity<DetalheIdade> actualResponse = funcionarioController.funcionarioMaiorIdade();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).funcionarioMaiorIdade();
    }

    @Test
    public void testListaPorOrdemAlfabetica() {
        List<Funcionario> funcionariosOrdenados = new ArrayList<>();
        ResponseEntity<List<Funcionario>> expectedResponse = ResponseEntity.ok(funcionariosOrdenados);

        when(funcionarioService.listaPorOrdemAlfabetica()).thenReturn(funcionariosOrdenados);

        ResponseEntity<List<Funcionario>> actualResponse = funcionarioController.listaPorOrdemAlfabetica();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).listaPorOrdemAlfabetica();
    }

    @Test
    public void testSomaSalarioTodosFuncionarios() {
        BigDecimal salarioTotal = new BigDecimal("5000.00");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(salarioTotal.toString());

        when(funcionarioService.somaSalarioTodosFuncionarios()).thenReturn(String.valueOf(salarioTotal));

        ResponseEntity<String> actualResponse = funcionarioController.somaSalarioTodosFuncionarios();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).somaSalarioTodosFuncionarios();
    }

    @Test
    public void testLitaFuncionarioSalarioMinimo() {
        List<DetalheSalarioMinimo> detalhesSalarioMinimo = new ArrayList<>();
        ResponseEntity<List<DetalheSalarioMinimo>> expectedResponse = ResponseEntity.ok(detalhesSalarioMinimo);

        when(funcionarioService.listaFuncionarioSalarioMinimo()).thenReturn(detalhesSalarioMinimo);

        ResponseEntity<List<DetalheSalarioMinimo>> actualResponse = funcionarioController.litaFuncionarioSalarioMinimo();

        assertEquals(expectedResponse, actualResponse);
        verify(funcionarioService, times(1)).listaFuncionarioSalarioMinimo();
    }
}
