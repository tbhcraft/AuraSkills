package dev.aurelium.auraskills.bukkit.hooks;

import dev.aurelium.auraskills.bukkit.AuraSkills;
import dev.aurelium.auraskills.common.hooks.Hook;
import net.countercraft.movecraft.Movecraft;
import net.countercraft.movecraft.MovecraftLocation;
import net.countercraft.movecraft.craft.PilotedCraft;
import net.countercraft.movecraft.events.CraftReleaseEvent;
import org.bukkit.Location;
import org.bukkit.World;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;

public class MovecraftHook extends Hook implements Listener {
    private final AuraSkills plugin;

    public MovecraftHook(AuraSkills plugin, ConfigurationNode config) {
        super(plugin, config);
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onCraftRelease(@NotNull CraftReleaseEvent e) {
        if (!(e.getCraft() instanceof PilotedCraft))
            return;

        if (Movecraft.getInstance() == null)
            return;


        World w = e.getCraft().getWorld();
        for (MovecraftLocation movecraftLocation : e.getCraft().getHitBox()) {
            Location loc = movecraftLocation.toBukkit(w);

            this.plugin.getRegionManager().addPlacedBlock(loc.getBlock());

        }
    }

    @Override
    public Class<MovecraftHook> getTypeClass() {
        return MovecraftHook.class;
    }

}


