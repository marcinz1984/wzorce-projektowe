package org.zieba.adapter;

import java.util.ArrayList;
import java.util.List;

public class MainForAdapter {

    public static void main(String[] args) {
        List<Sphere> spheres = new ArrayList<>();
        double boxHoleRadius = 10.0;
        Box box = new Box(boxHoleRadius, spheres);

        Sphere ball1 = new Ball("ball1", 8.0);
        Sphere ball2 = new Ball("ball2", 11.0);
        box.put(ball1);
        box.put(ball2);

        Cuboid cuboid1 = new Cuboid("cuboid1", 4.9, 6.0, 21.0);
        Cuboid cuboid2 = new Cuboid("cuboid2", 3.0, 4.0, 8.5);
        Sphere cuboidAdapter1 = new CuboidAdapter(cuboid1);
        Sphere cuboidAdapter2 = new CuboidAdapter(cuboid2);

        box.put(cuboidAdapter1);
        box.put(cuboidAdapter2);
    }
}
