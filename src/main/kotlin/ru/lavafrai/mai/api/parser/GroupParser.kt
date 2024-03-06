package ru.lavafrai.mai.api.parser

import ru.lavafrai.mai.api.GROUPS_PAGE_URL
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.network.getPage

fun parseGroupsList(): List<Group> {
    val page = getPage(GROUPS_PAGE_URL)

    val faculties: List<String> = page
        .select("#department")
        .first()
        .children()
        .filter { it.text().contains("Институт") }
        .map { it.text() }

    val courses = (1..6).toList().map { it.toString() }

    val facultyCoursePairs: MutableList<Pair<String, String>> = ArrayList()
    faculties.forEach { faculty ->
        courses.forEach { course ->
            facultyCoursePairs.add(Pair(faculty, course))
        }
    }

    val groups: MutableList<Group> = ArrayList()
    facultyCoursePairs.map { it ->
        val subPage = getPage(GROUPS_PAGE_URL, mapOf("department" to it.first, "course" to it.second))
        groups.addAll(
            subPage.select(".tab-content").select(".btn-group")?.map { group ->
                Group(group.text())
            } ?: listOf()
        )
    }

    return groups
}
