/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tiendaexpress;


import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Marco Jacho 
 */
public class Tiendaexpress {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<Double> carrito = new ArrayList<>();
            int opcion;
            
            do {
                mostrarMenu();
                opcion = leerOpcion(sc);
                
                switch (opcion) {
                    case 1 -> agregarProducto(carrito, leerPrecioProducto(sc));
                    case 3 -> pagar(carrito, sc);
                    case 4 -> System.out.println("Gracias por visitar Tienda Express.");
                    default -> System.out.println("Opción inválida. Intenta nuevamente.");
                }
                
            } while (opcion != 4);
        }
    }

    // Muestra el menú principal
    static void mostrarMenu() {
        System.out.println("\n--- MENÚ TIENDA EXPRESS ---");
        System.out.println("1. Agregar producto");
        System.out.println("3. Pagar");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
    }

    // Lee la opción del usuario
    static int leerOpcion(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }

    // Solicita el precio del producto
    static double leerPrecioProducto(Scanner sc) {
        System.out.print("Ingresa el precio del producto: ");
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido. Intenta nuevamente.");
            return leerPrecioProducto(sc); // Llamada recursiva
        }
    }

    // Agrega un producto al carrito
    static void agregarProducto(List<Double> carrito, double precio) {
        carrito.add(precio);
        System.out.println("Producto agregado: $" + precio);
    }

    // Calcula el total con impuesto
    static double total(double base, double impuesto) {
        return base + (base * impuesto);
    }

    // Valida correo básico (contiene '@' y '.')
    static boolean validarCorreo(String correo) {
        return correo.contains("@") && correo.contains(".");
    }

    // Solicita correo y confirma compra
    static void confirmarCompra(String correo, double total) {
        System.out.println("\n--- COMPRA CONFIRMADA ---");
        System.out.println("Correo: " + correo);
        System.out.printf("Total pagado: $%.2f\n", total);
        System.out.println("Gracias por tu compra en Tienda Express.");
    }

    // Lógica de pago
    static void pagar(List<Double> carrito, Scanner sc) {
        if (carrito.isEmpty()) {
            System.out.println("Tu carrito está vacío.");
            return;
        }

        double subtotal = 0;
        for (double precio : carrito) {
            subtotal += precio;
        }

        double impuesto = 0.15; // 15% IVA
        double totalConImpuesto = total(subtotal, impuesto);

        // Reporte
        System.out.println("\n--- REPORTE DE COMPRA ---");
        System.out.println("Productos comprados: " + carrito.size());
        System.out.printf("Subtotal: $%.2f\n", subtotal);
        System.out.printf("Total con IVA (15%%): $%.2f\n", totalConImpuesto);

        // Validar correo
        String correo;
        do {
            System.out.print("Ingresa tu correo para confirmar compra: ");
            correo = sc.nextLine();
            if (!validarCorreo(correo)) {
                System.out.println("Correo inválido. Intenta de nuevo.");
            }
        } while (!validarCorreo(correo));

        confirmarCompra(correo, totalConImpuesto);
        carrito.clear(); // Vaciar carrito después del pago
    }
}
    

