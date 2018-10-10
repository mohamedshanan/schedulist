package com.shanan.schedulist.model

import com.google.gson.annotations.SerializedName

data class AirportsResponse(
        @SerializedName("AirportResource") val airportResource: AirportResource
)

data class AirportResource(
        @SerializedName("Airports") val airports: Airports
)

data class Airports(
        @SerializedName("Airport") val airport: List<Airport>
)

data class Airport(
        @SerializedName("AirportCode") val airportCode: String,
        @SerializedName("Position") val position: Position,
        @SerializedName("CityCode") val cityCode: String,
        @SerializedName("CountryCode") val countryCode: String,
        @SerializedName("LocationType") val locationType: String,
        @SerializedName("Names") val names: Names
)

data class Names(
        @SerializedName("Name") val name: List<Name>
)

data class Name(
        @SerializedName("$") val value: String
)

data class Position(
        @SerializedName("Coordinate") val coordinate: Coordinate
)

data class Coordinate(
        @SerializedName("Latitude") val latitude: Double,
        @SerializedName("Longitude") val longitude: Double
)