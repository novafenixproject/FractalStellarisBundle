package novafenixproject.fractalstellaris.client.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import novafenixproject.fractalstellaris.common.Global;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class FractalLanguageProvider extends FabricLanguageProvider {
    public static class Registry<T>{
        private final String prefix;

        protected Registry(String prefix){
            this.prefix = prefix;
        }
        private final Map<String,String> objects = new HashMap<>();
        private String mountIdentifier(String id){
            return prefix + "." + Global.NAMESPACE + "." + id;
        }
        public void register(String id, String translated){
            this.objects.put(this.mountIdentifier(id),translated);
        }

        public void registers(TranslationBuilder translationBuilder){
            for(String identifier:this.objects.keySet()){
                String translated = this.objects.getOrDefault(identifier,"minecraft:air");
                translationBuilder.add(identifier,translated);
            }
        }
    }
    public final Registry<Block> BLOCK = new Registry<>("block");
    public final Registry<Item> ITEM = new Registry<>("item");

    public final Registry<CreativeModeTab> CREATIVE_MODETAB = new Registry<>("itemGroup");

    protected FractalLanguageProvider(FabricDataOutput dataOutput,String lauguageCode,CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput,lauguageCode, registryLookup);
    }
    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        this.BLOCK.registers(translationBuilder);
        this.ITEM.registers(translationBuilder);
        this.CREATIVE_MODETAB.registers(translationBuilder);

    }
}
