package com.projedata.testepraticoiniflex.controller;

import com.projedata.testepraticoiniflex.dto.DetalheIdade;
import com.projedata.testepraticoiniflex.dto.DetalheSalarioMinimo;
import com.projedata.testepraticoiniflex.dto.FuncionarioFormatado;
import com.projedata.testepraticoiniflex.model.Funcionario;
import com.projedata.testepraticoiniflex.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;


    @PostMapping("/preencheFuncionarios")
    public ResponseEntity<String> preencherFuncionarios() {
        try {
            funcionarioService.preencherFuncionarios();
            return ResponseEntity.ok("Funcionários preenchidos com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao preencher funcionários.");
        }
    }

    @DeleteMapping("/removeFuncionario")
    public ResponseEntity<String> removerFuncionario(@RequestParam String funcionarioNome) {
        try {
            funcionarioService.removerFuncionarioJoao(funcionarioNome);
            return ResponseEntity.ok("Funcionário removido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover funcionário.");
        }
    }

    @GetMapping("/listaFuncionarioFormatado")
    public ResponseEntity<List<FuncionarioFormatado>> listaFuncionarioFormatado() {
        try {
            return ResponseEntity.ok(funcionarioService.listaFuncionarioFormatado());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/atualizaSalario10PorCentoTodosFuncionarios")
    public ResponseEntity<String> atualizarSalario10PorCentoTodosFuncionarios() {
        try {
            funcionarioService.atualizarSalario10PorCentoTodosFuncionarios();
            return ResponseEntity.ok("Salários atualizados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar salários.");
        }
    }

    @PutMapping("/agrupaFuncionariosPorFuncao")
    public ResponseEntity<String> agruparFuncionariosPorFuncao() {
        try {
            funcionarioService.agruparFuncionariosPorFuncao();
            return ResponseEntity.ok("Funcionários agrupados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao agrupar funcionários.");
        }
    }

    @GetMapping("/funcionariosAgrupadosPorFuncao")
    public ResponseEntity<Map<String, List<Funcionario>>> funcionariosAgrupadosPorFuncao() {
        try {
            return ResponseEntity.ok(funcionarioService.funcionariosAgrupadosPorFuncao());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/aniversariantesDoMesOutubroEDezembro")
    public ResponseEntity<List<Funcionario>> aniversariantesDoMesOutubroEDezembro() {
        try {
            return ResponseEntity.ok(funcionarioService.aniversariantesDoMesOutubroEDezembro());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/funcionarioMaiorIdade")
    public ResponseEntity<DetalheIdade> funcionarioMaiorIdade() {
        try {
            return ResponseEntity.ok(funcionarioService.funcionarioMaiorIdade());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/listaPorOrdemAlfabetica")
    public ResponseEntity<List<Funcionario>> listaPorOrdemAlfabetica() {
        try {
            return ResponseEntity.ok(funcionarioService.listaPorOrdemAlfabetica());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/somaSalarioTodosFuncionarios")
    public ResponseEntity<String> somaSalarioTodosFuncionarios() {
        try {
            return ResponseEntity.ok(funcionarioService.somaSalarioTodosFuncionarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/listaFuncionarioSalarioMinimo")
    public ResponseEntity<List<DetalheSalarioMinimo>> litaFuncionarioSalarioMinimo() {
        try {
            return ResponseEntity.ok(funcionarioService.listaFuncionarioSalarioMinimo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
