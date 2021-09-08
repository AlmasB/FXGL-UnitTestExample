package com.almasb.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.example.EntityType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class BasicGameApp extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {

    }

    @Override
    protected void initInput() {
        onKey(KeyCode.W, () -> player.translateY(-5));
        onKey(KeyCode.S, () -> player.translateY(5));
        onKey(KeyCode.A, () -> player.translateX(-5));
        onKey(KeyCode.D, () -> player.translateX(5));
    }

    @Override
    protected void initGame() {
        player = makePlayer(100, 100);

        makeCoin(230, 300);
        makeCoin(300, 500);

        makeBarrier(400, 0);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new PlayerCoinCollisionHandler());

        onCollisionBegin(PLAYER, BARRIER, (player, barrier) -> {
            if (!barrier.getComponent(BarrierComponent.class).isUnlocked()) {
                player.translateX(-150);
            }
        });
    }

    private Entity makePlayer(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .type(PLAYER)
                .viewWithBBox(new Rectangle(40, 40, Color.BLUE))
                .collidable()
                .buildAndAttach();
    }

    private Entity makeCoin(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .type(COIN)
                .viewWithBBox(new Rectangle(10, 10, Color.RED))
                .collidable()
                .buildAndAttach();
    }

    private Entity makeBarrier(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .type(BARRIER)
                .viewWithBBox(new Rectangle(40, 600, Color.DARKGRAY))
                .with(new BarrierComponent())
                .collidable()
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
