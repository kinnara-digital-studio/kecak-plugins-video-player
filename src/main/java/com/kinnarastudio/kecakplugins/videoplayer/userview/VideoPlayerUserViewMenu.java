package com.kinnarastudio.kecakplugins.videoplayer.userview;

import org.joget.apps.app.service.AppUtil;
import org.joget.apps.userview.model.UserviewMenu;
import org.joget.plugin.base.PluginManager;
import org.joget.plugin.base.PluginWebSupport;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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



        return pluginManager.getPluginFreeMarkerTemplate(dataModel, getClassName(), "/templates/VideoPlayerUserViewMenu.ftl",
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
    public void webService(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws ServletException, IOException {

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
