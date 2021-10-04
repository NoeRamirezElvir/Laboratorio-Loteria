package hn.edu.ujcv;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
       Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        try {
            String[] nombres = new String[15];
            String[] venderMensual = new String[101];
            int[][] venderDiario = new int[15][5];
            int[] juegoDiario = new int[5];

            llenarMatriz(venderDiario);
            llenarVenderMensual(venderMensual);
            int opcion ,k = 0;
            String respuesta;
            System.out.println("         -- Loteria El Premio Gordo -- ");
            System.out.print("Bienvenido, ");
            do {
                System.out.println("seleccione una opcion:");
                System.out.print("1 - Vender Diario.   ");
                System.out.println("4 - Juego Diario.");
                System.out.print("2 - Vender Semanal.  ");
                System.out.println("5 - Juego Semanal.");
                System.out.print("3 - Vender Mensual.  ");
                System.out.println("6 - Juego Mensual.");
                System.out.print("> ");
                opcion = teclado.nextInt();
                switch (opcion){
                    case 1:
                        //Vender diario
                        venderDiario(nombres,venderDiario,k);
                        k++;
                        break;
                    case 2:
                        //Vender semanal


                        break;
                    case 3:
                        //Vender mensual
                        venderMensual(venderMensual);

                        break;

                    case 4:
                        //Juego diario
                        juegarDiario(venderDiario,juegoDiario,nombres);

                        break;

                    case 5:
                        //Juego semanal
                        imprimir(venderMensual);

                        break;
                    case 6:
                        //Juego mensual
                        jugarMensual(venderMensual);

                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }

                //
                System.out.print("Desea continuar S/N: ");
                respuesta = teclado.next();
                //

            }while (respuesta.equalsIgnoreCase("S"));
        }catch (Exception e){
            System.err.println("Error" + e.getMessage());
        }
    }
    //Numero Random
    private static int obtenerNumeroAzar(int minimo,int maximo){
        return (int) (Math.random()*((maximo-minimo)-1)) + minimo;
    }
    //Vender Diario
    private static void venderDiario(String[] nombres, int[][] numeros, int k){
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        int num,contador,numeroNuevo;
        System.out.print("Escriba el nombre: ");
        nombres[k] = teclado.next();
        for (int i = 0; i < 5; i++) {
            System.out.print("Ingrese el numero " + (i+1) +  " : ");
            num = teclado.nextInt();
            contador = verificarNumeros(numeros,num,k);
            if( contador > 0){
                System.out.println("El numero no esta disponible");
                System.out.println("Ingrese un nuevo numero: ");
                numeroNuevo = teclado.nextInt();
                numeros[k][i] = numeroNuevo;
            }else{
               numeros[k][i] = num;
            }
        }
        //System.out.println("  --->  Ticket #" + (k+1));
    }
    //
    private static void llenarMatriz(int[][] numeros){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                numeros[i][j] = 0;
            }
        }
    }
    private static void llenarVenderMensual(String[] nombres){
        for (int i = 0; i < 101; i++) {
            nombres[i] = "-";
        }
    }
    //
    private static int  verificarNumeros(int[][] numeros,int num,int k){
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            if (num == numeros[k][i])
                contador++;
        }
        return contador;

    }
    //Juego Diario
    private static void juegarDiario(int[][] numeros, int[] juego,String[] clientes){
        int fila;
        String ganadores;
        boolean condicion = false;
        for (int i = 0; i < 5; i++) {
            juego[i] = obtenerNumeroAzar(0,100);
            //juego[i] = 45+i;
        }
           // con = numeros.length;
        for (int i = 0; i < 15; i++) {
            fila = numeros[i][0];
            if (fila != 0){
                //Para visualizar la comparacion de los numeros
               /*/ System.out.print("Nombre: " + clientes[i]);
                System.out.print(" Numeros: ");
                for (int j = 0; j < 5; j++) {
                    System.out.print(numeros[i][j] + " ");
                }
                System.out.print("---");
                for (int j = 0; j < 5; j++) {
                    System.out.print(juego[j] + " ");
                }*/
                ganadores =  compararNumeros(numeros,juego,i,clientes);
                if (!ganadores.equals(" > No hay ganadores.")){
                    System.out.println(ganadores);
                    //condicion = false;

                }else if (ganadores.equals(" > No hay ganadores.") && !condicion){
                    System.out.println(ganadores);
                    condicion = true;
                }
            }
        }
    }
    //
    private static String compararNumeros(int[][] numeros,int[] juego,int fila,String[] clientes){
        int contador = 0,numeroCliente,numeroRandom;
        int[] cantidadNumeros = new int[5];
        String ganadores = " > No hay ganadores.";
        for (int i = 0; i < 5; i++) {
            numeroCliente = numeros[fila][i];
            for (int j = 0; j < 5; j++) {
                numeroRandom = juego[j];
                //System.out.println(numeroCliente + " - " + numeroRandom);
                if (numeroCliente == numeroRandom){
                    cantidadNumeros[i] += 1;
                }else{
                    cantidadNumeros[i] += 0;
                }
            }
        }
        //ganadores
        for (int i = 0; i < 5; i++) {
            contador += cantidadNumeros[i];
        }
        if (contador<3){
            ganadores = " > No hay ganadores.";
        } else if (contador == 3){
            ganadores = "Felicidades " + clientes[fila] +", su premio es de 5,000 lps.";
        }else if (contador == 4){
            ganadores = "Felicidades " + clientes[fila] +", su premio es de 10,000 lps.";
        }else if (contador==5){
            ganadores = "Felicidades " + clientes[fila] +", su premio es de 100,000 lps.";
        }
        //System.out.println(contador);
        return ganadores;
    }

    private static void  venderMensual(String[] venderMensual){
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        String nombre;
        int numero;
        boolean condicion = true;
        System.out.print("Ingrese el nombre: ");
        nombre = teclado.next();
       do {
           System.out.print("Ingrese el numero: ");
           numero = teclado.nextInt();
           if (venderMensual[numero].equals("-")){
               venderMensual[numero] = nombre;
               condicion = true;
           }else if(!venderMensual[numero].equals("-")){
               System.out.println("El numero no esta disponible");
               condicion = false;
           }

       }while(!condicion);
    }
    private static void imprimir(String[] vender){
        for (int i = 0; i < 101; i++) {
            System.out.println(vender[i]);
        }
    }
    private static void jugarMensual(String[] venderMensual){
        int numeroRandom;
        String nombre;
        numeroRandom = obtenerNumeroAzar(0,100);
       // numeroRandom = 50;
        System.out.println("El numero es: " + numeroRandom);
        if (!venderMensual[numeroRandom].equals("-")){
            nombre = venderMensual[numeroRandom];
            System.out.println("Felicidades " + nombre + ", ha ganado el juego.");
            System.out.println("Su premio es de 10,000 lps.");
        }else if (venderMensual[numeroRandom].equals("-")){
            System.out.println("No hay ganador");
        }
    }












    //
}
