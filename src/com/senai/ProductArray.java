package com.senai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Camila
 */
public class ProductArray {

    private static ArrayList<String> productList = new ArrayList<>();
    private static ArrayList<String> productClassificationList = new ArrayList<>();
    private static ArrayList<Integer> productInventoryList = new ArrayList<>();
    private static boolean hasRegisteredProduct = false;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // login do usuário
        login(input);

        // menu para opção do usuário
        int opcao = 1;

        while (opcao != 9) {
            opcao = menu(input);

            switch (opcao) {
                case 1:
                    insertProduct(input);
                    break;
                case 2:
                    if (hasRegisteredProduct) {
                        updateProduct(input);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                case 3:
                    if (hasRegisteredProduct) {
                        removeProduct(input);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                case 4:
                    if (hasRegisteredProduct) {
                        printProduct();
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                    break;
                case 5:
                    if (hasRegisteredProduct) {
                        printProductSorting(input);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                    break;
                case 7:
                    if (hasRegisteredProduct) {
                        addInventory(input);
                    } else {
                        System.out.println("O sistema ainda NÃO possui produtos cadastrados.");
                    }
                    break;
                case 8:
                    if (hasRegisteredProduct) {
                        removedInventory(input);
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
        boolean loginValid = false;

        while (!loginValid) {

            try {
                System.out.println("");
                System.out.println("Informe o usuário de login: ");
                user = entrada.nextLine();

                System.out.println("");
                System.out.println("Informe a senha de login: ");
                password = entrada.nextLine();

                if (userDataBase.equals(user) && passwordDataBase.equals(password)) {
                    loginValid = true;
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Usuário e/ou senha inválidos! Tente novamente...");
            }

        }

        return loginValid;

    }

    public static int menu(Scanner input) {

        boolean optionValid = false;
        int option = 0;

        while (!optionValid) {

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
                option = input.nextInt();

                if (option >= 1 && option <= 8) {
                    optionValid = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida, tente novamente... ");
            }
        }

        return option;

    }

    public static boolean insertProduct(Scanner input) {

        if (hasRegisteredProduct) {
            printProduct();
        } else {
            System.out.println("Ainda não há produtos cadastrados");
        }

        boolean insertedProduct = false;
        String product;
        int classificationProduct = 1;

        while (!insertedProduct) {

            System.out.println("");
            System.out.println("Informe o nome do produto: ");

            try {
                product = input.next();

                if (!product.equals("") || !product.equals(" ")) {

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
                        classificationProduct = input.nextInt();

                        if (classificationProduct >= 1 || classificationProduct <= 3) {

                            switch (classificationProduct) {
                                case 1:
                                    productClassificationList.add("First line");
                                    break;
                                case 2:
                                    productClassificationList.add("Regular line");
                                    break;
                                default:
                                    productClassificationList.add("Second line");
                                    break;
                            }

                            System.out.println("Produto cadastrado com sucesso!");
                            productList.add(product);
                            productInventoryList.add(0);
                            insertedProduct = true;
                            hasRegisteredProduct = true;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Classificação de produto inválida, tente novamente 1");
                        input.nextLine();
                    }

                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Classificação de produto inválida, tente novamente 2");
                input.nextLine();
            }

        }

        return insertedProduct;

    }

    public static void printProduct() {

        System.out.println("");
        System.out.println("ID | CLASSIFICAÇÃO | DESCRIÇÃO DO PRODUTO | QTD EM ESTOQUE");
        //Collections.sort(ProductArray.productList);
        //System.out.println(ProductArray.productList);

        for (int i = 0; i < ProductArray.productList.size(); i++) {

            System.out.printf("%s | %s | %s | %s \n",
                    i,
                    productClassificationList.get(i),
                    productList.get(i),
                    productInventoryList.get(i)
            );

        }

    }
    
    public static boolean printProductSorting(Scanner input) {
        
        boolean produtoOrdenado = false;
        int option;
        
        System.out.println("");
        System.out.println("[1] Ordenar ASC por descrição de produto");
        System.out.println("[2] Ordenar DESC por descrição de produto");
        System.out.println("[3] Ordenar DESC por ID de produto");
        System.out.println("[4] Retornar ao menu");
        
        while(!produtoOrdenado) {
            
            System.out.println("");
            System.out.println("Informe a opção de ordenação: ");
            option = input.nextInt();
            
            switch (option) {
                case 1:                    
                    Collections.sort(productList);
                    printProduct();   
                    produtoOrdenado = true;
                    break;
                case 2:
                    Collections.sort(productList, Collections.reverseOrder());
                    printProduct();   
                    produtoOrdenado = true;
                    break;
                case 3:
                    Collections.sort(productList, Comparator.reverseOrder());
                    printProduct(); 
                    produtoOrdenado = true;
                    break;
                case 4:
                    System.out.println("Back to the menu...");
                    produtoOrdenado = true;
                    break;
                default: 
                    System.out.println("[WARNING] Invalid option. Try again...");
                            
            }
            
            
        }
        
        return produtoOrdenado;   
    }

    public static boolean updateProduct(Scanner input) {

        boolean updatedProduct = false;
        int productID;
        String productDescription;

        if (hasRegisteredProduct) {
            printProduct();
        } else {
            System.out.println("Ainda não há produtos cadastrados");
        }

        while (!updatedProduct) {

            System.out.println("");
            System.out.println("Informe o ID do produto a ser atualizado: ");

            try {

                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    System.out.println("");
                    System.out.println("Informe a nova descrição do produto a ser atualizado: ");

                    try {
                        productDescription = input.next();

                        if (!productDescription.equals("") || !productDescription.equals(" ")) {
                            productList.set(productID, productDescription);
                            updatedProduct = true;
                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        System.out.println("Descrição de produto inválida! Tente novamente... ");
                        input.nextLine();
                    }

                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("ID de produto inexistente! Tente novamente... ");
                input.nextLine();
            }

        }

        return updatedProduct;
    }

    public static boolean removeProduct(Scanner input) {

        boolean deletedProduct = false;
        int productID;

        if (hasRegisteredProduct) {
            printProduct();
        } else {
            System.out.println("Ainda não há produtos cadastrados;");
        }

        while (!deletedProduct) {

            System.out.println("");
            System.out.println("Informe o ID do produto que deseja remover: ");

            try {

                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    productList.remove(productID);
                    productClassificationList.remove(productID);
                    productInventoryList.remove(productID);
                    deletedProduct = true;

                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("ID de produto inexistente. Tente novamente... ");
                input.nextLine();
            }

        }

        return deletedProduct;

    }

    public static boolean addInventory(Scanner input) {

        boolean addedInventory = false;
        int productID;
        int numberItems;
        int numberItemsCurrent;
        int numberItemsNew;

        if (hasRegisteredProduct) {
            printProduct();
        } else {
            System.out.println("Ainda não há produto cadastrado... ");
        }

        while (!addedInventory) {

            System.out.println("");
            System.out.println("Informe o ID do produto que deseja adicionar estoque: ");

            try {
                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    System.out.println("");
                    System.out.printf("Informe a quantidade de itens que deseja adicionar estoque do produto %s: ",
                            productList.get(productID)
                    );

                    try {
                        numberItems = input.nextInt();

                        if (numberItems == 0) {
                            System.out.println("Nenhuma quantidade de itens foi adicionada ao produto.");
                            addedInventory = true;
                        } else if (numberItems > 0) {
                            numberItemsCurrent = productInventoryList.get(productID);
                            numberItemsNew = numberItemsCurrent + numberItems;
                            productInventoryList.set(productID, numberItemsNew);
                            System.out.println("Quantidade de itens adicionada ao produto com sucesso!");
                            addedInventory = true;
                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        System.out.println("Quantidade de itens inválida. Tente novamente... ");
                        input.nextLine();
                    }

                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("ID de produto inexistente. Tente novamente... ");
                input.nextLine();
            }

        }

        return addedInventory;

    }

    public static boolean removedInventory(Scanner input) {

        boolean removedIntentory = false;
        int productID;
        int numberItems;
        int numberItemsCurrent;
        int numberItemsNew;

        if (hasRegisteredProduct) {
            printProduct();
        } else {
            System.out.println("Ainda não há produto cadastrado... ");
        }

        while (!removedIntentory) {

            System.out.println("");
            System.out.println("Informe o ID do produto que deseja remover estoque: ");

            try {
                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    if (productInventoryList.get(productID) == 0) {
                        System.out.println("[ATENÇÃO] Esse produto já possui estoque zerado!");
                        removedIntentory = true;

                    } else {

                        System.out.println("");
                        System.out.println("Informe a quantidade de itens que deseja remover do produto: ");

                        try {

                            numberItems = input.nextInt();

                            if (numberItems == 0) {

                                System.out.println("[ATENÇÃO] Quantidade ZERO não pode ser removida do estoque");
                                removedIntentory = true;

                            } else if (numberItems > 0) {

                                numberItemsCurrent = productInventoryList.get(productID);
                                numberItemsNew = numberItemsCurrent - numberItems;

                                if (numberItemsNew < 0) {
                                    System.out.println("""
                                                       [ATENÇÃO] A quantidade de itens informada foi SUPERIOR \u00e0 quantidade de itens em estoque. 
                                                       Com isso, o estoque do produto foi ZERADO para n\u00e3o ficar negativo, conforme regras de Compliance. 
                                                       """);
                                    productInventoryList.set(productID, 0);
                                } else {
                                    productInventoryList.set(productID, numberItemsNew);
                                }

                                System.out.println("Quantidade de itens removida do produto com sucesso!");
                                removedIntentory = true;

                            } else {
                                throw new Exception();                               
                            }

                        } catch (Exception e) {
                            System.out.println("Quantidade de itens inválida. Tente novamente... ");
                            input.nextLine();
                        }

                    }
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("ID de produto inexistente. Tente novamente... ");
                input.nextLine();
            }

        }

        return removedIntentory;
    }

}
