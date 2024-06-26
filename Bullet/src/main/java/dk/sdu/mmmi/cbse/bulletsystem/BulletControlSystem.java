package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private long lastBulletTime;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);


            if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() || bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
                bullet.setDestroyed(true);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setPolygonCoordinates(-1, -3, 1, -3, 1, 3, -1, 3, -1, -3);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(2);
        bullet.setShooter(shooter);

        return bullet;
    }

    private void setShape(Entity entity) {

    }
}
