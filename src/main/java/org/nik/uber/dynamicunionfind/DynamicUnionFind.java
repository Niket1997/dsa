package org.nik.uber.dynamicunionfind;

/*
*
*
* Given a Terrain (2D - land and water) implement -
addLand(x, y): sets (x, y) to land
isLand(x, y): true if (x, y) is a land, false otherwise (water)
countIslands(): returns the number of unique islands
*
*
* */

public class DynamicUnionFind {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind();
        uf.add(0, 0);
        uf.add(0, 1);
        uf.add(0, 2);
        uf.add(1, 5);
        uf.add(0, 3);
        System.out.println(uf.isLand(0, 0));
        System.out.println(uf.getNumIslands());
    }
}
