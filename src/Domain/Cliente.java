package Domain;

import java.util.List;

public class Cliente {

    private String name;
    private String cpf;
    private String endereco;

    public Cliente(String name, String cpf, String endereco) {
        this.name = name;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static List<Cliente> alterarCliente(List<Cliente> clientes, Cliente cliente){

        for(int i= 0; i < clientes.size(); i++){

            if(clientes.get(i).getCpf() == cliente.getCpf()){

                clientes.get(i).setName(cliente.getName());
                clientes.get(i).setEndereco(cliente.getEndereco());
                System.out.println("Cliente atualizado com sucesso!");
            }
        }
        return clientes;
    }

    public static List<Cliente> cadastrarCliente(List clientes, Cliente cliente){

        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");

        return clientes;
    }

    public static List<Cliente> listarClientes(List<Cliente> clientes){

        for(int i = 0; i < clientes.size(); i++){

            System.out.println("Nome do cliente: "+ clientes.get(i).getName());
            System.out.println("Cpf do cliente: "+ clientes.get(i).getCpf());
            System.out.println("Endereço do cliente: "+ clientes.get(i).getEndereco());
            System.out.println("-------------------------------------------------------------------");
        }

        return clientes;
    }

    public static Cliente buscaClienteCpf(String cpf, List<Cliente> clientes){

        Cliente cliente = clientes.stream().filter(p -> p.getCpf().equals(cpf)).findAny().orElse(null);

        System.out.println("Nome do cliente: "+ cliente.getName());
        System.out.println("Cpf do cliente: "+ cliente.getCpf());
        System.out.println("Endereço do cliente: "+ cliente.getEndereco());
        System.out.println("-------------------------------------------------------------------");

        if(cliente == null){
            throw new RuntimeException("Não existe cliente com esse cpf!");
        }

        return cliente;
    }

    public static List<Cliente> removeCliente(List<Cliente> clientes, Cliente cliente){

        for(int i = 0; i < clientes.size(); i++) {

            Cliente clienteRemove = clientes.get(i);

            if(clienteRemove.equals(cliente)){

                clientes.remove(clienteRemove);

                System.out.print("Cliente removido com sucesso!");

                break;
            }
        }
        return clientes;
    }
}
