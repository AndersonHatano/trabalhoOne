package Domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ClienteTest {

    private Cliente anderson;
    private Cliente jorge;
    private Cliente maria;

    List<Cliente> clientes = new ArrayList<>();

    @Before
    public Cliente criaCliente(){

        this.anderson = new Cliente("anderson","28916255", "Rua Tal" );

        System.out.print(this.anderson.getName());

        return this.anderson;
    }

    @Before
    public List<Cliente> criaListaCliente(){

        clientes.add(criaCliente());

        this.jorge = new Cliente("jorge", "112554445", "Rua Embaixador");

        clientes.add(this.jorge);

        this.maria = new Cliente("maria", "855959597", "Rua Embaixador 3");

        clientes.add(this.maria);

        return clientes;
    }

    @Test
    public void cadastrarClientTest(){

        Cliente testeCliente = criaCliente();

        clientes = Cliente.cadastrarCliente(clientes, testeCliente);

        List<Cliente> validateCliente = Cliente.listarClientes(clientes);

        Assert.assertEquals(validateCliente.get(0).getName(), "anderson");
        Assert.assertEquals(validateCliente.get(0).getCpf(), "28916255");
        Assert.assertEquals(validateCliente.get(0).getEndereco(), "Rua Tal");
    }

    @Test
    public void alterarClienteTest(){

        Cliente testeCliente = criaCliente();

        List<Cliente> validateClientes = Cliente.cadastrarCliente(clientes, testeCliente);

        Assert.assertEquals(validateClientes.get(0).getName(), "anderson");
        Assert.assertEquals(validateClientes.get(0).getCpf(), "28916255");
        Assert.assertEquals(validateClientes.get(0).getEndereco(), "Rua Tal");

        Cliente testeClienteAtualizar = new Cliente("João", "28916255", "Rua dos Palmares");

        List<Cliente> validateClientesAtualizar = Cliente.alterarCliente(validateClientes, testeClienteAtualizar);

        Assert.assertEquals(validateClientesAtualizar.get(0).getName(), "João");
        Assert.assertEquals(validateClientesAtualizar.get(0).getCpf(), "28916255");
        Assert.assertEquals(validateClientesAtualizar.get(0).getEndereco(), "Rua dos Palmares");
    }

    @Test
    public void listarClientesTest(){

        clientes = criaListaCliente();

        List<Cliente> clienteTeste = Cliente.listarClientes(clientes);

        Assert.assertEquals(clienteTeste.get(0).getName(), this.anderson.getName());
        Assert.assertEquals(clienteTeste.get(0).getEndereco(), this.anderson.getEndereco());
        Assert.assertEquals(clienteTeste.get(0).getCpf(), this.anderson.getCpf());

        Assert.assertEquals(clienteTeste.get(1).getName(), this.jorge.getName());
        Assert.assertEquals(clienteTeste.get(1).getEndereco(), this.jorge.getEndereco());
        Assert.assertEquals(clienteTeste.get(1).getCpf(), this.jorge.getCpf());

        Assert.assertEquals(clienteTeste.get(2).getName(), this.maria.getName());
        Assert.assertEquals(clienteTeste.get(2).getEndereco(), this.maria.getEndereco());
        Assert.assertEquals(clienteTeste.get(2).getCpf(), this.maria.getCpf());
    }

    @Test
    public void buscaClienteCpfTeste(){

        clientes = criaListaCliente();

        Cliente clienteTeste1 = Cliente.buscaClienteCpf(this.jorge.getCpf(), clientes);
        Cliente clienteTeste2 = Cliente.buscaClienteCpf(this.anderson.getCpf(), clientes);
        Cliente clienteTeste3 = Cliente.buscaClienteCpf(this.maria.getCpf(), clientes);

        Assert.assertEquals(clienteTeste1, this.jorge);
        Assert.assertNotEquals(clienteTeste1, this.anderson);
        Assert.assertNotEquals(clienteTeste1, this.maria);

        Assert.assertEquals(clienteTeste2, this.anderson);
        Assert.assertNotEquals(clienteTeste2, this.jorge);
        Assert.assertNotEquals(clienteTeste2, this.maria);

        Assert.assertEquals(clienteTeste3, this.maria);
        Assert.assertNotEquals(clienteTeste3, this.jorge);
        Assert.assertNotEquals(clienteTeste3, this.anderson);
    }

    @Test
    public void removeClienteTeste(){

        clientes = criaListaCliente();

        clientes = Cliente.removeCliente(clientes, this.anderson);

        for(int i = 0; i < clientes.size(); i++){

            Assert.assertNotEquals(clientes.get(i), this.anderson);
        }

        clientes = Cliente.removeCliente(clientes, this.jorge);

        for(int i = 0; i < clientes.size(); i++){

            Assert.assertNotEquals(clientes.get(i), this.anderson);
            Assert.assertNotEquals(clientes.get(i), this.jorge);
        }
    }
}