package fr.rpcore.rpchat.packets;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.trcgames.dbSynchronizer.DatabaseGetter;
import com.trcgames.dbSynchronizer.database.DBFolder;
import com.trcgames.dbSynchronizer.database.Database;
import fr.rpcore.rpchat.RPChat;
import fr.rpcore.rpchat.gui.NameRPGui;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NameRPMessageToClient implements IMessage{

        private String rpname;
        private EntityPlayer player1;
        private String actualRPName;



        public NameRPMessageToClient() { }



        public NameRPMessageToClient(String text, EntityPlayer player, String actualRPName) {

            this.rpname = text;
            this.player1 = player;
            this.actualRPName = actualRPName;

        }



        @Override

        public void fromBytes(ByteBuf buf) {

            rpname = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects

        }



        @Override

        public void toBytes(ByteBuf buf) {

            ByteBufUtils.writeUTF8String(buf, "");

        }



        public static class Handler implements IMessageHandler<fr.rpcore.rpchat.packets.NameRPMessageToClient, IMessage> {





            @Override
            public IMessage onMessage(NameRPMessageToClient message, MessageContext ctx) {

                if(Minecraft.getMinecraft().currentScreen == new NameRPGui()){

                    Minecraft.getMinecraft().fontRenderer.drawString(ChatFormatting.WHITE+"Votre nom rp actuel est"+message.actualRPName, 100, 100, 000);

                }

                return null;
            }
        }

    }