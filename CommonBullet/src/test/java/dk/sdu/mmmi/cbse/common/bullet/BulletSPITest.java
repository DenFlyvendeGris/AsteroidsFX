package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BulletSPITest {

    class TestBulletSPI implements BulletSPI {
        @Override
        public Entity createBullet(Entity e, GameData gameData) {
            Bullet bullet = new Bullet();
            bullet.setPolygonCoordinates(-1, -3, 1, -3, 1, 3, -1, 3, -1, -3);
            bullet.setX(e.getX());
            bullet.setY(e.getY());
            bullet.setRotation(e.getRotation());
            bullet.setRadius(2);
            bullet.setShooter(e);

            return bullet;
        }
    }

    @Test
    public void testCreateBullet() {

        GameData gameData = new GameData();
        Entity shooter = new Entity();
        shooter.setRotation(45);

        TestBulletSPI bulletSPI = new TestBulletSPI();
        Entity createdBullet = bulletSPI.createBullet(shooter, gameData);
        assertNotNull(createdBullet);
        assertTrue(createdBullet instanceof Bullet);

        assertEquals(shooter.getX(), createdBullet.getX(), 0);
        assertEquals(shooter.getY(), createdBullet.getY(), 0);
        assertEquals(shooter.getRotation(), createdBullet.getRotation(), 0);
        assertEquals(2, ((Bullet)createdBullet).getRadius(), 0);
        assertEquals(shooter, ((Bullet)createdBullet).getShooter());
    }
}