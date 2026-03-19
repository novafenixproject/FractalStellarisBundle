package novafenixproject.fractalstellaris.client.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class FractalLanguageProvider_en_us extends FractalLanguage{
    public FractalLanguageProvider_en_us(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup,"en_us");
    }
}
