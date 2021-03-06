package net.creeperhost.creeperlauncher.api.data.other;

import net.creeperhost.creeperlauncher.api.data.BaseData;

import java.util.HashMap;

public class InstalledFileEventData extends BaseData {
    public static class Reply extends BaseData {
        String fileName;
        String status;
        public Reply(String fileName, String status) {
            this.fileName = fileName;
            this.status = status;
            this.type = "installedFileEventDataReply";
        }
    }
}
