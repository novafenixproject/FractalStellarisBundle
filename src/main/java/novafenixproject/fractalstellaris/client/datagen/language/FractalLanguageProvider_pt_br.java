package novafenixproject.fractalstellaris.client.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class FractalLanguageProvider_pt_br extends FractalLanguage {
    public FractalLanguageProvider_pt_br(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput,registryLookup,"pt_br");
    }
}
