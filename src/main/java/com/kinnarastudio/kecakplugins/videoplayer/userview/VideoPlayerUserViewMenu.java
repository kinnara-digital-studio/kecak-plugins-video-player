package com.kinnarastudio.kecakplugins.videoplayer.userview;

import org.joget.apps.app.service.AppUtil;
import org.joget.apps.userview.model.UserviewMenu;
import org.joget.commons.util.LogUtil;
import org.joget.plugin.base.PluginManager;
import org.joget.plugin.base.PluginWebSupport;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class VideoPlayerUserViewMenu extends UserviewMenu implements PluginWebSupport {
    @Override
    public String getCategory() {
        return "Kecak";
    }

    @Override
    public String getIcon() {
        return "<i class=\"fas fa-table\"></i>";
    }

    @Override
    public String getRenderPage() {
        ApplicationContext applicationContext = AppUtil.getApplicationContext();
        PluginManager pluginManager = (PluginManager) applicationContext.getBean("pluginManager");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("className", getClassName());

        String url = getPropertyString("urlField");

        dataModel.put("urlField", url);

        return pluginManager.getPluginFreeMarkerTemplate(dataModel, getClassName(),
                "/templates/VideoPlayerUserViewMenu.ftl",
                null);
    }

    @Override
    public boolean isHomePageSupported() {
        return false;
    }

    @Override
    public String getDecoratedMenu() {
        return null;
    }

    @Override
    public String getName() {
        return "Video Player";
    }

    @Override
    public String getVersion() {
        PluginManager pluginManager = (PluginManager) AppUtil.getApplicationContext().getBean("pluginManager");
        ResourceBundle resourceBundle = pluginManager.getPluginMessageBundle(getClassName(), "/messages/BuildNumber");
        String buildNumber = resourceBundle.getString("buildNumber");
        return buildNumber;
    }

    @Override
    public String getDescription() {
        return getClass().getPackage().getImplementationTitle();
    }

    @Override
    public void webService(javax.servlet.http.HttpServletRequest httpServletRequest,
            javax.servlet.http.HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

        try {
            URL url = new URL(videoUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            httpServletResponse.setContentType("video/mp4");
            httpServletResponse.setHeader("Content-Disposition", "inline; filename=\"video.mp4\"");

            try (InputStream inputStream = connection.getInputStream();
                    OutputStream outputStream = httpServletResponse.getOutputStream()) {
                byte[] bytes = new byte[16384];
                int bytesRead;

                while ((bytesRead = inputStream.read(bytes)) >= 0) {
                    outputStream.write(bytes, 0, bytesRead);
                }
            }
        } catch (Exception e) {
            LogUtil.error(getClassName(), e, "Error streaming video from URL: " + videoUrl);
            if (!httpServletResponse.isCommitted()) {
                httpServletResponse.sendError(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error streaming video");
            }
        }
    }

    @Override
    public String getLabel() {
        return "Video Player";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClassName(), "/properties/userview/VideoPlayerUserViewMenu.json");
    }
}
