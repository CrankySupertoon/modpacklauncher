package net.creeperhost.creeperlauncher.api.handlers;

import net.creeperhost.creeperlauncher.Settings;
import net.creeperhost.creeperlauncher.api.data.SettingsConfigureData;
import net.creeperhost.creeperlauncher.util.SettingsChangeUtil;

import java.util.Map;

public class SettingsConfigureHandler implements IMessageHandler<SettingsConfigureData>
{
    @Override
    public void handle(SettingsConfigureData data)
    {
        for (Map.Entry<String, String> setting : data.settingsInfo.entrySet())
        {
            try {
                boolean changed = false;
                if (Settings.settings.containsKey(setting.getKey()) && !Settings.settings.get(setting.getKey()).equals(setting.getValue()))
                    changed = true;
                Settings.settings.remove(setting.getKey());
                Settings.settings.put(setting.getKey(), setting.getValue());
                if (changed) SettingsChangeUtil.settingsChanged(setting.getKey(), setting.getValue());
            } catch (Exception e) {
            }
        }
        Settings.saveSettings();
        Settings.webSocketAPI.sendMessage(new SettingsConfigureData.Reply(data, "success"));
    }
}