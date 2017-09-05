package com.digitalfishfun.modularmachines;

import com.digitalfishfun.modularmachines.blocks.BlockPressCore;
import com.digitalfishfun.modularmachines.blocks.BlockRegistry;
import com.digitalfishfun.modularmachines.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ModularMachines.MODID, version = ModularMachines.VERSION)
public class ModularMachines
{
    public static final String MODID = "modularmachines";
    public static final String VERSION = "1.0";

    @SidedProxy(serverSide = "com.digitalfishfun.modularmachines.proxy.CommonProxy", clientSide = "com.digitalfishfun.modularmachines.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> evt) {
            System.out.println("Registering blocks");
            BlockRegistry.register(evt.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> evt) {
            BlockRegistry.registerItems(evt.getRegistry());
        }

        @SubscribeEvent
        public static void registerItemModels(ModelRegistryEvent evt) {
            BlockRegistry.registerModels();
        }
    }
}
