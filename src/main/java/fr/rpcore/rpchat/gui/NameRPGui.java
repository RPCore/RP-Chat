package fr.rpcore.rpchat.gui;

import com.mojang.realmsclient.gui.ChatFormatting;

import com.trcgames.dbSynchronizer.DatabaseGetter;
import com.trcgames.dbSynchronizer.database.DBFolder;
import com.trcgames.dbSynchronizer.database.Database;
import fr.rpcore.rpchat.Methods;
import fr.rpcore.rpchat.RPChat;
import fr.rpcore.rpchat.packets.ChangeNameRP;
import fr.rpcore.rpchat.packets.NameRPMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class NameRPGui extends GuiScreen{


        Minecraft mc = Minecraft.getMinecraft();
        private final int ImageHeight = 250, ImageWidth = 250, ImageScale = 250;
        private GuiTextField rpNameField;


        public NameRPGui() {
        }

        @Override
        public void initGui() {



            buttonList.clear();


            this.rpNameField = new GuiTextField(1, Minecraft.getMinecraft().fontRenderer, this.width / 2 + 10, 140, 137, 20);
            rpNameField.setMaxStringLength(23);

            RPChat.network.sendToServer(new NameRPMessage("nimp", Minecraft.getMinecraft().player));

        }

        @Override
        public void updateScreen() {
            super.updateScreen();
            this.rpNameField.updateCursorCounter();

        }

        @Override
        public void drawScreen(int parWidth, int parHeight, float particle) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableColorMaterial();

            this.drawDefaultBackground();
            this.rpNameField.drawTextBox();

            super.drawScreen(parWidth, parHeight, particle);

            //Adjust these values to move locations of elements without individual adjustment
            int offLeft = (int) ((width - ImageWidth) / 2.0F);
            int offTop = 30;
            int topOffset = 20;


            //drawModalRectWithCustomSizedTexture(offLeft, offTop, 0, 0, ImageScale,ImageScale,ImageScale,ImageScale);

            fontRenderer.drawString(ChatFormatting.WHITE + "Changez votre nom rp :" , offLeft + 169 - 140, 35 + topOffset, 0X000000);

            if(rpNameField.getText().replace(" ", "").equals("")){
                fontRenderer.drawString(ChatFormatting.RED + "Vous devez définir un nom rp !" , offLeft + 169 - 140, 60 + topOffset, 0X000000);

            }

            File file = new File(Minecraft.getMinecraft().mcDataDir + "/config/", "rphudtheme.txt");


            if(this.mc.player.canUseCommand(4, "op")){

                this.buttonList.add(new GuiButton(2, offLeft + 169 - 140, 170, 100, 20, "RPChat AdminPanel"));


            }


            this.buttonList.add(new GuiButton(1, offLeft + 169 - 140, 140, 100, 20, "Changer votre nom RP"));


            super.drawScreen(parWidth, parHeight, particle);
        }

        @Override
        protected void keyTyped(char typedChar, int keyCode) {
            try {
                super.keyTyped(typedChar, keyCode);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.rpNameField.textboxKeyTyped(typedChar, keyCode);


        }

        @Override
        protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick) {
        }

        protected void mouseClicked(int x, int y, int btn) {
            try {
                super.mouseClicked(x, y, btn);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.rpNameField.mouseClicked(x, y, btn);

        }

        @Override
        protected void actionPerformed(GuiButton button) {
            if (button.id == 1) {


                if(rpNameField.getText().replace(" ", "").length()>0){

                    RPChat.network.sendToServer(new ChangeNameRP(rpNameField.getText()));

                }else{
                    this.mc.player.sendMessage(new TextComponentString(ChatFormatting.RED+"Vous devez définir un nom rp !"));
                }


            }
            if(button.id == 2){

                if(this.mc.player.canUseCommand(4, "op")){
                    Minecraft.getMinecraft().displayGuiScreen(new ConfigGui());
                }

            }
        }

        @Override
        public void onGuiClosed() {
        }

        @Override
        public boolean doesGuiPauseGame() {
            return false;
        }

        @SideOnly(Side.CLIENT)
        static class GenericButton extends GuiButton {
            public GenericButton(int x, int y, int width, int height, String text) {
                super(1, x, y, width, height, text);
            }
        }
    }
/*
    Class By Nathanael2611
 */