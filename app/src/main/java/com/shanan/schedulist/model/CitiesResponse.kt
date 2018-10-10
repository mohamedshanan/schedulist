package com.shanan.schedulist.model

object CitiesResponse {

    data class CitiesResponse(
            val CityResource: CityResource
    )

    data class CityResource(
            val Cities: Cities,
            val Meta: Meta
    )

    data class Cities(
            val City: List<City>
    )

    data class City(
            val CityCode: String,
            val CountryCode: String,
            val Position: Position,
            val Names: Names,
            val Airports: Airports
    )

    data class Names(
            val Name: Name
    )

    data class Name(
            val LanguageCode: String
    )

    data class Position(
            val Coordinate: Coordinate
    )

    data class Coordinate(
            val Latitude: Double,
            val Longitude: Double
    )

    data class Airports(
            val AirportCode: String
    )

    data class Meta(
            val Version: String,
            val Link: List<Link>,
            val TotalCount: Int
    )

    data class Link(
            val Href: String,
            val Rel: String
    )
}
