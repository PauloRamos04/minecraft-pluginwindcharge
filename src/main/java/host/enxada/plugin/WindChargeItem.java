package host.enxada.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class WindChargeItem implements Listener {

    private static JavaPlugin plugin;

    public WindChargeItem(JavaPlugin plugin) {
        WindChargeItem.plugin = plugin;
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(this, plugin);
    }

    public static ItemStack createItem() {
        ItemStack windCharge = new ItemStack(Material.WIND_CHARGE); // Usando WIND_CHARGE
        ItemMeta meta = windCharge.getItemMeta();
        meta.setDisplayName("Wind Charge Modificado");
        windCharge.setItemMeta(meta);
        return windCharge;
    }

    // Evento para detectar quando o item é usado
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item != null && item.getType() == Material.WIND_CHARGE && item.getItemMeta() != null
                && "Wind Charge Modificado".equals(item.getItemMeta().getDisplayName())) {

            double projectileSpeed = 3.0; // Velocidade do projétil

            // Lançar um projétil WindCharge
            WindCharge windCharge = player.launchProjectile(WindCharge.class);
            Vector velocity = player.getLocation().getDirection().multiply(projectileSpeed);
            windCharge.setVelocity(velocity);
            windCharge.setCustomName("WindChargeProjectile");
            windCharge.setCustomNameVisible(false);
        }
    }

    // Evento para detectar quando o projétil atinge algo
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile.getType() == EntityType.WIND_CHARGE && "WindChargeProjectile".equals(projectile.getCustomName())) {
            int explosionPower = 10; // Força da explosão
            boolean addParticles = true; // Adicionar partículas

            // Exemplo de lógica para a explosão e partículas
            projectile.getWorld().createExplosion(projectile.getLocation(), explosionPower);
            if (addParticles) {
                projectile.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, projectile.getLocation(), 10);
            }
        }
    }
}
