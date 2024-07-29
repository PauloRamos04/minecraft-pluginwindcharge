package host.enxada.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginWindCharged extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        // Carregar o arquivo de configuração
        saveDefaultConfig();

        // Registrar o comando e seu executor
        if (getCommand("windcharge") != null) {
            getCommand("windcharge").setExecutor(this);
        } else {
            getLogger().severe("Comando 'windcharge' não encontrado no plugin.yml!");
        }

        // Registrar o evento de interação com o item
        new WindChargeItem(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("windcharge")) {
                player.getInventory().addItem(WindChargeItem.createItem());
                player.sendMessage("Você recebeu um WindCharge modificado!");
                return true;
            }
        } else {
            sender.sendMessage("Este comando deve ser executado por um jogador.");
        }
        return false;
    }
}
