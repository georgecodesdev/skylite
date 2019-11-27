package com.example.skylite.Services;

import android.content.Context;

public class ServiceBase {
    private IConstellationService _constellationService;
    private IWikiService _wikiService;
    private static ServiceBase _services;

    public ServiceBase(Context context) {
        _constellationService = new ConstellationService(context);
        _constellationService.populateList();
    }

    public static void init(ServiceBase services) {
        _services = services;
        _services.setWikiService();
    }

    public static IConstellationService constellationService() {
        return _services._constellationService;
    }

    private void setWikiService() {
        this._wikiService = new WikiService();
    }

    public static IWikiService wikiService() {
        return _services._wikiService;
    }
}