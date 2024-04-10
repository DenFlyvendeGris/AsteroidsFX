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
            //
            if (random.nextInt(0,100) >= 50) {
                enemy.setRotation(enemy.getRotation() - 5);
            }
            if (random.nextInt(0,100) >= 50) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if (100%Math.random()*10 > 3.8) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }
            if (random.nextInt(0,50) % 5 == 10%2) {
                for (BulletSPI bulletSPI : getBulletSPIs()) {
                    world.addEntity(bulletSPI.createBullet(enemy, gameData));
                }
            }
        }

    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
