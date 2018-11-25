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

public class ChangeNameRP implements IMessage{


        private String text;



        public ChangeNameRP() { }



        public ChangeNameRP(String NewRPName) {

            this.text = NewRPName;

        }



        @Override

        public void fromBytes(ByteBuf buf) {

            text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects

        }



        @Override

        public void toBytes(ByteBuf buf) {

            ByteBufUtils.writeUTF8String(buf, text);

        }



        public static class Handler implements IMessageHandler<fr.rpcore.rpchat.packets.ChangeNameRP, IMessage> {



            @Override

            public IMessage onMessage(fr.rpcore.rpchat.packets.ChangeNameRP message, MessageContext ctx) {


                Database db = DatabaseGetter.getInstance(RPChat.MODID);

                DBFolder folder = db.getPersistentFolder();

                folder.setString("name."+ctx.getServerHandler().player.getName(), message.text);


                return null;

            }

        }

    }