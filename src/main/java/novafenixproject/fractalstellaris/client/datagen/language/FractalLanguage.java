package novafenixproject.fractalstellaris.client.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import novafenixproject.fractalstellaris.api.FractalBiomes;
import novafenixproject.fractalstellaris.api.Global;
import novafenixproject.fractalstellaris.api.TranslationKey;
import novafenixproject.fractalstellaris.api.blocks.FractalBlocks;

import java.util.concurrent.CompletableFuture;

public class FractalLanguage extends FabricLanguageProvider {
    public final String LANGUAGE_CODE;
    public FractalLanguage(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup,String languageCode) {
        super(dataOutput,languageCode,registryLookup);
        this.LANGUAGE_CODE = languageCode;
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        TranslationKey key = getKey(LANGUAGE_CODE);
        //traducao dos blocos
        for(FractalBlocks fractalBlocks : FractalBlocks.values()) {
            translationBuilder.add(fractalBlocks.BLOCK.getBlock(),fractalBlocks.BLOCK.getTranslation(key));
        }
        //traducao dos biomas
        for(FractalBiomes biome : FractalBiomes.values()) {
            String namespace = Global.NAMESPACE;
            String name = biome.name().toLowerCase();
            translationBuilder.add("biome." + namespace + "." + name,biome.getFractalBiome().getTranslation(key));
        }
    }
    private TranslationKey getKey(String key) {
        for(TranslationKey translationKey : TranslationKey.values()) {
            if(translationKey.get().equals(key)) {
                return translationKey;
            }
        }
        return null;
    }
}
