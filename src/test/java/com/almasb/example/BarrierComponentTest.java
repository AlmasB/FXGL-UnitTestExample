package com.almasb.example;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class BarrierComponentTest {

    @Test
    public void testUnlock() {
        var barrierComponent = new BarrierComponent();

        barrierComponent.open(1);

        assertThat(barrierComponent.isUnlocked(), is(false));

        barrierComponent.open(1);

        assertThat(barrierComponent.isUnlocked(), is(true));
    }

    @Test
    public void testIntegration() {
        var coin1 = new Entity();
        coin1.setType(EntityType.COIN);

        var coin2 = new Entity();
        coin2.setType(EntityType.COIN);

        var player = new Entity();
        player.setType(EntityType.PLAYER);

        var barrierComponent = new BarrierComponent();

        var barrier = new Entity();
        barrier.addComponent(barrierComponent);
        barrier.setType(EntityType.BARRIER);

        var world = new GameWorld();
        world.addEntities(player, coin1, coin2, barrier);

        // test
        var handler = new PlayerCoinCollisionHandler();

        assertThat(barrierComponent.isUnlocked(), is(false));

        handler.onCollisionBegin(player, coin1);
        handler.onCollisionBegin(player, coin2);

        assertThat(barrierComponent.isUnlocked(), is(true));
    }
}
