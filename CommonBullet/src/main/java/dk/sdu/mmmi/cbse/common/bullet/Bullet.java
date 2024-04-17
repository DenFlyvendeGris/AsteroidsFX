package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity {
    private Entity shooter;
    public Entity getShooter() {
        return shooter;
    }

    public void setShooter(Entity shooter) {
        this.shooter = shooter;
    }

    @Override
    public void onCollision(Entity entity) {
        this.setDestroyed(true);
    }
}
