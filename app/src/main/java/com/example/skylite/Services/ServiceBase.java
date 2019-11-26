package com.example.skylite.Services;

import android.content.Context;

public class ServiceBase {
    private IConstellationService _constellationService;
    private static ServiceBase _services;

    public ServiceBase(Context context) {
        _constellationService = new ConstellationService(context);
    }

    public static void init(ServiceBase services) {
        _services = services;
    }

    public static IConstellationService constellationService() {
        return _services._constellationService;
    }
}
