package fr.rpcore.rpchat.events.Events;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.rpcore.rpchat.Methods;
import fr.rpcore.rpchat.RPChat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Events {

    public static File configFile = new File(Minecraft.getMinecraft().mcDataDir+"/config/", "rp-chat.txt");


    boolean checkDistance(BlockPos loc1, BlockPos loc2, int maxDist){
        if(loc1.distanceSq(loc2) <= maxDist*maxDist){
            return true;
        }else{
            return false;
        }
    }

    public static String replaceAllValues(String val, String PlayerUsername, String PlayerRPName, String msg){

        if(val.contains("&")){
            val = val.replace("&", "§");
        }
        if(val.contains("{msg}")) {
            val = val.replace("{msg}", msg);
        }
        if(val.contains("{username}")) {
            val = val.replace("{username}", PlayerUsername);
        }
        if(val.contains("{rpname}")) {
            val = val.replace("{rpname}", PlayerRPName);
        }
        return val;


    }


    public static String getConfig(String configName){
        configFile = new File(Minecraft.getMinecraft().mcDataDir+"/config/", "rp-chat.txt");
        String result = Methods.ReadFileLine(configFile, configName);
        String result1 = result.replace(configName, "");
        return result1;

    }

    @SubscribeEvent
    public void playerChatEvent(ServerChatEvent e){



        System.out.println(Methods.ReadFileLine(configFile, "HRP PREFIX:"));
        System.out.println(Methods.FileReader(configFile));


        String hrp_prefix = getConfig("HRP PREFIX:");
        String shout_prefix = getConfig("SHOUT PREFIX:");
        String whisp_prefix = getConfig("WHISP PREFIX:");
        String action_prefix = getConfig("ACTION PREFIX:");

        String speak_distance= getConfig("SPEAK DISTANCE:");
        String shout_distance = getConfig("SHOUT DISTANCE:");
        String whisp_distance = getConfig("WHISP DISTANCE:");
        String action_distance = getConfig("ACTION DISTANCE:");

        String hrp_format = getConfig("HRP FORMAT:");
        String speak_format = getConfig("SPEAK FORMAT:");
        String shout_format = getConfig("SHOUT FORMAT:");
        String whisp_format = getConfig("WHISP FORMAT:");
        String action_format = getConfig("ACTION FORMAT:");



        //Cancel
        e.setCanceled(true);
        //Code du chat rp
        String message = e.getMessage();
        int x90 = e.getMessage().length();
        String msg = message.substring(1, x90);
        String firstchar = message.substring(0, 1);
        String name = e.getPlayer().getName();
        String[] vplayerlist = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getOnlinePlayerNames();
        List<EntityPlayer> playerHearList = new ArrayList<>();

        EntityPlayerMP player = e.getPlayer();

        //HRP
        if(firstchar.equals(hrp_prefix)||firstchar.equals(")")) {
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                lvpl.sendMessage(new TextComponentString(replaceAllValues(hrp_format, player.getName(), "fillwithrpname", msg)));
            }
        }

        //CRIE
        else if(firstchar.equals(shout_prefix)) {
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(checkDistance(player.getPosition(), lvpl.getPosition(), 30)){
                    playerHearList.add(lvpl);
                }

            }
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(playerHearList.contains(lvpl)) {
                    lvpl.sendMessage(new TextComponentString(replaceAllValues(shout_format, player.getName(), "fillwithrpname", msg)));
                }
            }
        }

        //CHUCHOTEMENT
        else if(firstchar.equals(whisp_prefix)) {
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(checkDistance(player.getPosition(), lvpl.getPosition(), 3)){
                    playerHearList.add(lvpl);
                }

            }
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(playerHearList.contains(lvpl)) {
                    lvpl.sendMessage(new TextComponentString(replaceAllValues(whisp_format, player.getName(), "fillwithrpname", msg)));
                }
            }
        }

        //ACTION
        else if(firstchar.equals(action_prefix)) {
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(checkDistance(player.getPosition(), lvpl.getPosition(), 15)){
                    playerHearList.add(lvpl);
                }

            }
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(playerHearList.contains(lvpl)) {
                    lvpl.sendMessage(new TextComponentString(replaceAllValues(action_format, player.getName(), "fillwithrpname", msg)));
                }
            }
        }else{

            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(checkDistance(player.getPosition(), lvpl.getPosition(), 15)){
                    playerHearList.add(lvpl);
                }

            }
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(playerHearList.contains(lvpl)) {
                    lvpl.sendMessage(new TextComponentString(replaceAllValues(speak_format, player.getName(), "fillwithrpname", msg)));
                }
            }

        }

    }





}
