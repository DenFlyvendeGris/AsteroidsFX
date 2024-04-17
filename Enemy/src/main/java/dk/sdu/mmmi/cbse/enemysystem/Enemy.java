package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {

    private final double shootingCooldown = 0.8d;
    private double shootingTimer;

    private final double movementCooldown = 0.5d;
    private double movementTimer;
    private int currentMove = 0;

    public void tickTimers(double time) {
        if (shootingTimer < shootingCooldown) {
            shootingTimer += time;
        }
        movementTimer += time;
    }

    public boolean canFire() {
        if (shootingTimer >= shootingCooldown) {
            shootingTimer -= shootingCooldown;
            return true;
        }
        return false;
    }

    public boolean canChangeMovement() {
        if (movementTimer >= movementCooldown) {
            movementTimer -= movementCooldown;
            return true;
        }
        return false;
    }

    public void setMove(int move) {
        this.currentMove = move;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    @Override
    public void onCollision(Entity entity) {
        this.setDestroyed(true);
    }
}
