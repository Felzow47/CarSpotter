package com.felzow47.carspotter.data.entity

enum class VehiculeType(val displayName: String) {
    BERLINE("Berline"),
    BREAK("Break"),
    COUPE("Coupé"),
    CABRIOLET("Cabriolet"),
    SUV("SUV"),
    CROSSOVER("Crossover"),
    HATCHBACK("Hatchback"),
    MONOSPACE("Monospace"),
    PICKUP("Pick-up"),
    UTILITAIRE("Utilitaire"),
    CAMION("Camion"),
    MOTO("Moto"),
    SCOOTER("Scooter"),
    SPORTIVE("Sportive"),
    LUXE("Luxe"),
    CLASSIQUE("Classique"),
    ELECTRIQUE("Électrique"),
    HYBRIDE("Hybride"),
    AUTRE("Autre");

    companion object {
        fun fromDisplayName(displayName: String): VehiculeType? {
            return values().find { it.displayName == displayName }
        }
    }
}
