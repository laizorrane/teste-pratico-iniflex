package com.projedata.testepraticoiniflex.mock;

import com.projedata.testepraticoiniflex.model.Funcionario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FuncionarioMockService {

    void startFuncionarios();
    void removerFuncionarioPorNome(String nome);
    void updateFuncionario(Funcionario funcionario);
    List<Funcionario> listarFuncionarios();
    void agruparFuncionariosPorFuncao();
    Map<String, List<Funcionario>> getFuncionariosPorFuncao();
    Funcionario funcionarioMaiorIdade();
    List<Funcionario> listaPorOrdemAlfabetica();
    List<BigDecimal> getSalarios();


    }
