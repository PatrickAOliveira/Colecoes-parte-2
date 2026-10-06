import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.poliveira.classes.Pessoa;

/**
 * @author PatrickAOliveira
 *         App
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // 1. Função para cadastrar pessoas
        List<Pessoa> pessoas = cadastrarPessoas(scanner);

        // 2. Função para filtrar por grupos de sexo
        List<Pessoa> gMasculino = filtrarPorSexo(pessoas, "Masculino");
        List<Pessoa> gFeminino = filtrarPorSexo(pessoas, "Feminino");

        // 3. Exibe o resultado na tela
        exibirResultados(gMasculino, gFeminino);

        scanner.close();
    }

    /**
     * Função responsável por realizar o loop de leitura e cadastro das pessoas.
     * 
     * @param scanner
     * @return
     */
    private static List<Pessoa> cadastrarPessoas(Scanner scanner) {
        List<Pessoa> lista = new ArrayList<>();
        System.out.println("=== Cadastro de Pessoas ===");

        while (true) {
            System.out.print("\nDigite o nome (ou 'sair' para encerrar): ");
            String nome = scanner.nextLine().trim();

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            String sexo = lerSexoValido(scanner);
            Pessoa pessoa = new Pessoa(nome, sexo);
            lista.add(pessoa);

            System.out.println("-> " + pessoa.getNome() + " cadastrado(a) com sucesso como " + pessoa.getSexo() + ".");
        }

        return lista;
    }

    /**
     * Função auxiliar para validar a entrada do sexo (garante retorno "Masculino"
     * ou "Feminino").
     * 
     * @param scanner
     * @return
     */
    private static String lerSexoValido(Scanner scanner) {
        while (true) {
            System.out.print("Digite o sexo (M/F): ");
            String entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.equals("M")) {
                return "Masculino";
            } else if (entrada.equals("F")) {
                return "Feminino";
            } else {
                System.out.println("Opção inválida! Por favor, digite M para Masculino ou F para Feminino.");
            }
        }
    }

    /**
     * Função genérica para filtrar a lista principal com base no sexo desejado.
     * 
     * @param pessoas
     * @param grupo
     * @return
     */
    private static List<Pessoa> filtrarPorSexo(List<Pessoa> pessoas, String grupo) {
        List<Pessoa> filtrados = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p.getSexo().equalsIgnoreCase(grupo)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    /**
     * Função responsável por formatar e imprimir os grupos finais no console.
     * 
     * @param masculino
     * @param feminino
     */
    private static void exibirResultados(List<Pessoa> masculino, List<Pessoa> feminino) {
        System.out.println("\n=================================");
        System.out.println("         RESULTADO FINAL         ");
        System.out.println("=================================");

        imprimirGrupo("Grupo Masculino", masculino);
        imprimirGrupo("Grupo Feminino", feminino);
    }

    /**
     * Função auxiliar para imprimir um grupo específico de pessoas.
     * 
     * @param titulo
     * @param grupo
     */
    private static void imprimirGrupo(String titulo, List<Pessoa> grupo) {
        System.out.println("\n" + titulo + " (" + grupo.size() + "):");
        if (grupo.isEmpty()) {
            System.out.println("  (Nenhum registro)");
        } else {
            for (Pessoa p : grupo) {
                System.out.println("  - " + p.getNome());
            }
        }
    }
}
