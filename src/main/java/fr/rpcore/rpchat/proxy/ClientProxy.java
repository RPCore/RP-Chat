package fr.rpcore.rpchat.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

import java.io.File;

public class ClientProxy extends CommonProxy{

    public static KeyBinding keyRPChatGUI;

    @Override
    public void preInit(File configFile)
    {

        super.preInit(configFile);

        System.out.println("salut ! Côté client c'te fois ci :)");


        keyRPChatGUI = new KeyBinding("RP-Chat", Keyboard.KEY_R, "key.categories.gameplay");
        ClientRegistry.registerKeyBinding(keyRPChatGUI);



    }


    @Override
    public void init()
    {
        super.init();
    }




}
/*

    Class By Nathanael2611

 */