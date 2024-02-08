package org.zieba.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CuboidAdapter implements Sphere {

    private final Cuboid cuboid;

    @Override
    public String getName() {
        return cuboid.getName();
    }

    @Override
    public double getRadius() {
        double l = cuboid.getLength();
        double w = cuboid.getWidth();
        double h = cuboid.getHeight();

        // Calculating the radius as half the diagonal of the cuboid
        return Math.sqrt(l * l + w * w + h * h) / 2;
    }
}
