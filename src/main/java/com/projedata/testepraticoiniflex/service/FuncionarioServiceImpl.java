package com.projedata.testepraticoiniflex.service;


import com.projedata.testepraticoiniflex.dto.DetalheIdade;
import com.projedata.testepraticoiniflex.dto.DetalheSalarioMinimo;
import com.projedata.testepraticoiniflex.dto.FuncionarioFormatado;
import com.projedata.testepraticoiniflex.mock.FuncionarioMockService;
import com.projedata.testepraticoiniflex.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

    @Autowired
    private FuncionarioMockService funcionarioMockService;

    @Override
    public void preencherFuncionarios() {
        funcionarioMockService.startFuncionarios();
    }

    @Override
    public void removerFuncionarioJoao(String funcionarioNome) {
        funcionarioMockService.removerFuncionarioPorNome(funcionarioNome);
    }

    @Override
    public void atualizarSalario10PorCentoTodosFuncionarios() {
        var funcionarios = funcionarioMockService.listarFuncionarios();
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.1"));
            funcionario.setSalario(salarioAtual.add(aumento));
        });
    }

    @Override
    public Map<String, List<Funcionario>> funcionariosAgrupadosPorFuncao() {
        return funcionarioMockService.getFuncionariosPorFuncao();
    }

    @Override
    public void agruparFuncionariosPorFuncao() {
        funcionarioMockService.agruparFuncionariosPorFuncao();
    }



    @Override
    public List<FuncionarioFormatado> listaFuncionarioFormatado() {
        var funcionarios = funcionarioMockService.listarFuncionarios();
        var funcionariosFormatados = new ArrayList<FuncionarioFormatado>();
        funcionarios.forEach(funcionario -> {
            var funcionarioFormatado = new FuncionarioFormatado(
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    funcionario.getFuncao(),
                    formatarValorNumerico(funcionario.getSalario()));
            funcionariosFormatados.add(funcionarioFormatado);
        });
        return funcionariosFormatados;

    }

    @Override
    public List<Funcionario> aniversariantesDoMesOutubroEDezembro() {
       var funcionarios = funcionarioMockService.listarFuncionarios();
       var funcionariosAniversariantes = new ArrayList<Funcionario>();
       funcionarios.stream().filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12).forEach(funcionariosAniversariantes::add);
       return funcionariosAniversariantes;
    }

    @Override
    public DetalheIdade funcionarioMaiorIdade() {
        var funcionario = funcionarioMockService.funcionarioMaiorIdade();
        var detalheIdadeDto = new DetalheIdade(funcionario.getNome(), calcularIdade(funcionario.getDataNascimento()));
        return detalheIdadeDto;
    }

    @Override
    public List<Funcionario> listaPorOrdemAlfabetica() {
        return funcionarioMockService.listaPorOrdemAlfabetica();
    }

    @Override
    public String somaSalarioTodosFuncionarios() {
        var totalSalario = funcionarioMockService.getSalarios().stream().mapToDouble(BigDecimal::doubleValue).sum();
        return formatarValorNumerico(new BigDecimal(totalSalario));
    }

    @Override
    public List<DetalheSalarioMinimo> listaFuncionarioSalarioMinimo() {
        var funcionarios = funcionarioMockService.listarFuncionarios();
        var detalheSalarioMinimo = new ArrayList<DetalheSalarioMinimo>();
    funcionarios.forEach(funcionario -> {
                var salarioMinimo = new DetalheSalarioMinimo(
                        funcionario.getNome(),
                        funcionario.getDataNascimento(),
                        funcionario.getFuncao(),
                        funcionario.getSalario(),
                        funcionario.getSalario().divide(new BigDecimal("1212.00"), MathContext.DECIMAL128));
                detalheSalarioMinimo.add(salarioMinimo);
            });
    return detalheSalarioMinimo;
    }

    int calcularIdade(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNascimento, dataAtual).getYears();
    }

    String formatarValorNumerico(BigDecimal valor) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(valor);
    }

}
