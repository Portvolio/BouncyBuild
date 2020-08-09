package wtf.violet.portvolio.bouncybuild.listener;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class BuildListener implements Listener
{

    private final Map<Player, Integer> blocksByPlayer = new HashMap<>();

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event)
    {
        final Player player = event.getPlayer();

        // This sets to 1 by default, adds 1 otherwise. I love this.
        final int result = blocksByPlayer.merge(player, 1, Integer::sum);

        if (result == 100)
        {
            blocksByPlayer.put(player, 0);

            final PlayerInventory inv = player.getInventory();
            final ItemStack[] contents = inv.getContents();

            ItemStack target = null;

            // Attempt 1: Try to find a sword
            for (final ItemStack stack : contents)
            {
                if (stack == null) continue;

                // Set the first item found to be the target, in case there's no sword
                if (target == null)
                {
                    target = stack;
                }

                if (stack.getType().toString().endsWith("SWORD"))
                {
                    target = stack;
                    break;
                }
            }

            if (target == null)
            {
                return; // Fine, no knockback for you
            }

            target.addUnsafeEnchantment(
                Enchantment.KNOCKBACK,
                target.getEnchantmentLevel(Enchantment.KNOCKBACK) + 1
            );
        }
    }

}
