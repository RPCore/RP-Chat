package fr.rpcore.rpchat;

import fr.rpcore.rpchat.events.Events.Events;
import fr.rpcore.rpchat.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Mod(modid = "rpchat")
public class RPChat {


    public static final String MODID = "rphud";


    @Mod.Instance(RPChat.MODID)
    public static RPChat instance;




    @SidedProxy(clientSide = "fr.rpcore.rpchat.proxy.ClientProxy", serverSide = "fr.rpcore.rpchat.proxy.ServerProxy")
    public static CommonProxy proxy;


    public RPChat(){
        MinecraftForge.EVENT_BUS.register(new Events());


    }

    public static Configuration cfg;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        proxy.preInit(event.getSuggestedConfigurationFile());






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