/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelview;

/**
 *
 * @author Sebas
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;

public class conjuntos {
    // Atributos para conjuntos disjuntos
    public int[] parent;
    public int[] rank;
    public HashMap<Integer, HashSet<String>> setsMap; // Mapa para rastrear conjuntos de placas

    // Atributos para la interfaz gráfica
    public JButton[][] botones;  // Botones para cada espacio
    public String[][] placas;    // Almacenar las placas de los vehículos
    public JFrame frame;
    public int filas;  // Número de filas de bloques de estacionamiento
    public int columnas;  // Número de columnas de bloques de estacionamiento

    // Constructor que inicializa tanto los conjuntos disjuntos como la interfaz gráfica
    public conjuntos(int filas, int columnas, int espaciosPorBloque) {
        this.filas = filas;
        this.columnas = columnas;

        int totalBloques = filas * columnas;

        // Inicializa los conjuntos disjuntos
        parent = new int[totalBloques];
        rank = new int[totalBloques];
        setsMap = new HashMap<>();

        for (int i = 0; i < totalBloques; i++) {
            parent[i] = i;
            rank[i] = 0;
            setsMap.put(i, new HashSet<>()); // Inicializar cada conjunto con su propio índice
        }

        // Inicializa la interfaz gráfica
        botones = new JButton[totalBloques][espaciosPorBloque];
        placas = new String[totalBloques][espaciosPorBloque]; // Inicializar arreglo de placas
        crearInterfaz(totalBloques, espaciosPorBloque);
    }

    // Método para encontrar la raíz de un conjunto (con compresión de caminos)
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Compresión de camino
        }
        return parent[x];
    }

    // Método para unir dos conjuntos
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                setsMap.get(rootX).addAll(setsMap.get(rootY)); // Fusionar los conjuntos
                setsMap.remove(rootY);
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                setsMap.get(rootY).addAll(setsMap.get(rootX)); // Fusionar los conjuntos
                setsMap.remove(rootX);
            } else {
                parent[rootY] = rootX;
                setsMap.get(rootX).addAll(setsMap.get(rootY)); // Fusionar los conjuntos
                setsMap.remove(rootY);
                rank[rootX]++;
            }
        }
    }

    // Método para añadir una placa a un conjunto específico
    public void addPlateToSet(int bloque, String placa) {
         int root = find(bloque);
    
    // Si no existe un HashSet para este bloque, lo inicializamos
    setsMap.putIfAbsent(root, new HashSet<>());
    
    // Ahora añadimos la placa al conjunto del bloque raíz
    setsMap.get(root).add(placa);
    
    System.out.println("Placa " + placa + " añadida al conjunto de bloque raíz " + root);
    System.out.println("Contenido actual del conjunto raíz " + root + ": " + setsMap.get(root).toString());
    }

    // Método para buscar si una placa está en el conjunto
    public boolean containsPlate(String placa) {
        for (HashSet<String> set : setsMap.values()) {
            if (set.contains(placa)) {
                return true;
            }
        }
        return false;
    }

 

    // Método para crear la interfaz gráfica
    private void crearInterfaz(int totalBloques, int espaciosPorBloque) {
        frame = new JFrame("Parqueadero");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(filas, columnas));

        for (int bloque = 0; bloque < totalBloques; bloque++) {
            JPanel panelBloque = new JPanel();
            panelBloque.setLayout(new GridLayout(espaciosPorBloque, 1));
            panelBloque.setBorder(BorderFactory.createTitledBorder("Bloque " + bloque));

            for (int espacio = 0; espacio < espaciosPorBloque; espacio++) {
                JButton botonEspacio = new JButton("Vacío");
                botonEspacio.setBackground(Color.GREEN);
                int finalBloque = bloque;
                int finalEspacio = espacio;

                botonEspacio.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (botonEspacio.getText().equals("Vacío")) {
                            String placa = JOptionPane.showInputDialog(frame, "Ingrese la placa del vehículo:");
                            if (placa != null && !placa.trim().isEmpty()) {
                                botonEspacio.setText(placa);
                                botonEspacio.setBackground(Color.RED);
                                placas[finalBloque][finalEspacio] = placa;
                                addPlateToSet(finalBloque, placa); // Añadir placa al conjunto
                                separarBloque(finalBloque); // Separar el bloque si se ocupa
                            }
                        } else {
                            botonEspacio.setText("Vacío");
                            botonEspacio.setBackground(Color.GREEN);
                            placas[finalBloque][finalEspacio] = null;
                            unirBloquesAdyacentes(finalBloque); // Unir bloques si está vacío
                        }
                    }
                });

                botones[bloque][espacio] = botonEspacio;
                panelBloque.add(botonEspacio);
            }

            frame.add(panelBloque);
        }

//        // Botón para buscar el primer espacio vacío utilizando conjuntos disjuntos
//        JButton buscarCercanoBtn = new JButton("Buscar Primer Espacio Vacío");
//        buscarCercanoBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                encontrarPrimerEspacioVacioConConjuntos();
//            }
//        });
//        frame.add(buscarCercanoBtn);

        // Botón para buscar placa utilizando conjuntos disjuntos y HashSet
//        JButton buscarPlacaBtn = new JButton("Buscar Placa");
//        buscarPlacaBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String placaBuscar = JOptionPane.showInputDialog(frame, "Ingrese la placa a buscar:");
//                if (placaBuscar != null && !placaBuscar.trim().isEmpty()) {
//                    int bloque = findBlockWithPlate(placaBuscar);
//                    if (bloque != -1) {
//                        JOptionPane.showMessageDialog(frame, "Placa encontrada en el conjunto del Bloque " + bloque);
//                    } else {
//                        JOptionPane.showMessageDialog(frame, "Placa no encontrada.");
//                    }
//                }
//            }
//        });
//        frame.add(buscarPlacaBtn);
//
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    // Método para verificar si todos los espacios en un bloque están vacíos
    public boolean todosEspaciosVacios(int bloque, int espaciosPorBloque) {
        for (int espacio = 0; espacio < espaciosPorBloque; espacio++) {
            if (placas[bloque][espacio] != null) {
                return false; // Encontró un espacio ocupado
            }
        }
        return true; // Todos los espacios están vacíos
    }

    // Método para unir bloques adyacentes vacíos
    public void unirBloquesAdyacentes(int bloque) {
        if (todosEspaciosVacios(bloque, placas[bloque].length)) {
            if (bloque > 0 && todosEspaciosVacios(bloque - 1, placas[bloque].length)) {
                union(bloque, bloque - 1);
            }
            if (bloque < placas.length - 1 && todosEspaciosVacios(bloque + 1, placas[bloque].length)) {
                union(bloque, bloque + 1);
            }
        }
    }

    // Método para separar un bloque si se ocupa
    public void separarBloque(int bloque) {
        if (!todosEspaciosVacios(bloque, placas[bloque].length)) {
            // Resetear conjuntos y unir bloques vacíos de nuevo
            parent = new int[filas * columnas];
            rank = new int[filas * columnas];
            setsMap = new HashMap<>();
            for (int i = 0; i < placas.length; i++) {
                if (todosEspaciosVacios(i, placas[i].length)) {
                    unirBloquesAdyacentes(i);
                }
            }
        }
    }

    // Método para encontrar el primer espacio vacío utilizando conjuntos disjuntos
    public boolean agregarPlacaEnPrimerEspacioVacio(String placa) {
        for (int bloque = 0; bloque < placas.length; bloque++) {
            for (int espacio = 0; espacio < placas[bloque].length; espacio++) {
                if (placas[bloque][espacio] == null) { // Si el espacio está vacío
                    // Insertar la placa en el espacio vacío
                    placas[bloque][espacio] = placa;
                    botones[bloque][espacio].setText(placa);
                    botones[bloque][espacio].setBackground(Color.RED);
                    addPlateToSet(bloque, placa); // Añadir placa al conjunto
                    separarBloque(bloque); // Actualizar la estructura de conjuntos disjuntos

                    JOptionPane.showMessageDialog(frame, "Placa " + placa + " añadida en Bloque " + bloque + ", Espacio " + espacio);
                    return true; // Indica que la placa fue agregada exitosamente
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "No hay espacios vacíos disponibles.");
        return false; // No se encontraron espacios vacíos
    }

    public void encontrarPrimerEspacioVacioConConjuntos() {
        for (int bloque = 0; bloque < placas.length; bloque++) {
            int root = find(bloque);
            for (int espacio = 0; espacio < placas[bloque].length; espacio++) {
                if (placas[bloque][espacio] == null) { // Espacio vacío
                    JOptionPane.showMessageDialog(frame, "El primer espacio vacío está en Bloque " + bloque + " (Conjunto raíz: " + root + "), Espacio " + espacio);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "No hay espacios vacíos disponibles.");
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
    public String encontrarPlaca(String placa) {
    for (int bloque = 0; bloque < placas.length; bloque++) {
        for (int espacio = 0; espacio < placas[bloque].length; espacio++) {
            if (placa.equals(placas[bloque][espacio])) { // Si la placa coincide
                JOptionPane.showMessageDialog(frame, "Placa " + placa + " encontrada en Bloque " + bloque + ", Espacio " + espacio);
                return "Placa encontrada en Bloque " + bloque + ", Espacio " + espacio;
            }
        }
    }
    JOptionPane.showMessageDialog(frame, "Placa " + placa + " no encontrada.");
    return "Placa no encontrada.";
    }
    public boolean vaciarEspacio(String placa) {
        for (int bloque = 0; bloque < placas.length; bloque++) {
            for (int espacio = 0; espacio < placas[bloque].length; espacio++) {
                if (placa.equals(placas[bloque][espacio])) { // Si la placa coincide
                    // Restablecer el botón a "Vacío" y verde
                    botones[bloque][espacio].setText("Vacío");
                    botones[bloque][espacio].setBackground(Color.GREEN);
                    placas[bloque][espacio] = null; // Eliminar la placa de la estructura de datos
                    JOptionPane.showMessageDialog(frame, "El espacio en Bloque " + bloque + ", Espacio " + espacio + " ha sido vaciado.");
                    return true; // Operación exitosa
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "No se encontró la placa " + placa + " en el parqueadero.");
        return false; // Si no se encuentra la placa
    }
}
