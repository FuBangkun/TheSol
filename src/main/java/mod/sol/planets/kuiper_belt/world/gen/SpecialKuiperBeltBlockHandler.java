package mod.sol.planets.kuiper_belt.world.gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SpecialKuiperBeltBlockHandler {
    ArrayList<SpecialKuiperBeltBlock> asteroidBlocks = new ArrayList<>();

    public SpecialKuiperBeltBlockHandler(SpecialKuiperBeltBlock... asteroidBlocks) {

        for (SpecialKuiperBeltBlock asteroidBlock : this.asteroidBlocks) {
            for (int i = 0; i < asteroidBlock.probability; ++i) {
                this.asteroidBlocks.add(asteroidBlock);
            }
        }

    }

    public SpecialKuiperBeltBlockHandler() {
    }

    public void addBlock(SpecialKuiperBeltBlock asteroidBlock) {
        for (int i = 0; i < asteroidBlock.probability; ++i) {
            this.asteroidBlocks.add(asteroidBlock);
        }

    }

    public SpecialKuiperBeltBlock getBlock(Random rand, int size) {
        int s = this.asteroidBlocks.size();
        if (s < 10) {
            return this.asteroidBlocks.get(rand.nextInt(s));
        } else {
            double r = rand.nextDouble();
            int index = (int) ((double) s * Math.pow(r, (double) (size + 5) * 0.05D));
            return this.asteroidBlocks.get(index);
        }
    }
}
