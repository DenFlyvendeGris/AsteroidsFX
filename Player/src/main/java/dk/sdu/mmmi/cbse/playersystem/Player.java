package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    private final double shootingCooldown = 0.2d;
    private double shootingTimer;

    public void tickBullet(double time) {
        if (shootingTimer < shootingCooldown) {
            shootingTimer += time;
        }
    }

    public boolean canFire() {
        if (shootingTimer >= shootingCooldown) {
            shootingTimer -= shootingCooldown;
            return true;
        }
        return false;
    }

}
