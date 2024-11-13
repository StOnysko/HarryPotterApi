package com.example.harrypotter.util

object Utils {
    const val BASE_URL = "https://hp-api.onrender.com"
    const val GET_CHARACTERS = "/api/characters"
    const val GET_CHARACTER = "/api/character/"
    const val MISSING_IMAGE = "https://cdn-icons-png.freepik.com/512/86/86485.png"
    const val GET_SPELLS = "/api/spells"
}

object UrlUtils {
    fun getImageByURL(url: String, defaultUrl: String = Utils.MISSING_IMAGE): String {
        return url.ifEmpty { defaultUrl }
    }

    fun getHouseByUrl(url: String, defaultValue: String = "Not in a house"): String {
        return url.ifEmpty { defaultValue }
    }

    fun getBirthByUrl(url: String?, defaultValue: String = "Unknown"): String {
        return url ?: defaultValue
    }

    fun getSpeciesByUrl(url: String, defaultValue: String = "Unknown"): String {
        return url.ifEmpty { defaultValue }
    }

    fun getGenderByUrl(url: String, defaultValue: String = "Unknown"): String {
        return url.ifEmpty { defaultValue }
    }

    fun getActorByByUrl(url: String, defaultValue: String = "Unknown"): String {
        return url.ifEmpty { defaultValue }
    }

    fun getAncestryByUrl(url: String, defaultValue: String = "Unknown"): String {
        return url.ifEmpty { defaultValue }
    }

    fun getSpellNameByUrl(
        url: String,
        defaultValue: String = "Spell name is not available"
    ): String {
        return url.ifEmpty { defaultValue }
    }

    fun getSpellDescriptionByUrl(
        url: String,
        defaultValue: String = "Description is not available"
    ): String {
        return url.ifEmpty { defaultValue }
    }
}

object HouseRowUtils {
    val houseRowList = listOf("All houses", "Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw")
}
object HouseUtils {
    val houseList = listOf("Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw")
}

object ScreenUtils {
    const val CHARACTER_LIST_SCREEN: String = "CharacterListScreen"
    const val CHARACTER_SCREEN: String = "CharacterList"
    const val CHOOSE_OPTION_SCREEN: String = "ChooseOptionScreen"
    const val SPELL_LIST_SCREEN: String = "SpellListScreen"
}