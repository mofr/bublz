package org.mofr.bublz.components;

import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    public ZComparator(){

    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        return (int) Math.signum(TransformComponent.mapper.get(entityB).z -
                TransformComponent.mapper.get(entityA).z);
    }
}