package com.projedata.testepraticoiniflex.mock;

import com.projedata.testepraticoiniflex.model.Funcionario;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FuncionarioMockServiceImpl implements FuncionarioMockService{

    @Override
    public void startFuncionarios() {
        var funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", stringParaLocalDate("18/10/2000"), new BigDecimal(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", stringParaLocalDate("12/05/1990"), new BigDecimal(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", stringParaLocalDate("02/05/1961"), new BigDecimal(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", stringParaLocalDate("14/10/1988"), new BigDecimal(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", stringParaLocalDate("05/01/1995"), new BigDecimal(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", stringParaLocalDate("19/11/1999"), new BigDecimal(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", stringParaLocalDate("31/03/1993"), new BigDecimal(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", stringParaLocalDate("08/07/1994"), new BigDecimal(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", stringParaLocalDate("24/05/2003"), new BigDecimal(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", stringParaLocalDate("02/09/1996"), new BigDecimal(2799.93), "Gerente"));
    }

    @Override
    public void removerFuncionarioPorNome(String nome) {
        FuncionarioMock.removerFuncionario(FuncionarioMock.getFuncionarioPorNome(nome));

    }

    @Override
    public void updateFuncionario(Funcionario funcionario) {
        FuncionarioMock.updateFuncionario(funcionario);
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return FuncionarioMock.getFuncionarios();
    }

    @Override
    public void agruparFuncionariosPorFuncao(){
        FuncionarioMock.agruparFuncionariosPorFuncao();
    }

    @Override
    public Map<String, List<Funcionario>> getFuncionariosPorFuncao(){
        return FuncionarioMock.getFuncionariosPorFuncao();
    }

    @Override
    public Funcionario funcionarioMaiorIdade(){
        return FuncionarioMock.funcionarioMaiorIdade();
    }

    @Override
    public List<Funcionario> listaPorOrdemAlfabetica() {
        return FuncionarioMock.listaPorOrdemAlfabetica();
    }

    @Override
    public List<BigDecimal> getSalarios() {
        return FuncionarioMock.getSalarios();
    }

    public LocalDate stringParaLocalDate(String dateString) throws DateTimeParseException {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

}
