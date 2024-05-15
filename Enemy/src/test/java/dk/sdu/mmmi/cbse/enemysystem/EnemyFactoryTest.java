package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyFactoryTest {

    private GameData gameData;

    @Test
    public void testCreateEnemies() {
        GameData gameData = new GameData();

        EnemyFactory creator = new EnemyFactory();
        Enemy enemy = creator.createEnemies(gameData);

        assertNotNull(enemy);
        assertEquals(6, enemy.getRadius(), 0);
        assertEquals(-5, enemy.getPolygonCoordinates()[0], 0);
        assertEquals(-5, enemy.getPolygonCoordinates()[1], 0);
        assertEquals(10, enemy.getPolygonCoordinates()[2], 0);
        assertEquals(0, enemy.getPolygonCoordinates()[3], 0);
        assertEquals(-5, enemy.getPolygonCoordinates()[4], 0);
        assertEquals(5, enemy.getPolygonCoordinates()[5], 0);

        assertTrue(enemy.getX() >= 0 && enemy.getX() <= gameData.getDisplayWidth());
        assertTrue(enemy.getY() >= 0 && enemy.getY() <= gameData.getDisplayHeight());

    }
}