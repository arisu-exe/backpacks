package io.github.falOut015.backpacks.loot;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class LootParameterSetsBackpacks {
    private static final BiMap<ResourceLocation, LootParameterSet> REGISTRY = HashBiMap.create();

    public static final LootParameterSet KNAPSACK = register("knapsack", builder -> {
        // Check for item
        // A knapsack can contain a set of items determined from the loot tables.
    });

    private static LootParameterSet register(String p_216253_0_, Consumer<LootParameterSet.Builder> p_216253_1_) {
        LootParameterSet.Builder lootparameterset$builder = new LootParameterSet.Builder();
        p_216253_1_.accept(lootparameterset$builder);
        LootParameterSet lootparameterset = lootparameterset$builder.build();
        ResourceLocation resourcelocation = new ResourceLocation(p_216253_0_);
        LootParameterSet lootparameterset1 = REGISTRY.put(resourcelocation, lootparameterset);
        if (lootparameterset1 != null) {
            throw new IllegalStateException("Loot table parameter set " + resourcelocation + " is already registered");
        } else {
            return lootparameterset;
        }
    }
    @Nullable
    public static LootParameterSet getValue(ResourceLocation p_216256_0_) {
        return REGISTRY.get(p_216256_0_);
    }
    @Nullable
    public static ResourceLocation getKey(LootParameterSet p_216257_0_) {
        return REGISTRY.inverse().get(p_216257_0_);
    }
}

// TODO