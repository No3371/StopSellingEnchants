package com.BAStudio.StopSellingEnchants;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EventHandlers {

    @SubscribeEvent
    public void RemoveEnchants(VillagerTradesEvent event) {
        int sumEvent = 0, sumTrade = 0;
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            sumEvent++;
            Int2ObjectMap<List<VillagerTrades.ITrade>> tradeLevels = event.getTrades();
            for (int i = 1; i < 6; i++) {
                List<VillagerTrades.ITrade> trades = tradeLevels.get(i);
                for (int t = trades.size() - 1; t > 0; t--) {
                    if (trades.get(t) instanceof VillagerTrades.EnchantedBookForEmeraldsTrade) {
                        trades.remove(t);
                        sumTrade++;
                    }
                }
            }
        }
        Logger logger = LogManager.getLogger(Consts.MODID);
        logger.log(Level.INFO, String.format("Touched VillagerTradesEvent: %d, Removed trades: %d", sumEvent, sumTrade));
        System.out.println(String.format("Touched VillagerTradesEvent: %d, Removed trades: %d", sumEvent, sumTrade));
    }
}
