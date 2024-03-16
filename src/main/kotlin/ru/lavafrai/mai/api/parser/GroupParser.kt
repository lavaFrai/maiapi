package ru.lavafrai.mai.api.parser

import kotlinx.serialization.decodeFromString
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.network.TolerantJson
import ru.lavafrai.mai.api.network.getPage

fun parseGroupsList(): List<Group> {
    return TolerantJson.decodeFromString<List<Group>>(getPage("https://public.mai.ru/schedule/data/groups.json"))
}
