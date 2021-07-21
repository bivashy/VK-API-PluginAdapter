package com.ubivashka.vk.bungee;

import java.util.concurrent.TimeUnit;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

public abstract class ProxyRunnable implements Runnable {
  private ScheduledTask _task;
  
  public void cancel() {
    this._task.cancel();
  }
  
  public abstract void run();
  
  public ScheduledTask runAsync(Plugin plugin) {
    return ProxyServer.getInstance().getScheduler().runAsync(plugin, this);
  }
  
  public ScheduledTask runAfter(Plugin plugin, long time, TimeUnit unit) {
    return ProxyServer.getInstance().getScheduler().schedule(plugin, this, time, unit);
  }
  
  public ScheduledTask runAfterEvery(Plugin plugin, long time, long repeat, TimeUnit unit) {
    return ProxyServer.getInstance().getScheduler().schedule(plugin, this, time, repeat, unit);
  }
}
