package ru.lavafrai.mai.api.models.schedule.raw

import ru.lavafrai.mai.api.models.time.Time

typealias TimeRaw = String

fun TimeRaw.toTime(): Time {
    return Time(this)
}
