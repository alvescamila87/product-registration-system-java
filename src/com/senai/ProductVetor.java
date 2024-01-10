
package com.senai;

import java.util.Scanner;

/**
 *
 * @author Camila
 */

public class ProductVetor {
    
    public static final int MAX_PRODUCTS = 50;
    private static int countProduct = 0;
    private static String[] tableProducts = new String[MAX_PRODUCTS];
    private static String[] tableClassificationProducts = new String[MAX_PRODUCTS];
    private static int[] tableInventoryProducts = new int[MAX_PRODUCTS];
    private static boolean findProduct = false;
    
    
    // Login autentication method
    public static void login() {

        Scanner keyboard = new Scanner(System.in);

        String usernameDataBase = "admin";
        String passwordDataBase = "123456";

        String username;
        String password;

        int option = 1;

        do {

            System.out.println();
            System.out.print("Type your username: ");
            username = keyboard.next();

            System.out.println();
            System.out.print("Type your password: ");
            password = keyboard.next();
            keyboard.nextLine();

            if (username.equals(usernameDataBase) && password.equals(passwordDataBase)) {

                System.out.println();
                System.out.println("Welcome to the Product Store");
                System.out.println();

                option = 1;
                
                // After authenticating user, the menu was displayed
                showMenu();

            } else {

                System.out.println();
                System.out.println("[ERROR] Invalid username and/or password!");
                System.out.println();
                System.out.println("Would you like to try again?");
                System.out.println("[1] EXIT");
                System.out.println("[2] TRY AGAIN");
                System.out.println();

                System.out.print("Type an option above: ");
                option = keyboard.nextInt();
                keyboard.nextLine();

                if (option == 1) {

                    System.out.println();
                    System.out.println("Thanks for coming...");
                    System.out.println("BYE-BYE");
                    System.out.println();
                }

            }

        } while (option == 2);

    }
    
    // The menu method that displays which operations the program has
    public static void showMenu() {

        Scanner keyboard = new Scanner(System.in);

        ProductVetor products = new ProductVetor();

        int optionMenu;
        
        do {
            System.out.println();
            System.out.println("---------------------- MENU ----------------------");
            System.out.println();
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
            System.out.println("--------------------------------------------------");
            System.out.println();

            System.out.println("Type an option from menu: ");
            optionMenu = keyboard.nextInt();

            switch (optionMenu) {

                case 1:
                    ProductVetor.insertProduct();
                    break;
                case 2:
                    ProductVetor.updateProduct();
                    break;
                case 3:
                    ProductVetor.removeProduct();
                    break;
                case 4:
                    ProductVetor.displayProducts();
                    break;
                case 5: 
                    ProductVetor.displaySortingProducts();
                    break;
                case 6: 
                    ProductVetor.displayClassificationProducts();
                    break;
                case 7:
                    ProductVetor.addInventory();
                    break;
                case 8:
                    ProductVetor.removeInventory();
                    break;
                case 9:
                    System.out.println("");
                    System.out.println("The program is finishing.");
                    break;
                default:
                    System.out.println("[ERROR] Invalid option. Try again!");

            }

        } while (optionMenu != 9);
    }
    
    // Insert product method aims to allow inputing any products and include a 
    // classification due to line of the product, related the quality it.
    public static void insertProduct() {
        
        ProductVetor.displayProducts();

        String descriptionProduct = "";
        char classificationProduct = 0;
        

        Scanner keyboard = new Scanner(System.in);

        while ("".equals(descriptionProduct)) {
            
            System.out.println();
            System.out.println("Type the product description: ");
            descriptionProduct = keyboard.next();

            if (!"".equals(descriptionProduct)) {

                ProductVetor.tableProducts[ProductVetor.countProduct] = descriptionProduct;
                ProductVetor.tableInventoryProducts[ProductVetor.countProduct] = 0;
                break;

            } else {
                System.out.println("[ERROR] There is no description product. Try again.");
            }

        }

        while (classificationProduct == '\0') {

            System.out.println();
            System.out.println("[A] Gold line");
            System.out.println("[B] Premium line");
            System.out.println("[C] Regular line");
            System.out.println("");
            System.out.println("Type the classification of the product: ");
            classificationProduct = keyboard.next().charAt(0);
            
            char classificationProductUpperCase = Character.toUpperCase(classificationProduct);
            
            switch(classificationProductUpperCase) {
                case 'A': 
                    ProductVetor.tableClassificationProducts[ProductVetor.countProduct] = "Gold line";
                    ProductVetor.countProduct++;
                    break;
                case 'B': 
                    ProductVetor.tableClassificationProducts[ProductVetor.countProduct] = "Premium line";
                    ProductVetor.countProduct++;
                    break;
                case 'C': 
                    ProductVetor.tableClassificationProducts[ProductVetor.countProduct] = "Regular line";
                    ProductVetor.countProduct++;
                    break;
                default: 
                    System.out.println("");
                    System.out.println("[ERROR] There is no classification product like that. Try again.");
                    classificationProduct = '\0';
            }

        }

    }
    
    // Update product method aims to change only the description of 
    // the product, it does not allow to change its classification.
    public static void updateProduct() {
        
        ProductVetor.displayProducts();
        
        int idProduct;
        String descriptionProduct = "";
        ProductVetor.findProduct = false;
        
        Scanner keyboard = new Scanner(System.in);
       
        while (!ProductVetor.findProduct) {
            
            System.out.println();
            System.out.println("Type the ID of the product that would you like to change: ");
            idProduct = keyboard.nextInt();

            if (idProduct < 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                    
                System.out.println();
                System.out.println("[ERROR] There is no negative ID and/or non-existent ID.");
                break;

            } else if (idProduct >= 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                
                if(ProductVetor.tableProducts[idProduct] == null || ProductVetor.tableProducts[idProduct].trim().isEmpty()) {
                    
                    System.out.println();
                    System.out.println("[ERROR] There is no product registered.");
                    break;
                
                } else {
                    
                    while (descriptionProduct == null || descriptionProduct.trim().isEmpty()) {

                        System.out.println();
                        System.out.println("Type the new description product: ");
                        descriptionProduct = keyboard.next();

                        ProductVetor.tableProducts[idProduct] = descriptionProduct;
                        System.out.println();
                        System.out.println("The description product was updated successfully!");
                        ProductVetor.findProduct = true;
                        break;                       

                    }
                }                
            } else {
                
                if(ProductVetor.findProduct) {
                    break;
                }
            } 
        }        
   
    }
    
    // Remove product method aims to delete the product and its 
    // dependencies, such as classification and inventory
    public static void removeProduct() {
        
        ProductVetor.displayProducts();
        
        int idProduct;
        ProductVetor.findProduct = false;
        
        Scanner keyboard = new Scanner(System.in);
       
        while (!ProductVetor.findProduct) {
            
            System.out.println();
            System.out.println("Type the ID of the product that would you like to remove: ");
            idProduct = keyboard.nextInt();

            if (idProduct < 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                    
                System.out.println();
                System.out.println("[ERROR] There is no negative ID and/or non-existent ID.");
                break;

            } else if (idProduct >= 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                
                if(ProductVetor.tableProducts[idProduct] == null || ProductVetor.tableProducts[idProduct].trim().isEmpty()) {
                    
                    System.out.println();
                    System.out.println("[ERROR] There is no product registered.");
                    break;
                
                } else {
                    
                    ProductVetor.tableProducts[idProduct] = null;
                    ProductVetor.tableClassificationProducts[idProduct] = null;
                    ProductVetor.tableInventoryProducts[idProduct] = 0;
                    System.out.println();
                    System.out.println("The product was removed successfully!");
                    ProductVetor.findProduct = true;
                    break;                       

                    
                }   
                
            } else {
                
                if(ProductVetor.findProduct) {
                    break;
                }
            } 
        }      
    }
    
    // Display products method aims to show all the product inserted by the user like a list
    // It displays the ID, Classification, Description and Inventory about Product
    public static void displayProducts() {

        // Header
        System.out.println(""); 
        System.out.println("| ID | CLASSIFICATION | DESCRIPTION PRODUCT  | INVENTORY |");
        
        for (int i = 0; i < ProductVetor.countProduct; i++) {

            if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {

                System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
            }

        }

    }
    
    // Display sorting products method aims to show all the product sorted by ascending and descending ID list.
    // The user can select the sorting option 
    // It displays the ID, Classification, Description and Inventory about Product
    public static void displaySortingProducts() {
        
        int classificationOption = 0;
        
        Scanner keyboard = new Scanner(System.in);
        
        while(classificationOption != 3) {
            System.out.println("");
            System.out.println("-------------------- LIST OF PRODUCTS -------------------");
            System.out.println("");
            System.out.println("[1] Display sorted in order list of products - ASC       ");
            System.out.println("[2] Display sorted in descending list of products - DESC ");
            System.out.println("[3] Back to the menu                                     ");
            System.out.println("");
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            System.out.println("Type an option from classification product: ");
            classificationOption = keyboard.nextInt();
            
            switch(classificationOption) {
                
                case 1: 
                    ProductVetor.displayProducts();
                    classificationOption = 3;
                    break;
                case 2:
                    
                    // Header
                    System.out.println("");
                    System.out.println("| ID | CLASSIFICATION | DESCRIPTION PRODUCT  | INVENTORY |");

                    for (int i = ProductVetor.countProduct; i >= 0; i--) {

                        if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {

                            System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
                        }

                    }
                    classificationOption = 3;
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Back to the menu...");
                    break;
                default: 
                    System.out.println("");
                    System.out.println("[ERROR] Invalid option. Try again!");
                    
            }
        }
        
        
    }
    
    // Display classification products method aims to show all the product filtered ascending by its 
    // classification product.
    // The user can select the sorting option 
    // It displays the ID, Classification, Description and Inventory about Product
    public static void displayClassificationProducts() {
        
        int classificationOption = 0;
        
        Scanner keyboard = new Scanner(System.in);
        
        while(classificationOption != 5) {
            System.out.println("");
            System.out.println("----------- FILTER BY CLASSIFICATION PRODUCT ------------");
            System.out.println("");
            System.out.println("[1] Gold line");
            System.out.println("[2] Premium line");
            System.out.println("[3] Regular line");
            System.out.println("[4] Order classication products");
            System.out.println("[5] Back to the menu                                     ");
            System.out.println("");
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            System.out.println("Type an option to filter classification products: ");
            classificationOption = keyboard.nextInt();
            
            switch(classificationOption) {
                case 1: 
                    // Header
                    System.out.println("");
                    System.out.println("| ID | CLASSIFICATION | DESCRIPTION PRODUCT  | INVENTORY |");

                    for (int i = 0; i < ProductVetor.countProduct; i++) {

                        if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {
                            
                            if("Gold line".equals(ProductVetor.tableClassificationProducts[i])) {
                                System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
                            }
                            
                        }

                    }
                    classificationOption = 5;
                    break;
                case 2:                   
                    // Header
                    System.out.println("");
                    System.out.println("| ID | CLASSIFICATION | DESCRIPTION PRODUCT  | INVENTORY |");

                    for (int i = 0; i < ProductVetor.countProduct; i++) {

                        if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {
                            
                            if("Premium line".equals(ProductVetor.tableClassificationProducts[i])) {
                                System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
                            }
                            
                        }

                    }
                    classificationOption = 5;
                    break;
                case 3:                    
                    // Header
                    System.out.println("");
                    System.out.println("| ID | CLASSIFICATION | DESCRIPTION PRODUCT  | INVENTORY |");

                    for (int i = 0; i < ProductVetor.countProduct; i++) {

                        if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {
                            
                            if("Regular line".equals(ProductVetor.tableClassificationProducts[i])) {
                                System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
                            }
                            
                        }

                    }
                    classificationOption = 5;
                    break;
                case 4:
                    // Header
                    System.out.println("");
                    System.out.println("| ID | CLASSIFICATION | DESCRIPTION PRODUCT  | INVENTORY |");

                    for (int i = 0; i < ProductVetor.countProduct; i++) {

                        if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {
                            
                            if("Gold line".equals(ProductVetor.tableClassificationProducts[i])) {
                                System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
                            }
                            
                        }

                    }
                    
                    for (int i = 0; i < ProductVetor.countProduct; i++) {

                        if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {
                            
                            if("Premium line".equals(ProductVetor.tableClassificationProducts[i])) {
                                System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
                            }
                            
                        }

                    }
                    
                    for (int i = 0; i < ProductVetor.countProduct; i++) {

                        if (!"".equals(ProductVetor.tableProducts[i]) && ProductVetor.tableProducts[i] != null) {
                            
                            if("Regular line".equals(ProductVetor.tableClassificationProducts[i])) {
                                System.out.printf("| %-2d | %-14s | %-20s | %-9d \n", i, ProductVetor.tableClassificationProducts[i], ProductVetor.tableProducts[i], ProductVetor.tableInventoryProducts[i]);
                            }
                            
                        }

                    }
                    classificationOption = 5;
                    break;               
                case 5:
                    System.out.println("");
                    System.out.println("Back to the menu...");
                    break;
                default: 
                    System.out.println("");
                    System.out.println("[ERROR] Invalid option. Try again!");
            }
        }
    }
    
    // Add inventory method aims to insert and add up the quantity of the item that 
    // each product has in the stock
    public static void addInventory() {
        
        ProductVetor.displayProducts();
        
        int idProduct;
        int quantityItemsProduct = -1;
        ProductVetor.findProduct = false;
        
        Scanner keyboard = new Scanner(System.in);
       
        while (!ProductVetor.findProduct) {
            
            System.out.println();
            System.out.println("Type the ID of the product that would you like to add items from inventory: ");
            idProduct = keyboard.nextInt();

            if (idProduct < 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                    
                System.out.println();
                System.out.println("[ERROR] There is no negative ID and/or non-existent ID.");
                break;

            } else if (idProduct >= 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                
                if(ProductVetor.tableProducts[idProduct] == null || ProductVetor.tableProducts[idProduct].trim().isEmpty()) {
                    
                    System.out.println();
                    System.out.println("[ERROR] There is no product registered.");
                    break;
                
                } else {
                    
                    while (quantityItemsProduct < 0 ) {
                        
                        System.out.println();
                        System.out.println("How many items of these product would you like to add: ");
                        quantityItemsProduct = keyboard.nextInt();
                        
                        if(quantityItemsProduct < 0) {
                            
                            System.out.println();
                            System.out.println("The quantity of the items must not be negative. Try again.");
                        
                        } else {
                            
                            ProductVetor.tableInventoryProducts[idProduct] = ProductVetor.tableInventoryProducts[idProduct] + quantityItemsProduct;
                            System.out.println();
                            System.out.println("The quantity was added to the product successfully!");
                            ProductVetor.findProduct = true;
                            break;
                            
                        }

                    }
                }                
            } else {
                
                if(ProductVetor.findProduct) {
                    break;
                }
            } 
        }
    }
    
    // Remove inventory method aims to remove all and descrease the quantity of the item that 
    // each product has in the stock
    // It's impossible for the stock to show a negative inventory
    public static void removeInventory() {
        
        ProductVetor.displayProducts();
        
        int idProduct;
        int quantityItemsProduct = 0;
        ProductVetor.findProduct = false;
        
        Scanner keyboard = new Scanner(System.in);
       
        while (!ProductVetor.findProduct) {
            
            System.out.println();
            System.out.println("Type the ID of the product that would you like to remove items from inventory: ");
            idProduct = keyboard.nextInt();

            if (idProduct < 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                    
                System.out.println();
                System.out.println("[ERROR] There is no negative ID and/or non-existent ID.");
                break;

            } else if (idProduct >= 0 || idProduct > ProductVetor.MAX_PRODUCTS) {
                
                if(ProductVetor.tableProducts[idProduct] == null || ProductVetor.tableProducts[idProduct].trim().isEmpty()) {
                    
                    System.out.println();
                    System.out.println("[ERROR] There is no product registered.");
                    break;
                
                } else if (ProductVetor.tableInventoryProducts[idProduct] == 0) {

                    System.out.println();
                    System.out.println("The inventory of this product is already zeroed.");
                    break;
                    
                } else {
                    
                    while (true) {
                        
                        System.out.println();
                        System.out.println("How many items of these product would you like to remove: ");
                        quantityItemsProduct = keyboard.nextInt();
                        
                        if(quantityItemsProduct < 0) {
                            
                            System.out.println();
                            System.out.println("The quantity of the items must not be negative. Try again!");
                        
                        } else if (quantityItemsProduct == 0) {
                            
                            System.out.println();
                            System.out.println("The quantity of the items typed was zeroed. There is no change found."); 
                            ProductVetor.findProduct = true;
                            break;
                            
                        } else if (ProductVetor.findProduct) {
                            break;
                            
                        } else {
                            
                            ProductVetor.tableInventoryProducts[idProduct] = ProductVetor.tableInventoryProducts[idProduct] - quantityItemsProduct;
                            
                            if(ProductVetor.tableInventoryProducts[idProduct] < 0) {
                                
                                ProductVetor.tableInventoryProducts[idProduct] = 0;
                                System.out.println();
                                System.out.println("[ATTENTION] The quantity was typed EXCEEDED the inventory ifo the product for removing!");
                                System.out.println("[ATTENTION] However, the product has its inventory reseted to zero, cause it's impossivel go to negative!");
                                 
                                
                            }

                            System.out.println();
                            System.out.println("The quantity was removed from the product successfully!");
                            ProductVetor.findProduct = true;
                            break;
                        }

                    }
                } 
                
            } else {
                
                if(ProductVetor.findProduct) {
                    break;
                }
            } 
        }
    }
    
    // Product
    public static void main(String[] args) {
        
        ProductVetor.login();

    }

}

