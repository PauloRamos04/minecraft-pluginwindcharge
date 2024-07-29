package host.enxada.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;

public class GiveWindChargeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack windCharge = new ItemStack(Material.WIND_CHARGE);
            ItemMeta meta = windCharge.getItemMeta();
            meta.setDisplayName("Wind Charge");
            windCharge.setItemMeta(meta);
            player.getInventory().addItem(windCharge);
            player.sendMessage("Você recebeu um Wind Charge!");
            return true;
        }
        return false;
    }
}
