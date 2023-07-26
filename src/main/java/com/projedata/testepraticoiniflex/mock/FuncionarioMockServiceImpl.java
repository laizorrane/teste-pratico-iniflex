package com.projedata.testepraticoiniflex.mock;

import com.projedata.testepraticoiniflex.model.Funcionario;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class FuncionarioMockServiceImpl implements FuncionarioMockService{

    @Override
    public void startFuncionarios() {

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

}
