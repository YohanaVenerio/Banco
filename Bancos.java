/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bancos;

/**
 *
 * @author yohan
 */
public class Bancos {

    // Classe Cliente
    public static class Cliente {
        private String nome;
        private String cpf;

        // Construtor
        public Cliente(String nome, String cpf) {
            this.nome = nome;
            this.cpf = cpf;
        }

        // Getters
        public String getNome() {
            return nome;
        }

        public String getCpf() {
            return cpf;
        }
    }

    // Classe Conta
    public static class Conta {
        protected int numero;
        protected double saldo;
        protected Cliente cliente;

        // Construtor
        public Conta(int numero, double saldo, Cliente cliente) {
            this.numero = numero;
            this.saldo = saldo;
            this.cliente = cliente;
        }

        // Métodos
        public void depositar(double valor) {
            if (valor > 0) {
                saldo += valor;
            } else {
                System.out.println("Valor inválido");
            }
        }

        public void sacar(double valor) {
            if (valor <= saldo) {
                saldo -= valor;
            } else {
                System.out.println("Saldo Insuficiente.");
            }
        }

        public double getSaldo() {
            return saldo;
        }

        public Cliente getCliente() {
            return cliente;
        }
    }

    // Classe ContaCorrente
    public static class ContaCorrente extends Conta {
        private double limite;

        public ContaCorrente(int numero, double saldo, Cliente cliente, double limite) {
            super(numero, saldo, cliente);
            this.limite = limite;
        }

        @Override
        public void sacar(double valor) {
            if (saldo + limite >= valor) {
                saldo -= valor;
            } else {
                System.out.println("Saldo insuficiente.");
            }
        }
    }

    // Classe ContaPoupanca
    public static class ContaPoupanca extends Conta {

        public ContaPoupanca(int numero, double saldo, Cliente cliente) {
            super(numero, saldo, cliente);
        }

        public void calcularRendimento(double taxa) {
            saldo += saldo * taxa;
        }
    }

    // Classe Banco
    public static class Banco {
        private Conta[] contas = new Conta[5];
        private int quantidadeDeContas = 0;

        public void adicionarConta(Conta conta) {
            if (quantidadeDeContas < contas.length) {
                contas[quantidadeDeContas] = conta;
                quantidadeDeContas++;
            } else {
                System.out.println("Limite de contas atingido.");
            }
        }

        public void listarContas() {
            for (int i = 0; i < quantidadeDeContas; i++) {
                Conta conta = contas[i];
                System.out.println("Conta nº: " + conta.numero +
                        ", Cliente: " + conta.getCliente().getNome() +
                        ", Saldo: " + conta.getSaldo());
            }
        }
    }

    
    
    
    public static void main(String[] args) {
        // Criar banco
        Banco banco = new Banco();

        // Criar clientes e contas
        Cliente cliente1 = new Cliente("João", "12345678900");
        ContaCorrente contaCorrente1 = new ContaCorrente(1, 1000, cliente1, 500);
        banco.adicionarConta(contaCorrente1);

        // Realizar operações
        contaCorrente1.depositar(500);
        contaCorrente1.sacar(800); // Utilizará o limite

        // Listar contas
        banco.listarContas();
    }
}

