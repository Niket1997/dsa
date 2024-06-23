package org.nik.uber.dynamicunionfind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class UnionFind {
    private HashMap<Cell, Cell> parents;
    private HashMap<Cell, Integer> ranks;
    // TODO: think of adding a param to determine number of islands

    public UnionFind() {
        parents = new HashMap<>();
        ranks = new HashMap<>();
    }

    private Cell find(Cell cell) {
        if (!parents.containsKey(cell)) {
            throw new RuntimeException("Cell " + cell + " not found");
        }

        if (!cell.equals(parents.get(cell))) {
            parents.put(cell, find(parents.get(cell)));
        }
        return parents.get(cell);
    }

    private boolean union(Cell cell1, Cell cell2) {
        Cell parent1 = find(cell1);
        Cell parent2 = find(cell2);
        if (parent1.equals(parent2)) {
            return false;
        }

        if (Objects.equals(ranks.get(parent1), ranks.get(parent2))) {
            parents.put(parent2, parent1);
            ranks.put(parent1, ranks.get(parent1) + 1);
        } else if (ranks.get(parent1) > ranks.get(parent2)) {
            parents.put(parent2, parent1);
        } else {
            parents.put(parent1, parent2);
        }

        return true;
    }

    // TC: O(n)
    // SC: O(n)
    public void add(int row, int col) {
        Cell cell = new Cell(row, col);
        if (isLand(row, col)) {
            System.out.println(cell + " is already land");
            return;
        }

        parents.put(cell, cell);
        ranks.put(cell, 0);

        int[][] nbs = {{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
        for (int[] nb : nbs) {
            int nr = nb[0];
            int nc = nb[1];

            Cell nbCell = new Cell(nr, nc);
            if (parents.containsKey(nbCell)) {
                union(cell, nbCell);
            }
        }
    }

    // TC: O(1)
    public boolean isLand(int row, int col) {
        Cell cell = new Cell(row, col);
        return parents.containsKey(cell);
    }

    // TC: O(n)
    // SC: O(n)
    public int getNumIslands() {
        HashSet<Cell> islands = new HashSet<>();
        for (Cell cell : parents.keySet()) {
            islands.add(find(cell));
        }
        return islands.size();
    }
}


