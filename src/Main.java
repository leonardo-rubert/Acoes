// Aluno: Arthur Land Avila
// Versão: 1.0
// Data Última Atualização: 2020 09 21

// Falta um metodo toString na HeapPriorityQueue

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner Entrada = new Scanner(System.in);

        String opcao = "";
        int novo_valor = 0;
        int nova_quantidade = 0;

        int min_valor_compra = 0;
        int min_quantidade_compra = 0;

        int min_valor_venda = 0;
        int min_quantidade_venda = 0;

        int diferenca = 0;

        int ciclo_principal = 0;

        HeapPriorityQueue FilaCompra = new HeapPriorityQueue();
        HeapPriorityQueue FilaVenda = new HeapPriorityQueue();

        System.out.println("Simulador de Bolsa de Valores");

        // Loop principal do simulador
        while (true)
        {
            ciclo_principal++;

            System.out.println("* Ciclo principal: " + ciclo_principal);
            if (!FilaCompra.isEmpty()) { System.out.println("  --> Numero de processos pendentes na fila de Compras: " + FilaCompra.size()); }
            if (!FilaVenda.isEmpty()) { System.out.println("  --> Numero de processos pendentes na fila de Vendas: " + FilaVenda.size()); }

            // Limpa variável nome se opção "E" (executar até o fim) não foi selecionada
            opcao = "";

            // Pergunta sobre novo processo a ser adicionado (ou não) na file
            while (opcao.equals(""))
            {
                System.out.print("Informe a opção: [C]ompra ou [V]enda ou {N]ada ou [S]air: ");
                // Input do novo processo
                opcao = Entrada.next();
            }

            if (opcao.equals("C") || opcao.equals("V") )
            {
                nova_quantidade = 0;

                while (nova_quantidade < 1)
                {
                    System.out.print("Informe a quantidade de ações [>0]: ");
                    nova_quantidade = Entrada.nextInt();

                }

               novo_valor = 0;

                while (novo_valor < 1)
                {
                    System.out.print("Informe o valor da ação [>0]: ");
                    novo_valor = Entrada.nextInt();
                }

                if (opcao.equals("C"))
                {
                    FilaCompra.insert(novo_valor, nova_quantidade);
                }
                else
                {
                    FilaVenda.insert(novo_valor, nova_quantidade);
                }

            }

            if (opcao.equals("S"))
            {
                // Sair do prgrama
                break;
            }


            if (FilaCompra.size()>0 && FilaVenda.size()>0)
            {

                System.out.println("# Operação de compra a venda será verificada");

                min_valor_compra = (int) FilaCompra.min().getKey();
                min_quantidade_compra = (int) FilaCompra.min().getValue();

                min_valor_venda = (int) FilaVenda.min().getKey();
                min_quantidade_venda = (int) FilaVenda.min().getValue();

                System.out.println("  Operação de compra de " + min_quantidade_compra + " ações no valor de " + min_valor_compra + " detectada");
                System.out.println("  Operação de venda de " + min_quantidade_venda + " ações no valor de " + min_valor_venda + " detectada");

                if (min_valor_venda <= min_valor_compra)
                {
                    // O valor da compra eh maior que o valor da venda
                    // então uma operação de compra/venda serah executada

                    System.out.println("# Operação de compre a venda será efetuada");
                    System.out.println("  Compra de " + min_quantidade_compra + " ações no valor de " + min_valor_compra);
                    System.out.println("  Venda de " + min_quantidade_venda + " ações no valor de " + min_valor_venda);

                    if (min_quantidade_compra == min_quantidade_venda)
                    {
                        // Se a quantidade da compra eh igual a quantidade da venda
                        // a operação é executada sem nenhuma sobra

                        FilaCompra.removeMin();
                        FilaVenda.removeMin();

                        System.out.println("  Operação de compra e venda executada sem sobra");
                    }
                    else
                    {
                        if (min_quantidade_compra < min_quantidade_venda)
                        {
                            // Se a quantidade da compra eh menor que a quantidade da venda
                            // a operação é executada e uma sobra eh inserida novamente para venda

                            FilaCompra.removeMin();
                            FilaVenda.removeMin();

                            diferenca = min_quantidade_venda - min_quantidade_compra;

                            FilaVenda.insert(min_valor_venda, diferenca);

                            System.out.println("  Operação de compra e venda executada com sobra de venda de " + diferenca + " ações");

                        }
                        else
                        {
                            // Se a quantidade da compra eh maior que a quantidade da venda
                            // a operação é executada e uma sobra eh inserida novamente para compra

                            FilaCompra.removeMin();
                            FilaVenda.removeMin();

                            diferenca = min_quantidade_compra - min_quantidade_venda;

                            FilaCompra.insert(min_valor_compra, diferenca);

                            System.out.println("  Operação de compra e venda executada com sobra de compra de " + diferenca + " ações");
                        }

                    }
                }
            }

            if (FilaCompra.isEmpty() && FilaVenda.isEmpty() && opcao.equals("N"))
            {
                // Se as filas estão vazias e a opção é nada, então encerra o programa
                break;
            }

        }

        System.out.println("Fim");

    }
}
