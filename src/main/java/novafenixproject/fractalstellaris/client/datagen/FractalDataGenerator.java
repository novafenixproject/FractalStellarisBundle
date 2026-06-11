package novafenixproject.fractalstellaris.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import novafenixproject.fractalstellaris.client.datagen.blocks.FractalModelProvider;
import novafenixproject.fractalstellaris.client.datagen.language.FractalLanguageProviderEnUs;
import novafenixproject.fractalstellaris.client.datagen.language.FractalLanguageProviderPtBr;

public class FractalDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FractalModelProvider::new);
        pack.addProvider(FractalLanguageProviderPtBr::new);
        pack.addProvider(FractalLanguageProviderEnUs::new);
    }
}
