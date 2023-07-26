package com.projedata.testepraticoiniflex.service;

import com.projedata.testepraticoiniflex.dto.DetalheIdade;
import com.projedata.testepraticoiniflex.dto.DetalheSalarioMinimo;
import com.projedata.testepraticoiniflex.dto.FuncionarioFormatado;
import com.projedata.testepraticoiniflex.model.Funcionario;

import java.util.List;
import java.util.Map;

public interface FuncionarioService {

    void preencherFuncionarios();
    void removerFuncionarioJoao(String funcionarioNome);
    List<FuncionarioFormatado> listaFuncionarioFormatado();
    void atualizarSalario10PorCentoTodosFuncionarios();
    void agruparFuncionariosPorFuncao();
    Map<String, List<Funcionario>> funcionariosAgrupadosPorFuncao();
    List<Funcionario> aniversariantesDoMesOutubroEDezembro();
    DetalheIdade funcionarioMaiorIdade();
    List<Funcionario> listaPorOrdemAlfabetica();
    String somaSalarioTodosFuncionarios();
    List<DetalheSalarioMinimo> listaFuncionarioSalarioMinimo();

}
