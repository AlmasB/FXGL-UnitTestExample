package com.almasb.example;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import static com.almasb.example.EntityType.BARRIER;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class PlayerCoinCollisionHandler extends CollisionHandler {

    public PlayerCoinCollisionHandler() {
        super(EntityType.PLAYER, EntityType.COIN);
    }

    @Override
    protected void onCollisionBegin(Entity player, Entity coin) {
        coin.getWorld()
                .getSingleton(BARRIER)
                .getComponent(BarrierComponent.class)
                .open(1);

        coin.removeFromWorld();
    }
}
