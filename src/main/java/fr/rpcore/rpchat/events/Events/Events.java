package fr.rpcore.rpchat.events.Events;

import com.mojang.realmsclient.gui.ChatFormatting;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Events {

    boolean checkDistance(BlockPos loc1, BlockPos loc2, int maxDist){
        if(loc1.distanceSq(loc2) <= maxDist){
            return true;
        }else{
            return false;
        }
    }


    @SubscribeEvent
    public void playerChatEvent(ServerChatEvent e){
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
        if(firstchar.equals("(")||firstchar.equals(")")) {
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                lvpl.sendMessage(new TextComponentString(name + " [HRP]: " + msg));
            }
            System.out.println(vplayerlist);
        }

        //CRIE
        if(firstchar.equals("!")) {
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(checkDistance(player.getPosition(), lvpl.getPosition(), 180)){
                    playerHearList.add(lvpl);
                }

            }
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(playerHearList.contains(lvpl)) {
                    lvpl.sendMessage(new TextComponentString(ChatFormatting.GRAY+name + ChatFormatting.RED+" crie: " + msg));
                }
            }
            System.out.println(vplayerlist);
        }

        //CHUCHOTEMENT
        if(firstchar.equals("$")) {
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(checkDistance(player.getPosition(), lvpl.getPosition(), 180)){
                    playerHearList.add(lvpl);
                }

            }
            for(String pll : vplayerlist) {
                EntityPlayer lvpl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(pll);
                if(playerHearList.contains(lvpl)) {
                    lvpl.sendMessage(new TextComponentString(ChatFormatting.GRAY+name + ChatFormatting.GREEN+" chuchotte: " + msg));
                }
            }
            System.out.println(vplayerlist);
        }

    }





}
