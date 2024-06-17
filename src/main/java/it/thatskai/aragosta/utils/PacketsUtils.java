package it.thatskai.aragosta.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public final class PacketsUtils {
    private PacketsUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static void sendPacket(Packet<?> packet) {
        mc.getNetHandler().addToSendQueue(packet);
    }
    public static void sendPacketNoEvent(Packet<?> packet) {
        mc.getNetHandler().addToSendQueue(packet);
    }
    public static void tpPacket(double d, double d2, double d3) {
        Minecraft.getMinecraft();
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;
        entityPlayerSP.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(entityPlayerSP.posX + d, entityPlayerSP.posY + d2, entityPlayerSP.posZ + d3, false));
        entityPlayerSP.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(entityPlayerSP.posX, entityPlayerSP.posY, entityPlayerSP.posZ, false));
        entityPlayerSP.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(entityPlayerSP.posX, entityPlayerSP.posY, entityPlayerSP.posZ, true));
    }
    public static Minecraft mc() {
        return Minecraft.getMinecraft();
    }
    public static EntityPlayerSP p() {
        mc();
        return Minecraft.getMinecraft().thePlayer;
    }}
