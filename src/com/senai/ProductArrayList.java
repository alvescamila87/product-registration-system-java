package com.senai;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Camila
 */
public class ProductArrayList {

    private static final ArrayList<String> productList = new ArrayList<>();
    private static final ArrayList<String> productClassificationList = new ArrayList<>();
    private static final ArrayList<Integer> productStockList = new ArrayList<>();
    private static boolean hasRegisteredProduct = false;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        login(input);

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
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 3:
                    if (hasRegisteredProduct) {
                        removeProduct(input);
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 4:
                    if (hasRegisteredProduct) {
                        printProduct();
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 5:
                    if (hasRegisteredProduct) {
                        printProductSorting(input);
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 6:
                    if (hasRegisteredProduct) {
                        printProductClassification(input);
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 7:
                    if (hasRegisteredProduct) {
                        addItemStock(input);
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 8:
                    if (hasRegisteredProduct) {
                        removeItemInventory(input);
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 9:
                    System.out.println("FINISHING THE PROGRAM...");
                    System.out.println("THANK YOU!!!");
                    break;

            }
        }
    }

    /**
     * Login to user authentication in the system
     * throws an exception if user or password are different from "harshcoded" database
     */
    public static boolean login(Scanner input) {

        String userDataBase = "admin";
        String passwordDataBase = "12345";
        String user;
        String password;
        boolean loginValid = false;

        while (!loginValid) {

            try {
                System.out.println("");
                System.out.print("Input the username: ");
                user = input.nextLine();

                System.out.println("");
                System.out.print("Input the password: ");
                password = input.nextLine();

                if (userDataBase.equals(user) && passwordDataBase.equals(password)) {
                    loginValid = true;
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("The username and/or passowrd are invalided! Try again...");
            }

        }

        return loginValid;

    }

    /**
     * Only after logging in, it's possible to see and input an option from the menu about system
     * throws an exception if the option is different from listed on the menu
     */
    public static int menu(Scanner input) {

        boolean optionValid = false;
        int option = 0;

        while (!optionValid) {

            System.out.println("");
            System.out.println("------------------ MENU ------------------");
            System.out.println("");
            System.out.println("""
                               [1] Insert product
                               [2] Update description product 
                               [3] Remove product 
                               [4] Display all products 
                               [5] Display all products by sorting
                               [6] Display all products by classification
                               [7] Add product item to stock 
                               [8] Remove product item from stock
                               [9] EXIT
                               """);
            System.out.println("------------------------------------------");
            System.out.println("");

            try {
                System.out.println("Input an option from the menu: ");
                option = input.nextInt();

                if (option >= 1 && option <= 9) {
                    optionValid = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid option, try again... ");
                input.next();
            }
        }

        return option;

    }

    /**
     * It allows inputting a description and classification product
     * throws an exception if the description or classification are blank
     */
    public static boolean insertProduct(Scanner input) {

        boolean insertedProduct = false;
        String product;
        int classificationProduct = 1;
        
        printProduct();

        while (!insertedProduct) {

            System.out.println("");
            System.out.println("Input the product description: ");

            try {
                product = input.next();

                if (!product.equals("") || !product.equals(" ")) {

                    System.out.println("");
                    System.out.println("---------- PRODUCT CLASSIFICATION ----------");
                    System.out.println("");
                    System.out.println("""
                               [1] First line
                               [2] Regular line
                               [3] Second line 
                               """);
                    System.out.println("-------------------------------------------");

                    try {
                        System.out.println("");
                        System.out.println("Input an option from the classification: ");
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

                            System.out.println("The product was successfully registered!");
                            productList.add(product);
                            productStockList.add(0);
                            insertedProduct = true;
                            hasRegisteredProduct = true;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid classification, try again...");
                        input.nextLine();
                    }

                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid classification, try again...");
                input.nextLine();
            }

        }

        return insertedProduct;

    }

    /**
     * It allows updating only the description product
     * throws an exception if the description is blank
     */
     public static boolean updateProduct(Scanner input) {

        boolean updatedProduct = false;
        int productID;
        String productDescription;
        
        printProduct();
       
        while (!updatedProduct) {

            System.out.println("");
            System.out.println("Input the product ID to be updated: ");

            try {

                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    System.out.println("");
                    System.out.println("Input the new description product to be updated: ");

                    try {
                        productDescription = input.next();

                        if (!productDescription.equals("") || !productDescription.equals(" ")) {
                            productList.set(productID, productDescription);
                            updatedProduct = true;
                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid product description! Try again... ");
                        input.nextLine();
                    }

                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Invalid product ID! Try again... ");
                input.nextLine();
            }

        }

        return updatedProduct;
    }

    /**
     * When remove product, it removes the product: ID, description, classification and stock
     * throws an exception if the ID not found
     */
    public static boolean removeProduct(Scanner input) {

        boolean deletedProduct = false;
        int productID;

        printProduct();

        while (!deletedProduct) {

            System.out.println("");
            System.out.println("Input the product ID to be removed: ");

            try {

                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    productList.remove(productID);
                    productClassificationList.remove(productID);
                    productStockList.remove(productID);
                    deletedProduct = true;

                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Invalid product ID! Try again... ");
                input.nextLine();
            }

        }

        return deletedProduct;

    }

    /**
     * It shows a list product with the info:
     * ID, description, classification and stock
     * throws an exception if there is no product on the list
     */
    public static void printProduct() {

        System.out.println("");
        System.out.println("ID | CLASSIFICATION  | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

        if(!productList.isEmpty()) {

            for (int i = 0; i < productList.size(); i++) {

                System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                        i,
                        productClassificationList.get(i),
                        productList.get(i),
                        productStockList.get(i)
                );

            }
            
            hasRegisteredProduct = true;
        } else {            
            System.out.println("There is no product registered!");                    
        }

    }

    /**
     * It shows a list product sorted by ID with the info:
     * ID, description, classification and stock
     * throws an exception if there is no product on the list
     */
    public static boolean printProductSorting(Scanner input) {
        
        boolean productSorted = false;
        int option;
        
        System.out.println("");
        System.out.println("[1] Sort ASC by product ID");
        System.out.println("[2] Sort DESC by product ID");
        System.out.println("[3] Back to the menu");
        
        while(!productSorted) {
            
            System.out.println("");
            System.out.println("Input an option to sort info: ");
            option = input.nextInt();
            
            switch (option) {
                case 1:                    
                    printProduct();
                    productSorted = true;
                    break;
                case 2:
                    if (!productList.isEmpty()) {
                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION  | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                                    i,
                                    productClassificationList.get(i),
                                    productList.get(i),
                                    productStockList.get(i));
                        }
                    } else {
                        System.out.println("There is no product registered!");
                    }
                    productSorted = true;
                    break;
                case 3:
                    System.out.println("Back to the menu...");
                    productSorted = true;
                    break;
                default: 
                    System.out.println("[WARNING] Invalid option. Try again...");
                    input.next();
                            
            }            
            
        }
        
        return productSorted;   
    }

    /**
     * It shows a list product filtered by classification with the info:
     * ID, description, classification and stock
     * throws an exception if there is no product on the list
     */
    public static boolean printProductClassification(Scanner input) {

        boolean classifiedProduct = false;
        int option;

        System.out.println("");
        System.out.println("---------- FILTER BY PRODUCT CLASSIFICATION ----------");
        System.out.println("");
        System.out.println("""
                               [1] First line
                               [2] Regular line
                               [3] Second line
                               [4] Sorting by all of them
                               [4] Back to the menu
                               """);
        System.out.println("-------------------------------------------");


        while(!classifiedProduct) {

            System.out.println("");
            System.out.println("Input an option to filter info: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    if ((!productList.isEmpty()) && productClassificationList.contains("First line")) {
                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION  | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if ("First line".equals(productClassificationList.get(i))) {

                                System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productStockList.get(i));
                            }
                        }
                    } else {
                        System.out.println("There is no product registered with First line classification!");
                    }
                    classifiedProduct = true;
                    break;
                case 2:
                    if ((!productList.isEmpty()) && productClassificationList.contains("Regular line")) {

                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION  | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if ("Regular line".equals(productClassificationList.get(i))) {

                                System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productStockList.get(i));
                            }
                        }
                    } else {
                        System.out.println("There is no product registered with Regular line classification!");
                    }
                    classifiedProduct = true;
                    break;
                case 3:
                    if ((!productList.isEmpty()) && productClassificationList.contains("Second line")) {

                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION  | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if ("Second line".equals(productClassificationList.get(i))) {

                                System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productStockList.get(i));
                            }
                        }
                    } else {
                        System.out.println("There is no product registered with Second line classification!");
                    }
                    classifiedProduct = true;
                    break;
                case 4:
                    if ((!productList.isEmpty()) && productClassificationList.contains("First line") || productClassificationList.contains("Regular line") || productClassificationList.contains("Second line")) {

                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION  | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if ("First line".equals(productClassificationList.get(i))) {

                                System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productStockList.get(i));
                            }
                        }

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if ("Regular line".equals(productClassificationList.get(i))) {

                                System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productStockList.get(i));
                            }
                        }

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if ("Second line".equals(productClassificationList.get(i))) {

                                System.out.printf("%-2s | %-15s | %-20s | %-18s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productStockList.get(i));
                            }
                        }
                    } else {
                        System.out.println("There is no product registered with First line classification!");
                    }
                    classifiedProduct = true;
                    break;
                case 5:
                    System.out.println("Back to the menu...");
                    classifiedProduct = true;
                    break;
                default:
                    System.out.println("[WARNING] Invalid option. Try again...");
                    input.next();

            }

        }

        return classifiedProduct;

    }

    /**
     * It allows adding the quantity of the items of the product in stock:
     * throws an exception if product ID is blank or quantity is invalid
     */
    public static boolean addItemStock(Scanner input) {

        boolean itemAddedStock = false;
        int productID;
        int numberItems;
        int numberItemsCurrent;
        int numberItemsNew;

        printProduct();
        
        while (!itemAddedStock) {

            System.out.println("");
            System.out.println("Input the product ID to add item to stock: ");

            try {
                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    System.out.println("");
                    System.out.printf("Input how many items do you would to add stock of the product %s: ",
                            productList.get(productID)
                    );

                    try {
                        numberItems = input.nextInt();

                        if (numberItems == 0) {
                            System.out.println("No items have been added to the product.");
                            itemAddedStock = true;
                        } else if (numberItems > 0) {
                            numberItemsCurrent = productStockList.get(productID);
                            numberItemsNew = numberItemsCurrent + numberItems;
                            productStockList.set(productID, numberItemsNew);
                            System.out.println("Quantity of items were successfully added to stock!");
                            itemAddedStock = true;
                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid quantity of items. Try again... ");
                        input.nextLine();
                    }

                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid product ID! Try again... ");
                input.nextLine();
            }

        }

        return itemAddedStock;

    }

    /**
     * It allows removing the quantity of the items of the product in stock:
     * throws an exception if product ID is blank or quantity is invalid
     */
    public static boolean removeItemInventory(Scanner input) {

        boolean ItemRemovedStock = false;
        int productID;
        int numberItems;
        int numberItemsCurrent;
        int numberItemsNew;
        
        printProduct();

        while (!ItemRemovedStock) {

            System.out.println("");
            System.out.println("Input the product ID to remove from stock: ");

            try {
                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    if (productStockList.get(productID) == 0) {
                        System.out.println("[WARNING] This product is alreary out of stock!");
                        ItemRemovedStock = true;

                    } else {

                        System.out.println("");
                        System.out.printf("Input how many items do you would to remove from stock of the product %s: ",
                            productList.get(productID));

                        try {

                            numberItems = input.nextInt();

                            if (numberItems == 0) {

                                System.out.println("[WARNING] Quantity ZERO cannot be removed from stock.");
                                ItemRemovedStock = true;

                            } else if (numberItems > 0) {

                                numberItemsCurrent = productStockList.get(productID);
                                numberItemsNew = numberItemsCurrent - numberItems;

                                if (numberItemsNew < 0) {
                                    System.out.println("""
                                                       [ATTENTION] The quantity of items reported was HIGHER than the quantity of items in stock. 
                                                       As a result, the product's stock has been reset to zero so as not to become negative, in accordance with Compliance rules. 
                                                       """);
                                    productStockList.set(productID, 0);
                                } else {
                                    productStockList.set(productID, numberItemsNew);
                                }

                                System.out.println("Quantity of items were successfully removed from stock!!");
                                ItemRemovedStock = true;

                            } else {
                                throw new Exception();                               
                            }

                        } catch (Exception e) {
                            System.out.println("Invalid quantity of items. Try again...");
                            input.nextLine();
                        }

                    }
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid product ID! Try again... ");
                input.nextLine();
            }

        }

        return ItemRemovedStock;
    }

}
