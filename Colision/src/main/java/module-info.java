import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Colision {
    requires Common;
    requires CommonBullet;
    requires java.net.http;
    exports dk.sdu.mmmi.cbse.collider;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collider.Collider;
}