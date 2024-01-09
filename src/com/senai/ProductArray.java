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

        // login
        login(input);

        // menu 
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
<<<<<<< HEAD
                    }
                    break;
                case 6:
                    if (hasRegisteredProduct) {
                        printProductClassification(input);
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
=======
>>>>>>> 6264405306cba1582221c7748266c7cb657202c0
                    }
                    break;
                case 7:
                    if (hasRegisteredProduct) {
                        addInventory(input);
                    } else {
                        System.out.println("There is no product registered. You should do it first!");
                    }
                    break;
                case 8:
                    if (hasRegisteredProduct) {
                        removedInventory(input);
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

    public static boolean login(Scanner input) {

        String userDataBase = "admin";
        String passwordDataBase = "12345";
        String user = "";
        String password = "";
        boolean loginValid = false;

        while (!loginValid) {

            try {
                System.out.println("");
                System.out.println("Input the username: ");
<<<<<<< HEAD
                user = input.nextLine();

                System.out.println("");
                System.out.println("Input the password: ");
                password = input.nextLine();
=======
                user = entrada.nextLine();

                System.out.println("");
                System.out.println("Input the password: ");
                password = entrada.nextLine();
>>>>>>> 6264405306cba1582221c7748266c7cb657202c0

                if (userDataBase.equals(user) && passwordDataBase.equals(password)) {
                    loginValid = true;
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("The username and/or passowrd are invalided! Try again...");
                input.next();
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
                            productInventoryList.add(0);
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

    public static void printProduct() {

        System.out.println("");
        System.out.println("ID | CLASSIFICATION | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");
        //Collections.sort(ProductArray.productList);
        //System.out.println(ProductArray.productList);
        
        if(!productList.isEmpty()) {

            for (int i = 0; i < productList.size(); i++) {

                System.out.printf("%s | %s | %s | %s \n",
                        i,
                        productClassificationList.get(i),
                        productList.get(i),
                        productInventoryList.get(i)
                );

            }
            
            hasRegisteredProduct = true;
        } else {            
            System.out.println("There is no product registered!");                    
        }

    }
    
    public static boolean printProductSorting(Scanner input) {
        
        boolean productSorted = false;
        int option;
        
        System.out.println("");
<<<<<<< HEAD
        System.out.println("[1] Sort ASC by product ID");
        System.out.println("[2] Sort DESC by product ID");
        System.out.println("[3] Back to the menu");
=======
        System.out.println("[1] Sort ASC by product description");
        System.out.println("[2] Sort DESC by product description");
        System.out.println("[3] Sort DESC by product ID");
        System.out.println("[4] Back to the menu");
>>>>>>> 6264405306cba1582221c7748266c7cb657202c0
        
        while(!productSorted) {
            
            System.out.println("");
            System.out.println("Input an option to sort info: ");
            option = input.nextInt();
            
            switch (option) {
                case 1:                    
<<<<<<< HEAD
                    printProduct();
                    productSorted = true;
                    break;
                case 2:
                    if (!productList.isEmpty()) {
                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            System.out.printf("%s | %s | %s | %s \n",
                                    i,
                                    productClassificationList.get(i),
                                    productList.get(i),
                                    productInventoryList.get(i));
                        }
                    } else {
                        System.out.println("There is no product registered!");
                    }
                    productSorted = true;
                    break;
                case 3:
=======
                    //Collections.sort(productList);
                    //printProduct(); 
                    produtoOrdenado = true;
                    break;
                case 2:
                    //Collections.sort(productList, Collections.reverseOrder());
                    //printProduct();   
                    
                    produtoOrdenado = true;
                    break;
                case 3:
                    //Collections.sort(productList, Comparator.reverseOrder());
                    //printProduct();                                        
                    produtoOrdenado = true;
                    break;
                case 4:
>>>>>>> 6264405306cba1582221c7748266c7cb657202c0
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
                               [4] Sorting all of them
                               [4] Back to the menu
                               """);
        System.out.println("-------------------------------------------");

        
        while(!classifiedProduct) {
            
            System.out.println("");
            System.out.println("Input an option to filter info: ");
            option = input.nextInt();
            
            switch (option) {
                case 1:                    
                    if (!productList.isEmpty() && productClassificationList.contains("First line")) {
                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if (productClassificationList.contains("First line")) {

                                System.out.printf("%s | %s | %s | %s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productInventoryList.get(i));
                            }
                        }
                    } else {
                        System.out.println("There is no product registered with First line classification!");
                    }
                    classifiedProduct = true;
                    break;
                case 2:
                    if (!productList.isEmpty() && productClassificationList.contains("Regular line")) {
                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if (productClassificationList.contains("Regular line")) {

                                System.out.printf("%s | %s | %s | %s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productInventoryList.get(i));
                            }
                        }
                    } else {
                        System.out.println("There is no product registered with First line classification!");
                    }
                    classifiedProduct = true;
                    break;
                case 3:
                    if (!productList.isEmpty() && productClassificationList.contains("Second line")) {
                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if (productClassificationList.contains("Regular line")) {

                                System.out.printf("%s | %s | %s | %s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productInventoryList.get(i));
                            }
                        }
                    } else {
                        System.out.println("There is no product registered with First line classification!");
                    }
                    classifiedProduct = true;
                    break;
                case 4:
                    if (!productList.isEmpty() && productClassificationList.contains("First line")) {
                        System.out.println("");
                        System.out.println("ID | CLASSIFICATION | PRODUCT DESCRIPTION | ITEMS IN INVENTORY");

                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if (productClassificationList.contains("First line")) {

                                System.out.printf("%s | %s | %s | %s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productInventoryList.get(i));
                            }
                        }
                    } else if (!productList.isEmpty() && productClassificationList.contains("Regular line")) {
                        
                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if (productClassificationList.contains("Regular line")) {

                                System.out.printf("%s | %s | %s | %s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productInventoryList.get(i));
                            }
                        }
                    } else if (!productList.isEmpty() && productClassificationList.contains("Second line")) {
                        
                        for (int i = productList.size() - 1; i >= 0; i--) {

                            if (productClassificationList.contains("Second line")) {

                                System.out.printf("%s | %s | %s | %s \n",
                                        i,
                                        productClassificationList.get(i),
                                        productList.get(i),
                                        productInventoryList.get(i));
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
                    productInventoryList.remove(productID);
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

    public static boolean addInventory(Scanner input) {

        boolean addedInventory = false;
        int productID;
        int numberItems;
        int numberItemsCurrent;
        int numberItemsNew;

        printProduct();
        
        while (!addedInventory) {

            System.out.println("");
            System.out.println("Input the product ID to add inventory: ");

            try {
                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    System.out.println("");
                    System.out.printf("Input how many items do you would to add inventory of the product %s: ",
                            productList.get(productID)
                    );

                    try {
                        numberItems = input.nextInt();

                        if (numberItems == 0) {
                            System.out.println("No items have been added to the product.");
                            addedInventory = true;
                        } else if (numberItems > 0) {
                            numberItemsCurrent = productInventoryList.get(productID);
                            numberItemsNew = numberItemsCurrent + numberItems;
                            productInventoryList.set(productID, numberItemsNew);
                            System.out.println("Quantity of items were successfully added!");
                            addedInventory = true;
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

        return addedInventory;

    }

    public static boolean removedInventory(Scanner input) {

        boolean removedIntentory = false;
        int productID;
        int numberItems;
        int numberItemsCurrent;
        int numberItemsNew;
        
        printProduct();

        while (!removedIntentory) {

            System.out.println("");
            System.out.println("Input the product ID to remove inventory: ");

            try {
                productID = input.nextInt();

                if (productID >= 0 && productID < productList.size()) {

                    if (productInventoryList.get(productID) == 0) {
                        System.out.println("[WARNING] This product is alreary out of stock!");
                        removedIntentory = true;

                    } else {

                        System.out.println("");
                        System.out.printf("Input how many items do you would to remove inventory of the product %s: ",
                            productList.get(productID));

                        try {

                            numberItems = input.nextInt();

                            if (numberItems == 0) {

                                System.out.println("[WARNING] Quantity ZERO cannot be removed from inventory.");
                                removedIntentory = true;

                            } else if (numberItems > 0) {

                                numberItemsCurrent = productInventoryList.get(productID);
                                numberItemsNew = numberItemsCurrent - numberItems;

                                if (numberItemsNew < 0) {
                                    System.out.println("""
                                                       [ATTENTION] The quantity of items reported was HIGHER than the quantity of items in inventory. 
                                                       As a result, the product's inventory has been reset to zero so as not to become negative, in accordance with Compliance rules. 
                                                       """);
                                    productInventoryList.set(productID, 0);
                                } else {
                                    productInventoryList.set(productID, numberItemsNew);
                                }

                                System.out.println("Quantity of items were successfully removed!!");
                                removedIntentory = true;

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

        return removedIntentory;
    }

}
