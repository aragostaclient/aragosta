package it.thatskai.aragosta.events.listeners;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.clickgui.ClickGUI;
import it.thatskai.aragosta.events.EventTarget;
import it.thatskai.aragosta.events.impl.ClientTickEvent;
import it.thatskai.aragosta.events.impl.PostMotionUpdateEvent;
import it.thatskai.aragosta.events.impl.PreMotionUpdateEvent;
import it.thatskai.aragosta.module.cheats.FlightModule;
import it.thatskai.aragosta.module.cheats.KillauraModule;
import it.thatskai.aragosta.module.cheats.NoFallModule;
import it.thatskai.aragosta.module.cheats.SpeedModule;
import it.thatskai.aragosta.module.cheats.BedFuckerModule;
import it.thatskai.aragosta.utils.BlockUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import static it.thatskai.aragosta.utils.PacketsUtils.sendPacket;

public class EventListener {
    private final Minecraft mc = Minecraft.getMinecraft();
    @EventTarget
    public void onTick(ClientTickEvent e){
        //Keybind
        if(mc.gameSettings.CRASHER_MENU.isPressed()){
            mc.displayGuiScreen(new ClickGUI());
        }
        //cheats
        if(NoFallModule.isToggled){
            if(mc.theWorld == null) return;
            if(mc.thePlayer == null) return;
            if(mc.thePlayer.fallDistance > 2F) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            }
        }
        if(FlightModule.isToggled){
            if(mc.theWorld == null) return;
            if(mc.thePlayer == null) return;
            mc.thePlayer.capabilities.isFlying = true;
        }
        if(SpeedModule.isToggled){
            if(mc.theWorld == null) return;
            if(mc.thePlayer == null) return;
            mc.thePlayer.setSprinting(true);
        }
        if(BedFuckerModule.isToggled){
            if(mc.theWorld == null) return;
            if(mc.thePlayer == null) return;
            BlockPos[] bps = BlockUtil.getBlocksAround(mc.thePlayer, (int) AragostaMain.instance.settingsManager.getSettingByName("Range").getValDouble(), false);
            for(final BlockPos bp : bps)
                if(mc.theWorld.getBlockState(bp).getBlock().getUnlocalizedName().equals("tile.bed"))
                    new Thread(() -> {
                        sendPacket(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, bp, EnumFacing.UP));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        sendPacket(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, bp, EnumFacing.UP));
                    }).start();
        }
    }

    @EventTarget
    public void onPre(PreMotionUpdateEvent event) {
        if(!KillauraModule.isToggled) return;
        KillauraModule.target = KillauraModule.getClosest(mc.playerController.getBlockReachDistance());
        if(KillauraModule.target == null)
            return;
        KillauraModule.updateTime();
        KillauraModule.yaw = mc.thePlayer.rotationYaw;
        KillauraModule.pitch = mc.thePlayer.rotationPitch;
        boolean block = KillauraModule.target != null && AragostaMain.instance.settingsManager.getSettingByName("AutoBlock").getValBoolean() && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword;
        if(block && KillauraModule.target.getDistanceToEntity(mc.thePlayer) < 8F)
            mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
        int delay = 8;
        if(KillauraModule.current - KillauraModule.last > 1000 / delay) {
            KillauraModule.attack(KillauraModule.target);
            KillauraModule.resetTime();
        }
    }

    @EventTarget
    public void onPost(PostMotionUpdateEvent event) {
        if(!KillauraModule.isToggled) return;
        if(KillauraModule.target == null)
            return;
        mc.thePlayer.rotationYaw = KillauraModule.yaw;
        mc.thePlayer.rotationPitch = KillauraModule.pitch;
    }
}
