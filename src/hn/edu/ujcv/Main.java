package hn.edu.ujcv;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        int        numCliente = 0, opcion, k = 0;;
        String[]   nombres = new String[15];
        String[]   ClienteSemanal = new String[100];
        String[]   venderMensual = new String[101];
        int   []   juegoDiario = new int[5];
        int   []   juegoSemanal = new int [2];
        int   [][] venderDiario = new int[15][5];
        int   [][] venderSemanal = new int[100][2];
        try {
            llenarMatriz(venderDiario);
            llenarVenderMensual(venderMensual);
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
                switch (opcion) {
                    case 1:
                        //Vender diario
                        venderDiario(nombres, venderDiario, k);
                        k++;
                        break;
                    case 2:
                        //Vender semanal
                        venderSemanal(ClienteSemanal, numCliente,venderSemanal);
                        numCliente++;

                        break;
                    case 3:
                        //Vender mensual
                        venderMensual(venderMensual);

                        break;

                    case 4:
                        //Juego diario
                        jugarDiario(venderDiario, juegoDiario, nombres);

                        break;

                    case 5:
                        //Juego semanal
                        jugarSemanal(juegoSemanal,numCliente,venderSemanal,ClienteSemanal);
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

            } while (respuesta.equalsIgnoreCase("S"));
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
        }
    }

    //Numero Random
    private static int obtenerNumeroAzar(int minimo, int maximo) {
        return (int) (Math.random() * ((maximo - minimo) - 1)) + minimo;
    }

    //Vender Diario
    private static void venderDiario(String[] nombres, int[][] numeros, int k) {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        int num, contador, numeroNuevo;
        System.out.print("Escriba el nombre: ");
        nombres[k] = teclado.next();

        for (int i = 0; i < 5; i++) {
            System.out.print("Ingrese el numero " + (i + 1) + " : ");
            num = teclado.nextInt();
            //validacion
            if(num<0 || num>100 ) {
                do {
                    System.out.println("Numero invalido! Ingrese un numero Valido:");
                    num = teclado.nextInt();
                }while (num<0 || num>100);
            }
            contador = verificarNumeros(numeros, num, k);
            if (contador > 0) {
                System.out.println("El numero no esta disponible");
                System.out.println("Ingrese un nuevo numero: ");
                numeroNuevo = teclado.nextInt();
                numeros[k][i] = numeroNuevo;
            } else {
                numeros[k][i] = num;
            }
        }
        //System.out.println("  --->  Ticket #" + (k+1));
    }

    //Juego Diario
    private static void jugarDiario(int[][] numeros, int[] juego, String[] clientes) {
        int fila;
        String ganadores;
        boolean condicion = false;
        for (int i = 0; i < 5; i++) {
            juego[i] = obtenerNumeroAzar(0, 100);
            //juego[i] = 45+i;
        }
        // con = numeros.length;
        for (int i = 0; i < 15; i++) {
            fila = numeros[i][0];
            if (fila != 0) {
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
                ganadores = compararNumeros(numeros, juego, i, clientes);
                if (!ganadores.equals(" > No hay ganadores.")) {
                    System.out.println(ganadores);
                    //condicion = false;

                } else if (ganadores.equals(" > No hay ganadores.") && !condicion) {
                    System.out.println(ganadores);
                    condicion = true;
                }
            }
        }
    }

    //vender semanal
    private static void venderSemanal(String[] ClienteSemanal, int numCliente,int numerosSemanales[][]) {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        int numeroNuevo;
        boolean condicion = false;
        System.out.println("Cliente: " + (numCliente + 1));
        System.out.print("Escriba el nombre: ");
        ClienteSemanal[numCliente] = teclado.next();

        for (int i = 0; i < 2; i++) {
            System.out.print("Ingrese el numero " + (i + 1) + " (0-100): ");
            numerosSemanales[numCliente][i] = teclado.nextInt();
            //validacion
            if(numerosSemanales[numCliente][i]<0 || numerosSemanales[numCliente][i]>100 ) {
                do {
                    System.out.println("Numero invalido! Ingrese un numero Valido:");
                    numerosSemanales[numCliente][i] = teclado.nextInt();
                }while (numerosSemanales[numCliente][i]<0 || numerosSemanales[numCliente][i]>100);
            }
            if (verificarNumeros2(numerosSemanales, numCliente)) {
                do {
                    System.out.println("El numero no esta disponible" + "\nIngrese un nuevo numero: ");
                    numeroNuevo = teclado.nextInt();
                }while(numeroNuevo == numerosSemanales[numCliente][i]);
                numerosSemanales[numCliente][i] = numeroNuevo;
            }
            //prueba llenado correcto
            //System.out.println("Cliente " + (numCliente + 1) + " :" + ClienteSemanal[numCliente] + " Numero: " + numerosSemanales[numCliente][i]);
        }
    }

    //Juego semanal
    private static void jugarSemanal(int [] juego,int numCliente, int numerosSemanales[][],String[] ClienteSemanal) {
        boolean SinGanadores = false; //" > No hay ganadores.";
        for (int i = 0; i < 2; i++) {
            juego[i] = obtenerNumeroAzar(0, 100);
        }
        //impresion numeros premiados
        System.out.print("Numeros Premiados: ");
        for (int i = 0; i < 2; i++) {
            System.out.print(juego[i]+ "- ");
        }
        System.out.println("");
        for (int i = 0; i < numCliente; i++) {
            if (juego[0]== numerosSemanales[i][0] && juego[1]== numerosSemanales[i][1]) {
                System.out.println("Felicidades " + ClienteSemanal[i] + ", su premio es de 100,000 lps.");
            }else if (juego[0]== numerosSemanales[i][0]){
                System.out.println("Felicidades " + ClienteSemanal[i] + ", su premio es de 10,000 lps.");
            }else if ( juego[1]== numerosSemanales[i][1]) {
                System.out.println("Felicidades " + ClienteSemanal[i] + ", su premio es de 1,000 lps.");
            }else{
                SinGanadores = true;
            }
        }
        if (SinGanadores)
            System.out.println(" > No hay ganadores.");

        if (numCliente==0)
            System.out.println("No hay registros de ventas");
    }

    //vender Mensual
    private static void venderMensual(String[] venderMensual) {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        String nombre;
        int numero;
        boolean condicion = true;
        System.out.print("Ingrese el nombre: ");
        nombre = teclado.next();
        do {
            System.out.print("Ingrese el numero: ");
            numero = teclado.nextInt();
            //validacion
            if(numero<0 || numero>100 ) {
                do {
                    System.out.println("Numero invalido! Ingrese un numero Valido:");
                    numero = teclado.nextInt();
                }while (numero<0 || numero>100);
            }

            if (venderMensual[numero].equals("-")) {
                venderMensual[numero] = nombre;
                condicion = true;
            } else if (!venderMensual[numero].equals("-")) {
                System.out.println("El numero no esta disponible");
                condicion = false;
            }

        } while (!condicion);
    }

    //jugar mensual
    private static void jugarMensual(String[] venderMensual) {
        int numeroRandom;
        String nombre;
        numeroRandom = obtenerNumeroAzar(0, 100);
        // numeroRandom = 50;
        System.out.println("El numero es: " + numeroRandom);
        if (!venderMensual[numeroRandom].equals("-")) {
            nombre = venderMensual[numeroRandom];
            System.out.println("Felicidades " + nombre + ", ha ganado el juego.");
            System.out.println("Su premio es de 10,000 lps.");
        } else if (venderMensual[numeroRandom].equals("-")) {
            System.out.println("No hay ganador");
        }
    }

    //metodos extras
    private static boolean verificarNumeros2(int num[][], int k) {
        boolean condicion = false;
        for (int j = 0; j < 1; j++) {
            if (num[k][j] == num[k][j + 1])
                condicion = true;
        }
        return condicion;
    }

    private static void llenarMatriz(int[][] numeros) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                numeros[i][j] = 0;
            }
        }
    }

    private static void llenarVenderMensual(String[] nombres) {
        for (int i = 0; i < 101; i++) {
            nombres[i] = "-";
        }
    }

    private static int verificarNumeros(int[][] numeros, int num, int k) {
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            if (num == numeros[k][i])
                contador++;
        }
        return contador;

    }

    private static String compararNumeros(int[][] numeros, int[] juego, int fila, String[] clientes) {
        int contador = 0, numeroCliente, numeroRandom;
        int[] cantidadNumeros = new int[5];
        String ganadores = " > No hay ganadores.";
        for (int i = 0; i < 5; i++) {
            numeroCliente = numeros[fila][i];
            for (int j = 0; j < 5; j++) {
                numeroRandom = juego[j];
                //System.out.println(numeroCliente + " - " + numeroRandom);
                if (numeroCliente == numeroRandom) {
                    cantidadNumeros[i] += 1;
                } else {
                    cantidadNumeros[i] += 0;
                }
            }
        }
        //ganadores
        for (int i = 0; i < 5; i++) {
            contador += cantidadNumeros[i];
        }
        if (contador < 3) {
            ganadores = " > No hay ganadores.";
        } else if (contador == 3) {
            ganadores = "Felicidades " + clientes[fila] + ", su premio es de 5,000 lps.";
        } else if (contador == 4) {
            ganadores = "Felicidades " + clientes[fila] + ", su premio es de 10,000 lps.";
        } else if (contador == 5) {
            ganadores = "Felicidades " + clientes[fila] + ", su premio es de 100,000 lps.";
        }
        //System.out.println(contador);
        return ganadores;
    }
}
