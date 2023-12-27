/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Camila
 */
public class ProductArray {
    
    private static ArrayList<String> listaProdutos = new ArrayList<>();
    private static ArrayList<String> listaClassificacaoProducos = new ArrayList<>();
    private static ArrayList<String> listaEstoqueProdutos = new ArrayList<>();
    private static int contadorProdutos = 0;
    
    public static void main(String[] args) {
        
    Scanner entrada = new Scanner (System.in);
    
    login(entrada);
    
        
    }
    
    // Login 
    public static void login(Scanner entrada) {
        
    
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
    
    }
    
}
