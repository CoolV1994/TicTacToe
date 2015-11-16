package com.coolv1994.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

/**
 *
 * @author Vinnie
 */
public class AI {

    private final String[] strategies;

    private final HashMap<String,String> posMap;
    private final ArrayList<String> playerPositions;
    private final ArrayList<String> aiPositions;
    private final HashMap<Integer,Integer> playerPosCount;
    private final HashMap<Integer,Integer> aiPosCount;
    private final ArrayList<Integer> importantPositions;
    private final ArrayList<Integer> normalPositions;

    public AI() {
        strategies = new String[] {
            "NW,N,NE", "W,C,E", "SW,S,SE", // Rows
            "NW,W,SW", "N,C,S", "NE,E,SE", // Columns
            "NW,C,SE", "NE,C,SW" // Diagonals
        };
        posMap = new HashMap<>();
        posMap.put("NW", "N,NE,W,SW,C,SE");
        posMap.put("N", "C,S,NW,NE");
        posMap.put("NE", "N,NW,E,SE,C,SW");
        posMap.put("W", "C,E,NW,SW");
        posMap.put("C", "N,S,E,W,NW,NE,SW,SE");
        posMap.put("E", "C,W,NE,SE");
        posMap.put("SW", "S,SE,W,NW,C,NE");
        posMap.put("S", "C,N,SW,SE");
        posMap.put("SE", "S,SW,E,NE,C,NW");
        playerPositions = new ArrayList<>();
        aiPositions = new ArrayList<>();
        playerPosCount = new HashMap<>();
        aiPosCount = new HashMap<>();
        importantPositions = new ArrayList<>();
        normalPositions = new ArrayList<>();
    }

    public void takeTurn() {
        if (canTakeCenter())
            return;
        getTakenPositions();
        countPosInStrategy();
        planStrategy();
        if (makeImportantMove())
            return;
        if (makeNormalMove())
            return;
        makeMove();
    }

    private boolean canTakeCenter() {
        JButton button = Main.gamePanel.getButton("C");
        if (button.getText().isEmpty()) {
            button.setText("O");
            System.out.println("---C");
            return true;
        }
        return false;
    }

    private void getTakenPositions() {
        for (String position : posMap.keySet()) {
            JButton button = Main.gamePanel.getButton(position);
            if (button.getText().equals("X")) {
                playerPositions.add(button.getName());
            }
            if (button.getText().equals("O")) {
                aiPositions.add(button.getName());
            }
        }
    }

    private void countPosInStrategy() {
        int index = 0;
        for (String strategy : strategies) {
            int pCount = 0;
            int aiCount = 0;
            for (String position : strategy.split(",")) {
                if (playerPositions.contains(position)) {
                    pCount++;
                    System.out.println("-PosCnt: P: "+position+" - "+pCount);
                }
                if (aiPositions.contains(position)) {
                    aiCount++;
                    System.out.println("-PosCnt: AI: "+position+" - "+pCount);
                }
            }
            playerPosCount.put(index, pCount);
            aiPosCount.put(index, aiCount);
            index++;
        }
    }

    private void planStrategy() {
        for (Map.Entry<Integer, Integer> entry : playerPosCount.entrySet()) {
            if (entry.getValue() == 2) {
                importantPositions.add(entry.getKey());
                System.out.println("-I : P: "+entry.getKey());
            }
            if (entry.getValue() == 1) {
                normalPositions.add(entry.getKey());
                System.out.println("-N : P: "+entry.getKey());
            }
        }
        for (Map.Entry<Integer, Integer> entry : aiPosCount.entrySet()) {
            if (entry.getValue() == 2) {
                importantPositions.add(entry.getKey());
                System.out.println("-I : AI: "+entry.getKey());
            }
            if (entry.getValue() == 1) {
                normalPositions.add(entry.getKey());
                System.out.println("-N : AI: "+entry.getKey());
            }
        }
    }

    private boolean makeImportantMove() {
        for (int index : importantPositions) {
            for (String position : strategies[index].split(",")) {
                JButton button = Main.gamePanel.getButton(position);
                if (button.getText().isEmpty()) {
                    button.setText("O");
                    System.out.println("---I");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean makeNormalMove() {
        for (int index : normalPositions) {
            for (String position : strategies[index].split(",")) {
                JButton button = Main.gamePanel.getButton(position);
                if (button.getText().isEmpty()) {
                    button.setText("O");
                    System.out.println("---N");
                    return true;
                }
            }
        }
        return false;
    }

    private void makeMove() {
        for (String position : posMap.keySet()) {
            javax.swing.JButton button = Main.gamePanel.getButton(position);
            if (button.getText().isEmpty()) {
                button.setText("O");
                System.out.println("---R");
                return;
            }
        }
    }

    public void resetAI() {
        playerPositions.clear();
        aiPositions.clear();
        playerPosCount.clear();
        aiPosCount.clear();
        importantPositions.clear();
        normalPositions.clear();
    }
}
