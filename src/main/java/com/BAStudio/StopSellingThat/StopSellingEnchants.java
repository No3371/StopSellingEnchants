package com.BAStudio.StopSellingEnchants;

import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Consts.MODID)
public class StopSellingEnchants {

    public StopSellingEnchants()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.serverSpec);
        MinecraftForge.EVENT_BUS.register(Config.class);
        MinecraftForge.EVENT_BUS.register(new EventHandlers());
    }

}

