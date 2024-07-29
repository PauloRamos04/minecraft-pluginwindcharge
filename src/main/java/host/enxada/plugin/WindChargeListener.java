package host.enxada.plugin;

import org.bukkit.Particle;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WindChargeListener implements Listener {

    private final PluginWindCharged plugin;

    public WindChargeListener(PluginWindCharged plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerUseWindCharge(PlayerInteractEvent event) {
        ItemStack item = event.getItem();

        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().equals("Wind Charge")) {
                event.getPlayer().sendMessage("Você usou o Wind Charge!");

                double explosionPower = plugin.getConfig().getDouble("windcharge.explosionPower", 2.0);
                boolean addParticles = plugin.getConfig().getBoolean("windcharge.addParticles", true);
                double projectileSpeed = plugin.getConfig().getDouble("windcharge.projectileSpeed", 1.0);

                Fireball fireball = event.getPlayer().launchProjectile(Fireball.class);
                fireball.setYield((float) explosionPower);
                fireball.setVelocity(event.getPlayer().getLocation().getDirection().multiply(projectileSpeed));

                if (addParticles) {
                    fireball.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, fireball.getLocation(), 10);
                }

                event.setCancelled(true);
            }
        }
    }
}
