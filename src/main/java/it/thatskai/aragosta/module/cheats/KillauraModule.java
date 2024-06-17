package it.thatskai.aragosta.module.cheats;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.settings.Setting;
import it.thatskai.aragosta.utils.MessageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

@IModule(
        name = "KillAura",
        key = 0,
        category = Category.CHEATS
)
public class KillauraModule extends Module {

    public static EntityLivingBase target;
    public static long current, last;
    public static float yaw, pitch;
    public static boolean isToggled;
    public static Minecraft mc = Minecraft.getMinecraft();
    private static AragostaMain Main = AragostaMain.instance;
    @Override
    public void setup() {
        AragostaMain.instance.settingsManager.rSetting(new Setting("Crack Size", this, 5, 0, 15, true));
        AragostaMain.instance.settingsManager.rSetting(new Setting("Existed", this, 30, 0, 500, true));
        AragostaMain.instance.settingsManager.rSetting(new Setting("FOV", this, 360, 0, 360, true));
        AragostaMain.instance.settingsManager.rSetting(new Setting("AutoBlock", this, true));
        AragostaMain.instance.settingsManager.rSetting(new Setting("Invisibles", this, false));
        AragostaMain.instance.settingsManager.rSetting(new Setting("Players", this, true));
        AragostaMain.instance.settingsManager.rSetting(new Setting("Animals", this, false));
        AragostaMain.instance.settingsManager.rSetting(new Setting("Monsters", this, false));
        AragostaMain.instance.settingsManager.rSetting(new Setting("Villagers", this, false));
        AragostaMain.instance.settingsManager.rSetting(new Setting("Teams", this, false));
    }

    @Override
    public void onEnable() {
        isToggled = true;
        MessageUtils.sendMessage("Killaura attivata", true);
    }

    @Override
    public void onDisable() {
        isToggled = false;
        MessageUtils.sendMessage("Killaura disattivata", true);
    }


    public static void attack(Entity entity) {
        for(int i = 0; i < Main.instance.settingsManager.getSettingByName("Crack Size").getValDouble(); i++)
            mc.thePlayer.onCriticalHit(entity);

        mc.thePlayer.swingItem();
        mc.playerController.attackEntity(mc.thePlayer, entity);
    }

    public static void updateTime() {
        current = (System.nanoTime() / 1000000L);
    }

    public static void resetTime() {
        last = (System.nanoTime() / 1000000L);
    }

    public static EntityLivingBase getClosest(double range) {
        double dist = range;
        EntityLivingBase target = null;
        for (Entity object : mc.theWorld.loadedEntityList) {
            if (object instanceof EntityLivingBase) {
                EntityLivingBase player = (EntityLivingBase) object;
                if (canAttack(player)) {
                    double currentDist = mc.thePlayer.getDistanceToEntity(player);
                    if (currentDist <= dist) {
                        dist = currentDist;
                        target = player;
                    }
                }
            }
        }
        return target;
    }

    public static boolean canAttack(EntityLivingBase player) {
        if(player instanceof EntityPlayer || player instanceof EntityAnimal || player instanceof EntityMob || player instanceof EntityVillager) {
            if (player instanceof EntityPlayer && !Main.instance.settingsManager.getSettingByName("Players").getValBoolean())
                return false;
            if (player instanceof EntityAnimal && !Main.instance.settingsManager.getSettingByName("Animals").getValBoolean())
                return false;
            if (player instanceof EntityMob && !Main.instance.settingsManager.getSettingByName("Monsters").getValBoolean())
                return false;
            if (player instanceof EntityVillager && !Main.instance.settingsManager.getSettingByName("Villagers").getValBoolean())
                return false;
        }
        if(player.isOnSameTeam(mc.thePlayer) && Main.instance.settingsManager.getSettingByName("Teams").getValBoolean())
            return false;
        if(player.isInvisible() && !Main.instance.settingsManager.getSettingByName("Invisibles").getValBoolean())
            return false;
        if(!isInFOV(player, Main.instance.settingsManager.getSettingByName("FOV").getValDouble()))
            return false;
        return
                player != mc.thePlayer
                        && player.isEntityAlive()
                        && mc.thePlayer.getDistanceToEntity(player) <= mc.playerController.getBlockReachDistance() && player.ticksExisted > AragostaMain.instance.settingsManager.getSettingByName("Existed").getValDouble();
    }

    public static boolean isInFOV(EntityLivingBase entity, double angle) {
        angle *= .5D;
        double angleDiff = getAngleDifference(mc.thePlayer.rotationYaw, getRotations(entity.posX, entity.posY, entity.posZ)[0]);
        return (angleDiff > 0 && angleDiff < angle) || (-angle < angleDiff && angleDiff < 0);
    }

    public static float getAngleDifference(float dir, float yaw) {
        float f = Math.abs(yaw - dir) % 360F;
        return f > 180F ? 360F - f : f;
    }

    public static float[] getRotations(double x, double y, double z) {
        double diffX = x + .5D - mc.thePlayer.posX;
        double diffY = (y + .5D) / 2D - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double diffZ = z + .5D - mc.thePlayer.posZ;

        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180D / Math.PI) - 90F;
        float pitch = (float)-(Math.atan2(diffY, dist) * 180D / Math.PI);

        return new float[] { yaw, pitch };
    }
}
