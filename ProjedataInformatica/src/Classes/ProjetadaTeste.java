package Classes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class ProjetadaTeste {
	
	
	public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

       
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Analsita BI"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Coordenador de T.I"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Aline", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Adriano Froes", LocalDate.of(1985, Month.MARCH, 9), new BigDecimal("4071.84"), "Futuro Programador Jr, na Projedata"));
        funcionarios.add(new Funcionario("Joaquin", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Edivania", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Esteticista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal("2799.93"), "Gerente"));

       
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        
        System.out.println("Funcionários cadastrados:");
        funcionarios.forEach(System.out::println);

      
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        });

              Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

             System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(System.out::println);
        });

       
        System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12:");
        funcionarios.stream()
            .filter(funcionario -> {
                Month mes = funcionario.getDataNascimento().getMonth();
                return mes == Month.OCTOBER || mes == Month.DECEMBER;
            })
            .forEach(System.out::println);

      
        Optional<Funcionario> funcionarioMaisVelho = funcionarios.stream()
            .min(Comparator.comparing(Funcionario::getDataNascimento));
        if (funcionarioMaisVelho.isPresent()) {
            Funcionario maisVelho = funcionarioMaisVelho.get();
            int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.println("\nFuncionário com a maior idade:");
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idade);
        }

               System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .forEach(System.out::println);

   
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + String.format("%,.2f", totalSalarios));

      
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, BigDecimal.ROUND_DOWN);
            System.out.println("Nome: " + funcionario.getNome() + ", Salários mínimos: " + quantidadeSalariosMinimos);
        });
    }
	
	

}
