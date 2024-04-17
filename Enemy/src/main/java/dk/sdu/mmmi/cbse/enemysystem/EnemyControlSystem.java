package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {



    Random random = new Random();
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {

            if (enemy.getX() <= 0 || enemy.getX() >= gameData.getDisplayWidth() || enemy.getY() <=0 || enemy.getY() >= gameData.getDisplayHeight()) {
                enemy.setDestroyed(true);
            }

            if (((Enemy) enemy).canChangeMovement())
                ((Enemy) enemy).setMove(random.nextInt(0, 3));


            if (((Enemy) enemy).getCurrentMove() == 1) {
                enemy.setRotation(enemy.getRotation() - 200 * gameData.getDeltaTimeInSec());

            }
            if (((Enemy) enemy).getCurrentMove() == 2) {
                enemy.setRotation(enemy.getRotation() + 200 * gameData.getDeltaTimeInSec());
            }

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);

            if (((Enemy) enemy).canFire()) {
                for (BulletSPI bulletSPI : getBulletSPIs()) {
                    world.addEntity(bulletSPI.createBullet(enemy, gameData));
                }
            }
            ((Enemy) enemy).tickTimers(gameData.getDeltaTimeInSec());
        }
        if (Math.random() < 0.005) {
            Enemy enemy = new EnemyFactory().createEnemies(gameData);
            world.addEntity(enemy);
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
