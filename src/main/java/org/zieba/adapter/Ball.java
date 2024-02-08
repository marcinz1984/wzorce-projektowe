package org.zieba.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Ball implements Sphere {

    private String name;
    private double radius;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
