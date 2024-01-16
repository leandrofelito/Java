import java.util.Scanner;

public class Main{

    static final int TAM = 5;  // ALTERA A QUANTIDADE DE PESSOAS QUE DESEJA CADASTRAR

    static class Cadastro {
        String nome;
        String cpf;
        String data;
        String vacina;
        int numeroLote;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cadastro[] pacientes = new Cadastro[TAM];
        int op;

        for (int i = 0; i < TAM; i++) {
            pacientes[i] = new Cadastro();
        }

        do {
            System.out.println("==== MENU DE OPÇÕES ====");
            System.out.println("1- Cadastrar Vacina");
            System.out.println("2- Listar aplicações");
            System.out.println("3- Consultar por CPF");
            System.out.println("4- Sair");
            System.out.print("Selecione a opção desejada: ");
            
            if (scanner.hasNextInt()) {
                op = scanner.nextInt();
                scanner.nextLine();  // Consome a quebra de linha deixada pelo nextInt()

                switch (op) {
                    case 1:
                        cadastrarVacina(pacientes, scanner);
                        break;
                    case 2:
                        listarAplicacoes(pacientes);
                        break;
                    case 3:
                        consultarPorCpf(pacientes, scanner);
                        break;
                    case 4:
                        System.out.println("Saindo do programa. Obrigado!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.nextLine();  // Consome a entrada inválida
                op = 0;  // Definir opção como 0 para continuar o loop
            }

        } while (op != 4);
    }

    private static void cadastrarVacina(Cadastro[] pacientes, Scanner scanner) {
        System.out.println("*****CADASTRO DE VACINAÇÃO*****");
        for (int i = 0; i < TAM; i++) {
            System.out.println("\nCódigo: " + (i + 1));
            System.out.print("Nome: ");
            pacientes[i].nome = scanner.nextLine();

            System.out.print("CPF: ");
            pacientes[i].cpf = scanner.nextLine();

            System.out.print("Vacina: ");
            pacientes[i].vacina = scanner.nextLine();

            System.out.print("Data: ");
            pacientes[i].data = scanner.nextLine();

            System.out.print("Número do lote: ");
            pacientes[i].numeroLote = scanner.nextInt();
            scanner.nextLine();  

            if (i < TAM - 1) {
                System.out.print("Deseja cadastrar mais pessoas? (S/N): ");
                String continuar = scanner.nextLine();
                if (!continuar.equalsIgnoreCase("S")) {
                    break;
                }
            }
        }
    }

    private static void listarAplicacoes(Cadastro[] pacientes) {
        System.out.println("*****LISTA DE APLICAÇÕES*****");
        boolean nenhumCadastrado = true;

        for (int i = 0; i < TAM; i++) {
            if (pacientes[i].nome != null) {
                System.out.println("\nCódigo: " + (i + 1));
                System.out.println("Nome: " + pacientes[i].nome);
                System.out.println("CPF: " + pacientes[i].cpf);
                System.out.println("Vacina: " + pacientes[i].vacina);
                System.out.println("Data da aplicação: " + pacientes[i].data);
                System.out.println("Número do lote: " + pacientes[i].numeroLote);
                System.out.println("====================");
                nenhumCadastrado = false;
            }
        }

        if (nenhumCadastrado) {
            System.out.println("Nenhum usuário cadastrado.");
        }
    }

    private static void consultarPorCpf(Cadastro[] pacientes, Scanner scanner) {
        System.out.println("*****CONSULTA DE CPF*****");
        System.out.print("Digite o CPF que deseja consultar: ");
        String consultaCpf = scanner.nextLine();

        int i = 0;
        boolean encontrado = false;

        while (i < TAM && !encontrado) {
            if (pacientes[i].nome != null && pacientes[i].cpf.equals(consultaCpf)) {
                encontrado = true;
            } else {
                i++;
            }
        }

        if (encontrado) {
            System.out.println("\nCódigo: " + (i + 1));
            System.out.println("Nome: " + pacientes[i].nome);
            System.out.println("CPF: " + pacientes[i].cpf);
            System.out.println("Nome da vacina: " + pacientes[i].vacina);
            System.out.println("Data da aplicação: " + pacientes[i].data);
            System.out.println("Número do lote: " + pacientes[i].numeroLote);
        } else {
            System.out.println("\nCPF não encontrado!");
        }
    }
}
