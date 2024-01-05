package com.senai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Camila
 */
public class ProductArray {

    private static ArrayList<String> listaProdutos = new ArrayList<>();
    private static ArrayList<String> listaClassificacaoProdutos = new ArrayList<>();
    private static ArrayList<Integer> listaEstoqueProdutos = new ArrayList<>();
    private static boolean possuiProdutoCadastrado = false;

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // login do usuário
        login(entrada);

        // menu para opção do usuário
        int opcao = 1;

        while (opcao != 9) {
            opcao = menu(entrada);

            switch (opcao) {
                case 1:
                    inserirProduto(entrada);
                    break;
                case 2:
                    if (possuiProdutoCadastrado) {
                        atualizarProduto(entrada);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                case 3:
                    if (possuiProdutoCadastrado) {
                        removerProduto(entrada);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                case 4:
                    if (possuiProdutoCadastrado) {
                        imprimirProdutos();
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                    break;
                case 7:
                    if (possuiProdutoCadastrado) {
                        adicionarEstoque(entrada);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                    break;
                case 8:
                    if (possuiProdutoCadastrado) {
                        removerEstoque(entrada);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                    break;
                case 9:
                    System.out.println("FIM PROGRAMA");
                    break;

            }
        }

    }

    public static boolean login(Scanner entrada) {

        String userDataBase = "admin";
        String passwordDataBase = "12345";
        String user = "";
        String password = "";
        boolean loginValido = false;

        while (!loginValido) {

            try {
                System.out.println("");
                System.out.println("Informe o usuário de login: ");
                user = entrada.nextLine();

                System.out.println("");
                System.out.println("Informe a senha de login: ");
                password = entrada.nextLine();

                if (userDataBase.equals(user) && passwordDataBase.equals(password)) {
                    loginValido = true;
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Usuário e/ou senha inválidos! Tente novamente...");
            }

        }

        return loginValido;

    }

    public static int menu(Scanner entrada) {

        boolean opcaoValida = false;
        int opcao = 0;

        while (!opcaoValida) {

            System.out.println("");
            System.out.println("------------------ MENU ------------------");
            System.out.println("");
            System.out.println("""
                               [1] Insert product
                               [2] Update product 
                               [3] Remove product 
                               [4] Display all products 
                               [5] Display all products by sorting
                               [6] Display all products by classification
                               [7] Add inventory 
                               [8] Remove inventory 
                               [9] Exit 
                               """);
            System.out.println("------------------------------------------");
            System.out.println("");

            try {
                System.out.println("Digite uma opção do menu: ");
                opcao = entrada.nextInt();

                if (opcao >= 1 && opcao <= 8) {
                    opcaoValida = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida, tente novamente... ");
            }
        }

        return opcao;

    }

    public static boolean inserirProduto(Scanner entrada) {

        if (possuiProdutoCadastrado) {
            imprimirProdutos();
        } else {
            System.out.println("Ainda não há produtos cadastrados");
        }

        boolean produtoInserido = false;
        String produto;
        int produtoClassificacao = 1;

        while (!produtoInserido) {

            System.out.println("");
            System.out.println("Informe o nome do produto: ");

            try {
                produto = entrada.next();

                if (!produto.equals("") || !produto.equals(" ")) {

                    System.out.println("");
                    System.out.println("---------- CLASSIFICAÇÃO PRODUTO ----------");
                    System.out.println("");
                    System.out.println("""
                               [1] First line
                               [2] Regular line
                               [3] Second line 
                               """);
                    System.out.println("-------------------------------------------");

                    try {
                        System.out.println("");
                        System.out.println("Digite uma opção de classificação: ");
                        produtoClassificacao = entrada.nextInt();

                        if (produtoClassificacao >= 1 || produtoClassificacao <= 3) {

                            switch (produtoClassificacao) {
                                case 1:
                                    listaClassificacaoProdutos.add("First line");
                                    break;
                                case 2:
                                    listaClassificacaoProdutos.add("Regular line");
                                    break;
                                default:
                                    listaClassificacaoProdutos.add("Second line");
                                    break;
                            }

                            System.out.println("Produto cadastrado com sucesso!");
                            listaProdutos.add(produto);
                            listaEstoqueProdutos.add(0);
                            produtoInserido = true;
                            possuiProdutoCadastrado = true;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Classificação de produto inválida, tente novamente 1");
                        entrada.nextLine();
                    }

                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Classificação de produto inválida, tente novamente 2");
                entrada.nextLine();
            }

        }

        return produtoInserido;

    }

    public static void imprimirProdutos() {

        System.out.println("");
        System.out.println("ID | CLASSIFICAÇÃO | DESCRIÇÃO DO PRODUTO | QTD EM ESTOQUE");
        //Collections.sort(ProductArray.listaProdutos);
        //System.out.println(ProductArray.listaProdutos);

        for (int i = 0; i < ProductArray.listaProdutos.size(); i++) {

            System.out.printf("%s | %s | %s | %s \n",
                    i,
                    listaClassificacaoProdutos.get(i),
                    listaProdutos.get(i),
                    listaEstoqueProdutos.get(i)
            );

        }

    }

    public static boolean atualizarProduto(Scanner entrada) {

        boolean produtoAtualizado = false;
        int indiceProduto;
        String descricaoProduto;

        if (possuiProdutoCadastrado) {
            imprimirProdutos();
        } else {
            System.out.println("Ainda não há produtos cadastrados");
        }

        while (!produtoAtualizado) {

            System.out.println("");
            System.out.println("Informe o ID do produto a ser atualizado: ");

            try {

                indiceProduto = entrada.nextInt();

                if (indiceProduto >= 0 && indiceProduto < listaProdutos.size()) {

                    System.out.println("");
                    System.out.println("Informe a nova descrição do produto a ser atualizado: ");

                    try {
                        descricaoProduto = entrada.next();

                        if (!descricaoProduto.equals("") || !descricaoProduto.equals(" ")) {
                            listaProdutos.set(indiceProduto, descricaoProduto);
                            produtoAtualizado = true;
                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        System.out.println("Descrição de produto inválida! Tente novamente... ");
                        entrada.nextLine();
                    }

                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("ID de produto inexistente! Tente novamente... ");
                entrada.nextLine();
            }

        }

        return produtoAtualizado;
    }

    public static boolean removerProduto(Scanner entrada) {

        boolean produtoRemovido = false;
        int indiceProduto;

        if (possuiProdutoCadastrado) {
            imprimirProdutos();
        } else {
            System.out.println("Ainda não há produtos cadastrados;");
        }

        while (!produtoRemovido) {

            System.out.println("");
            System.out.println("Informe o ID do produto que deseja remover: ");

            try {

                indiceProduto = entrada.nextInt();

                if (indiceProduto >= 0 && indiceProduto < listaProdutos.size()) {

                    listaProdutos.remove(indiceProduto);
                    listaClassificacaoProdutos.remove(indiceProduto);
                    listaEstoqueProdutos.remove(indiceProduto);
                    produtoRemovido = true;

                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("ID de produto inexistente. Tente novamente... ");
                entrada.nextLine();
            }

        }

        return produtoRemovido;

    }

    public static boolean adicionarEstoque(Scanner entrada) {

        boolean estoqueAdicionado = false;
        int indiceProduto;
        int quantidadeItens;
        int quantidadeItensAtual;
        int quantidadeItensNova;

        if (possuiProdutoCadastrado) {
            imprimirProdutos();
        } else {
            System.out.println("Ainda não há produto cadastrado... ");
        }

        while (!estoqueAdicionado) {

            System.out.println("");
            System.out.println("Informe o ID do produto que deseja adicionar estoque: ");

            try {
                indiceProduto = entrada.nextInt();

                if (indiceProduto >= 0 && indiceProduto < listaProdutos.size()) {

                    System.out.println("");
                    System.out.printf(
                            "Informe a quantidade de itens que deseja adicionar estoque do produto %s: ",
                            listaProdutos.get(indiceProduto)
                    );

                    try {
                        quantidadeItens = entrada.nextInt();

                        if (quantidadeItens == 0) {
                            System.out.println("Nenhuma quantidade de itens foi adicionada ao produto.");
                            estoqueAdicionado = true;
                        } else if (quantidadeItens > 0) {
                            quantidadeItensAtual = listaEstoqueProdutos.get(indiceProduto);
                            quantidadeItensNova = quantidadeItensAtual + quantidadeItens;
                            listaEstoqueProdutos.set(indiceProduto, quantidadeItensNova);
                            System.out.println("Quantidade de itens adicionada ao produto com sucesso!");
                            estoqueAdicionado = true;
                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        System.out.println("Quantidade de itens inválida. Tente novamente... ");
                        entrada.nextLine();
                    }

                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("ID de produto inexistente. Tente novamente... ");
                entrada.nextLine();
            }

        }

        return estoqueAdicionado;

    }

    public static boolean removerEstoque(Scanner entrada) {

        boolean estoqueRemovido = false;
        int indiceProduto;
        int quantidadeItens;
        int quantidadeItensAtual;
        int quantidadeItensNova;

        if (possuiProdutoCadastrado) {
            imprimirProdutos();
        } else {
            System.out.println("Ainda não há produto cadastrado... ");
        }

        while (!estoqueRemovido) {

            System.out.println("");
            System.out.println("Informe o ID do produto que deseja remover estoque: ");

            try {
                indiceProduto = entrada.nextInt();

                if (indiceProduto >= 0 && indiceProduto < listaProdutos.size()) {

                    if (listaEstoqueProdutos.get(indiceProduto) == 0) {
                        System.out.println("[ATENÇÃO] Esse produto já possui estoque zerado!");
                        estoqueRemovido = true;

                    } else {

                        System.out.println("");
                        System.out.println("Informe a quantidade de itens que deseja remover do produto: ");

                        try {

                            quantidadeItens = entrada.nextInt();

                            if (quantidadeItens == 0) {

                                System.out.println("[ATENÇÃO] Quantidade ZERO não pode ser removida do estoque");
                                estoqueRemovido = true;

                            } else if (quantidadeItens > 0) {

                                quantidadeItensAtual = listaEstoqueProdutos.get(indiceProduto);
                                quantidadeItensNova = quantidadeItensAtual - quantidadeItens;

                                if (quantidadeItensNova < 0) {
                                    System.out.println("""
                                                       [ATENÇÃO] A quantidade de itens informada foi SUPERIOR \u00e0 quantidade de itens em estoque. 
                                                       Com isso, o estoque do produto foi ZERADO para n\u00e3o ficar negativo, conforme regras de Compliance. 
                                                       """);
                                    listaEstoqueProdutos.set(indiceProduto, 0);
                                } else {
                                    listaEstoqueProdutos.set(indiceProduto, quantidadeItensNova);
                                }

                                System.out.println("Quantidade de itens removida do produto com sucesso!");
                                estoqueRemovido = true;

                            } else {
                                throw new Exception();                               
                            }

                        } catch (Exception e) {
                            System.out.println("Quantidade de itens inválida. Tente novamente... ");
                            entrada.nextLine();
                        }

                    }
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("ID de produto inexistente. Tente novamente... ");
                entrada.nextLine();
            }

        }

        return estoqueRemovido;
    }

}
