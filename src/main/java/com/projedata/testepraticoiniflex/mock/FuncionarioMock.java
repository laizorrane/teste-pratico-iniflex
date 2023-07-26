package com.projedata.testepraticoiniflex.mock;

import com.projedata.testepraticoiniflex.model.Funcionario;
import com.projedata.testepraticoiniflex.model.Pessoa;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioMock {

    private static final List<Funcionario> funcionarios = new ArrayList<>();
    private static final Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

    public static List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public static Funcionario getFuncionarioPorNome(String nome){
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    public static void updateFuncionario(Funcionario funcionario){
        Funcionario funcionarioAtualizado = getFuncionarioPorNome(funcionario.getNome());
        funcionarioAtualizado.setNome(funcionario.getNome());
        funcionarioAtualizado.setDataNascimento(funcionario.getDataNascimento());
        funcionarioAtualizado.setFuncao(funcionario.getFuncao());
        funcionarioAtualizado.setSalario(funcionario.getSalario());
    }

    public static void addFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

    public static void removerFuncionario(Funcionario funcionario){
        funcionarios.remove(funcionario);
    }

    public static void agruparFuncionariosPorFuncao(){
        funcionariosPorFuncao.putAll(
                funcionarios.stream()
                        .collect(Collectors.groupingBy(Funcionario::getFuncao))
        );
    }

    public static Map<String, List<Funcionario>> getFuncionariosPorFuncao() {
        return funcionariosPorFuncao;
    }

    public static Funcionario funcionarioMaiorIdade(){
        return funcionarios.stream()
                .max(Comparator.comparing(Pessoa::getDataNascimento))
                .orElse(null);
    }

    public static List<Funcionario> listaPorOrdemAlfabetica() {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .collect(Collectors.toList());
    }

    public static List<BigDecimal> getSalarios() {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .collect(Collectors.toList());
    }

}
