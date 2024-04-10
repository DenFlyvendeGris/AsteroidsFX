import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Colision {
    requires Common;
    exports dk.sdu.mmmi.cbse.collider;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collider.Collider;
}