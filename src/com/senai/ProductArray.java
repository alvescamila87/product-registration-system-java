
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
        
    Scanner entrada = new Scanner (System.in);
    
    // login do usuário
    login(entrada);  
    
    // menu para opção do usuário
    int opcao = 1;
    
    while(opcao != 9) {
         opcao = menu(entrada);
         
         switch (opcao) {
            case 1:
                 inserirProduto(entrada);
                 break;
            case 4:
                if(possuiProdutoCadastrado) {
                    imprimirProdutos();
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
    
    while(!loginValido) {      
        
        
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
        
        while(!opcaoValida) {
            
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
            
            try{
                System.out.println("Digite uma opção do menu: ");
                opcao = entrada.nextInt();
                
                
                if(opcao >= 1 && opcao <= 8) {
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
        
        if(possuiProdutoCadastrado) {
            imprimirProdutos();
        } else {
            System.out.println("Ainda não há produtos cadastrados");
        }
        
        
        boolean produtoInserido = false;
        String produto;
        int produtoClassificacao = 1;
        
        
        while(!produtoInserido) {
            
            System.out.println("");
            System.out.println("Informe o nome do produto: ");
            
            try {
                produto = entrada.next();

                if (!produto.equals("") || !produto.equals(" ")) {
                    
                    System.out.println("");
                    System.out.println("---------- CLASSIFICAÇÃO PRODUTO ----------");
                    System.out.println("");
                    System.out.println("""
                               [1] Premium line
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
                                    listaClassificacaoProdutos.add("Premium line");
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
                    }

                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Classificação de produto inválida, tente novamente 2");
            }
                
            
        }
        
        return produtoInserido;
        
    }
    
    public static void imprimirProdutos() {
        
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
    
    public static int atualizarProduto(int indiceProduto) {
        
        System.out.println("");
        System.out.println("Informe o ID do produto a ser atualizado: ");
                
                
        return indiceProduto;
    } 
    
}
