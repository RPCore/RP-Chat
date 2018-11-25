package fr.rpcore.rpchat;

import fr.rpcore.rpchat.events.Events.Events;
import fr.rpcore.rpchat.packets.ChangeNameRP;
import fr.rpcore.rpchat.packets.NameRPMessage;
import fr.rpcore.rpchat.packets.NameRPMessageToClient;
import fr.rpcore.rpchat.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Mod(modid = "rpchat")
public class RPChat {


    public static final String MODID = "rphud";


    @Mod.Instance(RPChat.MODID)
    public static RPChat instance;





    public static File getConfig(){
        File configFile = new File(Loader.instance().getConfigDir(), "rp-chat.txt");
        return configFile;
    }






    @SidedProxy(clientSide = "fr.rpcore.rpchat.proxy.ClientProxy", serverSide = "fr.rpcore.rpchat.proxy.ServerProxy")
    public static CommonProxy proxy;


    public RPChat(){
        MinecraftForge.EVENT_BUS.register(new Events());



    }

    public static Configuration cfg;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        proxy.preInit(event.getSuggestedConfigurationFile());

        File configFile = RPChat.getConfig();

        network  = NetworkRegistry.INSTANCE.newSimpleChannel(MODID+".CHANNEL");
        network.registerMessage(NameRPMessage.Handler.class, NameRPMessage.class, 0, Side.SERVER);

        network.registerMessage(NameRPMessageToClient.Handler.class, NameRPMessageToClient.class, 1, Side.CLIENT);
        network.registerMessage(ChangeNameRP.Handler.class, ChangeNameRP.class, 2, Side.SERVER);


        if (Methods.FileReader(configFile).equals("notexist")) {
            try (PrintWriter output = new PrintWriter(configFile)) {
                output.println("Configuration des préfixes:");
                output.println("HRP PREFIX:(");
                output.println("SHOUT PREFIX:!");
                output.println("WHISP PREFIX:$");
                output.println("ACTION PREFIX:*");
                output.println("");
                output.println("Configuration des distances:");
                output.println("SPEAK DISTANCE:15");
                output.println("SHOUT DISTANCE:30");
                output.println("WHISP DISTANCE:3");
                output.println("ACTION DISTANCE:15");
                output.println("");
                output.println("Configuration des formats de chat:");
                output.println("HRP FORMAT:&f{username} [HRP]: {msg}");
                output.println("SPEAK FORMAT:&7{rpname} &fdit: {msg}");
                output.println("SHOUT FORMAT:&7{rpname} &ccrie: {msg}");
                output.println("WHISP FORMAT:&7{rpname} &2chuchotte: {msg}");
                output.println("ACTION FORMAT:&a&o{rpname} {msg}");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }





    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        proxy.init();



    }


    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event)
    {

    }

}
/*

    Class By Nathanael2611

 */