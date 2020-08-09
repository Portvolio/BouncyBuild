package wtf.violet.portvolio.bouncybuild;

import org.bukkit.plugin.java.JavaPlugin;
import wtf.violet.portvolio.bouncybuild.listener.BuildListener;

public final class BouncyBuild extends JavaPlugin {

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new BuildListener(), this);
    }
    
}
