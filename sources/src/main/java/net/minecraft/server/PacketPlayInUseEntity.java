package net.minecraft.server;

import java.io.IOException;
import javax.annotation.Nullable;

public class PacketPlayInUseEntity implements Packet<PacketListenerPlayIn> {

    private int a; public int getEntityId() { return this.a; } // Paper - add accessor
    private PacketPlayInUseEntity.EnumEntityUseAction action;
    private Vec3D c;
    private EnumHand d;

    public PacketPlayInUseEntity() {}

    public PacketPlayInUseEntity(Entity entity) {
        this.a = entity.getId();
        this.action = PacketPlayInUseEntity.EnumEntityUseAction.ATTACK;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.g();
        this.action = packetdataserializer.a(PacketPlayInUseEntity.EnumEntityUseAction.class);
        if (this.action == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT_AT) {
            this.c = new Vec3D(packetdataserializer.readFloat(), packetdataserializer.readFloat(), packetdataserializer.readFloat());
        }

        if (this.action == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT || this.action == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT_AT) {
            this.d = packetdataserializer.a(EnumHand.class);
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.a(this.action);
        if (this.action == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT_AT) {
            packetdataserializer.writeFloat((float) this.c.x);
            packetdataserializer.writeFloat((float) this.c.y);
            packetdataserializer.writeFloat((float) this.c.z);
        }

        if (this.action == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT || this.action == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT_AT) {
            packetdataserializer.a(this.d);
        }

    }

    @Override
    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    @Nullable
    public Entity a(World world) {
        return world.getEntity(this.a);
    }

    public PacketPlayInUseEntity.EnumEntityUseAction a() {
        return this.action;
    }

    public EnumHand b() {
        return this.d;
    }

    public Vec3D c() {
        return this.c;
    }

    public static enum EnumEntityUseAction {

        INTERACT, ATTACK, INTERACT_AT;

        private EnumEntityUseAction() {}
    }
}
