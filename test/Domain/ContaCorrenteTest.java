package Domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ContaCorrenteTest {

    private ContaCorrente contaAnderson;
    private Cliente anderson;
    private ContaCorrente contaJorge;
    private Cliente jorge;
    private ContaCorrente contaMaria;
    private Cliente maria;

    List<Cliente> clientes = new ArrayList<>();
    List<ContaCorrente> contaCorrentes = new ArrayList<>();

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

    @Before
    public ContaCorrente criaContaCorrente(){

        this.anderson = new Cliente("anderson","28916255", "Rua Tal" );
        this.contaAnderson = new ContaCorrente(1l, this.anderson, 100.00);

        return this.contaAnderson;

    }

    @Before
    public List<ContaCorrente> criaListaContaCorrente(){

        contaCorrentes.add(criaContaCorrente());

        this.jorge = new Cliente("jorge", "112554445", "Rua Embaixador");

        this.contaJorge = new ContaCorrente(2l, this.jorge, 120.00);

        contaCorrentes.add(this.contaJorge);

        this.maria = new Cliente("maria", "855959597", "Rua Embaixador 3");

        this.contaMaria = new ContaCorrente(3l, this.maria, 130.00);

        contaCorrentes.add(this.contaMaria);

        return contaCorrentes;

    }

    @Test
    void cadastrarContaCorrente() {

        ContaCorrente testeContaCorrente = criaContaCorrente();
        List<Cliente> listaClientes = criaListaCliente();

        contaCorrentes = ContaCorrente.cadastrarContaCorrente(listaClientes, contaCorrentes, testeContaCorrente);

        List<ContaCorrente> validateContaCorrente = ContaCorrente.listarContaCorrente(contaCorrentes);

        Assert.assertEquals(validateContaCorrente.get(0).getNumero(), this.contaAnderson.getNumero());
        Assert.assertEquals(validateContaCorrente.get(0).getTitular(), this.contaAnderson.getTitular());
        Assert.assertEquals(validateContaCorrente.get(0).getSaldo(), this.contaAnderson.getSaldo());

    }

    @Test
    void listarContaCorrente() {

        contaCorrentes = criaListaContaCorrente();

        List<ContaCorrente> contaCorrenteTeste = ContaCorrente.listarContaCorrente(contaCorrentes);

        Assert.assertEquals(contaCorrenteTeste.get(0).getNumero(), this.contaAnderson.getNumero());
        Assert.assertEquals(contaCorrenteTeste.get(0).getSaldo(), this.contaAnderson.getSaldo());
        Assert.assertEquals(contaCorrenteTeste.get(0).getTitular(), this.contaAnderson.getTitular());

        Assert.assertEquals(contaCorrenteTeste.get(1).getNumero(), this.contaJorge.getNumero());
        Assert.assertEquals(contaCorrenteTeste.get(1).getSaldo(), this.contaJorge.getSaldo());
        Assert.assertEquals(contaCorrenteTeste.get(1).getTitular(), this.contaJorge.getTitular());

        Assert.assertEquals(contaCorrenteTeste.get(2).getNumero(), this.contaMaria.getNumero());
        Assert.assertEquals(contaCorrenteTeste.get(2).getSaldo(), this.contaMaria.getSaldo());
        Assert.assertEquals(contaCorrenteTeste.get(2).getTitular(), this.contaMaria.getTitular());

    }

    @Test
    void buscaContaCorrenteNumero() {

        contaCorrentes = criaListaContaCorrente();

        ContaCorrente contaCorrenteTeste1 = ContaCorrente.buscaContaCorrenteNumero(contaCorrentes, this.contaAnderson.getNumero());
        ContaCorrente contaCorrenteTeste2 = ContaCorrente.buscaContaCorrenteNumero(contaCorrentes, this.contaJorge.getNumero());
        ContaCorrente contaCorrenteTeste3 = ContaCorrente.buscaContaCorrenteNumero(contaCorrentes, this.contaMaria.getNumero());

        Assert.assertEquals(contaCorrenteTeste1,this.contaAnderson);
        Assert.assertNotEquals(contaCorrenteTeste1,this.contaJorge);
        Assert.assertNotEquals(contaCorrenteTeste1,this.contaMaria);

        Assert.assertEquals(contaCorrenteTeste2,this.contaJorge);
        Assert.assertNotEquals(contaCorrenteTeste2,this.contaAnderson);
        Assert.assertNotEquals(contaCorrenteTeste2,this.contaMaria);

        Assert.assertEquals(contaCorrenteTeste3,this.contaMaria);
        Assert.assertNotEquals(contaCorrenteTeste3,this.contaAnderson);
        Assert.assertNotEquals(contaCorrenteTeste3,this.contaJorge);

    }

    @Test
    void removeContaCorrente() {

        contaCorrentes = criaListaContaCorrente();

        contaCorrentes = ContaCorrente.removeContaCorrente(contaCorrentes, this.contaAnderson.getNumero());

        for(int i = 0; i < contaCorrentes.size(); i++){

            Assert.assertNotEquals(contaCorrentes.get(i), this.contaAnderson);
        }

        contaCorrentes = ContaCorrente.removeContaCorrente(contaCorrentes, this.contaJorge.getNumero());

        for(int i = 0; i < contaCorrentes.size(); i++){

            Assert.assertNotEquals(contaCorrentes.get(i), this.contaJorge);
        }
    }

    @Test
    void depositaContaCorrenteTest(){

        contaCorrentes = criaListaContaCorrente();

        Assert.assertEquals(contaCorrentes.get(0).getNumero(), this.contaAnderson.getNumero());
        Assert.assertEquals(contaCorrentes.get(0).getTitular(), this.contaAnderson.getTitular());
        Assert.assertEquals(contaCorrentes.get(0).getSaldo(), this.contaAnderson.getSaldo());

        Double valorDepositar = 30.0;
        Double valorTotal = this.contaAnderson.getSaldo() + valorDepositar;

        contaCorrentes = ContaCorrente.depositaContaCorrente(contaCorrentes, this.contaAnderson.getNumero(), valorDepositar);

        Assert.assertEquals(contaCorrentes.get(0).getNumero(), this.contaAnderson.getNumero());
        Assert.assertEquals(contaCorrentes.get(0).getTitular(), this.contaAnderson.getTitular());
        Assert.assertEquals(contaCorrentes.get(0).getSaldo(), valorTotal);

        Assert.assertEquals(contaCorrentes.get(1).getNumero(), this.contaJorge.getNumero());
        Assert.assertEquals(contaCorrentes.get(1).getTitular(), this.contaJorge.getTitular());
        Assert.assertEquals(contaCorrentes.get(1).getSaldo(), this.contaJorge.getSaldo());

        Assert.assertEquals(contaCorrentes.get(2).getNumero(), this.contaMaria.getNumero());
        Assert.assertEquals(contaCorrentes.get(2).getTitular(), this.contaMaria.getTitular());
        Assert.assertEquals(contaCorrentes.get(2).getSaldo(), this.contaMaria.getSaldo());

    }

    @Test
    void sacaContaCorrenteTest(){

        contaCorrentes = criaListaContaCorrente();

        Assert.assertEquals(contaCorrentes.get(0).getNumero(), this.contaAnderson.getNumero());
        Assert.assertEquals(contaCorrentes.get(0).getTitular(), this.contaAnderson.getTitular());
        Assert.assertEquals(contaCorrentes.get(0).getSaldo(), this.contaAnderson.getSaldo());

        Double valorSacar = 30.0;
        Double valorTotal = this.contaAnderson.getSaldo() - valorSacar;

        contaCorrentes = ContaCorrente.sacaContaCorrente(contaCorrentes, this.contaAnderson.getNumero(), valorSacar);

        Assert.assertEquals(contaCorrentes.get(0).getNumero(), this.contaAnderson.getNumero());
        Assert.assertEquals(contaCorrentes.get(0).getTitular(), this.contaAnderson.getTitular());
        Assert.assertEquals(contaCorrentes.get(0).getSaldo(), valorTotal);

        Assert.assertEquals(contaCorrentes.get(1).getNumero(), this.contaJorge.getNumero());
        Assert.assertEquals(contaCorrentes.get(1).getTitular(), this.contaJorge.getTitular());
        Assert.assertEquals(contaCorrentes.get(1).getSaldo(), this.contaJorge.getSaldo());

        Assert.assertEquals(contaCorrentes.get(2).getNumero(), this.contaMaria.getNumero());
        Assert.assertEquals(contaCorrentes.get(2).getTitular(), this.contaMaria.getTitular());
        Assert.assertEquals(contaCorrentes.get(2).getSaldo(), this.contaMaria.getSaldo());

    }
}