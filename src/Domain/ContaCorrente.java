package Domain;

import java.util.List;

public class ContaCorrente {

    private Long numero;
    private Cliente titular;
    private Double saldo = 0.0;

    public ContaCorrente(Long numero, Cliente titular, Double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public Boolean sacar(Double valor){

        if(this.saldo >= valor){
            this.saldo = this.saldo - valor;
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean depositar(Double valor){

        if(valor != null && valor >= 0){
            this.saldo = this.saldo + valor;
            return true;
        }
        else{
            return false;
        }
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public static List cadastrarContaCorrente(List clientes, List<ContaCorrente> contaCorrentes, ContaCorrente contaCorrente){

        Cliente validateCliente = Cliente.buscaClienteCpf(contaCorrente.getTitular().getCpf(), clientes);

        if(validateCliente != null) {

            contaCorrentes.add(contaCorrente);

            System.out.println("Conta Corrente cadastrada com sucesso!");

        }
        return contaCorrentes;
    }

    public static List listarContaCorrente(List<ContaCorrente> contaCorrentes){

        for(int i = 0; i < contaCorrentes.size(); i++){

            System.out.println("Número da conta corrente: "+ contaCorrentes.get(i).getNumero());
            System.out.println("Titular da conta corrente: "+ contaCorrentes.get(i).getTitular().getName());
            System.out.println("Saldo da conta corrente: "+ contaCorrentes.get(i).getSaldo());
            System.out.println("-------------------------------------------------------------------");
        }

        return contaCorrentes;
    }

    public static ContaCorrente buscaContaCorrenteNumero(List<ContaCorrente> contaCorrentes, Long numero){

        ContaCorrente contaCorrente = contaCorrentes.stream().filter(p -> p.getNumero().equals(numero)).findAny().orElse(null);

        if(contaCorrente == null){
            throw new RuntimeException("Não existe conta corrente com esse numero!");
        }

        else{
            System.out.println("Número da conta corrente: "+ contaCorrente.getNumero());
            System.out.println("Titular da conta corrente: "+ contaCorrente.getTitular().getName());
            System.out.println("Saldo da conta corrente: "+ contaCorrente.getSaldo());
            System.out.println("-------------------------------------------------------------------");
        }

        return contaCorrente;
    }

    public static List<ContaCorrente> depositaContaCorrente(List<ContaCorrente> contaCorrentes, Long numero, Double valor){

        for(int i = 0; i < contaCorrentes.size(); i++){

            if(contaCorrentes.get(i).getNumero() == numero){

                Boolean status = contaCorrentes.get(i).depositar(valor);

                if(status == true) {
                    System.out.println("Valor depositado com sucesso!");
                    System.out.println("Saldo disponível: " + contaCorrentes.get(i).getSaldo());
                }
                if(status != true){
                    System.out.println("Não foi possível depositar o valor na conta!");
                }
            }

        }
        return contaCorrentes;
    }
    public static List<ContaCorrente> sacaContaCorrente(List<ContaCorrente> contaCorrentes, Long numero, Double valor){

        for(int i = 0; i < contaCorrentes.size(); i++){

            if(contaCorrentes.get(i).getNumero() == numero){

                Boolean status = contaCorrentes.get(i).sacar(valor);

                if(status == true) {
                    System.out.println("Valor sacado com sucesso!");
                    System.out.println("Saldo disponível: " + contaCorrentes.get(i).getSaldo());
                }
                if(status != true){
                    System.out.println("Saldo insuficiente para ser sacado!");
                }
            }

        }
        return contaCorrentes;
    }
    public static List<ContaCorrente> removeContaCorrente(List<ContaCorrente> contaCorrentes, Long numero) {

        for (int i = 0; i < contaCorrentes.size(); i++) {

            ContaCorrente contaCorrenteRemove = contaCorrentes.get(i);

            if (contaCorrenteRemove.getNumero().equals(numero)) {

                contaCorrentes.remove(contaCorrenteRemove);

                System.out.println("Conta corrente foi removida com sucesso!");

                break;
            }
        }
        return contaCorrentes;
    }
}
