package com.nukkitx.exampleplugin.generator;

import net.daporkchop.lib.random.PRandom;
import org.cloudburstmc.server.block.*;
import org.cloudburstmc.server.level.ChunkManager;
import org.cloudburstmc.server.level.chunk.IChunk;
import org.cloudburstmc.server.level.generator.Generator;
import org.cloudburstmc.server.utils.Identifier;

public class DiscoChunkGenerator implements Generator {
    public static final Identifier ID = Identifier.from("exampleplugin", "disco");

    private static final BlockType[] TYPES = new BlockType[] {
            BlockTypes.COAL_BLOCK, BlockTypes.DIAMOND_BLOCK, BlockTypes.GOLD_BLOCK, BlockTypes.IRON_BLOCK,
            BlockTypes.REDSTONE_BLOCK, BlockTypes.EMERALD_BLOCK, BlockTypes.LAPIS_LAZULI_BLOCK
    };

    private final BlockState bedrock;

    public DiscoChunkGenerator(long seed, String options) {
        bedrock = BlockPalette.INSTANCE.getDefaultState(BlockIds.BEDROCK);
    }

    @Override
    public void generate(PRandom pRandom, IChunk iChunk, int i, int i1) {
        for (int x1 = 0; x1 < 16; x1++) {
            for (int z1 = 0; z1 < 16; z1++) {
                iChunk.setBlock(x1, 0, z1, bedrock);
                BlockType type = TYPES[pRandom.nextInt(TYPES.length)];
                iChunk.setBlock(x1, 1, z1, BlockPalette.INSTANCE.getDefaultState(type.getId()));
            }
        }

        if (iChunk.getX() == 0 && iChunk.getZ() == 0) {
            iChunk.setBlock(0, 4, 0, bedrock);
        }
    }

    @Override
    public void populate(PRandom pRandom, ChunkManager chunkManager, int i, int i1) {
        // Nothing to do... yet
    }

    @Override
    public void finish(PRandom pRandom, ChunkManager chunkManager, int i, int i1) {
        // Nothing to do... yet
    }
}
