/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.Serializable;
import java.util.*;

public class DataGame implements Serializable{
    private static final long serialVerionUID = 1L;
    public int playerX, playerY;
    public int mapaActual;
    public Map<String, Boolean> objetos = new HashMap<>();

    // Estado de NPCs
    public Map<String, Boolean> npcEstados = new HashMap<>();

    // Variables globales (ejemplo: tiempo, puntuación, misiones)
    public int tiempoJuego;
}
