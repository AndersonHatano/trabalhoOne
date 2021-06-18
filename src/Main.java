import Domain.Cliente;
import Domain.ContaCorrente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<ContaCorrente> contaCorrentes = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        String textoPadrao = "O que você deseja fazer?\n1- cadastrar um cliente\n2- listar todos os clientes\n" +
                "3- buscar cliente pelo cpf\n4- alterar os dados de um cliente\n5- remover um cliente pelo cpf\n" +
                "6- cadastrar uma conta corrente\n7- listar todas as contas correntes\n" +
                "8- buscar uma conta corrente pelo número\n9- depositar valor numa conta\n10- sacar valor de uma conta\n" +
                "11- remover uma conta\n12- sair do sistema\nDigite o número da operação desejada:";

        System.out.println(textoPadrao);

        Scanner input = new Scanner(System.in);

        int operador = 0;

        operador = input.nextInt();

        while (operador != 12) {

            String nome = "";
            String cpf = "";
            String endereco = "";

            Long numero = 0l;
            Cliente titular = null;
            Double saldo = 0.0;
            Double valor = 0.0;

            while (operador != 12)
                switch (operador) {

                    case 1:
                        System.out.print("Digite o nome do cliente: ");
                        nome = input.next();

                        System.out.print("Digite o cpf do cliente: ");
                        cpf = input.next();

                        System.out.print("Digite o endereço do cliente: ");
                        endereco = input.next();

                        System.out.println();

                        Cliente clienteCadastro = new Cliente(nome, cpf, endereco);

                        clientes = Cliente.cadastrarCliente(clientes, clienteCadastro);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 2:

                        clientes = Cliente.listarClientes(clientes);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 3:

                        System.out.print("Digite o cpf do cliente que deseja encontrar: ");
                        cpf = input.next();

                        Cliente clienteCpf = Cliente.buscaClienteCpf(cpf, clientes);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 4:

                        System.out.print("Digite o cpf da conta que vai atualizar: ");
                        cpf = input.next();

                        System.out.print("Qual o nome do cliente atualizado: ");
                        nome = input.next();

                        System.out.print("Qual o endereço do cliente atualizado: ");
                        endereco = input.next();

                        Cliente clienteAtualizar = Cliente.buscaClienteCpf(cpf, clientes);

                        clienteAtualizar.setName(nome);
                        clienteAtualizar.setEndereco(endereco);

                        clientes = Cliente.alterarCliente(clientes, clienteAtualizar);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 5:

                        System.out.print("Digite o cpf do cliente que deseja remover: ");
                        cpf = input.next();

                        Cliente clienteRemover = Cliente.buscaClienteCpf(cpf, clientes);

                        contaCorrentes = ContaCorrente.removeContaCorrentePorCliente(contaCorrentes, clienteRemover);

                        clientes = Cliente.removeCliente(clientes, clienteRemover);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 6:

                        System.out.print("Digite o numero da Conta Corrente: ");
                        numero = input.nextLong();

                        System.out.print("Digite o cpf do cliente que deseja criar a Conta Corrente: ");
                        cpf = input.next();
                        Cliente clienteCpfConta = Cliente.buscaClienteCpf(cpf, clientes);

                        System.out.print("Digite o saldo inicial da Conta Corrente: ");
                        saldo = input.nextDouble();

                        ContaCorrente contaCorrente = new ContaCorrente(numero, clienteCpfConta, saldo);

                        contaCorrentes = ContaCorrente.cadastrarContaCorrente(clientes, contaCorrentes, contaCorrente);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 7:

                        contaCorrentes = ContaCorrente.listarContaCorrente(contaCorrentes);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 8:

                        System.out.print("Digite o número da conta corrente que deseja encontrar: ");
                        numero = input.nextLong();

                        ContaCorrente contaCorrenteNumero = ContaCorrente.buscaContaCorrenteNumero(contaCorrentes, numero);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 9:

                        System.out.print("Digite o número da conta corrente que deseja depositar: ");
                        numero = input.nextLong();

                        System.out.print("Digite o valor que deseja depositar na conta corrente: ");
                        valor = input.nextDouble();

                        contaCorrentes = ContaCorrente.depositaContaCorrente(contaCorrentes, numero, valor);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 10:

                        System.out.print("Digite o número da conta corrente que deseja sacar: ");
                        numero = input.nextLong();

                        System.out.print("Digite o valor que deseja sacar na conta corrente: ");
                        valor = input.nextDouble();

                        contaCorrentes = ContaCorrente.sacaContaCorrente(contaCorrentes, numero, valor);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 11:

                        System.out.print("Digite o número da conta corrente que deseja remover: ");
                        numero = input.nextLong();

                        contaCorrentes = ContaCorrente.removeContaCorrente(contaCorrentes, numero);

                        System.out.println(textoPadrao);
                        operador = 0;
                        operador = input.nextInt();
                        break;

                    case 12:

                        break;
                }
        }
    }
}

