package com.nukkitx.exampleplugin;

import com.nukkitx.exampleplugin.generator.DiscoChunkGenerator;
import org.cloudburstmc.server.Server;
import org.cloudburstmc.server.event.Listener;
import org.cloudburstmc.server.event.player.PlayerJoinEvent;
import org.cloudburstmc.server.event.server.ServerInitializationEvent;
import org.cloudburstmc.server.event.server.ServerShutdownEvent;
import org.cloudburstmc.server.event.server.ServerStartEvent;
import org.cloudburstmc.server.plugin.Plugin;
import org.cloudburstmc.server.plugin.PluginDescription;
import org.cloudburstmc.server.utils.TextFormat;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.nio.file.Path;

@Plugin(id = "ExamplePlugin", authors = {"CloudburstMC Team"}, version = "1.0.0")
public class ExamplePlugin {
    private final Logger logger;
    private final PluginDescription description;
    private final Path dataFolder;
    private final Server server;

    @Inject
    private ExamplePlugin(Logger logger, PluginDescription description, Path dataFolder, Server server) {
        this.logger = logger;
        this.description = description;
        this.dataFolder = dataFolder;
        this.server = server;
    }

    /*
     * This event is called before the server has fully loaded.
     * Register custom generators, items, blocks, entities etc at this method.
     */
    @Listener
    public void onInitialization(ServerInitializationEvent event) {
        logger.info(TextFormat.DARK_GREEN + description.getId() + " initialization!");
        server.getGeneratorRegistry().register(DiscoChunkGenerator.ID, DiscoChunkGenerator::new, 500);
    }

    /*
     * This event is called after the server is fully loaded.
     */
    @Listener
    public void onStart(ServerStartEvent event) {
        logger.info(TextFormat.GREEN + description.getId() + " started!");
    }

    /*
     * This event is called before the server has fully shuts down.
     */
    @Listener
    public void onShutdown(ServerShutdownEvent event) {
        logger.info(TextFormat.DARK_RED + description.getId() + " shutting down!");
    }

    @Listener
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Welcome to the test server! This is experimental server software so there may be bugs.");
    }
}
