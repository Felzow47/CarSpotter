package com.felzow47.carspotter.data.database

import androidx.room.TypeConverter
import com.felzow47.carspotter.data.entity.VehiculeType
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromVehiculeType(value: String): VehiculeType {
        return VehiculeType.valueOf(value)
    }

    @TypeConverter
    fun vehiculeTypeToString(vehiculeType: VehiculeType): String {
        return vehiculeType.name
    }
}
