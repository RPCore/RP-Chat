package fr.rpcore.rpchat.packets;

import com.trcgames.dbSynchronizer.DatabaseGetter;
import com.trcgames.dbSynchronizer.database.DBFolder;
import com.trcgames.dbSynchronizer.database.Database;
import fr.rpcore.rpchat.RPChat;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NameRPMessage implements IMessage {
    private String text;
    private EntityPlayer player1;



    public NameRPMessage() { }



    public NameRPMessage(String text, EntityPlayer player) {

        this.text = text;
        this.player1 = player;

    }



    @Override

    public void fromBytes(ByteBuf buf) {

        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects

    }



    @Override

    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf, text);
        ByteBufUtils.writeUTF8String(buf, player1.getDisplayNameString());

    }



    public static class Handler implements IMessageHandler<NameRPMessage, IMessage> {



        @Override

        public IMessage onMessage(NameRPMessage message, MessageContext ctx) {

            System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().player.getDisplayName()));

            Database db = DatabaseGetter.getInstance(RPChat.MODID);

            DBFolder folder = db.getPersistentFolder();

            String x90  = folder.getString("name."+ctx.getServerHandler().player.getName());

            RPChat.network.sendTo(new NameRPMessageToClient(x90, message.player1, x90), ctx.getServerHandler().player);

            return null;

        }

    }

}