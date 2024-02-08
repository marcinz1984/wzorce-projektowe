package org.zieba.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Box {

    private double holeRadius;
    private List<Sphere> content;

    public void put(Sphere sphere) {
        if (sphere.getRadius() <= holeRadius) {
            content.add(sphere);
            System.out.println(sphere.getName() + " added added to the box!");
        } else {
            System.out.println(sphere.getName() + " is too large to fit through the box!");
        }
    }
}
