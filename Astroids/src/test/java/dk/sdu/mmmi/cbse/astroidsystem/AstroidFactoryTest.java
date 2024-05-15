package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AstroidFactoryTest {

        @Test
        public void testCreateAsteroid() {
            GameData gameData = new GameData();
            AstroidSize size = AstroidSize.LARGE;

            AstroidFactory creator = new AstroidFactory();
            Astroid asteroid = creator.createAsteroids(gameData, size);

            assertNotNull(asteroid);


            assertTrue(asteroid.getSpeed() >= 0 && asteroid.getSpeed() <= 1);

            if (size == AstroidSize.LARGE) {
                assertTrue(asteroid.getRadius() >= 20 && asteroid.getRadius() <= 30);
            } else if (size == AstroidSize.MEDIUM) {
                assertTrue(asteroid.getRadius() >= 15 && asteroid.getRadius() <= 20);
            } else if (size == AstroidSize.SMALL) {
                assertTrue(asteroid.getRadius() >= 5 && asteroid.getRadius() <= 10);
            }

            assertTrue(asteroid.getX() >= 0 && asteroid.getX() <= gameData.getDisplayWidth());
            assertTrue(asteroid.getY() >= 0 && asteroid.getY() <= gameData.getDisplayHeight());

            assertEquals(180, asteroid.getRotation());
        }
    }
