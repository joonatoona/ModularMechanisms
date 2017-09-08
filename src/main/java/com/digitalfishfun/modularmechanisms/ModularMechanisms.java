/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms;

import com.digitalfishfun.modularmechanisms.blocks.BlockRegistry;
import com.digitalfishfun.modularmechanisms.client.MMTab;
import com.digitalfishfun.modularmechanisms.items.ItemPressHead;
import com.digitalfishfun.modularmechanisms.items.ItemRegistry;
import com.digitalfishfun.modularmechanisms.proxy.CommonProxy;
import com.digitalfishfun.modularmechanisms.recipes.RecipePressHead;
import com.digitalfishfun.modularmechanisms.utils.MMLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModularMechanisms.MODID, version = ModularMechanisms.VERSION)
public class ModularMechanisms
{
    public static final String MODID = "modularmechanisms";
    public static final String VERSION = "0.0.1";

    public static final MMTab creativeTab = new MMTab();

    @SidedProxy(serverSide = "com.digitalfishfun.modularmechanisms.proxy.CommonProxy", clientSide = "com.digitalfishfun.modularmechanisms.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MMLogger.logger = event.getModLog();
        ForgeRegistries.RECIPES.register(new RecipePressHead());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    // TODO: Move this into a separate class
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> evt) {
            MMLogger.info("Registering Blocks");
            BlockRegistry.register(evt.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> evt) {
            MMLogger.info("Registering Items");
            BlockRegistry.registerItems(evt.getRegistry());
            ItemRegistry.register(evt.getRegistry());
        }

        @SubscribeEvent
        public static void registerItemModels(ModelRegistryEvent evt) {
            MMLogger.info("Registering Models");
            BlockRegistry.registerModels();
            ItemRegistry.registerModels();
        }
    }
}
