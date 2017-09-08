/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.config;

import com.digitalfishfun.modularmechanisms.utils.MMLogger;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    public Configuration conf;

    private Property headMaterialList;

    public List<Item> headMaterials = new ArrayList<>();

    public ConfigManager(File file) {
        conf = new Configuration(file);

        conf.load();
        headMaterialList = conf.get("general", "headMaterials", new String[] {
                "minecraft:diamond", "minecraft:iron_ingot", "minecraft:gold_ingot"
        });
        conf.save();

        for (String material : headMaterialList.getStringList()) {
            Item item = Item.getByNameOrId(material);
            if (item == Items.AIR) {
                MMLogger.error("Invalid material "+material);
                continue;
            }
            MMLogger.info("Loading "+material);
            headMaterials.add(item);
        }

        MMLogger.info("Initialized " + headMaterials.size() + " head materials");
    }
}
