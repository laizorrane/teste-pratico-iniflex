package com.projedata.testepraticoiniflex.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetalheSalarioMinimo(String nome,
                                   LocalDate dataNascimento,
                                   String funcao,
                                   BigDecimal salario,
                                   BigDecimal qtdeSalarioMinimo){
}
